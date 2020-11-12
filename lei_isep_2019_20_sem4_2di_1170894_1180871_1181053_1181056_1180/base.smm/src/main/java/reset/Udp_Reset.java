package reset;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.smmmanagement.application.SMMController;
import eapli.base.smmmanagement.application.SMMPrinter;
import eapli.base.smmmanagement.domain.EstadoMaquina;
import eapli.base.smmmanagement.repositories.EstadoMaquinaRepository;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Udp_Reset {

    public static void main(String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        EstadoMaquinaRepository estadoMaquinaRepositoryRepository = PersistenceContext.repositories().estadosMaquinas();
        Iterable<EstadoMaquina> estados = estadoMaquinaRepositoryRepository.findAll();
        final SelectWidget<EstadoMaquina> selectorEstadoMaquina = new SelectWidget<>("Maquinas:", estados,
                new SMMPrinter());
        selectorEstadoMaquina.show();

        if (selectorEstadoMaquina.selectedOption() <= 0) {
            System.out.println("Erro na escolha da maquina");
        } else {
            final EstadoMaquina em = selectorEstadoMaquina.selectedElement();
            UdpClient reset = new UdpClient(em);
            reset.run();
        }
    }
}

