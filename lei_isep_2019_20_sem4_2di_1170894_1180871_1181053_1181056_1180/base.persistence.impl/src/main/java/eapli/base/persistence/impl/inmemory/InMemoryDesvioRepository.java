package eapli.base.persistence.impl.inmemory;

import eapli.base.mensagemmanagement.domain.Desvio;
import eapli.base.mensagemmanagement.repositories.DesvioRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryDesvioRepository extends InMemoryDomainRepository<CodigoOrdem, Desvio> implements DesvioRepository {
    static {
        InMemoryInitializer.init();
    }
}
