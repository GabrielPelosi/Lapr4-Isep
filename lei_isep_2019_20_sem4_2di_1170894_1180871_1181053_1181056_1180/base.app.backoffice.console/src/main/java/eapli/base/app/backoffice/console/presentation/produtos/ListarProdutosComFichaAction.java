package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.app.backoffice.console.presentation.materias.ListarCategoriasUI;
import eapli.framework.actions.Action;

public class ListarProdutosComFichaAction implements Action {
    @Override
    public boolean execute() {
        return new ListarProdutosComFichaUI().show();
    }
}
