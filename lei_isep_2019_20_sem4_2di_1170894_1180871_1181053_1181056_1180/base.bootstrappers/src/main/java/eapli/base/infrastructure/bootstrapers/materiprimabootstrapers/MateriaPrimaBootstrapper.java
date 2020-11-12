package eapli.base.infrastructure.bootstrapers.materiprimabootstrapers;

import eapli.base.materiaprimamanagement.application.ListCategoriaController;
import eapli.base.materiaprimamanagement.application.RegistarMateriaPrimaController;
import eapli.base.materiaprimamanagement.domain.*;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class MateriaPrimaBootstrapper implements Action {

    final RegistarMateriaPrimaController controller1 = new RegistarMateriaPrimaController();
    final ListCategoriaController controller2 = new ListCategoriaController();

    @Override
    public boolean execute() {
        try {

            register(9870, "ganga", 4567, "tecidos", "ganga.pdf");
            register(8765, "PETE",1234, "plasticos", "pete.pdf");
            register(9876, "aco",2345, "metais", "aco.pdf");
            register(4321, "pinho",3456, "madeiras", "pinho.pdf");
            register(5432, "malha",4567, "tecidos", "malha.pdf");
            register(3210, "bombazine",4567, "tecidos", "bombazine.pdf");
            register(4444, "erro",3333, "tecidos", "er.pdf");
            register(1927, "botoes de metal",5678, "botoes", "botaoMetal.pdf");
            register(4444, "erro",3333, "tecidos", "er.pdf");
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao da Materia Prima");
        }

        return true;
    }

    private void register(final long codInterno, final String descricao, final long codCategoria,
                          final String descricaoCategoria, final String ficha) {
        CodigoCategoria cod = new CodigoCategoria(codCategoria);
        DescricaoCategoria desc = new DescricaoCategoria(descricaoCategoria);
        Categoria c = new Categoria(cod, desc);
        if(validarCategoria(c)) {
            controller1.guardarMateria(codInterno, descricao, c, ficha);
        }
    }

    private boolean validarCategoria(final Categoria categoria) {
        Iterable<Categoria> itCat = controller2.listCategorias();
        boolean verify = false;

        for (Categoria c : itCat) {
            if (c.equals(categoria)) {
                verify = true;
                break;
            }
        }
        return verify;
    }
}

