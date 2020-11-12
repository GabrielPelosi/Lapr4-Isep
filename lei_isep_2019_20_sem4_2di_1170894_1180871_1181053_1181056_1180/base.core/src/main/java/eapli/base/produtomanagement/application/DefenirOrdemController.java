package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.produtomanagement.domain.*;
import eapli.base.produtomanagement.repositories.OrdemRepository;

import java.util.Set;

public class DefenirOrdemController {

    private final ListarProdutosComFichaService svc = new ListarProdutosComFichaService();
    private final OrdemRepository repository = PersistenceContext.repositories().ordens();


    public Ordem registerOrdem(final String codOrdem, final String dataEmissao, final String dataPrevista, final Produto produto,
                               final int quantidade, final String unidade, final Set<CodigoEncomenda> se, final String estado) {

        CodigoOrdem cO = new CodigoOrdem(codOrdem);
        String dE = dataEmissao;
        String dP = dataPrevista;
        UnidadeProduto unid = new UnidadeProduto(unidade);


        Ordem newOrdem = new Ordem(cO, dE, dP, produto, quantidade, unid, se, estado);

        return newOrdem;


    }


    public boolean guardarOrdem(final String codOrdem, int anoEm, int mesEm, int diaEm, int ano, int mes, int dia, final Produto produto,
                                final int quantidade, final String unidade, final Set<CodigoEncomenda> se, final String estado) {


        String dataEmissao = anoEm + twoDigitNumberEnforcer(mesEm) + twoDigitNumberEnforcer(diaEm);
        String dataPrevista = ano + twoDigitNumberEnforcer(mes) + twoDigitNumberEnforcer(dia);
        Ordem oaux = registerOrdem(codOrdem, dataEmissao, dataPrevista, produto, quantidade, unidade, se, estado);
        Ordem o = this.repository.save(oaux);

        return o != null;
    }

    public Iterable<Produto> produtos() {
        return this.svc.produtosComFicha();
    }

    public String twoDigitNumberEnforcer(int num) {
        String s = Integer.toString(num);
        if (num < 10)
            s = "0" + s;

        return s;
    }
}

