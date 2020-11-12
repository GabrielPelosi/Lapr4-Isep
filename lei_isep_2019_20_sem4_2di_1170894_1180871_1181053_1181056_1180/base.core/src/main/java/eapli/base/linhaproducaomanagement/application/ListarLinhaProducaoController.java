package eapli.base.linhaproducaomanagement.application;

import eapli.base.linhaproducaomanagement.domain.LinhaProducao;

public class ListarLinhaProducaoController {

    private final ListarLinhaProducaoService svc = new ListarLinhaProducaoService();

    public Iterable<LinhaProducao> listarLinhasProducao() {
        return this.svc.allLinhasProducao();
    }
}
