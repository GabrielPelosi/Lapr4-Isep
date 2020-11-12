package eapli.base.materiaprimamanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.materiaprimamanagement.domain.*;
import eapli.base.materiaprimamanagement.repositories.CategoriaRepository;


public class RegistarCategoriaController {

    private final CategoriaRepository categoriaRepository = PersistenceContext.repositories().categorias();

    public Categoria registerCategoria(final long id, final String descricao) {

        CodigoCategoria cc = new CodigoCategoria(id);
        DescricaoCategoria d = new DescricaoCategoria(descricao);

        Categoria newCat = new Categoria(cc, d);

        return newCat;
    }

    public boolean guardarCategoria(final long id, final String descricao) {
        Categoria caux = registerCategoria(id, descricao);
        Categoria c = this.categoriaRepository.save(caux);
        if(c != null){
            return true;
        }
        return false;
    }
}