package eapli.base.app.backoffice.console.presentation.categorias;

import eapli.framework.actions.Action;

public class CategoriasTransformarJsonAction implements Action {

    @Override
    public boolean execute() {
        return new CategoriasTransformarJsonUI().show();
    }
}
