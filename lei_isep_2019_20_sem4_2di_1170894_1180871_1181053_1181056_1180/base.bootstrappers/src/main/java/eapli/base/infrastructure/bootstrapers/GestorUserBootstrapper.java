package eapli.base.infrastructure.bootstrapers;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.HashSet;
import java.util.Set;

public class GestorUserBootstrapper extends UsersBootstrapperBase implements Action {
    @Override
    public boolean execute() {
        registerGestorProjeto("producao", TestDataConstants.PRODUCAO1, "Paulo", "Santos",
                "paulo.santos@email.local");
        registerGestorChaoFabrica("fabrica", TestDataConstants.CHAO_FABRICA1, "Tiago", "Silva",
                "tiago.silva@email.local");
        return true;
    }

    /**
     * Registar um Gestor de Projeto no sistema.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     */
    private void registerGestorProjeto(final String username, final String password, final String firstName,
                                       final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.GESTOR_PRODUCAO);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    /**
     * Registar um Gestor de Chao de Fabrica no sistema.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     */
    private void registerGestorChaoFabrica(final String username, final String password, final String firstName,
    final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.GESTOR_CHAO_FABRICA);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
