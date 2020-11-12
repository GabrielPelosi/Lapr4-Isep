package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.DefinirProdutoController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class DefinirProdutoUI extends AbstractUI {

    private final DefinirProdutoController theController = new DefinirProdutoController();

    @Override
    protected boolean doShow() {

        final String codigoFabrico = Console.readLine("Codigo de Fabrico");
        final String codigoComercial = Console.readLine("Codigo Comercial");
        final String descricaoBreve = Console.readLine("Descricao Breve");
        final String descricaoCompleta = Console.readLine("Descricao Completa");
        final String unidade = Console.readLine("Unidade de medida");
        final String categoria = Console.readLine("Categoria");

        try{
            this.theController.guardarProduto(codigoFabrico,codigoComercial,descricaoBreve,descricaoCompleta,unidade,categoria);
        }catch (final IntegrityViolationException e){
            System.out.println("You tried to enter a Produto that already exists.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Definir Produto";
    }
}
