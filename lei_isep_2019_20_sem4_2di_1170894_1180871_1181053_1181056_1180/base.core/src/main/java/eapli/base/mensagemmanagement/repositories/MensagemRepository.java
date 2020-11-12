package eapli.base.mensagemmanagement.repositories;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.mensagemmanagement.domain.Mensagem;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Calendar;

public interface MensagemRepository extends DomainRepository<Long, Mensagem> {

    /**
     * Devolve todas as mensagens da linha de producao dada com filtros opcionais. E possivel filtrar por datas e por
     * estado de processamento.
     *
     * @param data1      data inicial do intervalo. Se for <code>null</code>, o filtro nao e aplicado.
     * @param data2      data final do intervalo. Se for <code>null</code>, o filtro nao e aplicado.
     * @param idLinha    id da linha de producao
     * @return mensagens filtradas
     */
    Iterable<Mensagem> findMensagemByLinhaWithData(Calendar data1, Calendar data2, IdLinhaProducao idLinha);

    Iterable<CodigoInternoMaquina> findMaquinasDiferentesInOrdem(String ordem);

    Mensagem findMensagemAntigaRecenteByMaquina(CodigoInternoMaquina maquina, boolean antiga);

    Mensagem findMensagemAntigaRecenteByOrdem(String ordem, boolean antiga);

    Iterable<Mensagem> findMensagensRetomaParagemDe1MaquinaBetweenDatas(CodigoInternoMaquina maquina, Calendar dataInicio, Calendar dataFim);
}
