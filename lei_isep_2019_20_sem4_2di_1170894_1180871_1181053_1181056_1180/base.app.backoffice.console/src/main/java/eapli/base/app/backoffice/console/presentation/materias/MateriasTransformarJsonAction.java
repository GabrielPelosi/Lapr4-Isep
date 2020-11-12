package eapli.base.app.backoffice.console.presentation.materias;

import eapli.framework.actions.Action;

public class MateriasTransformarJsonAction implements Action {

    @Override
    public boolean execute() {
        return new MateriasTransformarJsonUI().show();
    }
}
