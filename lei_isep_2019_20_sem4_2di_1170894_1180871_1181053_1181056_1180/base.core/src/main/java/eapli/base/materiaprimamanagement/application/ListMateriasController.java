package eapli.base.materiaprimamanagement.application;

import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;

public class ListMateriasController {

    private final ListMateriasService svc = new ListMateriasService();

    public Iterable<MateriaPrima> listMaterias() {
        return this.svc.allMaterias();
    }
}
