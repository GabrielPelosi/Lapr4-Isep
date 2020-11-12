package eapli.base.app.backoffice.console.presentation.produtos;


import eapli.base.produtomanagement.application.ListarProdutosComFichaController;
import eapli.base.produtomanagement.domain.Produto;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListarProdutosComFichaUI extends AbstractListUI<Produto> {
    private final ListarProdutosComFichaController theController = new ListarProdutosComFichaController();

    @Override
    protected Iterable<Produto> elements() {
        return this.theController.listProdutoComFicha();
    }

    @Override
    protected Visitor<Produto> elementPrinter() {
        return new ProdutosPrinter();
    }

    @Override
    protected String elementName() {
        return "Produto";
    }

    @Override
    protected String listHeader() {
        return "PRODUTOS:";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "Listar Produtos";
    }
}
