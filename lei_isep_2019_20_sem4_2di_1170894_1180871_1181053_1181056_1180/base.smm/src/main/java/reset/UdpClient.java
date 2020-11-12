package reset;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.smmmanagement.application.SMMController;
import eapli.base.smmmanagement.domain.EstadoMaquina;
import eapli.base.smmmanagement.repositories.EstadoMaquinaRepository;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    private EstadoMaquina estadoMaquina;

    public UdpClient(EstadoMaquina estadoMaq) {
        this.estadoMaquina = estadoMaq;
    }

    private static final SMMController theController = new SMMController();
    private static final EstadoMaquinaRepository theRepository = PersistenceContext.repositories().estadosMaquinas();

    private static final int TIMEOUT = 60;

    private static final int VERSION = 0;
    private static final int CODE = 1;
    private static final int ID_BYTE1 = 2;
    private static final int ID_BYTE2 = 3;
    private static final int DATA_LENGTH_BYTE1 = 4;
    private static final int DATA_LENGTH_BYTE2 = 5;

    private static final byte RESET = (byte) 3;
    private static final byte ACK = (byte) 150;
    private static final byte NACK = (byte) 151;

    public void run() {
        try {
            byte[] data = new byte[512];

            String ipString = this.estadoMaquina.ipAddress().substring(1);
            InetAddress ip = InetAddress.getByName(ipString);
            DatagramSocket sock = new DatagramSocket();
            DatagramPacket udpPacket = new DatagramPacket(data, data.length, ip, 30902);

            data[VERSION] = (byte) 0;
            data[CODE] = RESET;
            data[ID_BYTE1] = (byte) 0;
            data[ID_BYTE2] = (byte) 0;
            data[DATA_LENGTH_BYTE1] = (byte) 0;
            data[DATA_LENGTH_BYTE2] = (byte) 0;

            udpPacket.setData(data);
            udpPacket.setLength(data.length);
            sock.send(udpPacket);

            try {

                    byte[] newData2 = new byte[512];
                    DatagramPacket udpResponse = new DatagramPacket(newData2, newData2.length);

                    sock.receive(udpResponse);

                    String code;
                    if (newData2[CODE] == ACK) {
                        code = "ACK";
                        this.theController.guardarEstadoMaquina(this.estadoMaquina.identity(), ip, " ");
                        System.out.println("Reset feito com sucesso");
                    } else if (newData2[CODE] == NACK) {
                        code = "NACK";
                        this.theRepository.save(this.estadoMaquina);
                        System.out.println("Erro a fazer Reset");
                    }

            } catch (IOException unknownHostException) {
                unknownHostException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
