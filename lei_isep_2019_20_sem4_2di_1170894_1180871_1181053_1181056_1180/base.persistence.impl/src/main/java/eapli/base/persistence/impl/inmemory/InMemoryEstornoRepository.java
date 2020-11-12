package eapli.base.persistence.impl.inmemory;

import eapli.base.mensagemmanagement.domain.Estorno;
import eapli.base.mensagemmanagement.repositories.EstornoRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryEstornoRepository extends InMemoryDomainRepository<Long, Estorno> implements EstornoRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Estorno> findEstornosOrdem(CodigoOrdem ordem) {
        return null;
    }
}
