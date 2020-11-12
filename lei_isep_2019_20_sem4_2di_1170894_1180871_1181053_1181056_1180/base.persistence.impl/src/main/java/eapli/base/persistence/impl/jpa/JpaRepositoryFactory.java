package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositomanagement.repositories.DepositoRepository;
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
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }


    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MateriaRepository materias() {
        return new JpaMateriaRepository();
    }

    @Override
    public MaquinaRepository maquinas() {
        return new JpaMaquinaRepository();
    }

    @Override
    public DepositoRepository depositos() {
        return new JpaDepositoRepository();
    }

    @Override
    public CategoriaRepository categorias() {
        return new JpaCategoriaRepository();
    }

    @Override
    public ProdutoRepository produtos() {
        return new JpaProdutoRepository();
    }

    @Override
    public LoteRepository lotes() {
        return new JpaLoteRepository();
    }

    @Override
    public OrdemRepository ordens() {
        return new JpaOrdemRepository();
    }

    @Override
    public LinhaProducaoRepository linhasProducao() {
        return new JpaLinhaProducaoRepository();
    }

    @Override
    public FichaProducaoRepository fichasProducao() {
        return new JpaFichaProducaoRepository();
    }

    @Override
    public NotificacaoRepository notificacoes() {
        return new JpaNotificacaoRepository();
    }

    @Override
    public MensagemRepository mensagens() {
        return new JpaMensagemRepository();
    }

    @Override
    public ProducaoRepository producoes() {
        return new JpaProducaoRepository();
    }

    @Override
    public ConsumoRepository consumos() {
        return new JpaConsumoRepository();
    }

    @Override
    public EstornoRepository estornos() {
        return new JpaEstornoRepository();
    }

    @Override
    public DesvioRepository desvios() {
        return new JpaDesvioRepository();
    }

    @Override
    public TempoProducaoRepository temposProducao() {
        return new JpaTempoProducaoRepository();
    }

    @Override
    public EstadoMaquinaRepository estadosMaquinas() {
        return new JpaEstadoMaquinaRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }


}
