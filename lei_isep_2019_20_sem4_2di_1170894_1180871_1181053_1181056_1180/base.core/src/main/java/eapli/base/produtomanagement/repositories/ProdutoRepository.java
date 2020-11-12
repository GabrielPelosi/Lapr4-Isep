package eapli.base.produtomanagement.repositories;

import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.Produto;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProdutoRepository extends DomainRepository<CodigoFabrico, Produto> {
}
