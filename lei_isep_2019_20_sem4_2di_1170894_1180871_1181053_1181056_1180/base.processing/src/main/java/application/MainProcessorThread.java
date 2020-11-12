package application;

import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.mensagemmanagement.domain.Mensagem;
import eapli.base.mensagemmanagement.domain.Tempo;

import java.util.*;

public class MainProcessorThread implements Runnable {

    /**
     * Se <code>true</code> foi escolhido o 1o modo. Se <code>false</code> foi escolhido o 2o modo.
     */
    private final boolean modo;

    // 1o modo
    private Calendar data1 = null;
    private Calendar data2 = null;
    private List<LinhaProducao> linhasProducao = new ArrayList<>();

    // 2o modo
    private Calendar dataInicio = null;
    private Tempo intervalo = null;

    // 1o modo
    public MainProcessorThread(Calendar data1, Calendar data2, List<LinhaProducao> linhasProducao) {
        this.data1 = data1;
        this.data2 = data2;
        this.linhasProducao = linhasProducao;
        this.modo = true;
    }

    // 2o modo
    public MainProcessorThread(Calendar dataInicio, Tempo intervalo) {
        this.dataInicio = dataInicio;
        this.intervalo = intervalo;
        this.modo = false;
    }

    public void processar() {
        // Se nao tiver sido escolhida nenhuma linha faz se para todas (1o modo apenas)
        if (linhasProducao.isEmpty() && modo) {
            linhasProducao = new ArrayList<>();
            Iterable<LinhaProducao> linhaProducaoIterable = ProcessarMensagemSingleton.getInstance().linhasProducao();
            for (LinhaProducao lp : linhaProducaoIterable)
                linhasProducao.add(lp);
        }

        // Por cada linha na lista criar uma Thread de Processamento
        for (LinhaProducao linha : linhasProducao) {

            // Mensagens de uma thread
            Iterable<Mensagem> mensagensLinhaIt = ProcessarMensagemSingleton.getInstance().findMensagemByLinhaWithData(
                    data1, data2, linha.identity());
            List<Mensagem> mensagemLinhaList = new ArrayList<>();
            mensagensLinhaIt.forEach(mensagemLinhaList::add);

            // Criar Thread
            new Thread(new ProcessarMensagemThread(mensagemLinhaList, linha.identity())).start();

        }
    }

    public void recorrente() {

        // Se ainda nao for hora de processar
        if (Calendar.getInstance().before(dataInicio)) {
            long timeoutMillis = dataInicio.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
            try {
                System.out.printf("Waiting %s...\n", new Tempo().withMillis(timeoutMillis).toString());
                ProcessarMensagemSingleton.getInstance().waitTimeOutMillis(timeoutMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wait completed!");
        }

        // Lista com as linhas de producao ativas
        Iterable<LinhaProducao> linhasProducaoAtivasIterable = ProcessarMensagemSingleton.getInstance().linhasProducaoAtivas();
        linhasProducao = new ArrayList<>();
        linhasProducaoAtivasIterable.forEach(linhasProducao::add);

        // Se houver linhas de producao ativas
        if (!linhasProducao.isEmpty()) {
            processar();
        }

        //System.out.println("Nao ha linhas de producao ativas. Processamento recorrente parado.");
    }

    @Override
    public void run() {
        // 1o modo
        if (modo) {
            processar();

            // 2o modo
        } else {
            Timer timer = new Timer();
            try {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        recorrente();
                    }
                }, 0, intervalo.toMillis());
            } catch (IllegalArgumentException e) {
                System.out.println("Intervalo nao pode ser negativo ou nulo! Processamento recorrente cancelado.");
            }
        }
    }
}
