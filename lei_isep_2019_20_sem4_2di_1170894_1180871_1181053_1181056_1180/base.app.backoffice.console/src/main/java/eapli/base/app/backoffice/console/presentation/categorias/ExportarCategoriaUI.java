package eapli.base.app.backoffice.console.presentation.categorias;

import eapli.base.materiaprimamanagement.application.ExportarCategoriaController;
import eapli.base.materiaprimamanagement.application.RegistarCategoriaController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ExportarCategoriaUI extends AbstractUI {

    private final ExportarCategoriaController theController = new ExportarCategoriaController();

    @Override
    protected boolean doShow() {

        try {
            this.theController.exportarCategoria();
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro na exportação.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Exportar Categoria";
    }
}
