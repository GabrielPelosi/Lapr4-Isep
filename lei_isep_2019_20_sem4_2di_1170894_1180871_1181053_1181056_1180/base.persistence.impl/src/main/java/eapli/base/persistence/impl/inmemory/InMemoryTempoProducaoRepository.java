package eapli.base.persistence.impl.inmemory;

import eapli.base.mensagemmanagement.domain.TempoProducao;
import eapli.base.mensagemmanagement.repositories.TempoProducaoRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryTempoProducaoRepository extends InMemoryDomainRepository<CodigoOrdem, TempoProducao> implements TempoProducaoRepository {
    static {
        InMemoryInitializer.init();
    }
}
