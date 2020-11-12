package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.ExportarFichaProducaoController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class ExportarFichaProducaoUI extends AbstractUI {

    private final ExportarFichaProducaoController exportarFichaProducaoController = new ExportarFichaProducaoController();

    @Override
    protected boolean doShow() {
        try {
            this.exportarFichaProducaoController.exportarFichas();
        } catch (final IntegrityViolationException e) {
            System.out.println("Erro na exportação.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Exportar Ficha de Producao";
    }
}