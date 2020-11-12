package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.framework.actions.Action;

public class MaquinasTransformarJsonAction implements Action {

    @Override
    public boolean execute() {
        return new MaquinasTransformarJsonUI().show();
    }
}
