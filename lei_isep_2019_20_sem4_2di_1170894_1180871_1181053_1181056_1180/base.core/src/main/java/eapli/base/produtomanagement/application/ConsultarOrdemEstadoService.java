package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.mensagemmanagement.domain.*;
import eapli.base.mensagemmanagement.repositories.*;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.base.produtomanagement.domain.Ordem;
import eapli.base.produtomanagement.repositories.OrdemRepository;

public class ConsultarOrdemEstadoService {

    private final OrdemRepository ordemRepository = PersistenceContext.repositories().ordens();
    private final ConsumoRepository consumoRepository = PersistenceContext.repositories().consumos();
    private final ProducaoRepository producaoRepository = PersistenceContext.repositories().producoes();
    private final EstornoRepository estornoRepository = PersistenceContext.repositories().estornos();
    private final DesvioRepository desvioRepository = PersistenceContext.repositories().desvios();
    private final TempoProducaoRepository tempoProducaoRepository = PersistenceContext.repositories().temposProducao();

    public Iterable<Ordem> ordensEstado(String estado) {
        return ordemRepository.findByEstado(estado);
    }

    public Iterable<Consumo> consumosOrdem(CodigoOrdem ordem) {
        return consumoRepository.findConsumosOrdem(ordem);
    }

    public Iterable<Producao> producoesOrdem(CodigoOrdem ordem) {
        return producaoRepository.findProducoesOrdem(ordem);
    }

    public Iterable<Estorno> estornosOrdem(CodigoOrdem ordem) {
        return estornoRepository.findEstornosOrdem(ordem);
    }

    public Desvio desvioOfIdentity(CodigoOrdem ordem) {
        return desvioRepository.ofIdentity(ordem).orElse(null);
    }

    public TempoProducao tempoProducaoOfIdentity(CodigoOrdem ordem) {
        return tempoProducaoRepository.ofIdentity(ordem).orElse(null);
    }
}
