package eapli.base.produtomanagement.application;


import eapli.base.produtomanagement.domain.Produto;

import java.util.Iterator;


public class ListProdutoSemFichaController {

    private final ListProdutoSemFichaService svc = new ListProdutoSemFichaService();

    public Iterable<Produto> listProdutoSemFicha() {
        return this.svc.produtosSemFicha();
    }
}
