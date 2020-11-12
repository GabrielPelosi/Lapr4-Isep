/**
 *
 */
package eapli.base.infrastructure.persistence;

import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.depositomanagement.repositories.DepositoRepository;
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

/**
 * @author Paulo Gandra Sousa
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    ClientUserRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientUserRepository clientUsers();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    MateriaRepository materias();

    MaquinaRepository maquinas();

    LinhaProducaoRepository linhasProducao();

    DepositoRepository depositos();

    CategoriaRepository categorias();

    ProdutoRepository produtos();

    LoteRepository lotes();

    FichaProducaoRepository fichasProducao();

    OrdemRepository ordens();

    NotificacaoRepository notificacoes();

    MensagemRepository mensagens();

    ProducaoRepository producoes();

    ConsumoRepository consumos();

    EstornoRepository estornos();

    DesvioRepository desvios();

    TempoProducaoRepository temposProducao();

    EstadoMaquinaRepository estadosMaquinas();
}
