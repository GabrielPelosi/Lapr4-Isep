package eapli.base.app.backoffice.console.presentation.produtos;

;
import eapli.base.produtomanagement.domain.Produto;
import eapli.framework.visitor.Visitor;

public class ProdutosPrinter implements Visitor<Produto> {

    @Override
    public void visit(final Produto visitee) {
        System.out.printf("%s   -    %s", visitee.identity().toString(), visitee.descricaoBreve().toString());
    }
}
