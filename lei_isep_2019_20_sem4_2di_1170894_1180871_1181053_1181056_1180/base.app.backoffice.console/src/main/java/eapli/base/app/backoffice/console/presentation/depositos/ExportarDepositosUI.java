package eapli.base.app.backoffice.console.presentation.depositos;

import eapli.base.depositomanagement.application.ExportarDepositoController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class ExportarDepositosUI extends AbstractUI {

    private final ExportarDepositoController exportarDepositoController = new ExportarDepositoController();


    @Override
    protected boolean doShow() {
        try {
            this.exportarDepositoController.exportarDepositos();
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro na exportação.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Exportar Depositos";
    }
}
