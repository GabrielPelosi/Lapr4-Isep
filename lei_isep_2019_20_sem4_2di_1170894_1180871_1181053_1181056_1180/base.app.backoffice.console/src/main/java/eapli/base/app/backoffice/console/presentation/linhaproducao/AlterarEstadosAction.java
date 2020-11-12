package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.framework.actions.Action;

public class AlterarEstadosAction implements Action {
    @Override
    public boolean execute() {
        return new AlterarEstadosUI().show();
    }
}
