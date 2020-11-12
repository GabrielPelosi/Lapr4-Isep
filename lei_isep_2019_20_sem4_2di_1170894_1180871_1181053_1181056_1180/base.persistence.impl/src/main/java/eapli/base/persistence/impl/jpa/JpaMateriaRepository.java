package eapli.base.persistence.impl.jpa;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.base.materiaprimamanagement.repositories.MateriaRepository;

public class JpaMateriaRepository extends BasepaRepositoryBase<MateriaPrima, CodigoInternoMateria, CodigoInternoMateria> implements MateriaRepository {

    public JpaMateriaRepository(){
        super("codInterno");
    }
}
