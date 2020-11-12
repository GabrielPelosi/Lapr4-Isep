package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.app.backoffice.console.presentation.materias.ListarCategoriasUI;
import eapli.framework.actions.Action;

public class ListarLinhaProducaoAction implements Action {
    @Override
    public boolean execute() {
        return new ListarLinhaProducaoUI().show();
    }
}
