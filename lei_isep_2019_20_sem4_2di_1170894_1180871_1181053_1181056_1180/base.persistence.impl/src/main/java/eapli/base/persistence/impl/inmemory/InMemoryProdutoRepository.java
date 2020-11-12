package eapli.base.persistence.impl.inmemory;

import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.Produto;
import eapli.base.produtomanagement.repositories.ProdutoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProdutoRepository extends InMemoryDomainRepository<CodigoFabrico, Produto> implements ProdutoRepository {
    static {
        InMemoryInitializer.init();
    }
}
