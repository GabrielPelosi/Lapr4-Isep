package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.TVTextService;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class MaquinasTransformarTxtUI extends AbstractUI {

    TVTextService theService = new TVTextService();

    @Override
    protected boolean doShow() {

        try {
            String xmlFilename = "maquinas";
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
        return "Transformar XML maquinas em .txt";
    }
}
