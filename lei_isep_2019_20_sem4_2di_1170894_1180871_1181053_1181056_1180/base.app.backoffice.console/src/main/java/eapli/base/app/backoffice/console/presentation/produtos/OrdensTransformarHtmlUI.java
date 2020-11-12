package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.TVHtmlService;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class OrdensTransformarHtmlUI extends AbstractUI {

    TVHtmlService theService = new TVHtmlService();

    @Override
    protected boolean doShow() {

        try {
            String xmlFilename = "ordens";
            this.theService.transform(xmlFilename);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro na exportação.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline() {
        return "Transformar XML ordens em HTML";
    }
}
