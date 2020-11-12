package eapli.base.persistence.impl.jpa;

import eapli.base.depositomanagement.domain.CodigoDeposito;
import eapli.base.depositomanagement.domain.Deposito;
import eapli.base.depositomanagement.repositories.DepositoRepository;

public class JpaDepositoRepository extends BasepaRepositoryBase<Deposito, CodigoDeposito, CodigoDeposito> implements DepositoRepository {

    public JpaDepositoRepository(){
        super("code");
    }
}
