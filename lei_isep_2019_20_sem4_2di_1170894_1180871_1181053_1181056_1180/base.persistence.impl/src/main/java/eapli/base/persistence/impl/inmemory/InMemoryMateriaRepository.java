package eapli.base.persistence.impl.inmemory;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.base.materiaprimamanagement.repositories.MateriaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryMateriaRepository extends InMemoryDomainRepository<CodigoInternoMateria, MateriaPrima> implements MateriaRepository {

    static {
        InMemoryInitializer.init();
    }
}
