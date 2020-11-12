package eapli.base.app.backoffice.console.presentation.depositos;

import eapli.framework.actions.Action;

public class ExportarDepositosAction implements Action {
    @Override
    public boolean execute() {
        return new ExportarDepositosUI().show();
    }
}
