package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.ImportarProdutosCSVController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ImportarProdutosUI extends AbstractUI {

    ImportarProdutosCSVController theController = new ImportarProdutosCSVController();

    @Override
    protected boolean doShow() {

        String fileRawName = Console.readLine("Nome do ficheiro com .csv a importar");

        if (!fileRawName.endsWith(".csv"))
            fileRawName += ".csv";

        final String fileName = fileRawName;

        try {
            this.theController.CSV(fileName);
        } catch (final IntegrityViolationException e) {
            System.out.println("You tried to import a Produto that already exists.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Importar Produto";
    }
}
