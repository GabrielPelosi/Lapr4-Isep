package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.app.backoffice.console.presentation.linhaproducao.LinhasProducaoPrinter;
import eapli.base.mensagemmanagement.application.ConsultarNotificacoesAtivasController;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultarNotificacoesAtivasUI extends AbstractListUI<Notificacao> {

    private final ConsultarNotificacoesAtivasController theController = new ConsultarNotificacoesAtivasController();

    @Override
    protected Iterable<Notificacao> elements() {
        final String tipoString = "Tipo de erro";
        final String linhaString = "Linha de Producao";
        ArrayList<String> filtrosList = new ArrayList<>();
        filtrosList.add(tipoString);
        filtrosList.add(linhaString);

        Notificacao.Tipo tipo = null;
        LinhaProducao linha = null;

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
                    if (linha != null)
                        filtrosList.remove(op);
                    break;
            }
            selector = new SelectWidget<>("Escolher filtros:", filtrosList, new NotificacaoFiltrosPrinter());
            selector.show();
        }

        return theController.notificacoesAtivas(tipo, linha);
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
        return "NOTIFICACOES ATIVAS";
    }

    @Override
    protected String emptyMessage() {
        return "no data";
    }

    @Override
    public String headline() {
        return "Listar notificacoes ativas";
    }

    private Notificacao.Tipo selectTipo() {
        List<Notificacao.Tipo> notificacoes = Arrays.asList(Notificacao.Tipo.values());

        final SelectWidget<Notificacao.Tipo> selector = new SelectWidget<>("Tipos de erro:", notificacoes,
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
}
