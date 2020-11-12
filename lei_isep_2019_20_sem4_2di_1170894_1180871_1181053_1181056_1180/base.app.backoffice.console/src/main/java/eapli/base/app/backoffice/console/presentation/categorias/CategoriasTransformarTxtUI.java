package eapli.base.app.backoffice.console.presentation.categorias;

import eapli.base.TVTextService;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

public class CategoriasTransformarTxtUI extends AbstractUI {

    TVTextService theService = new TVTextService();

    @Override
    protected boolean doShow() {

        try {
            String xmlFilename = "categorias";
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
        return "Transformar XML categorias em .txt";
    }
}
