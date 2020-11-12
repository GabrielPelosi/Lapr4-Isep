package ui;

import eapli.framework.actions.Action;

public class ProcessarRecorrenteAction implements Action {
    @Override
    public boolean execute() {
        return new ProcessarRecorrenteUI().doShow();
    }
}