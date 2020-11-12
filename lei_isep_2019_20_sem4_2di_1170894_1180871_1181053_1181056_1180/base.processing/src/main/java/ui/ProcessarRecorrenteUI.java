package ui;

import application.ProcessarMensagemController;
import eapli.base.app.backoffice.console.presentation.linhaproducao.LinhasProducaoPrinter;
import eapli.base.linhaproducaomanagement.application.AlterarEstadoController;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.mensagemmanagement.domain.Tempo;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProcessarRecorrenteUI extends AbstractUI {

    final ProcessarMensagemController processarController = new ProcessarMensagemController();
    final AlterarEstadoController estadoController = new AlterarEstadoController();

    @Override
    protected boolean doShow() {
        Calendar dataInicio = Console.readCalendar("Insira a data de inicio de processamento: (yyyyMMddHHmmss)", "yyyyMMddHHmmss");
        Calendar tempoIntervalo = Console.readCalendar("Insira o periodo de espera entre cada sessao de processamento (HHmmss)", "HHmmss");
        Tempo tempo = new Tempo().withHora(tempoIntervalo.get(Calendar.HOUR_OF_DAY)).
                withMinuto(tempoIntervalo.get(Calendar.MINUTE)).withSegundo(tempoIntervalo.get(Calendar.SECOND));

        Iterable<LinhaProducao> linhaProducaoIterable = processarController.linhaProducao();
        List<LinhaProducao> linhasProducaoDisponiveis = new ArrayList<>();
        linhaProducaoIterable.forEach(linhasProducaoDisponiveis::add);

        SelectWidget<LinhaProducao> selector;

        do {
            selector = new SelectWidget<>("Linhas de Producao: (opcional)", linhasProducaoDisponiveis,
                    new LinhasProducaoPrinter());
            selector.show();
            LinhaProducao linhaProducao = selector.selectedOption() == 0 ? null : selector.selectedElement();
            if (linhaProducao != null) {
                estadoController.alterarEstado(linhaProducao);
            }
        } while (selector.selectedOption() != 0);

        processarController.processamentoRecorrente(dataInicio, tempo);
        return false;
    }

    @Override
    public String headline() {
        return "Processar Mensagens recorrentemente";
    }
}
