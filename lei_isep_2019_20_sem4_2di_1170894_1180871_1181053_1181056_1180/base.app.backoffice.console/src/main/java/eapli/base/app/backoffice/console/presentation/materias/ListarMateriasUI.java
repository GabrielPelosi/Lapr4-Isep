package eapli.base.app.backoffice.console.presentation.materias;

import eapli.base.materiaprimamanagement.application.ListMateriasController;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListarMateriasUI extends AbstractListUI<MateriaPrima> {

    private final ListMateriasController theController = new ListMateriasController();

    @Override
    protected Iterable<MateriaPrima> elements() {
        return this.theController.listMaterias();
    }

    @Override
    protected Visitor<MateriaPrima> elementPrinter() {
        return new MateriasPrinter();
    }

    @Override
    protected String elementName() {
        return "Materia-Prima";
    }

    @Override
    protected String listHeader() {
        return "MATERIAS-PRIMAS:";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "Listar Materias";
    }
}
