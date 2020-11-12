package eapli.base.persistence.impl.jpa;

import eapli.base.mensagemmanagement.domain.Producao;
import eapli.base.mensagemmanagement.repositories.ProducaoRepository;
import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.CodigoOrdem;

import javax.persistence.TypedQuery;

public class JpaProducaoRepository extends BasepaRepositoryBase<Producao, Long, Long> implements ProducaoRepository {
    JpaProducaoRepository() {
        super("idProducao");
    }

    public Iterable<Producao> findProducaoOrdem(CodigoFabrico produto, CodigoOrdem ordem) {
        final TypedQuery<Producao> query = createQuery(
                "SELECT p FROM Producao p WHERE p.ordem = :ordem"
                        + " AND p.produto = :produto"
                , Producao.class);
        query.setParameter("ordem", ordem);
        query.setParameter("produto", produto);

        return query.getResultList();
    }

    @Override
    public Iterable<Producao> findProducoesOrdem(CodigoOrdem ordem) {
        final TypedQuery<Producao> query = createQuery(
                "SELECT p FROM Producao p WHERE p.ordem = :ordem"
                , Producao.class);
        query.setParameter("ordem", ordem);

        return query.getResultList();
    }
}
