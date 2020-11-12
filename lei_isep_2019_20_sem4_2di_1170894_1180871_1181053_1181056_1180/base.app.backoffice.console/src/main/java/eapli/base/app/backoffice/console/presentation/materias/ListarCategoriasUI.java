package eapli.base.app.backoffice.console.presentation.materias;

import eapli.base.materiaprimamanagement.application.ListCategoriaController;
import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListarCategoriasUI extends AbstractListUI<Categoria> {

    private final ListCategoriaController theController = new ListCategoriaController();

    @Override
    protected Iterable<Categoria> elements() {
        return this.theController.listCategorias();
    }

    @Override
    protected Visitor<Categoria> elementPrinter() {
        return new CategoriasPrinter();
    }

    @Override
    protected String elementName() {
        return "Categoria";
    }

    @Override
    protected String listHeader() {
        return "CATEGORIAS:";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "Listar categorias";
    }
}
