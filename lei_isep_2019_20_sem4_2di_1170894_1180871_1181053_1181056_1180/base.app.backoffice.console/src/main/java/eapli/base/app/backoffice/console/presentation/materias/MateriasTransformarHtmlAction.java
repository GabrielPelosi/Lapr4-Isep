package eapli.base.app.backoffice.console.presentation.materias;

import eapli.base.app.backoffice.console.presentation.categorias.CategoriasTransformarHtmlUI;
import eapli.framework.actions.Action;

public class MateriasTransformarHtmlAction implements Action {

    @Override
    public boolean execute() {
        return new MateriasTransformarHtmlUI().show();
    }
}
