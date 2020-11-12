
import java.io.*;
import java.util.Scanner;

public class ImportarFicheirosThread implements Runnable {

    @Override
    public void run() {
        int a=2;
        Scanner scanner = new Scanner(System.in);
        File[] aa;
        //mudar a path conforme o projeto muda de pasta
        File dir = new File("por_processar");


            //System.out.println("Para importar as msg dos ficheiros do diretorio de entrada" +
              //      "para o log, digite 1");
            //a = scanner.nextInt();
            if (dir.isDirectory()){
                aa = dir.listFiles();
                for (File file: aa){
                    new Thread(new EscreverMsgImportadasThread(new File(file.toString()))).start();
                }
            }else{
                System.out.println("Ficheiros não encontrados, verifique se estão no diretorio correto");
            }



    }

}
