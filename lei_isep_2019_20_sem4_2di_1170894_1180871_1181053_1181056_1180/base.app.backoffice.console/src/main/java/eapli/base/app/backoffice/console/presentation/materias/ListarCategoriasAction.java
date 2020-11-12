package eapli.base.app.backoffice.console.presentation.materias;


import eapli.framework.actions.Action;

public class ListarCategoriasAction implements Action {

    @Override
    public boolean execute() {
        return new ListarCategoriasUI().show();
    }
}
