package eapli.base.persistence.impl.inmemory;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryMaquinaRepository extends InMemoryDomainRepository<CodigoInternoMaquina, Maquina> implements MaquinaRepository {

    static {
        InMemoryInitializer.init();
    }

}
