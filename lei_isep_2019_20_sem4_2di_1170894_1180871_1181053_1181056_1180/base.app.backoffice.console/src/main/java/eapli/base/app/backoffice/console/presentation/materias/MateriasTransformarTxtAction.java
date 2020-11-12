package eapli.base.app.backoffice.console.presentation.materias;

import eapli.framework.actions.Action;

public class MateriasTransformarTxtAction implements Action {

    @Override
    public boolean execute() {
        return new MateriasTransformarTxtUI().show();
    }
}
