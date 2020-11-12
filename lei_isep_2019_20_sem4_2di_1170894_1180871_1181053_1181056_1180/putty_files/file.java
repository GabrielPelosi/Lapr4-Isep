import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Scanner;
import javax.net.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;

class ConfigCliSSL {

    static final int SERVER_PORT = 30905;
    static final String KEYSTORE_PASS = "forgotten";

    static InetAddress serverIP;
    static SSLSocket sock;
    static Socket sock2;

    public static void main(String args[]) throws Exception {

        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", "client.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);
        //System.setProperty("java.net.preferIPv4Stack","true");
        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", "client.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);
        System.setProperty("https.protocols", "TLSv1.1");
        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        //System.out.println(args[0]);
        try {
            serverIP = InetAddress.getByName("localhost");
            System.out.println(serverIP.toString());
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + args[0]);
            System.exit(1);
        }

        try {
            System.out.println("SSL SOCK NAO CRIADO");
            sock = (SSLSocket) sf.createSocket(serverIP, SERVER_PORT);
            //sock2 = new Socket(serverIP, SERVER_PORT);
            System.out.println("SSL SOCK CRIADO");
        } catch (IOException ex) {
            System.out.println("Failed to connect to: " + serverIP + ":" + SERVER_PORT);
            System.out.println("Application aborted.");
            ex.printStackTrace();
            System.exit(1);
        }
        System.out.println("Connected to: " + "localhost" + ":" + SERVER_PORT);


        sock.startHandshake();
        System.out.println("HAND SHAKE FEITO");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //DataOutputStream sOut = new DataOutputStream(sock2.getOutputStream());
        //DataInputStream sIn = new DataInputStream(sock2.getInputStream());


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sOut = new DataOutputStream(sock.getOutputStream());
        DataInputStream sIn = new DataInputStream(sock.getInputStream());

        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Digite o nome do ficheiro");
        //String fileName = scanner.next();

        //int idMaquina = 12;//deve ser enviado do servidor scanner.nextInt();
        Integer idMaquina = new Integer(12);
        try {
            //File file = new File(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));


            byte req[] = new byte[1024];
            byte resp[] = new byte[1024];
            byte reqSize[] = new byte[24];

            String linha = "";

            resp[0] = (byte) 0;
            resp[1] = (byte) 2; //code config
            int primeiroByte = (idMaquina >> (8 * 1)) & 0xff;
            int segundoByte = (idMaquina >> (8 * 0)) & 0xff;

            resp[2] = (byte) primeiroByte;
            resp[3] = (byte) segundoByte;


            int inicio = 6;
            String linha2 = "";
            while ((linha = bufferedReader.readLine()) != null) {
                linha2 = linha + linha2;

            }
            int j = 0;
            byte[] b = linha2.getBytes(StandardCharsets.UTF_8);
            int dataL = b.length;
            int primeiroByteL = (dataL >> (8 * 1)) & 0xff;
            int segundoByteL = (dataL >> (8 * 0)) & 0xff;
            resp[4] = (byte) primeiroByteL;
            resp[5] = (byte) segundoByteL;
            for (int i = 6; j < dataL; i++) {
                resp[i] = b[j];
                j++;
            }

            System.out.println(Arrays.toString(resp));
            String linhaNew = new String(resp, StandardCharsets.UTF_8);
            System.out.println(linhaNew);
            sOut.write(resp);
            sIn.read(req);
            System.out.println(Arrays.toString(reqSize));
            System.out.println(Arrays.toString(req));
            if (req[1] == (byte) 150) {
                System.out.println("Ficheir de config enviado com sucesso");
            } else if (req[1] == (byte) 151) {
                System.out.println("Erro ao guardar o ficheiro de configuracao");
            } else {
                System.out.println("Erro inesperado");
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Ficheiro nào encontrado!!");
        }
        sock.close();
    }
}

