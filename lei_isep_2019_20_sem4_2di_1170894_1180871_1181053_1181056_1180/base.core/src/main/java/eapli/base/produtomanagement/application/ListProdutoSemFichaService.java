package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.produtomanagement.domain.FichaProducao;
import eapli.base.produtomanagement.domain.Produto;
import eapli.base.produtomanagement.repositories.FichaProducaoRepository;
import eapli.base.produtomanagement.repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.Iterator;

public class ListProdutoSemFichaService {

    private final ProdutoRepository prodRepo = PersistenceContext.repositories().produtos();
    private final FichaProducaoRepository fichaRep = PersistenceContext.repositories().fichasProducao();

    public Iterable<Produto> produtosSemFicha() {

        Iterable<Produto> itProd = prodRepo.findAll();

        Iterable<FichaProducao> itFicha = fichaRep.findAll();
        ArrayList<Produto> aux = new ArrayList<>();

        boolean verify = false;


        for (Produto p : itProd) {

            for (FichaProducao fp : itFicha) {


                if (p.identity().equals(fp.identity())) {
                    verify = true;

                }
            }


            if (verify == false) {
                aux.add(p)
                ;
            }
            verify = false;


        }
        Iterator<Produto> iterator = aux.iterator();
        return new Iterable<Produto>() {
            @Override
            public Iterator<Produto> iterator() {
                return iterator;
            }
        };

    }

}
