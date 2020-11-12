package eapli.base.infrastructure.bootstrapers.materiprimabootstrapers;

import eapli.base.materiaprimamanagement.application.RegistarCategoriaController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.framework.actions.Action;

public class CategoriaBootstraper implements Action{

    private static final Logger LOGGER = LogManager.getLogger(CategoriaBootstraper.class);
    private static final RegistarCategoriaController controller = new RegistarCategoriaController();

    @Override
    public boolean execute() {
        register(1234,"plasticos");
        register(2345,"metais");
        register(3456,"madeiras");
        register(4567,"tecidos");
        register(5678,"botoes");
        return true;
    }

    /**
     *
     */
    private void register(final long cod, final String desc) {

        try {
            controller.guardarCategoria(cod, desc);
            LOGGER.info(desc);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", cod);
            LOGGER.trace("Assuming existing record", ex);
        }
    }



}
