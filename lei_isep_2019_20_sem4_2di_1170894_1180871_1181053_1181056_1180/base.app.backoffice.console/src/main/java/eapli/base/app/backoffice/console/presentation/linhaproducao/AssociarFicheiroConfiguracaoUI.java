package eapli.base.app.backoffice.console.presentation.linhaproducao;

import eapli.base.linhaproducaomanagement.application.AssociarFicheiroConfiguracaoController;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

public class AssociarFicheiroConfiguracaoUI extends AbstractUI {

    private final AssociarFicheiroConfiguracaoController theController = new AssociarFicheiroConfiguracaoController();


    @Override
    protected boolean doShow() {
        final Iterable<Maquina> maquinas = this.theController.maquinas();

        final String nomeFicheiroConfig = Console.readLine("Nome Ficheiro de Configuracao");

        final SelectWidget<Maquina> selector = new SelectWidget<>("Maquinas:", maquinas, new MaquinasPrinter());
        selector.show();

        final Maquina maquina = selector.selectedElement();

        try {
            this.theController.associarFicheiroConfiguracao(maquina, nomeFicheiroConfig);
        } catch (final IntegrityViolationException e) {
            System.out.println("You tried to enter a Ficheiro that already exists.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Associar um Ficheiro de Configuracao a uma Maquina\n";
    }
}
