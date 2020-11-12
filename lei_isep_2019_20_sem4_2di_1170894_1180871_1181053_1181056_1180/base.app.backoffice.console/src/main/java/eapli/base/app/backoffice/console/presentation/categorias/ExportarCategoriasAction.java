package eapli.base.app.backoffice.console.presentation.categorias;

import eapli.framework.actions.Action;

public class ExportarCategoriasAction implements Action {

    @Override
    public boolean execute() {
        return new ExportarCategoriaUI().show();
    }
}
