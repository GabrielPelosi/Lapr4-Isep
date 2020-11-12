package eapli.base.mensagemmanagement.repositories;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.mensagemmanagement.domain.Consumo;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.domain.repositories.DomainRepository;

public interface ConsumoRepository extends DomainRepository<Long, Consumo> {

    Iterable<Consumo> findConsumosOrdemDeUmaMateria(CodigoInternoMateria materia, CodigoOrdem ordem);

    Iterable<Consumo> findConsumosOrdem(CodigoOrdem ordem);
}
