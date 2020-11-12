package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.framework.actions.Action;

public class ImportarProdutosAction implements Action {

    @Override
    public boolean execute() {
        return new ImportarProdutosUI().show();
    }
}
