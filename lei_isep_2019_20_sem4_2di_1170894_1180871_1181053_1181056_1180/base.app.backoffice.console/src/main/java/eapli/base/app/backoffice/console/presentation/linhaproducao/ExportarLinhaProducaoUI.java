package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.ExportarLinhaProducaoController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class ExportarLinhaProducaoUI extends AbstractUI {

    private final ExportarLinhaProducaoController theController = new ExportarLinhaProducaoController();

    @Override
    protected boolean doShow() {
        try {
            theController.exportarLinhaProducao();
        } catch (final IntegrityViolationException e) {
            System.out.println("Erro na exportacao");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Exportar Linhas de Producao";
    }
}
