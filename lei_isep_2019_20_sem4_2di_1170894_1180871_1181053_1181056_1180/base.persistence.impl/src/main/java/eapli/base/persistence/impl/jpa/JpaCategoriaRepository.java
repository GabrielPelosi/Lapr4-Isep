package eapli.base.persistence.impl.jpa;

import eapli.base.materiaprimamanagement.domain.CodigoCategoria;
import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.repositories.CategoriaRepository;

public class JpaCategoriaRepository extends BasepaRepositoryBase<Categoria, CodigoCategoria, CodigoCategoria> implements CategoriaRepository {

    public JpaCategoriaRepository(){
        super("codCategoria");
    }
}
