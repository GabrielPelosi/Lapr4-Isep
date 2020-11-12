package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.framework.actions.Action;

public class EspecificarLinhaProducaoAction implements Action {
    @Override
    public boolean execute() {
        return new EspecificarLinhaProducaoUI().show();
    }
}
