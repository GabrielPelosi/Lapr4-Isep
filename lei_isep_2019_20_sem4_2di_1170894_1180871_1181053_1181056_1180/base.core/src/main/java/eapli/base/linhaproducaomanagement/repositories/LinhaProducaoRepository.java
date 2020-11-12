package eapli.base.linhaproducaomanagement.repositories;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.framework.domain.repositories.DomainRepository;

public interface LinhaProducaoRepository extends DomainRepository<IdLinhaProducao, LinhaProducao> {

    Iterable<LinhaProducao> linhasAtivas();

    LinhaProducao linhaWithMaquina(CodigoInternoMaquina maquina);
}
