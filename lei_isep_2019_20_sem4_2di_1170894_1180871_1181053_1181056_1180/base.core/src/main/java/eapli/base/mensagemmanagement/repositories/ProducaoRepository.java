package eapli.base.mensagemmanagement.repositories;

import eapli.base.mensagemmanagement.domain.Producao;
import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProducaoRepository extends DomainRepository<Long, Producao> {
    Iterable<Producao> findProducaoOrdem(CodigoFabrico produto, CodigoOrdem ordem);

    Iterable<Producao> findProducoesOrdem(CodigoOrdem ordem);
}
