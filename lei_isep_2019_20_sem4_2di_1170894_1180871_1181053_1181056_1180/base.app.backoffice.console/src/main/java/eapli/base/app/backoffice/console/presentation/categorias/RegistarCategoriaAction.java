package eapli.base.app.backoffice.console.presentation.categorias;

import eapli.base.app.backoffice.console.presentation.materias.RegistarMateriaPrimaUI;

import eapli.framework.actions.Action;

public class RegistarCategoriaAction implements Action {

    @Override
    public boolean execute() {
        return new RegistarCategoriaUI().show();
    }
}
