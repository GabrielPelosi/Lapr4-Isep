package eapli.base.app.backoffice.console.presentation.depositos;

import eapli.base.depositomanagement.application.DefinirDepositoController;
import eapli.base.linhaproducaomanagement.application.RegistarMaquinaController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class DefinirDepositoUI extends AbstractUI {

    private final DefinirDepositoController theController = new DefinirDepositoController();

    @Override
    protected boolean doShow() {

        final long codigo = Console.readLong("Codigo");
        final String descricao = Console.readLine("Descricao");

        try {
            this.theController.guardarDeposito(codigo, descricao);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a Deposito which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Registar Deposito";
    }

}

