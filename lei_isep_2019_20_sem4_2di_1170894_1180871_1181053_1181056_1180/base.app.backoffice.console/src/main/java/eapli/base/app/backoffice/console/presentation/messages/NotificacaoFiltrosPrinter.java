package eapli.base.app.backoffice.console.presentation.messages;

import eapli.framework.visitor.Visitor;

public class NotificacaoFiltrosPrinter implements Visitor<String> {
    @Override
    public void visit(String visitee) {
        System.out.print(visitee);
    }
}
