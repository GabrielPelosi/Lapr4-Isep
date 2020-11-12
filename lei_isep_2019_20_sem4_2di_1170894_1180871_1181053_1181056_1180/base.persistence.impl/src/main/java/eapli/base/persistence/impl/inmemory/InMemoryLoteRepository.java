package eapli.base.persistence.impl.inmemory;

import eapli.base.produtomanagement.domain.Lote;
import eapli.base.produtomanagement.repositories.LoteRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryLoteRepository extends InMemoryDomainRepository<String, Lote> implements LoteRepository {
    static {
        InMemoryInitializer.init();
    }
}
