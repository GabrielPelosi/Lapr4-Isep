package eapli.base.app.backoffice.console.presentation.depositos;

import eapli.framework.actions.Action;

public class DepositosTransformarTxtAction implements Action {

    @Override
    public boolean execute() {
        return new DepositosTransformarTxtUI().show();
    }
}
