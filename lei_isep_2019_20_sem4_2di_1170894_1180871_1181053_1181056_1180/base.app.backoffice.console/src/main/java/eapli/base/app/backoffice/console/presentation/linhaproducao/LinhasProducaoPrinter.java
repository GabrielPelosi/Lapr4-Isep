package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.framework.visitor.Visitor;

public class LinhasProducaoPrinter implements Visitor<LinhaProducao> {

    @Override
    public void visit(final LinhaProducao visitee){
        System.out.printf("%1$-15s%2$-15s%3$-25s%4$-15s", visitee.identity().toString(), visitee.estado() ? "ATIVA" : "INATIVA",
                visitee.processamentoRecenteToString(),visitee.sequenciaMaquinaToString());
    }
}
