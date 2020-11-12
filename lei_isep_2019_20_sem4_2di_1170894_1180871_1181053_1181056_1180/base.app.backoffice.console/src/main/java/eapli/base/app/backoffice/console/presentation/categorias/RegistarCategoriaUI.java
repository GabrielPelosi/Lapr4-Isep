package eapli.base.app.backoffice.console.presentation.categorias;

import eapli.base.materiaprimamanagement.application.RegistarCategoriaController;
import eapli.base.materiaprimamanagement.application.RegistarMateriaPrimaController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class RegistarCategoriaUI extends AbstractUI {
    private final RegistarCategoriaController theController = new RegistarCategoriaController();

    @Override
    protected boolean doShow() {
        final long id = Console.readLong("Codigo");

        final String descricao = Console.readLine("Descricao");

        try {
            this.theController.guardarCategoria(id, descricao);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a Categoria which already exists in the database.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Registar Categoria";
    }
}
