package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.framework.actions.Action;

public class ConsultarOrdemEstadoAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultarOrdemEstadoUI().show();
    }
}
