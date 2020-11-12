package eapli.base.smmmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EstadoMaquina implements AggregateRoot<Long> {

    @Id
    private Long idMaquina;

    @Column(nullable = false)
    private String ip;
    private String status;

    public EstadoMaquina(final Long idMaq, final String ipMaquina, final String status) {
        if (idMaq == null || ipMaquina == null || status == null){
          throw new IllegalArgumentException();
        }
        this.idMaquina = idMaq;
        this.ip = ipMaquina;
        this.status = status;
    }

    protected EstadoMaquina() {
        // for ORM only
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof EstadoMaquina)) {
            return false;
        }
        final EstadoMaquina that = (EstadoMaquina) other;
        if (this == that) {
            return true;
        }
        return identity().equals(that.identity());
    }

    public String Status() {
        return status;
    }

    public String ipAddress() {
        return ip;
    }

    @Override
    public Long identity() {
        return this.idMaquina;
    }
}
