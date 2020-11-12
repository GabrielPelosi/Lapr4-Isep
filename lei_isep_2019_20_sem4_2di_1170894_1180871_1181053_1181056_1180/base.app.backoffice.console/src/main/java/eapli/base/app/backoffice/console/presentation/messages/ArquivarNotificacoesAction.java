package eapli.base.app.backoffice.console.presentation.messages;

import eapli.framework.actions.Action;

public class ArquivarNotificacoesAction implements Action {
    @Override
    public boolean execute() {
        return new ArquivarNotificacoesUI().show();
    }
}
