package eapli.base.produtomanagement.application;

import eapli.base.produtomanagement.domain.Produto;

public class ListarProdutosComFichaController {

    private final ListarProdutosComFichaService svc = new ListarProdutosComFichaService();

    public Iterable<Produto> listProdutoComFicha() {
        return this.svc.produtosComFicha();
    }
}
