package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.produtomanagement.domain.CodigoEncomenda;
import eapli.base.produtomanagement.domain.Ordem;
import eapli.base.produtomanagement.repositories.OrdemRepository;

public class ConsultarOrdemEncomendaService {

    private final OrdemRepository ordemRepository = PersistenceContext.repositories().ordens();

    public Iterable<Ordem> ordensEncomenda(CodigoEncomenda idEncomenda) {
        return ordemRepository.findEncomenda(idEncomenda.toString());
    }
}
