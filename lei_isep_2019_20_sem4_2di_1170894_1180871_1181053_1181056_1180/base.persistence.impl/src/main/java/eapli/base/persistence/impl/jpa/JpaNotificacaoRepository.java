package eapli.base.persistence.impl.jpa;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.base.mensagemmanagement.repositories.NotificacaoRepository;

import javax.persistence.TypedQuery;
import java.util.Calendar;

public class JpaNotificacaoRepository extends BasepaRepositoryBase<Notificacao, Long, Long> implements NotificacaoRepository {
    JpaNotificacaoRepository() {
        super("idNotificacao");
    }

    public Iterable<Notificacao> findAllNotificacoes(Notificacao.Estado estado, Notificacao.Tipo tipo, CodigoInternoMaquina maquina, Calendar data1, Calendar data2) {
        String queryString = buildQueryString(tipo, maquina, data1, data2);

        final TypedQuery<Notificacao> query = createQuery(queryString, Notificacao.class);
        query.setParameter("estado", estado);
        if (tipo != null)
            query.setParameter("tipo", tipo);
        if (maquina != null)
            query.setParameter("maquina", maquina);
        if (data1 != null && data2 != null) {
            query.setParameter("data1", data1);
            query.setParameter("data2", data2);
        }

        return query.getResultList();
    }

    private String buildQueryString(Notificacao.Tipo tipo, CodigoInternoMaquina maquina, Calendar data1, Calendar data2) {
        String queryText = "SELECT e FROM Notificacao e WHERE e.estado = :estado";
        String tipoString = " AND e.tipo = :tipo";
        String maquinaString = " AND e.maquina = :maquina";
        String dataString = " AND e.data BETWEEN :data1 AND :data2";

        if (tipo != null)
            queryText += tipoString;

        if (maquina != null)
            queryText += maquinaString;

        if (data1 != null && data2 != null)
            queryText += dataString;

        return queryText;
    }


}
