package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.ImportarOrdensCSVController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ImportarOrdensUI extends AbstractUI {

    ImportarOrdensCSVController theController = new ImportarOrdensCSVController();

    @Override
    protected boolean doShow() {

        String fileRawName = Console.readLine("Nome do ficheiro com .csv a importar");

        if (!fileRawName.endsWith(".csv"))
            fileRawName += ".csv";

        final String fileName = fileRawName;

        try {
            this.theController.CSVO(fileName);
        } catch (final IntegrityViolationException e) {
            System.out.println("You tried to import a Ordem that already exists.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Importar Ordem";
    }
}
