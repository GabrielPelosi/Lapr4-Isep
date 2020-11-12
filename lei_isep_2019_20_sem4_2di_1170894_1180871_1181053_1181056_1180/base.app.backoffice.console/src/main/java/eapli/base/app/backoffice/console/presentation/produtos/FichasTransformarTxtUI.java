package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.TVTextService;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class FichasTransformarTxtUI extends AbstractUI {

    TVTextService theService = new TVTextService();

    @Override
    protected boolean doShow() {

        try {
            String xmlFilename = "fichasProducao";
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
        return "Transformar XML fichasProducao em .txt";
    }
}
