package eapli.base.persistence.impl.inmemory;

import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.base.produtomanagement.domain.Ordem;
import eapli.base.produtomanagement.repositories.OrdemRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrdemRepository extends InMemoryDomainRepository<CodigoOrdem, Ordem> implements OrdemRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Ordem> findEncomenda(String idEnc) {
        return null;
    }

    @Override
    public Iterable<Ordem> findByEstado(String estado) {
        return null;
    }
}
