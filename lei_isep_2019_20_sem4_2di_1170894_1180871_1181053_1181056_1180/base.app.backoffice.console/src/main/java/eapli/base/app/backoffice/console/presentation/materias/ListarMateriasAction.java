package eapli.base.app.backoffice.console.presentation.materias;

import eapli.framework.actions.Action;

public class ListarMateriasAction implements Action {

    @Override
    public boolean execute() {
        return new ListarMateriasUI().show();
    }
}
