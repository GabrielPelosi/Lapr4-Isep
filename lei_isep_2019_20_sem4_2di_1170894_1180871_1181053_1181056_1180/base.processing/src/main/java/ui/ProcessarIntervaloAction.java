package ui;

import eapli.framework.actions.Action;

public class ProcessarIntervaloAction implements Action {
    @Override
    public boolean execute() {
        return new ProcessarIntervaloUI().doShow();
    }
}
