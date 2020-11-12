package eapli.base.infrastructure.bootstrapers.produtobootstrappers;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.produtomanagement.application.EspecificarFichaProducaoController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.util.Arrays;
import java.util.List;

public class FichaProducaoBootstrapper implements Action {

    final EspecificarFichaProducaoController theController = new EspecificarFichaProducaoController();

    @Override
    public boolean execute() {
        try {
            register("8100001", Arrays.asList(CodigoInternoMateria.valueOf("9876")), Arrays.asList((double) 5),
                    Arrays.asList("UN"));
            register("8100000", Arrays.asList(CodigoInternoMateria.valueOf("9876"), CodigoInternoMateria.valueOf("1927")),
                    Arrays.asList((double) 50, (double) 20), Arrays.asList("UN", "KG"));
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao da Ficha de Producao");
        }
        return true;
    }

    private void register(final String codInterno, final List<CodigoInternoMateria> seqMateria,
                          final List<Double> seqQuantidade, final List<String> seqUnidade) {
        theController.guardarFichaProducao(codInterno, seqMateria, seqQuantidade, seqUnidade);
    }
}
