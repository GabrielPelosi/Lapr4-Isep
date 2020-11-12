package eapli.base.persistence.impl.inmemory;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.mensagemmanagement.domain.Consumo;
import eapli.base.mensagemmanagement.repositories.ConsumoRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryConsumoRepository extends InMemoryDomainRepository<Long, Consumo> implements ConsumoRepository {
    static {
        InMemoryInitializer.init();
    }

    public Iterable<Consumo> findConsumosOrdemDeUmaMateria(CodigoInternoMateria materia, CodigoOrdem ordem) {
        return null;
    }

    @Override
    public Iterable<Consumo> findConsumosOrdem(CodigoOrdem ordem) {
        return null;
    }
}
