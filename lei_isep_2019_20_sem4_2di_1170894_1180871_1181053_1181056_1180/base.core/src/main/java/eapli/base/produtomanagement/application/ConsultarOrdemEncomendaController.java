package eapli.base.produtomanagement.application;

import eapli.base.produtomanagement.domain.CodigoEncomenda;
import eapli.base.produtomanagement.domain.Ordem;

public class ConsultarOrdemEncomendaController {

    private final ConsultarOrdemEncomendaService svc = new ConsultarOrdemEncomendaService();

    public Iterable<Ordem> listarOrdemEncomenda(CodigoEncomenda idEncomenda) {
        return this.svc.ordensEncomenda(idEncomenda);
    }
}
