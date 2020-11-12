package eapli.base.infrastructure.bootstrapers.depositobootstrappers;

import eapli.base.depositomanagement.application.DefinirDepositoController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

public class DepositoBootstrapper implements Action {

    final DefinirDepositoController controller = new DefinirDepositoController();

    @Override
    public boolean execute() {
        try {
            Map<String, Double> materias1;
            materias1 = new HashMap<>();
            materias1.put("9876", 12000.0);
            materias1.put("1927", 15000.0);
            register(12344, "Deposito para metal", materias1);
            Map<String, Double> materias2;
            materias2 = new HashMap<>();
            materias2.put("4321", 550.4);
            register(23498, "Deposito para madeira", materias2);
            Map<String, Double> materias3;
            materias3 = new HashMap<>();
            materias3.put("8765", 50.0);
            register(28309, "Deposito para plasticos", materias3);
            Map<String, Double> materias4;
            materias4 = new HashMap<>();
            materias4.put("9870", 500.5);
            materias4.put("5432", 100.1);
            materias4.put("3210", 499.9);
            register(56789, "Deposito para tecidos", materias4);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao da Deposito");
        }

        return true;
    }

    private void register(final long code, final String desc, final Map<String, Double> materias){
        controller.guardarDeposito(code, desc, materias);
    }
}
