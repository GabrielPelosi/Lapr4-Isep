package application;

import eapli.base.linhaproducaomanagement.application.ListarLinhaProducaoService;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.mensagemmanagement.domain.Tempo;

import java.util.Calendar;
import java.util.List;

public class ProcessarMensagemController {

    private final ListarLinhaProducaoService svc = new ListarLinhaProducaoService();

    /**
     * Processa todas as mensagens das linhas da lista dada dentro do  intervalo de tempo dado.
     * Se as datas forem <code>null</code>, o filtro é ignorado
     *
     * @param data1          data inicial
     * @param data2          data final
     * @param linhasProducao lista de linhas de producao
     */
    public void processamentoIntervalo(Calendar data1, Calendar data2, List<LinhaProducao> linhasProducao) {
        new Thread(new MainProcessorThread(data1, data2, linhasProducao)).start();
    }

    public void processamentoRecorrente(Calendar dataInicio, Tempo intervalo) {
        new Thread(new MainProcessorThread(dataInicio, intervalo)).start();
    }

    public Iterable<LinhaProducao> linhaProducao() {
        return this.svc.allLinhasProducao();
    }
}
