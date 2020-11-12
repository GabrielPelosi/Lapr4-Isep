package eapli.base.app.backoffice.console.presentation.depositos;

import eapli.framework.actions.Action;

public class DepositosTransformarJsonAction implements Action {

    @Override
    public boolean execute() {
        return new DepositosTransformarJsonUI().show();
    }
}
