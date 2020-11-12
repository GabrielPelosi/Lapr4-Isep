package eapli.base.mensagemmanagement.repositories;

import eapli.base.mensagemmanagement.domain.Estorno;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.domain.repositories.DomainRepository;

public interface EstornoRepository extends DomainRepository<Long, Estorno> {

    Iterable<Estorno> findEstornosOrdem(CodigoOrdem ordem);
}
