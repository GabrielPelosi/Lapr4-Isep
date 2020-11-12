package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;

public class AlterarEstadoController {

    private final ListarLinhaProducaoService svc = new ListarLinhaProducaoService();
    private final LinhaProducaoRepository linhaRepo = PersistenceContext.repositories().linhasProducao();

    public void alterarEstado(LinhaProducao linha) {
        if (linha.estado()) {
            linha.desativar();
        } else {
            linha.ativar();
        }
        linhaRepo.save(linha);
    }

    public void desativarTodasLinhas() {
        Iterable<LinhaProducao> linhas = linhaProducao();
        for (LinhaProducao l : linhas) {
            l.desativar();
            linhaRepo.save(l);
        }
    }

    public Iterable<LinhaProducao> linhaProducao() {
        return this.svc.allLinhasProducao();
    }
}
