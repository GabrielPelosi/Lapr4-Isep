package eapli.base.smmmanagement.application;

import eapli.base.smmmanagement.domain.EstadoMaquina;
import eapli.framework.visitor.Visitor;

public class SMMPrinter implements Visitor<EstadoMaquina> {

    @Override
    public void visit(final EstadoMaquina visitee){
        System.out.printf("%s    -    %s    -    %s",
                visitee.identity().toString(), visitee.ipAddress(), visitee.Status());
    }
}
