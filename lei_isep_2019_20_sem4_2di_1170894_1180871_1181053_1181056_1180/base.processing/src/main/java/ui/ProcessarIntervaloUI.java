package ui;

import application.ProcessarMensagemController;
import eapli.base.app.backoffice.console.presentation.linhaproducao.LinhasProducaoPrinter;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProcessarIntervaloUI extends AbstractUI {

    private final ProcessarMensagemController processarController = new ProcessarMensagemController();

    private static void printDate(Calendar data) {
        System.out.printf("%d-%d-%d %d:%d:%d\n", data.get(Calendar.YEAR), data.get(Calendar.MONTH),
                data.get(Calendar.DATE), data.get(Calendar.HOUR_OF_DAY),
                data.get(Calendar.MINUTE), data.get(Calendar.SECOND));
    }

    @Override
    protected boolean doShow() {
        Calendar data1 = Console.readCalendar("Data inicial: (yyyyMMddHHmmss)", "yyyyMMddHHmmss");
        Calendar data2 = Console.readCalendar("Data final: (yyyyMMddHHmmss)", "yyyyMMddHHmmss");

        Iterable<LinhaProducao> linhaProducaoIterable = processarController.linhaProducao();
        List<LinhaProducao> linhasProducaoDisponiveis = new ArrayList<>();
        linhaProducaoIterable.forEach(linhasProducaoDisponiveis::add);

        List<LinhaProducao> linhasProducaoEscolhidas = new ArrayList<>();
        SelectWidget<LinhaProducao> selector;

        do {
            selector = new SelectWidget<>("Linhas de Producao: (opcional)", linhasProducaoDisponiveis,
                    new LinhasProducaoPrinter());
            selector.show();
            LinhaProducao linhaProducao = selector.selectedOption() == 0 ? null : selector.selectedElement();
            if (linhaProducao != null) {
                linhasProducaoEscolhidas.add(linhaProducao);
                linhasProducaoDisponiveis.remove(linhaProducao);
            }
        } while (selector.selectedOption() != 0);

        processarController.processamentoIntervalo(data1, data2, linhasProducaoEscolhidas);
        return false;
    }

    @Override
    public String headline() {
        return "Processar Mensagens com intervalo";
    }
}
