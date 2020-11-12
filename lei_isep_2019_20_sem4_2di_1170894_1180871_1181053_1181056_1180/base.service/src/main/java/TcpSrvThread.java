

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TcpSrvThread implements Runnable {

    private Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public TcpSrvThread(Socket cli_s) {
        s = cli_s;
    }


    public void run() {

        InetAddress clientIP;

        clientIP = s.getInetAddress();

        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());
        try {
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());

            byte ab[] = new byte[1024];
            //byte resp[] = new byte[1024];

            sOut.flush();
            System.out.println("Antes do read");
            sIn.read(ab);
            byte[] temp = new byte[2];
            temp[0] = ab[2];
            temp[1] = ab[3];

            int val = ((ab[2] & 0xff) << 8) | (ab[3] & 0xff);

            String codigoMaquina = String.valueOf(val);
            System.out.println("Antes do read");
            System.out.println("cod: "+codigoMaquina);
            System.out.println(ab[1]);

            //verificar se a maquina existe
            if (Singleton.getInstance().checkMaquinaExists(codigoMaquina)) {
                ab[1] = (byte) 150;
            } else {
                ab[1] = (byte) 151;
                Thread.currentThread().interrupt();
            }
            System.out.format("0x%x \n", ab[1]);

            System.out.println(ab.length);
            System.out.println(Arrays.toString(ab));

            sOut.write(ByteBuffer.allocate(4).putInt(ab.length).array());
            sOut.flush();
            sOut.write(ab);
            sOut.flush();
            do {
                System.out.println("Antes do read");
                sIn.read(ab);
                System.out.println("Depois do read");
                byte newByte[] = new byte[1024];
                int i;
                for (i=2; i<ab.length; i++){

                    newByte[i-2]=ab[i];
                }
                i=0;
                while(newByte[i]!=0){
                    i++;}
                byte na[] = new byte[i];
                i=0;
                while(newByte[i]!=0){
                    na[i]=newByte[i];
                    i++;}
                 String a = new String(na, StandardCharsets.UTF_8);
                System.out.println("Mensagem: "+a);
                System.out.println("Tamanho : "+a.length());
                //cadastrar a msg na base de dadados, se tiver sucesso, retorna 150, se não, 151
                if (Singleton.getInstance().saveMensagem(a)) {
                    ab[1] = (byte) 150;
                } else {
                    ab[1] = (byte) 151;
                }
                Singleton.getInstance().writeToFile(a);
                sOut.write(ByteBuffer.allocate(4).putInt(ab.length).array());
                sOut.flush();
                sOut.write(ab);
                sOut.flush();
                if (sIn.readBoolean() == false)
                    break;
            } while (true);

        } catch (EOFException eo) {
            try {
                s.close();
            } catch (IOException io) {
                System.out.println("aa");
            }
        } catch (IOException io) {
            System.out.println("IOExcetion after close");
        }
    }
}
