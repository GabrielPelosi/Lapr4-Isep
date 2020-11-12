package eapli.base.app.backoffice.console.presentation.messages;

import eapli.framework.actions.Action;

public class ConsultarNotificacoesAquivadasAction implements Action {
    @Override
    public boolean execute() {
        return new ConsultarNotificacoesArquivadasUI().show();
    }
}
