package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.ExportarMaquinaController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class ExportarMaquinasUI extends AbstractUI {

    private final ExportarMaquinaController theController = new ExportarMaquinaController();

    @Override
    protected boolean doShow() {

        try {
            this.theController.exportarMaquina();
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro na exportação.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Exportar Maquinas";
    }
}
