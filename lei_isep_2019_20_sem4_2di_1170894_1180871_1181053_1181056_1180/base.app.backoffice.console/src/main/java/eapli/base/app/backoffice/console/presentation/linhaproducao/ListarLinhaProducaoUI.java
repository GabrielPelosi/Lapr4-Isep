package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.ListarLinhaProducaoController;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListarLinhaProducaoUI extends AbstractListUI<LinhaProducao> {

    private final ListarLinhaProducaoController theController = new ListarLinhaProducaoController();
    @Override
    protected Iterable<LinhaProducao> elements() {
        return this.theController.listarLinhasProducao();
    }

    @Override
    protected Visitor<LinhaProducao> elementPrinter() {
        return new LinhasProducaoPrinter();
    }

    @Override
    protected String elementName() {
        return "Linha de Producao";
    }

    @Override
    protected String listHeader() {
        return "LINHAS DE PRODUCAO";
    }

    @Override
    protected String emptyMessage() {
        return "no data.";
    }

    @Override
    public String headline() {
        return "Listar linhas de producao";
    }
}
