package eapli.base.persistence.impl.jpa;

import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.base.produtomanagement.domain.Ordem;
import eapli.base.produtomanagement.repositories.OrdemRepository;

import javax.persistence.TypedQuery;

public class JpaOrdemRepository extends BasepaRepositoryBase<Ordem, CodigoOrdem, CodigoOrdem> implements OrdemRepository {
    public JpaOrdemRepository() {
        super("codigoOrdem");
    }

    public Iterable<Ordem> findEncomenda(String idEnc) {
        final TypedQuery<Ordem> query = super.createQuery(
                "SELECT * FROM ORDEM ordem, ORDEM_SEQUENCIAENCOMENDA seq WHERE ordem.codigoordem = seq.ordem_codigoordem AND seq.code = '" + idEnc + "'",
                Ordem.class);

        return query.getResultList();
    }

    public Iterable<Ordem> findByEstado(String estado) {
        final TypedQuery<Ordem> query = super.createQuery(
                "SELECT ordem FROM Ordem ordem WHERE ordem.estado = :estado1" ,
                Ordem.class);
        query.setParameter("estado1", estado);

        return query.getResultList();
    }
}
