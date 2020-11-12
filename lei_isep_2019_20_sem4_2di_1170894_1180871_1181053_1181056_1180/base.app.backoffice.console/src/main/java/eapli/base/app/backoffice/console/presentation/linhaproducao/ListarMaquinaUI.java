package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.ListarMaquinaController;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListarMaquinaUI extends AbstractListUI<Maquina> {

    private final ListarMaquinaController theController = new ListarMaquinaController();
    @Override
    protected Iterable<Maquina> elements() {
        return this.theController.listarMaquinas();
    }

    @Override
    protected Visitor<Maquina> elementPrinter() {
        return new MaquinasPrinter();
    }

    @Override
    protected String elementName() {
        return "Maquina";
    }

    @Override
    protected String listHeader() {
        return "MAQUINAS";
    }

    @Override
    protected String emptyMessage() {
        return "no data.";
    }

    @Override
    public String headline() {
        return "Listar maquinas";
    }
}
