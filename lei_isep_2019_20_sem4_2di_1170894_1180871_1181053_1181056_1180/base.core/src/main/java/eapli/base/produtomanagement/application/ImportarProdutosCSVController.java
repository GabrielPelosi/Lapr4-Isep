package eapli.base.produtomanagement.application;

import eapli.base.materiaprimamanagement.domain.Categoria;

public class ImportarProdutosCSVController {

    private final CSVFileReaderService svc = new CSVFileReaderService();

    public void CSV(String fileName){
        svc.getAllDataFromCSVProdutos(fileName);
    }

}
