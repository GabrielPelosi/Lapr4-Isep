package eapli.base.infrastructure.bootstrapers.linhaproducaobootstrappers;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.application.RegistarMaquinaController;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class MaquinaBootstrapper implements Action {

    final RegistarMaquinaController controller = new RegistarMaquinaController();

    @Override
    public boolean execute() {

        register("B1234", 19028, "Maquina de 2 agulhas", 2017, 10, 9,
                "Care", "X200", 12345, 0, 1234);
        register("A5678", 23456, "Botoneira", 2017, 10, 9,
                "Care", "A39S", 12345, 1, 2345);
        register("B5678", 47890, "Maquina de soldagem", 2018, 3, 25,
                "Ford", "AA08", 34567, 0, 3456);
        register("C1234", 39280, "Maquina para prensa", 2018, 3, 25,
                "Ford", "R348", 56789, 0, 4567);
        register("12", 26548, "Maquina para areia", 2019, 6,5,
                "Ford", "AA06", 67890, 0, 4567);
        return true;
    }

    private void register(final String codInterno, final int numSerie, final String descricao,
                          final int ano, final int mes, final int dia, final String marca, final String modelo,
                          final long idLinhaProducao, final int posicao, final long idProtocolo) {
        final LinhaProducaoRepository linhaRepository = PersistenceContext.repositories().linhasProducao();
        try {
            IdLinhaProducao id = new IdLinhaProducao(idLinhaProducao);
            LinhaProducao linhaProducao = linhaRepository.ofIdentity(id).get();
            controller.guardarMaquina(codInterno, numSerie, descricao, ano, mes, dia, marca, modelo, linhaProducao, posicao, idProtocolo);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao da Maquina");
            e.printStackTrace();
        }
    }
}