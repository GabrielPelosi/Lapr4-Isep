package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.app.backoffice.console.presentation.linhaproducao.LinhasProducaoPrinter;
import eapli.base.app.backoffice.console.presentation.linhaproducao.MaquinasPrinter;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.base.mensagemmanagement.application.ConsultarNotificacoesArquivadasController;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import eapli.framework.visitor.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ConsultarNotificacoesArquivadasUI extends AbstractListUI<Notificacao> {

    private final ConsultarNotificacoesArquivadasController theController = new ConsultarNotificacoesArquivadasController();


    @Override
    protected Iterable<Notificacao> elements() {
        final String tipoString = "Tipo de erro";
        final String linhaString = "Linha de Producao";
        final String maquinaString = "Maquina";
        final String dataString = "Intervalo de tempo";
        ArrayList<String> filtrosList = new ArrayList<>();
        filtrosList.add(tipoString);
        filtrosList.add(linhaString);
        filtrosList.add(maquinaString);
        filtrosList.add(dataString);

        Notificacao.Tipo tipo = null;
        LinhaProducao linha = null;
        CodigoInternoMaquina maquina = null;
        Calendar data1 = null;
        Calendar data2 = null;

        SelectWidget<String> selector = new SelectWidget<>("Escolher filtros:", filtrosList,
                new NotificacaoFiltrosPrinter());
        selector.show();
        while (selector.selectedOption() != 0) {
            String op = selector.selectedElement();
            switch (op) {
                case tipoString:
                    tipo = selectTipo();
                    if (tipo != null)
                        filtrosList.remove(op);
                    break;

                case linhaString:
                    linha = selectLinha();
                    if (linha != null) {
                        filtrosList.remove(op);
                        filtrosList.remove(maquinaString);
                    }
                    break;

                case maquinaString:
                    maquina = selectMaquina();
                    if (maquina != null) {
                        filtrosList.remove(op);
                        filtrosList.remove(linhaString);
                    }
                    break;

                case dataString:
                    data1 = selectData("inicial");
                    data2 = selectData("final");
                    while (!data1.before(data2)) {
                        System.out.println("Data final nao pode ser futura a data inicial");
                        data2 = selectData("final");
                    }
                    filtrosList.remove(op);
                    break;
            }
            selector = new SelectWidget<>("Escolher filtros:", filtrosList, new NotificacaoFiltrosPrinter());
            selector.show();
        }

        return theController.notificacoesArquivadas(tipo, linha, maquina, data1, data2);
    }

    @Override
    protected Visitor<Notificacao> elementPrinter() {
        return new NotificacaoPrinter();
    }

    @Override
    protected String elementName() {
        return "Notificacao";
    }

    @Override
    protected String listHeader() {
        return "NOTIFICACOES ARQUIVADAS";
    }

    @Override
    protected String emptyMessage() {
        return "no data";
    }

    @Override
    public String headline() {
        return "Listar notificacoes arquivadas";
    }

    private Notificacao.Tipo selectTipo() {
        List<Notificacao.Tipo> notificacoes = Arrays.asList(Notificacao.Tipo.values());

        final SelectWidget<Notificacao.Tipo> selector = new SelectWidget<>("\nTipos de erro:", notificacoes,
                new NotificacaoTipoPrinter());
        selector.show();

        if (selector.selectedOption() == 0)
            return null;
        return selector.selectedElement();
    }

    private LinhaProducao selectLinha() {
        final Iterable<LinhaProducao> linhasProducao = this.theController.linhasProducao();
        final SelectWidget<LinhaProducao> selector = new SelectWidget<>("Linhas de Producao:", linhasProducao,
                new LinhasProducaoPrinter());
        selector.show();
        if (selector.selectedOption() == 0)
            return null;
        return selector.selectedElement();
    }

    private CodigoInternoMaquina selectMaquina() {
        final Iterable<Maquina> maquinas = this.theController.maquinas();
        final SelectWidget<Maquina> selector = new SelectWidget<>("Maquinas:", maquinas, new MaquinasPrinter());
        System.out.printf("   %10s%10s%30s%15s%15s%15s%20s%20s\n",
                "ID", "NR_SERIE", "DESCRICAO", "INSTALACAO", "MARCA", "MODELO", "CONFIG", "PROTOCOLO");
        selector.show();
        if (selector.selectedOption() == 0)
            return null;
        return selector.selectedElement().identity();
    }

    private Calendar selectData(String adjetivo) {
        final String format = "yyyyMMddHHmmssSSS";
        final String prompt = String.format("Data %s: (%s)", adjetivo, format);

        return Console.readCalendar(prompt, format);
    }
}
