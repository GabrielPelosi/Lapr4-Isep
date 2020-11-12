package eapli.base.smmmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.smmmanagement.domain.EstadoMaquina;
import eapli.base.smmmanagement.repositories.EstadoMaquinaRepository;

import java.net.InetAddress;

public class SMMController {

    private final EstadoMaquinaRepository theRepository = PersistenceContext.repositories().estadosMaquinas();

    public EstadoMaquina registerEstadoMaquina(final long idMaq, final InetAddress ipMaquina, final String status) {

        String ip = ipMaquina.toString();
        final EstadoMaquina newEstado = new EstadoMaquina(idMaq, ip, status);

        return newEstado;
    }

    public boolean guardarEstadoMaquina(final long idMaq, final InetAddress ipMaquina, final String status) {
        EstadoMaquina estadoAux = registerEstadoMaquina(idMaq, ipMaquina, status);
        EstadoMaquina estadoMaq = this.theRepository.save(estadoAux);
        if(estadoMaq != null){
            return true;
        }
        return false;
    }
}
