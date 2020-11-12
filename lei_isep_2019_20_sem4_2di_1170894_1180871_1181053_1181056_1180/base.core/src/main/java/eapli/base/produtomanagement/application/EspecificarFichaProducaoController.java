package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.materiaprimamanagement.application.ListCategoriaService;
import eapli.base.materiaprimamanagement.application.ListMateriasService;
import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.base.produtomanagement.domain.*;
import eapli.base.produtomanagement.repositories.FichaProducaoRepository;

import java.util.List;

public class EspecificarFichaProducaoController {

    private final ListMateriasService svc = new ListMateriasService();
    private final ListProdutoSemFichaService svc2 = new ListProdutoSemFichaService();
    private final FichaProducaoRepository repository = PersistenceContext.repositories().fichasProducao();


    public FichaProducao registerFcichaProducao(final String codInterno, final List<CodigoInternoMateria> sequenciaMateria, final List<Double> sequenciaQuantidade, final List<String> sequenciaUnidade) {

        CodigoFabrico cod = new CodigoFabrico(codInterno);


        FichaProducao newFP = new FichaProducao(cod, sequenciaMateria, sequenciaQuantidade, sequenciaUnidade);

        return newFP;


    }


    public boolean guardarFichaProducao(final String codInterno, final List<CodigoInternoMateria> sequenciaMateria, final List<Double> sequenciaQuantidade, final List<String> sequenciaUnidade) {

        FichaProducao faux = registerFcichaProducao(codInterno, sequenciaMateria, sequenciaQuantidade, sequenciaUnidade);
        FichaProducao fc = this.repository.save(faux);

        if (fc != null) {


            return true;
        }
        return false;

    }

    public Iterable<MateriaPrima> materias() {
        return this.svc.allMaterias();
    }

    public Iterable<Produto> produtos() {
        return this.svc2.produtosSemFicha();
    }
}
