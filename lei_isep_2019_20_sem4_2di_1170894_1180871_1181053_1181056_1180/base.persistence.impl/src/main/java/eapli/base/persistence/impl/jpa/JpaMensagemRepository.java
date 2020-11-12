package eapli.base.persistence.impl.jpa;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.mensagemmanagement.domain.Mensagem;
import eapli.base.mensagemmanagement.repositories.MensagemRepository;

import javax.persistence.TypedQuery;
import java.util.Calendar;

public class JpaMensagemRepository extends BasepaRepositoryBase<Mensagem, Long, Long> implements MensagemRepository {
    JpaMensagemRepository() {
        super("idMensagem");
    }

    /**
     * Devolve todas as mensagens da linha de producao dada com filtros opcionais. E possivel filtrar por datas e por
     * estado de processamento.
     *
     * @param data1   data inicial do intervalo. Se for <code>null</code>, o filtro nao e aplicado.
     * @param data2   data final do intervalo. Se for <code>null</code>, o filtro nao e aplicado.
     * @param idLinha id da linha de producao
     * @return mensagens filtradas
     */
    public Iterable<Mensagem> findMensagemByLinhaWithData(Calendar data1, Calendar data2, IdLinhaProducao idLinha) {
        String dataString = (data1 != null || data2 != null) ? " AND m.dataHora BETWEEN :data1 AND :data2" : "";

        final TypedQuery<Mensagem> query = createQuery(
                "SELECT m FROM Mensagem m WHERE m.maquina IN"
                        + " (SELECT seq.idMaquina FROM LinhaProducao lp JOIN lp.sequenciaMaquina seq"
                        + " WHERE lp.idLinhaProducao = :idLinha)"
                        + " AND m.processada = :processada"
                        + dataString
                        + " ORDER BY m.dataHora"
                , Mensagem.class);
        query.setParameter("idLinha", idLinha);
        query.setParameter("processada", false);

        if (data1 != null || data2 != null) {
            query.setParameter("data1", data1);
            query.setParameter("data2", data2);
        }

        return query.getResultList();
    }

    public Iterable<CodigoInternoMaquina> findMaquinasDiferentesInOrdem(String ordem) {
        final TypedQuery<CodigoInternoMaquina> query = createQuery(
                "SELECT m.maquina FROM Mensagem m"
                        + " WHERE m.ordem = :ordem"
                        + " GROUP BY m.maquina"
                , CodigoInternoMaquina.class);
        query.setParameter("ordem", ordem);

        return query.getResultList();
    }

    public Mensagem findMensagemAntigaRecenteByMaquina(CodigoInternoMaquina maquina, boolean antiga) {
        String orderType = antiga ? "ASC" : "DESC";

        final TypedQuery<Mensagem> query = createQuery(
                "SELECT m FROM Mensagem m"
                        + " WHERE m.maquina = :maquina"
                        + " ORDER BY m.dataHora " + orderType
                , Mensagem.class).setMaxResults(1);
        query.setParameter("maquina", maquina);

        return query.getSingleResult();
    }

    public Mensagem findMensagemAntigaRecenteByOrdem(String ordem, boolean antiga) {
        String orderType = antiga ? "ASC" : "DESC";

        final TypedQuery<Mensagem> query = createQuery(
                "SELECT m FROM Mensagem m"
                        + " WHERE m.ordem = :ordem"
                        + " ORDER BY m.dataHora " + orderType
                , Mensagem.class).setMaxResults(1);
        query.setParameter("ordem", ordem);

        return query.getSingleResult();
    }

    public Iterable<Mensagem> findMensagensRetomaParagemDe1MaquinaBetweenDatas(CodigoInternoMaquina maquina, Calendar dataInicio, Calendar dataFim) {
        final TypedQuery<Mensagem> query = createQuery(
                "SELECT m FROM Mensagem m"
                        + " WHERE (m.tipoMsg LIKE :retoma"
                        + " OR m.tipoMsg LIKE :paragem)"
                        + " AND m.dataHora BETWEEN :data1 AND :data2"
                        + " AND m.maquina = :maquina"
                        + " ORDER BY m.dataHora"
                , Mensagem.class);
        query.setParameter("retoma", Mensagem.Tipo.S1);
        query.setParameter("paragem", Mensagem.Tipo.S8);
        query.setParameter("data1", dataInicio);
        query.setParameter("data2", dataFim);
        query.setParameter("maquina", maquina);

        return query.getResultList();
    }

}








