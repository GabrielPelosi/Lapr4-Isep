package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.domain.Ordem;
import eapli.framework.visitor.Visitor;

public class OrdensPrinter implements Visitor<Ordem> {
    @Override
    public void visit(Ordem visitee) {
        System.out.printf("%s", visitee.identity().toString());
    }
}
