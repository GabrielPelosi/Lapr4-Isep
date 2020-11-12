package application;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.mensagemmanagement.domain.*;
import eapli.base.produtomanagement.domain.*;

import java.io.InvalidObjectException;
import java.util.*;

public class ProcessarMensagemThread implements Runnable {

    private final List<Mensagem> mensagens;
    private final IdLinhaProducao idLinhaProducao;
    private final List<CodigoInternoMaquina> maquinasAtivas;
    private final List<Calendar> temposParagemOrdem;
    private CodigoOrdem ordem;
    private CodigoFabrico produtoOrdem;
    private List<CodigoInternoMateria> materiasOrdem;
    private int maquinasOrdem;

    /**
     * Thread para processar uma lista de mensagens
     *
     * @param mensagens       lista de mensagens da linha de producao
     * @param idLinhaProducao id da linha de producao usado para impressao no caso de ausencia de mensagens
     */
    public ProcessarMensagemThread(List<Mensagem> mensagens, IdLinhaProducao idLinhaProducao) {
        this.mensagens = mensagens;
        this.idLinhaProducao = idLinhaProducao;

        this.ordem = null;
        this.produtoOrdem = null;
        this.materiasOrdem = new ArrayList<>();
        this.maquinasAtivas = new ArrayList<>();
        this.temposParagemOrdem = new ArrayList<>();
        this.maquinasOrdem = 0;
    }

    @Override
    public void run() {

        // Se houver MENSAGENS
        if (mensagens.size() > 0) {


            // Processar MENSAGENS
            for (Mensagem mensagem : mensagens) {
                boolean processada = false;
                switch (mensagem.tipoMsg()) {
                    case S0:
                        try {
                            processada = iniciarAtividade(mensagem);
                        } catch (InvalidObjectException e) {
                            System.out.println("Ordem de producao com produto sem Ficha de Producao. Ordem Cancelada: " + this.ordem);
                            this.ordem = null;
                        }
                        break;
                    case S9:
                        processada = terminarAtividade(mensagem);
                        break;
                    case S1:
                        processada = retomarMaquina(mensagem);
                        break;
                    case S8:
                        processada = interromperMaquina(mensagem);
                        break;
                    case C0:
                        processada = consumir(mensagem);
                        break;
                    case P1:
                        processada = produzir(mensagem);
                        break;
                    case P2:
                        processada = estornar(mensagem);
                        break;
                    case C9:
                        processada = entregar(mensagem);
                        break;
                }
                // Se nenhum erro foi gerado
                if (processada) {
                    // Alterar estado da MENSAGEM para PROCESSADA
                    mensagem.processar();
                    ProcessarMensagemSingleton.getInstance().saveMensagem(mensagem);
                }
            }

            // Nao ha mensagens
        } else {
            String idLinha = idLinhaProducao.toString();
            System.out.printf("Nao foram encontradas mensagens na Linha de Producao: %s\n", idLinha);
        }
    }

    // S0 - INICIO DE ATIVIDADE
    private boolean iniciarAtividade(Mensagem mensagem) throws InvalidObjectException {
        Ordem ordem = ProcessarMensagemSingleton.getInstance().findOrdem(CodigoOrdem.valueOf(mensagem.ordem()));
        FichaProducao fichaProducao = ProcessarMensagemSingleton.getInstance().findFichaProducao(ordem.prod().identity());

        if (this.ordem != null && !CodigoOrdem.valueOf(mensagem.ordem()).equals(this.ordem)) {
            generateNotificacao(Notificacao.Tipo.VARIAS_ORDENS_INICIADAS, mensagem.maquina());
        } else if (this.maquinasAtivas.contains(mensagem.maquina())) {
            generateNotificacao(Notificacao.Tipo.MAQUINA_JA_ATIVA, mensagem.maquina());
        } else if (fichaProducao == null) {
            generateNotificacao(Notificacao.Tipo.FICHA_PRODUCAO_NAO_EXISTE, mensagem.maquina());
            this.ordem = CodigoOrdem.valueOf(mensagem.ordem());
            throw new InvalidObjectException("Produto nao tem ficha de producao");
        } else {
            // Objetos da ordem
            this.ordem = CodigoOrdem.valueOf(ordem.identity().toString());
            this.produtoOrdem = ordem.prod().identity();
            this.materiasOrdem = fichaProducao.sequenciaMateria();

            this.maquinasAtivas.add(mensagem.maquina());
            this.maquinasOrdem++;
            executarOrdem();
            return true;
        }
        return false;
    }

    // S9 - FIM DE ATIVIDADE
    private boolean terminarAtividade(Mensagem mensagem) {
        if (this.ordem == null) {
            generateNotificacao(Notificacao.Tipo.FIM_SEM_INICIO, mensagem.maquina());
        } else {
            this.maquinasAtivas.remove(mensagem.maquina());
            this.maquinasOrdem--;

            // Se nenhuma maquina da ordem estiver ativa, concluir ordem e efetuar calculos
            if (maquinasOrdem == 0) {
                calcular(mensagem);
                concluirOrdem();
                atualizarLinhaProducao(mensagem);
            }
            return true;
        }
        return false;
    }

    // S1 - RETOMA DE ATIVIDADE
    private boolean retomarMaquina(Mensagem mensagem) {
        if (this.ordem != null) {
            //generateNotificacao(Notificacao.Tipo.ORDEM_NAO_EXISTENTE, mensagem.maquina());

            if (this.maquinasAtivas.contains(mensagem.maquina())) {
                generateNotificacao(Notificacao.Tipo.RETOMA_SEM_PARAGEM, mensagem.maquina());
            } else {
                if (maquinasAtivas.isEmpty()) {
                    // Adicionar tempo de retoma da ordem
                    temposParagemOrdem.add(mensagem.dataHora());
                    // Retomar ordem
                    executarOrdem();
                }
                this.maquinasAtivas.add(mensagem.maquina());

                return true;
            }
        }
        return false;
    }

    // S8 - INTERROMPER ATIVIDADE
    private boolean interromperMaquina(Mensagem mensagem) {
        if (this.ordem != null) {
            //generateNotificacao(Notificacao.Tipo.ORDEM_NAO_EXISTENTE, mensagem.maquina());

            if (!maquinasAtivas.contains(mensagem.maquina())) {
                generateNotificacao(Notificacao.Tipo.INTERROMPER_MAQUINA_NAO_ATIVA, mensagem.maquina());
            } else {
                this.maquinasAtivas.remove(mensagem.maquina());

                // Se a ordem nao tiver nenhuma maquina a executa-la
                if (maquinasAtivas.isEmpty()) {
                    interromperOrdem();
                    temposParagemOrdem.add(mensagem.dataHora());
                }
                return true;
            }
        }
        return false;
    }

    // C0 - CONSUMIR MATERIA
    private boolean consumir(Mensagem mensagem) {
        // MATERIA PRIMA
        CodigoInternoMateria idMateria = CodigoInternoMateria.valueOf(mensagem.materia());
        // QUANTIDADE
        double quantidade = mensagem.quantidade();

        if (this.ordem != null) {
            //generateNotificacao(Notificacao.Tipo.ORDEM_NAO_EXISTENTE, mensagem.maquina());

            if (!materiasOrdem.contains(idMateria)) {
                generateNotificacao(Notificacao.Tipo.MATERIA_NAO_NECESSARIA, mensagem.maquina());
            } else {
                // Criar consumo e guardar na BD
                Consumo consumo = new Consumo(idMateria, quantidade, this.ordem);
                ProcessarMensagemSingleton.getInstance().saveConsumo(consumo);
                return true;
            }
        }
        return false;
    }

    // P1 - PRODUCAO
    private boolean produzir(Mensagem mensagem) {
        // PRODUTO
        CodigoFabrico idProduto = CodigoFabrico.valueOf(mensagem.produto());
        // QUANTIDADE
        double quantidade = mensagem.quantidade();
        // LOTE
        Lote lote = ProcessarMensagemSingleton.getInstance().findLote(mensagem.lote());

        if (this.ordem != null) {
            //generateNotificacao(Notificacao.Tipo.ORDEM_NAO_EXISTENTE, mensagem.maquina());

            if (!idProduto.equals(this.produtoOrdem)) {
                generateNotificacao(Notificacao.Tipo.PRODUTO_NAO_NECESSARIO, mensagem.maquina());
            } else {
                // Criar PRODUCAO e guardar na BD
                Producao producao = new Producao(idProduto, quantidade, this.ordem);
                ProcessarMensagemSingleton.getInstance().saveProducao(producao);

                // Adicionar PORDUTO ao LOTE e guardar na BD
                lote.adicionarProdutoQuantidade(quantidade);
                ProcessarMensagemSingleton.getInstance().saveLote(lote);
                return true;
            }
        }
        return false;
    }

    // P2 - ESTORNO
    private boolean estornar(Mensagem mensagem) {
        if (this.ordem != null) {
            //generateNotificacao(Notificacao.Tipo.ORDEM_NAO_EXISTENTE, mensagem.maquina());

            // MATERIA
            CodigoInternoMateria materiaPrimaEstorno = CodigoInternoMateria.valueOf(mensagem.materia());
            // QUANTIDADE
            double quantidadeEstorno = mensagem.quantidade();

            // Criar Estorno e guardar na BD
            Estorno estorno = new Estorno(materiaPrimaEstorno, quantidadeEstorno, this.ordem);
            ProcessarMensagemSingleton.getInstance().saveEstorno(estorno);
            return true;
        }
        return false;
    }

    // C9 - ENTREGA DE PRODUCAO
    private boolean entregar(Mensagem mensagem) {
        //generateNotificacao(Notificacao.Tipo.ORDEM_NAO_EXISTENTE, mensagem.maquina());

        return this.ordem != null;
    }

    // NOTIFICACAO
    private void generateNotificacao(Notificacao.Tipo tipo, CodigoInternoMaquina maquina) {
        Notificacao notificacao = Notificacao.valueOf(tipo, maquina);
        ProcessarMensagemSingleton.getInstance().saveNotificacao(notificacao);
    }

    /**
     * Metodo que muda o estado da ordem para "Em execucao" e guarda na BD
     */
    private void executarOrdem() {
        Ordem ordem = ProcessarMensagemSingleton.getInstance().findOrdem(this.ordem);
        ordem.executar();
        ProcessarMensagemSingleton.getInstance().saveOrdem(ordem);
    }

    /**
     * Metodo que muda o estado da ordem para "Execucao Parada Temporariamente"
     */
    private void interromperOrdem() {
        Ordem ordem = ProcessarMensagemSingleton.getInstance().findOrdem(this.ordem);
        ordem.interromper();
        ProcessarMensagemSingleton.getInstance().saveOrdem(ordem);
    }

    /**
     * Metodo que muda o estado da ordem para "Concluida" e esvazia as variaveis de ordem da classe
     */
    private void concluirOrdem() {
        Ordem ordem = ProcessarMensagemSingleton.getInstance().findOrdem(this.ordem);
        ordem.concluir();
        ProcessarMensagemSingleton.getInstance().saveOrdem(ordem);
        System.out.println("Ordem concluida: " + this.ordem.toString());

        this.ordem = null;
        this.produtoOrdem = null;
        this.materiasOrdem = new ArrayList<>();
    }

    private void atualizarLinhaProducao(Mensagem mensagem) {
        CodigoInternoMaquina idMaquina = mensagem.maquina();
        LinhaProducao linhaProducao = ProcessarMensagemSingleton.getInstance().findLinhaProducao(idMaquina);
        linhaProducao.marcarProcessamento(mensagem.dataHora());
        ProcessarMensagemSingleton.getInstance().saveLinha(linhaProducao);
    }

    private void calcular(Mensagem mensagem) {
        // ORDEM
        CodigoOrdem idOrdem = CodigoOrdem.valueOf(mensagem.ordem());
        Ordem ordem = ProcessarMensagemSingleton.getInstance().findOrdem(idOrdem);

        // DESVIOS
        calcularDesvios(ordem);

        // TEMPOS
        calcularTempos(ordem);
    }

    /**
     * Calcula e guarda os desvios de consumo e producao da ordem dada
     *
     * @param ordem ordem de producao
     */
    private void calcularDesvios(Ordem ordem) {
        // PRODUTO
        CodigoFabrico produto = ordem.prod().identity();
        // QUANTIDADE PRODUZIDA
        double quantidadeProduzida = calcularTotalQuantProduzido(produto, ordem.identity());
        // MATERIAS NECESSARIAS
        Map<CodigoInternoMateria, Double> materiasNecessarias = calcularMateriaNecessaria(produto, quantidadeProduzida);
        // MATERIAS CONSUMIDAS
        Map<CodigoInternoMateria, Double> materiasConsumidas = calcularMateriasConsumidas(materiasNecessarias, ordem.identity());

        // DESVIO DE MATERIA
        Map<CodigoInternoMateria, Double> desviosMateria = calcularDesvioConsumo(materiasNecessarias, materiasConsumidas);

        // QUANTIDADE PEDIDA
        double quantidadePedida = ordem.quant();
        // DESVIOS DE PRODUTO
        double quantidadeProducaoDiferenca = quantidadeProduzida - quantidadePedida;

        // DESVIO
        Desvio desvio = new Desvio(ordem.identity(), desviosMateria, produto, quantidadeProducaoDiferenca);
        ProcessarMensagemSingleton.getInstance().saveDesvio(desvio);
    }

    /**
     * Calcula e devolve a quantidade total de produto produzido somando as quantidades de todas as producoes
     * nao processadas desde produto
     *
     * @param produto produto cuja quantidade vamos somar
     * @return quantidade total de produto produida e nao processada
     */
    private double calcularTotalQuantProduzido(CodigoFabrico produto, CodigoOrdem ordem) {
        // Iterable com todas as producoes nao processadas de um produto
        Iterable<Producao> producaoIterable = ProcessarMensagemSingleton.getInstance().getProducoes(produto, ordem);
        double quantidade = 0;

        // Calcular a quantidade total de produto produzido
        for (Producao p : producaoIterable) {
            quantidade += p.quantidade();
        }
        return quantidade;
    }

    /**
     * Calcula e devolve um mapa com as quantidades de materia necessaria para a producao do produto dada
     *
     * @param produto           produto que foi produzido
     * @param quantidadeProduto quantidade total de produto produzida
     * @return mapa com mapterias e quantidades totais necessaria para a producao
     */
    private Map<CodigoInternoMateria, Double> calcularMateriaNecessaria(CodigoFabrico produto, double quantidadeProduto) {
        // FICHA DE PRODUCAO
        FichaProducao ficha = ProcessarMensagemSingleton.getInstance().findFichaProducao(produto);

        // Materias e quantidades necessarias para 1 produto
        List<CodigoInternoMateria> materias = ficha.sequenciaMateria();
        List<Double> quantidadesMateria = ficha.sequenciaQuantidade();

        // Mapa que com todas as materias necessarias e quantidades para a criacao do produto na sua quantidade total
        Map<CodigoInternoMateria, Double> materiasNecessarias = new HashMap<>();

        int index = 0;
        for (CodigoInternoMateria m : materias) {
            // Calcular a quantidade total de materia necessaria
            double q = quantidadesMateria.get(index) * quantidadeProduto;
            // Guardar no mapa a materia e a quantidade
            materiasNecessarias.put(m, q);
        }
        return materiasNecessarias;
    }

    /**
     * Calcula e devolve um mapa com todas as materias calculadas anteriormente nas quantidades reais de consumo
     *
     * @param materiasCalculadas mapa de materias a procurar o consumo
     * @return mapa de materias com quantidades consumidas
     */
    private Map<CodigoInternoMateria, Double> calcularMateriasConsumidas(Map<CodigoInternoMateria, Double> materiasCalculadas, CodigoOrdem ordem) {
        // Mapa com materias consumidas e respetivas quantidades
        Map<CodigoInternoMateria, Double> materiasConsumidas = new HashMap<>();

        for (Map.Entry<CodigoInternoMateria, Double> entry : materiasCalculadas.entrySet()) {
            CodigoInternoMateria c = entry.getKey();
            double q = consumoTotalDeUmaMateria(c, ordem);

            materiasConsumidas.put(c, q);
        }
        return materiasConsumidas;
    }

    /**
     * Calcula e devolve a soma das quantidades consumidas de uma dada materia em consumos nao processados
     *
     * @param materia materia cuja quantidade consumida vamos somar
     * @return quantidade total da materia dada consumida ainda nao processada
     */
    private double consumoTotalDeUmaMateria(CodigoInternoMateria materia, CodigoOrdem ordem) {
        // Todos os consumos referentes a materia dada e nao procesados
        Iterable<Consumo> consumosIterable = ProcessarMensagemSingleton.getInstance().getConsumos(materia, ordem);
        double quantidadeTotalMateria = 0;

        // Calcular quantidade total consumida
        for (Consumo c : consumosIterable) {
            quantidadeTotalMateria += c.quantidade();
        }

        return quantidadeTotalMateria;
    }

    /**
     * Calcula e devolve um mapa com a diferenca de quantidades entre os dois mapas de materias
     *
     * @param materiasNecessarias mapa com materias e quantidades calculadas para uma certa quantidade de produto
     * @param materiasConsumidas  mapa com materias e quantidades reais para uma certa quantidade de produto
     * @return mapa com a diferenca de quantidade entre os dois mapas dados
     */
    private Map<CodigoInternoMateria, Double> calcularDesvioConsumo(Map<CodigoInternoMateria, Double> materiasNecessarias, Map<CodigoInternoMateria, Double> materiasConsumidas) {
        Map<CodigoInternoMateria, Double> desviosMateria = new HashMap<>();

        for (Map.Entry<CodigoInternoMateria, Double> necessarias : materiasNecessarias.entrySet()) {
            CodigoInternoMateria materia = necessarias.getKey();
            double quantNecessaria = necessarias.getValue();
            double quantConsumida = materiasConsumidas.get(materia);
            double diferenca = quantConsumida - quantNecessaria;

            desviosMateria.put(materia, diferenca);
        }

        return desviosMateria;
    }

    /**
     * Calcula o tempo de producao da ordem dada
     *
     * @param ordem ordem de producao
     */
    private void calcularTempos(Ordem ordem) {
        // Iterable com maquinas diferentes da ordem
        Iterable<CodigoInternoMaquina> maquinas = ProcessarMensagemSingleton.getInstance().findMaquinasByOrder(ordem.identity().toString());
        // Mapa com os tempos brutos de cada maquina
        Map<CodigoInternoMaquina, Tempo> temposBrutos = new HashMap<>();
        Map<CodigoInternoMaquina, Tempo> temposEfetivos = new HashMap<>();

        for (CodigoInternoMaquina m : maquinas) {
            // Primeira mensagem da maquina nesta ordem
            Calendar dataAntiga = ProcessarMensagemSingleton.getInstance().findMensagemAntigaRecenteMaquina(m, true).dataHora();
            // Ultima mensagem da maquina nesta ordem
            Calendar dataRecente = ProcessarMensagemSingleton.getInstance().findMensagemAntigaRecenteMaquina(m, false).dataHora();

            // TEMPO BRUTO DE 1 MAQUINA
            Tempo tempoBrutoMaq = calcularTempoDiferenca2Datas(dataAntiga, dataRecente);
            temposBrutos.put(m, tempoBrutoMaq);

            // TEMPO DE PARAGEM DE 1 MAQUINA
            Tempo tempoParagemMaq = calcularTemposParagemDe1Maquina(m, dataAntiga, dataRecente);

            // TEMPO EFETIVO DE 1 MAQUINA
            Tempo tempoEfetivoMaq = Tempo.diferenca(tempoBrutoMaq, tempoParagemMaq);
            temposEfetivos.put(m, tempoEfetivoMaq);
        }
        // Datas de início e fim de ordem
        Calendar inicioOrdem = ProcessarMensagemSingleton.getInstance().findMensagemAntigaRecenteOrdem(ordem.identity().toString(), true).dataHora();
        Calendar fimOrdem = ProcessarMensagemSingleton.getInstance().findMensagemAntigaRecenteOrdem(ordem.identity().toString(), false).dataHora();

        // TEMPO BRUTO DA ORDEM
        Tempo tempoBrutoOrdem = calcularTempoDiferenca2Datas(inicioOrdem, fimOrdem);

        // TEMPO DE PARAGEM DA ORDEM
        Tempo tempoParagemOrdem = calcularTemposParagemOrdem();

        // TEMPO EFETIVO DA ORDEM
        Tempo tempoEfetivoOrdem = Tempo.diferenca(tempoBrutoOrdem, tempoParagemOrdem);

        // TEMPO DE PRODUCAO DA ORDEM
        TempoProducao tempoProducao = new TempoProducao(ordem.identity(), tempoBrutoOrdem, tempoEfetivoOrdem, temposBrutos, temposEfetivos);
        ProcessarMensagemSingleton.getInstance().saveTempoProducao(tempoProducao);
    }

    /**
     * Devolve a diferenca entre duas datas no formato <code>Tempo</code>
     *
     * @param dataAntiga  data mais passada
     * @param dataRecente data mais futura
     * @return diferenca entre as datas
     */
    private Tempo calcularTempoDiferenca2Datas(Calendar dataAntiga, Calendar dataRecente) {
        long diff = dataRecente.getTimeInMillis() - dataAntiga.getTimeInMillis();
        return new Tempo().withMillis(diff);
    }

    /**
     * Calcula e devolve os tempos de paragem de uma dada maquina no formato <code>Tempo</code>
     *
     * @param maquina    maquina dada
     * @param dataInicio data do inicio da ordem de producao
     * @param dataFim    data do fim da ordem de producao
     * @return tempo de paragem total da maquina
     */
    private Tempo calcularTemposParagemDe1Maquina(CodigoInternoMaquina maquina, Calendar dataInicio, Calendar dataFim) {
        Iterator<Mensagem> mensagemIterator = ProcessarMensagemSingleton.getInstance().findMensagemParagemRetomaBetweenDatas(maquina, dataInicio, dataFim).iterator();
        Tempo tempoParagemTotal = new Tempo();
        while (mensagemIterator.hasNext()) {
            try {
                Calendar paragem = mensagemIterator.next().dataHora();
                Calendar retoma = mensagemIterator.next().dataHora();

                Tempo tempo = calcularTempoDiferenca2Datas(paragem, retoma);
                tempoParagemTotal = Tempo.plusTempo(tempoParagemTotal, tempo);

            } catch (NoSuchElementException ignored) {
                // Nothing
            }
        }
        return tempoParagemTotal;
    }

    /**
     * Calcula e devolve o tempo total de paragem da ordem no formato Tempo
     *
     * @return tempo total de paragem da ordem
     */
    private Tempo calcularTemposParagemOrdem() {
        Iterator<Calendar> iterator = temposParagemOrdem.iterator();
        Tempo tempoParagemTotal = new Tempo();
        while (iterator.hasNext()) {
            try {
                Calendar paragem = iterator.next();
                Calendar retoma = iterator.next();

                Tempo tempo = calcularTempoDiferenca2Datas(paragem, retoma);
                tempoParagemTotal = Tempo.plusTempo(tempoParagemTotal, tempo);

            } catch (NoSuchElementException ignored) {
                // Nothing
            }
        }
        return tempoParagemTotal;
    }
}
