package eapli.base.materiaprimamanagement.application;


import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.repositories.CategoriaRepository;

public class ListCategoriaService {
    private final CategoriaRepository categoriaRepository = PersistenceContext.repositories().categorias();

    public Iterable<Categoria> allCategorias() {
        return this.categoriaRepository.findAll();
    }

}
