package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.produtomanagement.domain.*;
import eapli.base.produtomanagement.repositories.ProdutoRepository;

public class DefinirProdutoController {

    private final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();

    public Produto registarProduto(String codigoFabrico, String codigoComercial, String descricaoBreve, String descricaoCompleta, String unidade, String categoria) {

        CodigoFabrico cF = new CodigoFabrico(codigoFabrico);
        CodigoComercial cC = new CodigoComercial(codigoComercial);
        DescricaoBreveProduto dB = new DescricaoBreveProduto(descricaoBreve);
        DescricaoCompletaProduto dC = new DescricaoCompletaProduto(descricaoCompleta);
        UnidadeProduto u = new UnidadeProduto(unidade);
        CategoriaProduto c = new CategoriaProduto(categoria);

        Produto novoProduto = new Produto(cF, cC, dB, dC, u, c);

        return novoProduto;
    }

    public boolean guardarProduto(String codigoFabrico, String codigoComercial, String descricaoBreve, String descricaoCompleta, String unidade, String categoria) {
        Produto produto = registarProduto(codigoFabrico, codigoComercial, descricaoBreve, descricaoCompleta, unidade, categoria);
        Produto p = produtoRepository.save(produto);
        return p != null;
    }

}
