package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.framework.visitor.Visitor;


public class MaquinasPrinter implements Visitor<Maquina> {

    @Override
    public void visit(final Maquina visitee) {
        System.out.printf("%10s%10s%30s%15s%15s%15s%20s%20s",
                visitee.identity().toString(),
                visitee.numeroSerie().toString(),
                visitee.descricaoMaquina().toString(),
                visitee.dataInstalacao().toAnoMesDiaString(),
                visitee.marca().toString(),
                visitee.modelo().toString(),
                visitee.ficheiroConfiguracaoToString(),
                visitee.idProtocolo());
    }
}
