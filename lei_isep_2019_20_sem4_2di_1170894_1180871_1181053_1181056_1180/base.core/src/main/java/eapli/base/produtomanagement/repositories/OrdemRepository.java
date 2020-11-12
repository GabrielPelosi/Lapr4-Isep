package eapli.base.produtomanagement.repositories;

import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.base.produtomanagement.domain.Ordem;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrdemRepository extends DomainRepository<CodigoOrdem, Ordem> {

    Iterable<Ordem> findEncomenda(String idEnc);
    Iterable<Ordem> findByEstado(String estado);
}
