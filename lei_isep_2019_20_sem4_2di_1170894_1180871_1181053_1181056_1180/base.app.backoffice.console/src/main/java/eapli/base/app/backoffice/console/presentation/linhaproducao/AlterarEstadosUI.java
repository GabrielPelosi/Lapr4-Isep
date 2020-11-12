package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.AlterarEstadoController;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AlterarEstadosUI extends AbstractUI {

    final AlterarEstadoController theController = new AlterarEstadoController();

    @Override
    protected boolean doShow() {
        SelectWidget<LinhaProducao> selector;

        do {
            selector = new SelectWidget<>("Alterar estado de linha de producao:",
                    theController.linhaProducao(), new LinhasProducaoPrinter());
            selector.show();
            LinhaProducao linhaProducao = selector.selectedOption() == 0 ? null : selector.selectedElement();
            if (linhaProducao != null) {
                theController.alterarEstado(linhaProducao);
            }
        } while (selector.selectedOption() != 0);
        return false;
    }

    @Override
    public String headline() {
        return "Consultar estados das Linhas de Producao";
    }
}
