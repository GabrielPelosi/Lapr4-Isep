package eapli.base.app.backoffice.console.presentation.materias;

import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.framework.visitor.Visitor;

public class MateriasPrinter implements Visitor<MateriaPrima> {

    @Override
    public void visit(final MateriaPrima visitee) {
        System.out.printf("%s   -    %s", visitee.identity().toString(), visitee.descricao());
    }}
