package eapli.base.app.backoffice.console.presentation.produtos;


import eapli.framework.actions.Action;


public class ListProdutosSemFichaAction implements Action {

    @Override
    public boolean execute() {
        return new ListProdutosSemFichaUI().show();
    }
}
