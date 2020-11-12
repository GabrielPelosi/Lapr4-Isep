package eapli.base.produtomanagement.application;

public class ImportarOrdensCSVController {
    private final CSVFileReaderService svc = new CSVFileReaderService();

    public void CSVO(String fileName){
        svc.getAllDataFromCSVOrdens(fileName);
    }

}
