package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;

public class ListarLinhaProducaoService {
    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhasProducao();

    public Iterable<LinhaProducao> allLinhasProducao() {
        return this.linhaProducaoRepository.findAll();
    }

    public LinhaProducao save(LinhaProducao linhaProducao) {
        return this.linhaProducaoRepository.save(linhaProducao);
    }
}
