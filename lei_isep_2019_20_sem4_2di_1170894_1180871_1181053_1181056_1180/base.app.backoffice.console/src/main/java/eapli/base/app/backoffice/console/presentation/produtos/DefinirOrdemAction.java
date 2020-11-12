package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.app.backoffice.console.presentation.materias.RegistarMateriaPrimaUI;
import eapli.framework.actions.Action;

public class DefinirOrdemAction implements Action {

    @Override
    public boolean execute() {
        return new DefenirOrdemUI().show();
    }
}
