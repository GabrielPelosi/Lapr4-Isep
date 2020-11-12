package eapli.base.infrastructure.bootstrapers.produtobootstrappers;

import eapli.base.produtomanagement.application.DefinirProdutoController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class ProdutoBootstrapper implements Action {

    final DefinirProdutoController controller = new DefinirProdutoController();

    @Override
    public boolean execute() {
        try {
            register("7000030", "320090110083", "Calcas",
                    "Calcas de bombazine", "UN", "TE-RO");
            register("7000031", "320090111083", "Calcas",
                    "Calcas de ganga", "UN", "TE-RO");
            register("7000032", "320090111183", "Calcas",
                    "Calcas de malha", "UN", "TE-RO");
            register("8100000", "420090111083", "Forma",
                    "Forma para Bolachas", "UN", "ME-UT");
            register("8100001", "420091111083", "Forma",
                    "Forma para bolos", "UN", "ME-UT");
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao do Produto");
        }

        return true;
    }

    private void register(final String codigoFabrico, final String codigoComercial, final String descricaoBreve,
                          final String descricaoCompleta, final String unidade, final String categoria){
        controller.guardarProduto(codigoFabrico, codigoComercial, descricaoBreve, descricaoCompleta, unidade, categoria);
    }
}
