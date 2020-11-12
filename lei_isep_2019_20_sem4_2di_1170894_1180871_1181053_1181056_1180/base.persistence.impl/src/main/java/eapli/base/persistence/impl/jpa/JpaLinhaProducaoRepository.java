package eapli.base.persistence.impl.jpa;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;

import javax.persistence.TypedQuery;

public class JpaLinhaProducaoRepository extends BasepaRepositoryBase<LinhaProducao, IdLinhaProducao, IdLinhaProducao> implements LinhaProducaoRepository {
    public JpaLinhaProducaoRepository() {
        super("idLinhaProducao");
    }

    public Iterable<LinhaProducao> linhasAtivas() {
        final TypedQuery<LinhaProducao> query = createQuery(
                "SELECT lp FROM LinhaProducao lp"
                        + " WHERE lp.ativa = :ativa"
                , LinhaProducao.class);
        query.setParameter("ativa", true);

        return query.getResultList();
    }

    public LinhaProducao linhaWithMaquina(CodigoInternoMaquina maquina){
        final TypedQuery<LinhaProducao> query = createQuery(
                "SELECT lp FROM LinhaProducao lp JOIN lp.sequenciaMaquina seq"
                        + " WHERE seq.idMaquina = :maquina"
        ,LinhaProducao.class);
        query.setParameter("maquina", maquina.toString());

        return query.getSingleResult();
    }
}
