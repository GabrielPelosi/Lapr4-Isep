package eapli.base.infrastructure.bootstrapers.linhaproducaobootstrappers;

import eapli.base.linhaproducaomanagement.application.EspecificarLinhaProducaoController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class LinhaProducaoBootstrapper implements Action {

    final EspecificarLinhaProducaoController controller = new EspecificarLinhaProducaoController();

    @Override
    public boolean execute() {
        try {
            register(12345);
            register(23456);
            register(34567);
            register(56789);
            register(67890);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao da Linha de Producao");
        }
        return true;
    }

    private void register(long idLinhaProducao){
        controller.guardarLinhaProducao(idLinhaProducao);
    }
}
