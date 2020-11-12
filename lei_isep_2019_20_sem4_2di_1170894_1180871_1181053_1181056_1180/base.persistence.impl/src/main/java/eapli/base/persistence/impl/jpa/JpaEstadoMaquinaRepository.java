package eapli.base.persistence.impl.jpa;


import eapli.base.smmmanagement.domain.EstadoMaquina;
import eapli.base.smmmanagement.repositories.EstadoMaquinaRepository;

public class JpaEstadoMaquinaRepository extends BasepaRepositoryBase<EstadoMaquina, Long, Long> implements EstadoMaquinaRepository {

    public JpaEstadoMaquinaRepository() {
        super("idEstado");
    }
}
