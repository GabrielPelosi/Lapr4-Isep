package eapli.base.mensagemmanagement.application;

import eapli.base.linhaproducaomanagement.application.ListarLinhaProducaoService;
import eapli.base.linhaproducaomanagement.application.ListarMaquinaService;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.base.mensagemmanagement.domain.Notificacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConsultarNotificacoesArquivadasController {

    private final ListarLinhaProducaoService svcLinha = new ListarLinhaProducaoService();
    private final ListarMaquinaService svcMaquina = new ListarMaquinaService();
    private final ConsultarNotificacoesService svcNot = new ConsultarNotificacoesService();

    public Iterable<Notificacao> notificacoesArquivadas(Notificacao.Tipo tipo, LinhaProducao linha, CodigoInternoMaquina maquina,
                                                        Calendar data1, Calendar data2) {
        if (linha != null) {
            Iterable<CodigoInternoMaquina> maquinas = linha.maquinas();
            List<Notificacao> notificacoes = new ArrayList<>();

            for (CodigoInternoMaquina codigo : maquinas) {
                Iterable<Notificacao> iterableNotificacao =
                        this.svcNot.allNotificacoesWithFiltros(Notificacao.Estado.ARQUIVADA, tipo, codigo, data1, data2);

                iterableNotificacao.forEach(notificacoes::add);
            }
            return notificacoes;
        }
        return this.svcNot.allNotificacoesWithFiltros(Notificacao.Estado.ARQUIVADA, tipo, maquina, data1, data2);
    }

    public Iterable<LinhaProducao> linhasProducao() {
        return this.svcLinha.allLinhasProducao();
    }

    public Iterable<Maquina> maquinas(){
        return this.svcMaquina.allMaquinas();
    }

}
