package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.ExportarProdutosController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class ExportarProdutosUI extends AbstractUI {

    private final ExportarProdutosController theController = new ExportarProdutosController();

    @Override
    protected boolean doShow() {

        try{
            theController.exportarProduto();
        } catch (final IntegrityViolationException e) {
            System.out.println("Erro na exportacao.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Exportar Produtos";
    }
}
