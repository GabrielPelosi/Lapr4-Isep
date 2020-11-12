package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.framework.visitor.Visitor;

public class CodigoMaquinaPrinter implements Visitor<CodigoInternoMaquina> {
    @Override
    public void visit(CodigoInternoMaquina visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
