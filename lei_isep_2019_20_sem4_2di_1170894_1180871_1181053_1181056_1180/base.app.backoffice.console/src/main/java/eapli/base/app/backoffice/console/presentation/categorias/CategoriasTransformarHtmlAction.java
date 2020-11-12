package eapli.base.app.backoffice.console.presentation.categorias;

import eapli.framework.actions.Action;

public class CategoriasTransformarHtmlAction implements Action {

    @Override
    public boolean execute() {
        return new CategoriasTransformarHtmlUI().show();
    }
}
