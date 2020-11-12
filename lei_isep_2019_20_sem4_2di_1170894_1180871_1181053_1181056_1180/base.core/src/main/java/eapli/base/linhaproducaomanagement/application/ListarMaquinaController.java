package eapli.base.linhaproducaomanagement.application;

import eapli.base.linhaproducaomanagement.domain.Maquina;

public class ListarMaquinaController {

    private final ListarMaquinaService svc = new ListarMaquinaService();

    public Iterable<Maquina> listarMaquinas() {
        return this.svc.allMaquinas();
    }
}
