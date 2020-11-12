package eapli.base.app.backoffice.console.presentation.produtos;


import eapli.base.produtomanagement.application.ListProdutoSemFichaController;
import eapli.base.produtomanagement.domain.Produto;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListProdutosSemFichaUI extends AbstractListUI<Produto> {

    private final ListProdutoSemFichaController theController = new ListProdutoSemFichaController();

    @Override
    protected Iterable<Produto> elements() {
        return this.theController.listProdutoSemFicha();
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
        return "Listar produtos sem ficha de produção";
    }
}

