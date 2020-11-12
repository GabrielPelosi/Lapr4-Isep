package eapli.base.persistence.impl.jpa;

import eapli.base.mensagemmanagement.domain.Estorno;
import eapli.base.mensagemmanagement.repositories.EstornoRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;

import javax.persistence.TypedQuery;

public class JpaEstornoRepository extends BasepaRepositoryBase<Estorno, Long, Long> implements EstornoRepository {
    JpaEstornoRepository() {
        super("idEstorno");
    }

    @Override
    public Iterable<Estorno> findEstornosOrdem(CodigoOrdem ordem) {
        final TypedQuery<Estorno> query = createQuery(
                "SELECT e FROM Estorno e WHERE e.ordem = :ordem"
                , Estorno.class);
        query.setParameter("ordem", ordem);

        return query.getResultList();
    }
}
