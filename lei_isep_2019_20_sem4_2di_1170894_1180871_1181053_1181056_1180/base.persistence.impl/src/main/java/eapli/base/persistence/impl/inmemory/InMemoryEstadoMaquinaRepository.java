package eapli.base.persistence.impl.inmemory;

import eapli.base.smmmanagement.domain.EstadoMaquina;
import eapli.base.smmmanagement.repositories.EstadoMaquinaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryEstadoMaquinaRepository extends InMemoryDomainRepository<Long, EstadoMaquina> implements EstadoMaquinaRepository {

    static {
        InMemoryInitializer.init();
    }
}
