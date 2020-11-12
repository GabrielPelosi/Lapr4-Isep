package eapli.base.app.backoffice.console.presentation.materias;

import eapli.base.materiaprimamanagement.application.ExportarMateriaController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class ExportarMateriaUI extends AbstractUI {

    private final ExportarMateriaController theController = new ExportarMateriaController();

    @Override
    protected boolean doShow() {

        try {
            this.theController.exportarMateria();
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro na exportação.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Exportar Materias Primas";
    }
}
