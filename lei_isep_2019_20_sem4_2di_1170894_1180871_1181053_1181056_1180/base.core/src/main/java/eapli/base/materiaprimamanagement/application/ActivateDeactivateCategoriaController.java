package eapli.base.materiaprimamanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.repositories.CategoriaRepository;

public class ActivateDeactivateCategoriaController {
    private final ListCategoriaService svc = new ListCategoriaService();
    private final CategoriaRepository categoriaRepository = PersistenceContext.repositories().categorias();


    public Iterable<Categoria> allCategorias() {
        return this.svc.allCategorias();
    }

}
