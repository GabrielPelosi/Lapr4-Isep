package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;

public class ListarMaquinaService {
    private final MaquinaRepository maquinaRepo = PersistenceContext.repositories().maquinas();

    public Iterable<Maquina> allMaquinas() {
        return this.maquinaRepo.findAll();
    }
}
