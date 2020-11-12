package eapli.base.app.backoffice.console.presentation.materias;

import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.framework.visitor.Visitor;

class CategoriasPrinter implements Visitor<Categoria> {

    @Override
    public void visit(final Categoria visitee) {
        System.out.printf("%s    -    %s", visitee.identity().toString(), visitee.descricao().toString());
    }
}
