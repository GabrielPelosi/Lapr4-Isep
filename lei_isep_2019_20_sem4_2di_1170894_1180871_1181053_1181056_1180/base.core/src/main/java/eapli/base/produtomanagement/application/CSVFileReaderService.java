package eapli.base.produtomanagement.application;

import eapli.base.produtomanagement.domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class CSVFileReaderService {

    DefinirProdutoController theController = new DefinirProdutoController();
    DefenirOrdemController theController2 = new DefenirOrdemController();
    ListarProdutosComFichaController theController3 = new ListarProdutosComFichaController();

    public void getAllDataFromCSVProdutos(String fileName) {
        File f = new File(fileName);
        try {
            Scanner inputStream = new Scanner(f);
            inputStream.nextLine(); //ignorar primeira linha
            while (inputStream.hasNext()) {
                String data = inputStream.nextLine(); //linha completa
                System.out.println(data + "***");
                String[] values = data.split(";");
                theController.guardarProduto(values[0], values[1], values[2], values[3], values[4], values[5]);
            }
            inputStream.close();
            System.out.println("Import finished\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Iterable<Produto> listProd() {
        return theController3.listProdutoComFicha();
    }

    public void getAllDataFromCSVOrdens(String fileName) {
        File f = new File(fileName);
        Set<CodigoEncomenda> enc = new LinkedHashSet<>();
        try {
            int count = 0, i;
            Scanner inputStream = new Scanner(f);
            inputStream.nextLine(); //ignorar primeira linha
            while (inputStream.hasNext()) {
                String data = inputStream.nextLine(); //linha completa
                System.out.println(data + "***");
                String[] values = data.split(";");
                String ano = values[1].substring(0, 4);
                int anoI = Integer.parseInt(ano);
                String mes = values[1].substring(5, 6);
                int mesI = Integer.parseInt(mes);
                String dia = values[1].substring(7, 8);
                int diaI = Integer.parseInt(dia);
                String ano1 = values[1].substring(0, 4);
                int ano1I = Integer.parseInt(ano1);
                String mes1 = values[1].substring(5, 6);
                int mes1I = Integer.parseInt(mes1);
                String dia1 = values[1].substring(7, 8);
                int dia1I = Integer.parseInt(dia1);
                CodigoFabrico cF = new CodigoFabrico(values[3]);
                int quantidade = Integer.parseInt(values[4]);
                String encomendas = values[6];
                String[] encSeperadas = encomendas.split(",");
                int numElem = encSeperadas.length;
                for (i = 0; i < numElem; i++) {
                    CodigoEncomenda codE = new CodigoEncomenda(encSeperadas[i]);
                    enc.add(codE);
                }
                for (Produto pro : listProd()) {
                    if (pro.identity().equals(cF)) {
                        count++;
                        String s = "UN";
                        String c1 = "1232434556";
                        CodigoComercial cc = new CodigoComercial(c1);
                        DescricaoBreveProduto d = new DescricaoBreveProduto("aaa");
                        DescricaoCompletaProduto dc = new DescricaoCompletaProduto("aaaaaaa");
                        UnidadeProduto u = new UnidadeProduto(s);
                        CategoriaProduto c = new CategoriaProduto(c1);
                        Produto p1 = new Produto(cF, cc, d, dc, u, c);
                        theController2.guardarOrdem(values[0], anoI, mesI, diaI, ano1I, mes1I, dia1I, p1, quantidade, values[5], enc,"pendente");
                        break;
                    }

                }

                if (count == 0) {
                    System.out.println("Produto da ordem não existe!");
                }
                count = 0;
            }
            inputStream.close();
            System.out.println("Import finished\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
