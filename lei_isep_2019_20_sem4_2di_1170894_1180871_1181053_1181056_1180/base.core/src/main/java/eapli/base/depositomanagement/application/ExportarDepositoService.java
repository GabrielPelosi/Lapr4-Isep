package eapli.base.depositomanagement.application;

import eapli.base.depositomanagement.domain.Deposito;
import eapli.base.depositomanagement.repositories.DepositoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

public class ExportarDepositoService {

    private final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();

    public Iterable<Deposito> allDeposits(){
        return depositoRepository.findAll();
    }
}
