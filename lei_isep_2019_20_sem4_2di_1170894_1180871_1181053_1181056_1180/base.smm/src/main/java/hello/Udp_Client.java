package hello;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.*;
import eapli.base.linhaproducaomanagement.repositories.*;
import eapli.base.smmmanagement.application.SMMController;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

class Udp_Client {

    public static void main(String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        LinhaProducaoRepository linhaRepository = PersistenceContext.repositories().linhasProducao();
        Iterable<LinhaProducao> listLinhas = linhaRepository.findAll();
        for (LinhaProducao linha : listLinhas) {
            Thread udpThread = new Thread(new Udp(linha));
            udpThread.start();
        }
    }
}

class Udp implements Runnable {

    private LinhaProducao linhaProducao;

    public Udp(LinhaProducao linha) {
        this.linhaProducao = linha;
    }

    private final SMMController theController = new SMMController();

    private static final int TIMEOUT_REQUEST = 30;
    private static final int TIMEOUT_RESPONSE = 60;

    private static final int VERSION = 0;
    private static final int CODE = 1;
    private static final int ID_BYTE1 = 2;
    private static final int ID_BYTE2 = 3;
    private static final int DATA_LENGTH_BYTE1 = 4;
    private static final int DATA_LENGTH_BYTE2 = 5;
    private static final int RAW_DATA = 6;

    private static final byte HELLO = (byte) 0;
    private static final byte ACK = (byte) 150;
    private static final byte NACK = (byte) 151;

    @Override
    public void run() {
        try {
            byte[] data = new byte[512];

            InetAddress ip = InetAddress.getByName("255.255.255.255");
            DatagramSocket sock = new DatagramSocket();
            DatagramPacket udpPacket = new DatagramPacket(data, data.length, ip, 30902);

            data[VERSION] = (byte) 0;
            data[CODE] = HELLO;
            data[ID_BYTE1] = (byte) 0;
            data[ID_BYTE2] = (byte) 0;
            data[DATA_LENGTH_BYTE1] = (byte) 0;
            data[DATA_LENGTH_BYTE2] = (byte) 0;

            udpPacket.setData(data);
            udpPacket.setLength(data.length);
            sock.send(udpPacket);
            sock.setSoTimeout(1000 * TIMEOUT_REQUEST);

            try {
                while (true) {
                    byte[] newData2 = new byte[512];
                    DatagramPacket udpResponse = new DatagramPacket(newData2, newData2.length);

                    sock.receive(udpResponse);

                    ByteBuffer byteToShortID = ByteBuffer.allocate(2);
                    byteToShortID.order(ByteOrder.LITTLE_ENDIAN);
                    byteToShortID.put(newData2[ID_BYTE1]);
                    byteToShortID.put(newData2[ID_BYTE2]);
                    long id = byteToShortID.getShort(0);
                    if (verificarMaquinaId(id, this.linhaProducao)) {

                        String code;
                        if (newData2[CODE] == ACK) {
                            code = "ACK";
                        } else if (newData2[CODE] == NACK) {
                            code = "NACK";
                        }

                        ByteBuffer byteToShortDL = ByteBuffer.allocate(2);
                        byteToShortDL.order(ByteOrder.LITTLE_ENDIAN);
                        byteToShortDL.put(newData2[DATA_LENGTH_BYTE1]);
                        byteToShortDL.put(newData2[DATA_LENGTH_BYTE2]);
                        int dataLength = byteToShortDL.getShort(0);


                        String status = new String(udpResponse.getData(), RAW_DATA, dataLength-RAW_DATA);

                        System.out.println(status);

                        InetAddress ipMaquina = udpResponse.getAddress();
                        theController.guardarEstadoMaquina(id, ipMaquina, status);
                    }
                }
            } catch (IOException unknownHostException) {
                unknownHostException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean verificarMaquinaId(long id, LinhaProducao linhaProducao) {
        List<CodigoInternoMaquina> listCodInt = linhaProducao.maquinas();
        CodigoInternoMaquina codId = new CodigoInternoMaquina("" + id);
        for (CodigoInternoMaquina cod : listCodInt) {
            if (cod.equals(codId)) {
                return true;
            }
        }
        return false;
    }
}