package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.ConsultarOrdemEstadoController;
import eapli.base.produtomanagement.domain.Ordem;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

public class ConsultarOrdemEstadoUI extends AbstractUI {

    private final ConsultarOrdemEstadoController theController = new ConsultarOrdemEstadoController();

    @Override
    protected boolean doShow() {
        String estado = "";
        System.out.println("Estados Possiveis:\n1: pendente\n2: Em Execucao\n3: Execucao Parada Temporariamente\n4: Concluida\n5: Suspensa");
        int num;
        do {
            num = Console.readInteger("Estado pretendido:");
            if (num == 1) {
                estado = "pendente";
            } else if (num == 2) {
                estado = "Em Execucao";
            } else if (num == 3) {
                estado = "Execucao Parada Temporariamente";
            } else if (num == 4) {
                estado = "Concluida";
            } else if (num == 5) {
                estado = "Suspensa";
            }
        } while (num < 1 || num > 5);

        Iterable<Ordem> ordensEstado = theController.listarOrdemEstado(estado);
        SelectWidget<Ordem> selector;
        do {
            selector = new SelectWidget<>("Escolher uma ordem para consultar detalhes:", ordensEstado,
                    new OrdensPrinter());
            selector.show();
            if (selector.selectedOption() != 0) {
                theController.exibirDetalhes(selector.selectedElement());
            }
        } while (selector.selectedOption() != 0);

        return false;
    }

    @Override
    public String headline() {
        return "Consultar Ordens num determinado estado";
    }
}
