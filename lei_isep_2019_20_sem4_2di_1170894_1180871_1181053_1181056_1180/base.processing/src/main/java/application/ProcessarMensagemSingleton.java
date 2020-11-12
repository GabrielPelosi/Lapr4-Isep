package application;

import eapli.base.depositomanagement.domain.CodigoDeposito;
import eapli.base.depositomanagement.domain.Deposito;
import eapli.base.depositomanagement.repositories.DepositoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;
import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.base.materiaprimamanagement.repositories.MateriaRepository;
import eapli.base.mensagemmanagement.domain.*;
import eapli.base.mensagemmanagement.repositories.*;
import eapli.base.produtomanagement.domain.*;
import eapli.base.produtomanagement.repositories.FichaProducaoRepository;
import eapli.base.produtomanagement.repositories.LoteRepository;
import eapli.base.produtomanagement.repositories.OrdemRepository;
import eapli.base.produtomanagement.repositories.ProdutoRepository;

import java.util.Calendar;
import java.util.Optional;

public class ProcessarMensagemSingleton {
    private static final ProcessarMensagemSingleton inst = new ProcessarMensagemSingleton();

    public static ProcessarMensagemSingleton getInstance() {
        return inst;
    }

    // LINHAS DE PRODUCAO
    public synchronized Iterable<LinhaProducao> linhasProducao() {
        final LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();
        return repository.findAll();
    }

    public synchronized Iterable<LinhaProducao> linhasProducaoAtivas() {
        final LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();
        return repository.linhasAtivas();
    }

    public synchronized LinhaProducao findLinhaProducao(CodigoInternoMaquina maquina) {
        final LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();
        return repository.linhaWithMaquina(maquina);
    }

    public synchronized void saveLinha(LinhaProducao linha) {
        final LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();
        repository.save(linha);
    }

    // DEPOSITO
    public synchronized void saveDeposito(Deposito deposito) {
        final DepositoRepository repository = PersistenceContext.repositories().depositos();
        repository.save(deposito);
    }

    public synchronized Deposito findDeposito(CodigoDeposito id) {
        final DepositoRepository repository =
                PersistenceContext.repositories().depositos();
        Optional<Deposito> deposito = repository.ofIdentity(id);
        return deposito.orElse(null);
    }

    // FICHA
    public synchronized FichaProducao findFichaProducao(CodigoFabrico id) {
        final FichaProducaoRepository repository = PersistenceContext.repositories().fichasProducao();
        Optional<FichaProducao> fichaProducao = repository.ofIdentity(id);
        return fichaProducao.orElse(null);
    }

    // MATERIA
    public synchronized MateriaPrima findMateriaPrima(CodigoInternoMateria id) {
        final MateriaRepository repository = PersistenceContext.repositories().materias();
        Optional<MateriaPrima> materiaPrima = repository.ofIdentity(id);
        return materiaPrima.orElse(null);
    }

    // PRODUTO
    public synchronized Produto findProduto(CodigoFabrico id) {
        final ProdutoRepository repository = PersistenceContext.repositories().produtos();
        Optional<Produto> produto = repository.ofIdentity(id);
        return produto.orElse(null);
    }

    // LOTE
    public synchronized Lote findLote(String id) {
        final LoteRepository repository = PersistenceContext.repositories().lotes();
        Optional<Lote> lote = repository.ofIdentity(id);
        return lote.orElse(null);
    }

    public synchronized void saveLote(Lote lote) {
        final LoteRepository repository = PersistenceContext.repositories().lotes();
        repository.save(lote);
    }

    // CONSUMOS
    public synchronized void saveConsumo(Consumo consumo) {
        final ConsumoRepository repository = PersistenceContext.repositories().consumos();
        repository.save(consumo);
    }

    public synchronized Iterable<Consumo> getConsumos(CodigoInternoMateria materia, CodigoOrdem ordem) {
        final ConsumoRepository repository = PersistenceContext.repositories().consumos();
        return repository.findConsumosOrdemDeUmaMateria(materia, ordem);
    }

    // PRODUCAO
    public synchronized void saveProducao(Producao producao) {
        final ProducaoRepository repository = PersistenceContext.repositories().producoes();
        repository.save(producao);
    }

    public synchronized Iterable<Producao> getProducoes(CodigoFabrico produto, CodigoOrdem ordem) {
        final ProducaoRepository repository = PersistenceContext.repositories().producoes();
        return repository.findProducaoOrdem(produto, ordem);
    }

    // ESTORNO
    public synchronized void saveEstorno(Estorno estorno) {
        final EstornoRepository repository = PersistenceContext.repositories().estornos();
        repository.save(estorno);
    }

    // NOTIFICACAO
    public synchronized void saveNotificacao(Notificacao notificacao) {
        final NotificacaoRepository repository = PersistenceContext.repositories().notificacoes();
        repository.save(notificacao);
    }

    // MENSAGEM
    public synchronized void saveMensagem(Mensagem mensagem) {
        final MensagemRepository repository = PersistenceContext.repositories().mensagens();
        repository.save(mensagem);
    }

    /**
     * Devolve todas as mensagens da linha de producao dada com filtros opcionais. E possivel filtrar por datas e por
     * estado de processamento.
     *
     * @param data1      data inicial do intervalo. Se for <code>null</code>, o filtro nao e aplicado.
     * @param data2      data final do intervalo. Se for <code>null</code>, o filtro nao e aplicado.
     * @param idLinha    id da linha de producao
     * @return mensagens filtradas
     */
    public synchronized Iterable<Mensagem> findMensagemByLinhaWithData(Calendar data1, Calendar data2, IdLinhaProducao idLinha) {
        final MensagemRepository repository = PersistenceContext.repositories().mensagens();
        return repository.findMensagemByLinhaWithData(data1, data2, idLinha);
    }

    // ORDEM
    public synchronized Ordem findOrdem(CodigoOrdem id) {
        final OrdemRepository repository = PersistenceContext.repositories().ordens();
        Optional<Ordem> ordem = repository.ofIdentity(id);
        return ordem.orElse(null);
    }

    public synchronized void saveOrdem(Ordem ordem) {
        final OrdemRepository repository = PersistenceContext.repositories().ordens();
        repository.save(ordem);
    }

    // DESVIOS
    public synchronized void saveDesvio(Desvio desvio) {
        final DesvioRepository repository = PersistenceContext.repositories().desvios();
        repository.save(desvio);
    }

    // TEMPO
    public synchronized Iterable<CodigoInternoMaquina> findMaquinasByOrder(String ordem) {
        final MensagemRepository repository = PersistenceContext.repositories().mensagens();
        return repository.findMaquinasDiferentesInOrdem(ordem);
    }

    public synchronized Mensagem findMensagemAntigaRecenteMaquina(CodigoInternoMaquina maquina, boolean antiga) {
        final MensagemRepository repository = PersistenceContext.repositories().mensagens();
        return repository.findMensagemAntigaRecenteByMaquina(maquina, antiga);
    }

    public synchronized Mensagem findMensagemAntigaRecenteOrdem(String ordem, boolean antiga) {
        final MensagemRepository repository = PersistenceContext.repositories().mensagens();
        return repository.findMensagemAntigaRecenteByOrdem(ordem, antiga);
    }

    public synchronized Iterable<Mensagem> findMensagemParagemRetomaBetweenDatas(CodigoInternoMaquina maquina, Calendar dataInicio, Calendar dataFim) {
        final MensagemRepository repository = PersistenceContext.repositories().mensagens();
        return repository.findMensagensRetomaParagemDe1MaquinaBetweenDatas(maquina, dataInicio, dataFim);
    }

    public synchronized void saveTempoProducao(TempoProducao tempoProducao) {
        final TempoProducaoRepository repository = PersistenceContext.repositories().temposProducao();
        repository.save(tempoProducao);
    }

    // WAIT
    public synchronized void waitTimeOutMillis(long timoutMillis) throws InterruptedException {
        wait(timoutMillis);
    }

}
