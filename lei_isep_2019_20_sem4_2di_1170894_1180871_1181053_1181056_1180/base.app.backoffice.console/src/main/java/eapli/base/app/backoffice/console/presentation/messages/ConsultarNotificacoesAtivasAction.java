package eapli.base.app.backoffice.console.presentation.messages;

import eapli.framework.actions.Action;

public class ConsultarNotificacoesAtivasAction implements Action {
    @Override
    public boolean execute() {
        return new ConsultarNotificacoesAtivasUI().show();
    }
}
