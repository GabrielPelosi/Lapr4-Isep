


import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSrv{
    
    static final int SERVER_PORT=30902;
    static final String TRUSTED_STORE="server.jks";
    static final String KEYSTORE_PASS="forgotten";
    public static void main(String args[]) throws Exception {
        SSLServerSocket sock=null;
        Socket cliSock;

        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
	        new Thread(new ImportarFicheirosThread()).start();
            sock = (SSLServerSocket) sslF.createServerSocket(SERVER_PORT);
            sock.setNeedClientAuth(true);
            //sock = new ServerSocket(SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while (true) {

            System.out.println("Servidor pronto para receber conexoes!");
            cliSock = sock.accept();
            new Thread(new TcpSrvThread(cliSock)).start();
        }
    }
}



