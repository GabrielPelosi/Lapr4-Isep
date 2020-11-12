package eapli.base.app.backoffice.console.presentation.depositos;

import eapli.base.app.backoffice.console.presentation.materias.RegistarMateriaPrimaUI;
import eapli.framework.actions.Action;
import javax.swing.*;

public class DefinirDepositoAction implements Action {

    @Override
    public boolean execute() {
        return new DefinirDepositoUI().show();
    }
}
