package eapli.base.persistence.impl.jpa;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.mensagemmanagement.domain.Consumo;
import eapli.base.mensagemmanagement.repositories.ConsumoRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;

import javax.persistence.TypedQuery;

public class JpaConsumoRepository extends BasepaRepositoryBase<Consumo, Long, Long> implements ConsumoRepository {
    JpaConsumoRepository() {
        super("idConsumo");
    }

    public Iterable<Consumo> findConsumosOrdemDeUmaMateria(CodigoInternoMateria materia, CodigoOrdem ordem) {
        final TypedQuery<Consumo> query = createQuery(
                "SELECT c FROM Consumo c WHERE c.ordem = :ordem"
                        + " AND c.materia = :materia"
                , Consumo.class);
        query.setParameter("ordem", ordem);
        query.setParameter("materia", materia);

        return query.getResultList();
    }

    @Override
    public Iterable<Consumo> findConsumosOrdem(CodigoOrdem ordem) {
        final TypedQuery<Consumo> query = createQuery(
                "SELECT c FROM Consumo c WHERE c.ordem = :ordem"
                , Consumo.class);
        query.setParameter("ordem", ordem);

        return query.getResultList();
    }
}
