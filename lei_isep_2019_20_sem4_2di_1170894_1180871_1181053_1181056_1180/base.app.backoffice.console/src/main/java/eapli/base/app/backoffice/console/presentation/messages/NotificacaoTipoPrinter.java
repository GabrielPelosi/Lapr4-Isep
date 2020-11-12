package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.framework.visitor.Visitor;

public class NotificacaoTipoPrinter  implements Visitor<Notificacao.Tipo> {
    @Override
    public void visit(Notificacao.Tipo visitee) {
        System.out.print(visitee.toString());
    }
}
