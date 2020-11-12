package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.app.backoffice.console.presentation.categorias.ExportarCategoriaUI;
import eapli.framework.actions.Action;

public class ExportarOrdensAction implements Action {

    @Override
    public boolean execute() {
        return new ExportarOrdensUI().show();
    }
}
