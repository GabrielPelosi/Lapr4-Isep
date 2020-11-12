package eapli.base.persistence.impl.jpa;

import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.Produto;
import eapli.base.produtomanagement.repositories.ProdutoRepository;

public class JpaProdutoRepository extends BasepaRepositoryBase<Produto, CodigoFabrico, CodigoFabrico> implements ProdutoRepository {
    public JpaProdutoRepository() {
        super("codigoFabrico");
    }
}
