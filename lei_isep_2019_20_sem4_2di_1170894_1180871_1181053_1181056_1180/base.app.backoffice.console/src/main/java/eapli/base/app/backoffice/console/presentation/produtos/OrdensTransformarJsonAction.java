package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.framework.actions.Action;

public class OrdensTransformarJsonAction implements Action {

    @Override
    public boolean execute() {
        return new OrdensTransformarJsonUI().show();
    }
}
