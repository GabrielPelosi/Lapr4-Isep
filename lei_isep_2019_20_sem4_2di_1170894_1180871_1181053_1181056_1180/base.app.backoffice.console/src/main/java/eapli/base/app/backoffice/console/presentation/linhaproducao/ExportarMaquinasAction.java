package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.framework.actions.Action;

public class ExportarMaquinasAction implements Action {

    @Override
    public boolean execute() {
        return new ExportarMaquinasUI().show();
    }
}
