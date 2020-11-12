package eapli.base.materiaprimamanagement.application;

import eapli.base.materiaprimamanagement.domain.Categoria;

public class ListCategoriaController {

    private final ListCategoriaService svc = new ListCategoriaService();

    public Iterable<Categoria> listCategorias() {
        return this.svc.allCategorias();
    }
}
