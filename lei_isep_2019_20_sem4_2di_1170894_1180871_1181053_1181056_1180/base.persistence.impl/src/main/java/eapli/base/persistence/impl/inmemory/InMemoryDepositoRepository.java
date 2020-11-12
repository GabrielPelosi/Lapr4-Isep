package eapli.base.persistence.impl.inmemory;

import eapli.base.depositomanagement.domain.CodigoDeposito;
import eapli.base.depositomanagement.domain.Deposito;
import eapli.base.depositomanagement.repositories.DepositoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryDepositoRepository extends InMemoryDomainRepository<CodigoDeposito, Deposito> implements DepositoRepository {

    static {
        InMemoryInitializer.init();
    }
}
