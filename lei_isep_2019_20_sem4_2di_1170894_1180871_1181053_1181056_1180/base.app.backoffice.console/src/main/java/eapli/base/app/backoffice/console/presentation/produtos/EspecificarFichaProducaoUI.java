package eapli.base.app.backoffice.console.presentation.produtos;


import eapli.base.app.backoffice.console.presentation.materias.MateriasPrinter;
import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.base.produtomanagement.application.EspecificarFichaProducaoController;
import eapli.base.produtomanagement.domain.Produto;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.LinkedList;
import java.util.List;

public class EspecificarFichaProducaoUI extends AbstractUI {

    private final EspecificarFichaProducaoController theController = new EspecificarFichaProducaoController();

    @Override
    protected boolean doShow() {
        final Iterable<Produto> produtos = this.theController.produtos();
        final Iterable<MateriaPrima> materias = this.theController.materias();

        final SelectWidget<Produto> selector = new SelectWidget<>("Produtos:", produtos,
                new ProdutosPrinter());


        selector.show();

        final Produto p = selector.selectedElement();

        final String codigo = p.identity().toString();

        List<CodigoInternoMateria> sequenciaMateria = new LinkedList<>();
        List<Double> sequenciaQuantidade= new LinkedList<>();
        List<String> sequenciaUnidade= new LinkedList<>();




        //------------------------------------------Seleção das materias-----------------------------------------------------\\


        SelectWidget<MateriaPrima> selector2 = new SelectWidget<>("Materias-Primas:", materias,
                new MateriasPrinter());
        selector2.show();

        MateriaPrima mp = selector2.selectedElement();
        sequenciaMateria.add(mp.identity());

        double quantidade = Console.readDouble("Quantidade:");
        sequenciaQuantidade.add(quantidade);

        String unidade = Console.readLine("Unidade:");
        sequenciaUnidade.add(unidade);

        selector2 = new SelectWidget<>("Materias-Primas:", materias,
                new MateriasPrinter());
        selector2.show();

        while (selector2.selectedOption() != 0) {
            mp = selector2.selectedElement();
            sequenciaMateria.add(mp.identity());

            quantidade = Console.readDouble("Quantidade:");
            sequenciaQuantidade.add(quantidade);

            unidade = Console.readLine("Unidade:");
            sequenciaUnidade.add(unidade);
            selector2 = new SelectWidget<>("Materias-Primas:", materias,
                    new MateriasPrinter());
            selector2.show();
        }


        try {
            this.theController.guardarFichaProducao(codigo, sequenciaMateria, sequenciaQuantidade, sequenciaUnidade);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter Ficha de Producao which already exists in the database.");
        } catch (final NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public String headline() {
        return "Registar Ficha de Producao";
    }


}
