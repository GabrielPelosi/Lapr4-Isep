package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.EspecificarLinhaProducaoController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class EspecificarLinhaProducaoUI extends AbstractUI {

    private final EspecificarLinhaProducaoController theController = new EspecificarLinhaProducaoController();

    @Override
    protected boolean doShow() {

        final long idLinhaProducao = Console.readLong("Id da Linha de Producao");

        try {
            this.theController.guardarLinhaProducao(idLinhaProducao);
        } catch (final IntegrityViolationException e) {
            System.out.println("You tried to enter a Linha Producao that already exists.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Especificar Linha de Producao";
    }
}
