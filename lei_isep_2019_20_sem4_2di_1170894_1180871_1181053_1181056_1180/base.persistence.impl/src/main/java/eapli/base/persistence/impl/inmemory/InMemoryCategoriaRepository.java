package eapli.base.persistence.impl.inmemory;

import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.domain.CodigoCategoria;
import eapli.base.materiaprimamanagement.repositories.CategoriaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCategoriaRepository extends InMemoryDomainRepository<CodigoCategoria, Categoria> implements CategoriaRepository {

    static {
        InMemoryInitializer.init();
    }
}
