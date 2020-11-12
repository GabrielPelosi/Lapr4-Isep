package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.mensagemmanagement.application.ArquivarNotificacoesController;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ArquivarNotificacoesUI extends AbstractUI {

    private final ArquivarNotificacoesController theController = new ArquivarNotificacoesController();

    @Override
    protected boolean doShow() {
        Iterable<Notificacao> notificacoes = this.theController.notificacoesAtivas();

        SelectWidget<Notificacao> selector = new SelectWidget<>("Escolha uma notificacao para arquivar", notificacoes,
                new NotificacaoPrinter());
        selector.show();
        while (selector.selectedOption() != 0) {
            this.theController.arquivarNotificacao(selector.selectedElement());
            notificacoes = this.theController.notificacoesAtivas();
            selector = new SelectWidget<>("Escolha uma notificacao para arquivar", notificacoes, new NotificacaoPrinter());
            selector.show();
        }
        return false;
    }

    @Override
    public String headline() {
        return "Arquivar notificacoes";
    }
}
