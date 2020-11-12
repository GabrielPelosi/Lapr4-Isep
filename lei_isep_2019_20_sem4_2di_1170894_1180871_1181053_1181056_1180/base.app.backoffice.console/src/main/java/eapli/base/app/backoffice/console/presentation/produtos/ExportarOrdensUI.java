package eapli.base.app.backoffice.console.presentation.produtos;

import eapli.base.produtomanagement.application.ExportarOrdemController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class ExportarOrdensUI extends AbstractUI {

    private final ExportarOrdemController theController = new ExportarOrdemController();

    @Override
    protected boolean doShow() {

        String filtros = "";
        String dataInicio = "";
        String dataFim = "";

        do {
            filtros = Console.readLine("Deseja aplicar filtros temporais? (Y/N)");
        } while (!(filtros.equalsIgnoreCase("Y") || filtros.equalsIgnoreCase("N")));

        if (filtros.equalsIgnoreCase("Y")) {
            dataInicio = Console.readLine("Insira a data inicial: (YYYY-MM-DD)");
            while (!isDataCorrect(dataInicio)) {
                System.out.println("Formato de data incorreto!");
                dataInicio = Console.readLine("Insira a data inicial: (YYYY-MM-DD)");
            }

            dataFim = Console.readLine("Insira a data final: (YYYY-MM-DD)");
            while (!isDataCorrect(dataFim)) {
                System.out.println("Formato de data incorreto!");
                dataFim = Console.readLine("Insira a data final: (YYYY-MM-DD)");
            }

            while (dataFim.compareTo(dataInicio) < 0) {
                System.out.println("Data final nao pode ser anterior a data inicial!");
                dataFim = Console.readLine("Insira a data final: (YYYY-MM-DD)");
                while (!isDataCorrect(dataFim)) {
                    System.out.println("Formato de data incorreto!");
                    dataFim = Console.readLine("Insira a data final: (YYYY-MM-DD)");
                }
            }
        }


        try {
            this.theController.exportarOrdem(changeFormatData(dataInicio), changeFormatData(dataFim));
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro na exportação.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Exportar Ordens";
    }

    private boolean isDataCorrect(String data) {
        String[] datas = data.split("-");
        boolean correctData = datas.length == 3
                && datas[0].length() == 4
                && datas[1].length() == 2
                && datas[2].length() == 2
                && datas[1].compareTo("12") <= 0
                && datas[2].compareTo("31") <= 0;
        return correctData;
    }

    //FIX ME
    private String changeFormatData(String data) {
        String nula = "";
        if(data.length()>0) {
            String[] datas = data.split("-");
            return datas[0] + datas[1] + datas[2];
        }
        return nula;
    }
}
