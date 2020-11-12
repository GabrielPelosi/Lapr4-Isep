package eapli.base.infrastructure.bootstrapers.produtobootstrappers;

import eapli.base.produtomanagement.application.DefenirOrdemController;
import eapli.base.produtomanagement.domain.*;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.util.LinkedHashSet;
import java.util.Set;

public class OrdemBootstrapper implements Action  {

    final DefenirOrdemController theController = new DefenirOrdemController();

    @Override
    public boolean execute() {
        try {
            Set<CodigoEncomenda> encomendas1 = new LinkedHashSet<>();
            CodigoEncomenda codE1 = CodigoEncomenda.valueOf("EC2001/23456");
            encomendas1.add(codE1);
            CodigoEncomenda codE2 = new CodigoEncomenda("EC2002/12345");
            encomendas1.add(codE2);
            Produto produto1 = new Produto(new CodigoFabrico("8100001"), new CodigoComercial("420091111083"),
                    new DescricaoBreveProduto("Forma"),new DescricaoCompletaProduto("Forma para bolos"),
                    new UnidadeProduto("UN"), new CategoriaProduto("ME-UT"));
            register("OR20192", 2020, 05, 02, 2020, 05, 15,
                        produto1, 150, "UN", encomendas1, "pendente");


            Set<CodigoEncomenda> encomendas2 = new LinkedHashSet<>();
            CodigoEncomenda codE3 = new CodigoEncomenda("EC2007/34567");
            encomendas2.add(codE3);
            Produto produto2 = new Produto(new CodigoFabrico("7000030"), new CodigoComercial("320090110083"),
                    new DescricaoBreveProduto("Calcas"),new DescricaoCompletaProduto("Calcas de bombazine"),
                    new UnidadeProduto("UN"), new CategoriaProduto("TE-RO"));
            register("OR20393", 2020, 05, 01, 2020, 05, 25, produto2,
                    20,"UN", encomendas2, "pendente");


            Set<CodigoEncomenda> encomendas3 = new LinkedHashSet<>();
            CodigoEncomenda codE4 = new CodigoEncomenda("EC2003/45678");
            encomendas3.add(codE4);
            Produto produto3 = new Produto(new CodigoFabrico("7000031"), new CodigoComercial("320090111083"),
                    new DescricaoBreveProduto("Calcas"),new DescricaoCompletaProduto("Calcas de ganga"),
                    new UnidadeProduto("UN"), new CategoriaProduto("TE-RO"));
            register("OR20393", 2020, 05, 01, 2020, 05, 25, produto3,
                    50,"UN", encomendas3, "pendente");


            Set<CodigoEncomenda> encomendas4 = new LinkedHashSet<>();
            CodigoEncomenda codE5 = new CodigoEncomenda("EC2004/67890");
            encomendas4.add(codE5);
            CodigoEncomenda codE6 = new CodigoEncomenda("EC2005/45892");
            encomendas4.add(codE6);
            Produto produto4 = new Produto(new CodigoFabrico("8100000"), new CodigoComercial("420090111083"),
                    new DescricaoBreveProduto("Forma"),new DescricaoCompletaProduto("Forma para Bolachas"),
                    new UnidadeProduto("UN"), new CategoriaProduto("ME-UT"));
            register("OR20193",2020, 05, 05, 2020, 10, 30, produto4,
                    100,"UN", encomendas4, "pendente");

        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao do Produto");
        }

        return true;
    }

    private void register(final String codOrdem, final int anoEm, final int mesEm, final int diaEm,
                          final int ano, final int mes, final int dia, final Produto produto, final int quantidade,
                          final String unidade, final Set<CodigoEncomenda> encomendas, final String estado){
        theController.guardarOrdem(codOrdem, anoEm, mesEm, diaEm, ano, mes, dia, produto, quantidade,
        unidade, encomendas, estado);
    }
}
