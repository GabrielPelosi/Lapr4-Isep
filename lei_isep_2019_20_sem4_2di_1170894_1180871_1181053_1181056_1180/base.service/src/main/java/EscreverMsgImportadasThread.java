
import eapli.base.mensagemmanagement.application.ValidarMensagensController;
import eapli.base.mensagemmanagement.application.ValidarMensagensController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class EscreverMsgImportadasThread implements Runnable{
    private final ValidarMensagensController theController = new ValidarMensagensController();

    private BufferedReader threafFileReader;
    private File threadFile;
    public EscreverMsgImportadasThread(File file){
        threadFile = file;
        try {
            threafFileReader= new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String linha;
            while ((linha = threafFileReader.readLine()) != null) {
                theController.guardarMensagem(linha);
                Singleton.getInstance().writeToFile(linha);
            }
            threafFileReader.close();
            Singleton.getInstance().mudarParaProcessados(threadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
