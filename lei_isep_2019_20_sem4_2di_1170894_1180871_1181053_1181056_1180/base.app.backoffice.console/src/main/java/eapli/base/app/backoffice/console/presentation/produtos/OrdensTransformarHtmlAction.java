package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.app.backoffice.console.presentation.categorias.CategoriasTransformarHtmlUI;
import eapli.framework.actions.Action;

public class OrdensTransformarHtmlAction implements Action {

    @Override
    public boolean execute() {
        return new OrdensTransformarHtmlUI().show();
    }
}
