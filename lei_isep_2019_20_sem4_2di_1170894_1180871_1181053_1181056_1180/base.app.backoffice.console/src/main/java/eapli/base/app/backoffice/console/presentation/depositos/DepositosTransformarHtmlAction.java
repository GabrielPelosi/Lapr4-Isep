package eapli.base.app.backoffice.console.presentation.depositos;

import eapli.base.app.backoffice.console.presentation.categorias.CategoriasTransformarHtmlUI;
import eapli.framework.actions.Action;

public class DepositosTransformarHtmlAction implements Action {

    @Override
    public boolean execute() {
        return new DepositosTransformarHtmlUI().show();
    }
}
