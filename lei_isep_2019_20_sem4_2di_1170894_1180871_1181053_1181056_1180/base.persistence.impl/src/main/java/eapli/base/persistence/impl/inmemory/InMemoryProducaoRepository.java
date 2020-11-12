package eapli.base.persistence.impl.inmemory;

import eapli.base.mensagemmanagement.domain.Producao;
import eapli.base.mensagemmanagement.repositories.ProducaoRepository;
import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProducaoRepository extends InMemoryDomainRepository<Long, Producao> implements ProducaoRepository {
    static {
        InMemoryInitializer.init();
    }

    public Iterable<Producao> findProducaoOrdem(CodigoFabrico produto, CodigoOrdem ordem) {
        return null;
    }

    @Override
    public Iterable<Producao> findProducoesOrdem(CodigoOrdem ordem) {
        return null;
    }
}
