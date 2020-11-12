package eapli.base.produtomanagement.application;

import eapli.base.mensagemmanagement.domain.*;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.base.produtomanagement.domain.Ordem;

public class ConsultarOrdemEstadoController {

    private final ConsultarOrdemEstadoService svc = new ConsultarOrdemEstadoService();

    public void exibirDetalhes(Ordem ordem) {
        String codigo = ordem.identity().toString();
        String estado = ordem.est();
        String dataEmissao = ordem.dataEm();
        String dataPrevista = ordem.dataPre();
        String produto = ordem.prod().identity().toString();
        String quantidade = Integer.toString(ordem.quant());
        String unidade = ordem.unid().toString();
        String encomendas = ordem.sequenciaEncomendaToString();

        System.out.printf("\n%1$-15s%2$-15s%3$-15s%4$-15s%5$-15s%6$-15s%7$-15s%8$-15s\n",
                "ORDEM", "ESTADO", "EMISSAO", "PREVISTA", "PRODUTO", "QUANTIDADE", "UNIDADE", "ENCOMENDAS");
        System.out.printf("%1$-15s%2$-15s%3$-15s%4$-15s%5$-15s%6$-15s%7$-15s%8$-15s\n\n",
                codigo, estado, dataEmissao, dataPrevista, produto, quantidade, unidade, encomendas);

        if (estado.equals("Concluida")) {
            ordemConcluida(ordem.identity());
        }
    }

    private void ordemConcluida(CodigoOrdem ordem) {
        // CONSUMOS
        Iterable<Consumo> consumos = this.svc.consumosOrdem(ordem);
        System.out.println("\nCONSUMOS ==================================\n");
        System.out.printf("%1$-15s%2$-15s%3$-15s%4$-15s\n", "ID", "ORDEM", "MATERIA", "QUANTIDADE");
        for (Consumo c : consumos) {
            System.out.println(c.consumoString());
        }
        // PRODUCOES
        Iterable<Producao> producoes = this.svc.producoesOrdem(ordem);
        System.out.println("\nPRODUCOES =================================\n");
        System.out.printf("%1$-15s%2$-15s%3$-15s%4$-15s\n", "ID", "ORDEM", "PRODUTO", "QUANTIDADE");
        for (Producao p : producoes) {
            System.out.println(p.producaoString());
        }
        // ESTORNOS
        Iterable<Estorno> estornos = this.svc.estornosOrdem(ordem);
        System.out.println("\nESTORNOS ==================================\n");
        System.out.printf("%1$-15s%2$-15s%3$-15s%4$-15s\n", "ID", "ORDEM", "MATERIA", "QUANTIDADE");
        for (Estorno e : estornos) {
            System.out.println(e.estornoString());
        }
        // DESVIOS
        Desvio desvio = this.svc.desvioOfIdentity(ordem);
        System.out.println("\nDESVIO ====================================\n");
        if (desvio != null) {
            System.out.println(desvio.desvioString());
        }
        // TEMPOS DE PRODUCAO
        TempoProducao tempoProducao = this.svc.tempoProducaoOfIdentity(ordem);
        System.out.println("\nTEMPO DE PRODUCAO =========================\n");
        if (tempoProducao != null) {
            System.out.println(tempoProducao.tempoProducaoString());
        }
    }

    public Iterable<Ordem> listarOrdemEstado(String estado) {
        return this.svc.ordensEstado(estado);
    }
}
