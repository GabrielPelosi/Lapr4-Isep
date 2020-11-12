
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;
import eapli.base.mensagemmanagement.domain.Mensagem;
import eapli.base.mensagemmanagement.repositories.MensagemRepository;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Singleton {
    private static final File DIR_PROCESSADOS = new File("processados");
    private static final Singleton inst = new Singleton();
    private static final String FILE_NAME = "LogMaquinas.txt";
    private static FileWriter writer;

    {
        try {
            writer = new FileWriter(FILE_NAME, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Singleton() {
        super();
    }

    public static Singleton getInstance() {
        return inst;
    }

    public synchronized void writeToFile(String str) {
        try {
            writer.write(str + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void mudarParaProcessados(File threadFile){
        if (DIR_PROCESSADOS.isDirectory()){
            System.out.println(threadFile.toString());
            System.out.println(DIR_PROCESSADOS.toPath().toString());
            try {

                FileUtils.moveFileToDirectory(threadFile, DIR_PROCESSADOS,false);
                Thread.currentThread().stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private final MaquinaRepository maquinaRepository = PersistenceContext.repositories().maquinas();

    public synchronized boolean checkMaquinaExists(String id){
        return maquinaRepository.containsOfIdentity(CodigoInternoMaquina.valueOf(id));
    }

    private final MensagemRepository mensagemRepository = PersistenceContext.repositories().mensagens();
    public synchronized boolean saveMensagem(String linhaMensagem){
        Mensagem mensagem = Mensagem.valueOf(linhaMensagem);
        return mensagemRepository.save(mensagem) != null;
    }

}