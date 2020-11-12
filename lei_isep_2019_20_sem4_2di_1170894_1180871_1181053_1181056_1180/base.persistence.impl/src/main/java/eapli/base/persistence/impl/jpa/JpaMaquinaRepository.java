package eapli.base.persistence.impl.jpa;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.base.linhaproducaomanagement.domain.NumeroSerie;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;

public class JpaMaquinaRepository extends BasepaRepositoryBase<Maquina, CodigoInternoMaquina, CodigoInternoMaquina> implements MaquinaRepository {
    public JpaMaquinaRepository(){
        super("codigoInternoMaquina");
    }
}
