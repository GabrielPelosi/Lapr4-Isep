package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.framework.actions.Action;

public class LinhasTransformarJsonAction implements Action {

    @Override
    public boolean execute() {
        return new LinhasTransformarJsonUI().show();
    }
}
