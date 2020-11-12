package eapli.base.persistence.impl.inmemory;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryLinhaProducaoRepository extends InMemoryDomainRepository<IdLinhaProducao, LinhaProducao> implements LinhaProducaoRepository {
    static{
        InMemoryInitializer.init();
    }

    public Iterable<LinhaProducao> linhasAtivas() {
        return null;
    }

    public LinhaProducao linhaWithMaquina(CodigoInternoMaquina maquina) {
        return null;
    }
}
