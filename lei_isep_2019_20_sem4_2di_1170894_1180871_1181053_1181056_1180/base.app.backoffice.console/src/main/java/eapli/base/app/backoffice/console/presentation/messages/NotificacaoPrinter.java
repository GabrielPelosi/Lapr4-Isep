package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.framework.visitor.Visitor;

public class NotificacaoPrinter implements Visitor<Notificacao> {
    @Override
    public void visit(Notificacao visitee) {
        System.out.printf("%1$-5s%2$-5s%3$-30s%4$-10s%5$-25s",
                visitee.identity(), "", visitee.tipoString(), visitee.maquinaString(), visitee.dataString());
    }
}
