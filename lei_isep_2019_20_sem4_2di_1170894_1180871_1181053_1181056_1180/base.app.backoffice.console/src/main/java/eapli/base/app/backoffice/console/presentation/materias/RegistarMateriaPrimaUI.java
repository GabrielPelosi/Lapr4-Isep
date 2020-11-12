package eapli.base.app.backoffice.console.presentation.materias;

import eapli.base.materiaprimamanagement.application.RegistarMateriaPrimaController;
import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

public class RegistarMateriaPrimaUI extends AbstractUI {

    private final RegistarMateriaPrimaController theController = new RegistarMateriaPrimaController();

    @Override
    protected boolean doShow() {
        final Iterable<Categoria> categorias = this.theController.categoria();

        final long codigo = Console.readLong("Codigo");

        final String descricao = Console.readLine("Descricao");

        final SelectWidget<Categoria> selector = new SelectWidget<>("Categorias:", categorias,
                new CategoriasPrinter());
        selector.show();

        final Categoria c = selector.selectedElement();

        final String localizacaoFicheiro = Console.readLine("Localizacao do Ficheiro");

        try {
            this.theController.guardarMateria(codigo, descricao, c, localizacaoFicheiro);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a Materia Prima which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Registar Materia Prima";
    }
}
