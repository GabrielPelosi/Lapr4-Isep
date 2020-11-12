package eapli.base.persistence.impl.inmemory;


import eapli.base.depositomanagement.domain.CodigoDeposito;
import eapli.base.depositomanagement.domain.Deposito;
import eapli.base.depositomanagement.repositories.DepositoRepository;
import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.FichaProducao;
import eapli.base.produtomanagement.repositories.FichaProducaoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryFichaProducaoRepository extends InMemoryDomainRepository<CodigoFabrico, FichaProducao> implements FichaProducaoRepository {

    static {
        InMemoryInitializer.init();
    }

}