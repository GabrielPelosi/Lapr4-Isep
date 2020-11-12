package eapli.base.mensagemmanagement.application;

import eapli.base.linhaproducaomanagement.application.ListarLinhaProducaoService;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.mensagemmanagement.domain.Notificacao;

import java.util.ArrayList;
import java.util.List;

public class ConsultarNotificacoesAtivasController {

    private final ListarLinhaProducaoService svcLinha = new ListarLinhaProducaoService();
    private final ConsultarNotificacoesService svcNot = new ConsultarNotificacoesService();

    public Iterable<Notificacao> notificacoesAtivas(Notificacao.Tipo tipo, LinhaProducao linha) {
        if (linha != null) {
            Iterable<CodigoInternoMaquina> maquinas = linha.maquinas();
            List<Notificacao> notificacoes = new ArrayList<>();

            for (CodigoInternoMaquina codigo : maquinas) {
                Iterable<Notificacao> iterableNotificacao =
                        this.svcNot.allNotificacoesWithFiltros(Notificacao.Estado.ATIVA, tipo, codigo, null, null);

                iterableNotificacao.forEach(notificacoes::add);
            }
            return notificacoes;
        }
        return this.svcNot.allNotificacoesWithFiltros(Notificacao.Estado.ATIVA, tipo, null, null, null);
    }

    public Iterable<LinhaProducao> linhasProducao() {
        return this.svcLinha.allLinhasProducao();
    }
}
