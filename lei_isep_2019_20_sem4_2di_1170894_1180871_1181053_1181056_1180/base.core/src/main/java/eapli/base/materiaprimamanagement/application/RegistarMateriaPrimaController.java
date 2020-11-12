package eapli.base.materiaprimamanagement.application;

import eapli.base.materiaprimamanagement.domain.*;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.materiaprimamanagement.repositories.MateriaRepository;


public class RegistarMateriaPrimaController {

    private final ListCategoriaService svc = new ListCategoriaService();
    private final MateriaRepository materiaRepository = PersistenceContext.repositories().materias();

    public MateriaPrima registerMateriaPrima(final long codInterno, final String descricao, final Categoria categoria, final String ficha) {

        CodigoInternoMateria cm = new CodigoInternoMateria(codInterno);
        DescricaoMateria d = new DescricaoMateria(descricao);
        FichaTecnica f = new FichaTecnica(ficha);
        final MateriaPrima newMat = new MateriaPrima(cm, d, categoria, f);

        return newMat;
    }

    public boolean guardarMateria(final long codInterno, final String descricao, final Categoria categoria, final String ficha) {
        MateriaPrima maux = registerMateriaPrima(codInterno, descricao, categoria, ficha);
        MateriaPrima m = this.materiaRepository.save(maux);
        if(m != null){
            return true;
        }
        return false;
    }

    public Iterable<Categoria> categoria() {
        return this.svc.allCategorias();
    }
}
