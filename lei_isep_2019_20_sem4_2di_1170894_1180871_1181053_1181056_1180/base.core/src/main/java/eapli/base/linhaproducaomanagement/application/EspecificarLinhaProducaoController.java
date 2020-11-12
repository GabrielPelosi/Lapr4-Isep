package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;

public class EspecificarLinhaProducaoController {

    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhasProducao();

    public LinhaProducao registarLinhaProducao(long idLinhaProducao) {

        IdLinhaProducao id = new IdLinhaProducao(idLinhaProducao);

        LinhaProducao novaLinhaProducao = new LinhaProducao(id);

        return novaLinhaProducao;
    }

    public boolean guardarLinhaProducao(long idLinhaProducao) {
        LinhaProducao linhaProducao = registarLinhaProducao(idLinhaProducao);
        LinhaProducao lp = linhaProducaoRepository.save(linhaProducao);
        return lp != null;
    }
}
