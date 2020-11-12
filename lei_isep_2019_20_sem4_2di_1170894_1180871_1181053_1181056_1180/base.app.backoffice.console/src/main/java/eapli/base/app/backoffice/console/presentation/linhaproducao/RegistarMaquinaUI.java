package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.RegistarMaquinaController;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.ArrayList;
import java.util.List;

public class RegistarMaquinaUI extends AbstractUI {

    private final RegistarMaquinaController theController = new RegistarMaquinaController();

    @Override
    protected boolean doShow() {
        final Iterable<LinhaProducao> linhasProducao = this.theController.linhaProducao();

        final String codigo = Console.readLine("Codigo");
        final int numSerie = Console.readInteger("NumeroSerie");
        final String descricao = Console.readLine("Descricao");
        final int ano = Console.readInteger("ano");
        final int mes = Console.readInteger("mes");
        final int dia = Console.readInteger("dia");
        final String marca = Console.readLine("marca");
        final String modelo = Console.readLine("modelo");
        final long idProtocolo = Console.readLong("idProtocolo");

        final SelectWidget<LinhaProducao> selectorLinha = new SelectWidget<>("Linhas de Producao:", linhasProducao,
                new LinhasProducaoPrinter());
        selectorLinha.show();
        if (selectorLinha.selectedOption() <= 0) {
            System.out.println("Add Maquina cancelado");
            return false;
        }
        final LinhaProducao lp = selectorLinha.selectedElement();


        final Iterable<CodigoInternoMaquina> maquinas = maquinasSelectorBuilder(lp);


        final SelectWidget<CodigoInternoMaquina> selectorMaquina = new SelectWidget<>("Posicao na Linha de Producao:", maquinas,
                new CodigoMaquinaPrinter());
        selectorMaquina.show();
        final int posicao = selectorMaquina.selectedOption() - 1;

        if (posicao < 0) {
            System.out.println("Add Maquina cancelado");
            return false;
        }

        try {
            this.theController.guardarMaquina(codigo, numSerie, descricao, ano, mes, dia, marca, modelo, lp, posicao, idProtocolo);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a Maquina which already exists in the database.");
        } catch (final IndexOutOfBoundsException e) {
            System.out.println("You tried to enter an invalid position.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Registar Maquina";
    }

    private Iterable<CodigoInternoMaquina> maquinasSelectorBuilder(LinhaProducao linhaProducao) {
        List<CodigoInternoMaquina> maquinas = new ArrayList<>();
        maquinas.addAll(linhaProducao.maquinas());
        maquinas.add(CodigoInternoMaquina.valueOf("Fim da Linha"));
        return maquinas;
    }

}
