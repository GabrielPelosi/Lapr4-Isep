package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.framework.actions.Action;

public class LinhasTransformarTxtAction implements Action {

    @Override
    public boolean execute() {
        return new LinhasTransformarTxtUI().show();
    }
}
