package eapli.base.app.backoffice.console.presentation.materias;

import eapli.framework.actions.Action;

public class ExportarMateriaAction implements Action {

    @Override
    public boolean execute() {
        return new ExportarMateriaUI().show();
    }
}
