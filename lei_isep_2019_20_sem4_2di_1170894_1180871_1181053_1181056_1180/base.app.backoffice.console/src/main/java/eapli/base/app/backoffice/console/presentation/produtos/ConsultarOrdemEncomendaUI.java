package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.ConsultarOrdemEncomendaController;
import eapli.base.produtomanagement.domain.CodigoEncomenda;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.util.Console;
import eapli.framework.visitor.Visitor;

public class ConsultarOrdemEncomendaUI extends AbstractListUI {

    private final ConsultarOrdemEncomendaController theController = new ConsultarOrdemEncomendaController();

    @Override
    protected Iterable elements() {
        final String id = Console.readLine("Id da Encomenda:");
        CodigoEncomenda idEncomenda = new CodigoEncomenda(id);

        return this.theController.listarOrdemEncomenda(idEncomenda);
    }

    @Override
    protected Visitor elementPrinter() {
        return new OrdensPrinter();
    }

    @Override
    protected String elementName() {
        return "Ordem";
    }

    @Override
    protected String listHeader() {
        return "ORDENS:";
    }

    @Override
    protected String emptyMessage() {
        return "Nenhuma Ordem foi encontrada com essa Encomenda.";
    }

    @Override
    public String headline() {
        return "Consultar Ordens de uma Encomenda";
    }
}
