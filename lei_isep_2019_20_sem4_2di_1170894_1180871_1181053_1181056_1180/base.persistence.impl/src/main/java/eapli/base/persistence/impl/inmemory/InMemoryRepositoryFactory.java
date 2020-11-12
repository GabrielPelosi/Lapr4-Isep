package eapli.base.persistence.impl.inmemory;

import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositomanagement.repositories.DepositoRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;
import eapli.base.materiaprimamanagement.repositories.CategoriaRepository;
import eapli.base.materiaprimamanagement.repositories.MateriaRepository;
import eapli.base.mensagemmanagement.repositories.*;
import eapli.base.produtomanagement.repositories.FichaProducaoRepository;
import eapli.base.produtomanagement.repositories.LoteRepository;
import eapli.base.produtomanagement.repositories.OrdemRepository;
import eapli.base.produtomanagement.repositories.ProdutoRepository;
import eapli.base.smmmanagement.repositories.EstadoMaquinaRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }


    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }


    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public MateriaRepository materias() {
        return new InMemoryMateriaRepository();
    }

    @Override
    public MaquinaRepository maquinas() {
        return new InMemoryMaquinaRepository();
    }

    @Override
    public DepositoRepository depositos() {
        return new InMemoryDepositoRepository();
    }

    @Override
    public CategoriaRepository categorias() {
        return new InMemoryCategoriaRepository();
    }

    @Override
    public ProdutoRepository produtos() {
        return new InMemoryProdutoRepository();
    }

    @Override
    public LoteRepository lotes() {
        return new InMemoryLoteRepository();
    }

    @Override
    public OrdemRepository ordens() {
        return new InMemoryOrdemRepository();
    }

    @Override
    public LinhaProducaoRepository linhasProducao() {
        return new InMemoryLinhaProducaoRepository();
    }

    @Override
    public FichaProducaoRepository fichasProducao() {
        return new InMemoryFichaProducaoRepository();
    }

    @Override
    public NotificacaoRepository notificacoes() {
        return new InMemoryNotificacaoRepository();
    }

    @Override
    public MensagemRepository mensagens() {
        return new InMemoryMensagemRepository();
    }

    @Override
    public ProducaoRepository producoes() {
        return new InMemoryProducaoRepository();
    }

    @Override
    public ConsumoRepository consumos() {
        return new InMemoryConsumoRepository();
    }

    @Override
    public EstornoRepository estornos() {
        return new InMemoryEstornoRepository();
    }

    @Override
    public DesvioRepository desvios() {
        return new InMemoryDesvioRepository();
    }

    @Override
    public TempoProducaoRepository temposProducao() {
        return new InMemoryTempoProducaoRepository();
    }

    @Override
    public EstadoMaquinaRepository estadosMaquinas() {
        return new InMemoryEstadoMaquinaRepository();
    }
}
