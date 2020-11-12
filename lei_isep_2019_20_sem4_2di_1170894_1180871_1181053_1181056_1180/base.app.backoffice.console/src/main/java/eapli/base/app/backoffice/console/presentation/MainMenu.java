/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;

import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.categorias.*;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.app.backoffice.console.presentation.depositos.*;
import eapli.base.app.backoffice.console.presentation.linhaproducao.*;
import eapli.base.app.backoffice.console.presentation.materias.*;
import eapli.base.app.backoffice.console.presentation.messages.ArquivarNotificacoesAction;
import eapli.base.app.backoffice.console.presentation.messages.ConsultarNotificacoesAquivadasAction;
import eapli.base.app.backoffice.console.presentation.messages.ConsultarNotificacoesAtivasAction;
import eapli.base.app.backoffice.console.presentation.produtos.*;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 *
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 3;
    // GESTOR PRODUCAO
    private static final int MATERIA_OPTION = 4;
    private static final int CATEGORIA_OPTION = 5;
    private static final int PRODUTO_OPTION = 6;
    private static final int ORDEM_OPTION = 7;
    // GESTOR CHAO FABRICA
    private static final int LINHA_PRODUCAO_OPTION = 4;
    private static final int MAQUINA_OPTION = 5;
    private static final int DEPOSITO_OPTION = 6;

    private static final String SEPARATOR_LABEL = "--------------";
    // MATERIA
    private static final int CREATE_MATERIA = 1;
    private static final int EXPORTAR_MATERIA = 2;
    private static final int TRANSF_HMTL_MAT = 3;
    private static final int TRANSF_TXT_MAT = 4;
    private static final int TRANSF_JSON_MAT = 5;
    // LINHAS DE PRODUCAO
    private final static int CREATE_LINHA_PRODUCAO = 1;
    private final static int LIST_LINHA_PRODUCAO = 2;
    // MAQUINAS
    private static final int CREATE_MAQUINA = 1;
    private static final int ASSOCIATE_FICHEIRO_CONFIG = 2;
    private static final int EXPORTAR_MAQUINAS = 3;
    //DEPOSITO
    private static final int CREATE_DEPOSITO = 1;
    private static final int EXPORTAR_DEPOSITOS = 2;
    // CATEGORIA
    private static final int CREATE_CATEGORIA = 1;
    private static final int CATEGORIAS_LIST = 2;
    private static final int EXPORTAR_CATEGORIAS = 3;
    private static final int TRANSF_HMTL_CAT = 4;
    private static final int TRANSF_TXT_CAT = 5;
    private static final int TRANSF_JSON_CAT = 6;
    // PRODUTOS
    private static final int CREATE_PRODUTO = 1;
    private static final int IMPORTAR_OPTION = 2;
    private static final int PRODUTOSEMFICHA_LIST = 3;
    private static final int PRODUTOS_LIST_C_FICHA = 4;
    private static final int FICHAPRODUCAO_OPTION = 5;
    private static final int EXPORTAR_PRODUTOS = 6;
    private static final int EXPORTAR_FICHA_PRODUCAO = 7;
    private static final int TRANSF_HMTL_PRO = 8;
    private static final int TRANSF_TXT_PRO = 9;
    private static final int TRANSF_JSON_PRO = 10;
    private static final int TRANSF_HMTL_FICH = 11;
    private static final int TRANSF_TXT_FICH = 12;
    private static final int TRANSF_JSON_FICH = 13;
    //ORDEM
    private static final int CREATE_ORDEM = 1;
    private static final int IMPORTAR_ORDEM = 2;
    private static final int CONSULTAR_ORDEM_ENCOMENDA = 3;
    private static final int CONSULTAR_ORDEM_ESTADO = 4;
    private static final int EXPORTAR_ORDENS = 5;
    private static final int TRANSF_HMTL_ORD = 6;
    private static final int TRANSF_TXT_ORD = 7;
    private static final int TRANSF_JSON_ORD = 8;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }
        int optionNumber = 1;
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_PRODUCAO, BaseRoles.GESTOR_CHAO_FABRICA)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(++optionNumber, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(++optionNumber, settingsMenu);



            if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_PRODUCAO)) {
                final Menu materiaMenu = buildMateriaMenu();
                mainMenu.addSubMenu(++optionNumber, materiaMenu);
                final Menu categoriaMenu = buildCategoriaMenu();
                mainMenu.addSubMenu(++optionNumber, categoriaMenu);
                final Menu produtoMenu = buildProdutoMenu();
                mainMenu.addSubMenu(++optionNumber, produtoMenu);
                final Menu ordemMenu = buildOrdemMenu();
                mainMenu.addSubMenu(++optionNumber, ordemMenu);
            }

            final Menu linhaProducaoMenu = buildLinhaProducaoMenu();
            mainMenu.addSubMenu(++optionNumber, linhaProducaoMenu);
            final Menu maquinaMenu = buildMaquinaMenu();
            mainMenu.addSubMenu(++optionNumber, maquinaMenu);
            final Menu depositoMenu = buildDepositoMenu();
            mainMenu.addSubMenu(++optionNumber, depositoMenu);

            if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_CHAO_FABRICA)){
                final Menu notificacaoMenu = buildNotificacaoMenu();
                mainMenu.addSubMenu(++optionNumber, notificacaoMenu);
            }

        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildMateriaMenu() {
        final Menu menu = new Menu("Materias >");
        menu.addItem(CREATE_MATERIA, "Add Materia Prima", new RegistarMateriaAction());
        menu.addItem(EXPORTAR_MATERIA, "Exportar todas as Materias Primas para xml", new ExportarMateriaAction());
        menu.addItem(TRANSF_HMTL_MAT, "Transformar o XML das Materias Primas num ficheiro HTML", new MateriasTransformarHtmlAction());
        menu.addItem(TRANSF_TXT_MAT, "Transformar o XML das Materias Primas num ficheiro de texto", new MateriasTransformarTxtAction());
        menu.addItem(TRANSF_JSON_MAT, "Transformar o XML das Materias Primas num ficheiro JSON", new MateriasTransformarJsonAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildMaquinaMenu() {
        final Menu menu = new Menu("Maquinas >");
        int optionNumber = 0;
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_CHAO_FABRICA)) {
            menu.addItem(++optionNumber, "Add Maquina", new RegistarMaquinaAction());
            menu.addItem(++optionNumber, "Associar Ficheiro de Configuracao", new AssociarFicheiroConfiguracaoAction());
        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_PRODUCAO)) {
            menu.addItem(++optionNumber, "Exportar todas as Maquinas para xml", new ExportarMaquinasAction());
            menu.addItem(++optionNumber, "Transformar o XML das máquinas num ficheiro HTML", new MaquinasTransformarHtmlAction());
            menu.addItem(++optionNumber, "Transformar o XML das máquinas num ficheiro de texto", new MaquinasTransformarTxtAction());
            menu.addItem(++optionNumber, "Transformar o XML das máquinas num ficheiro JSON", new MaquinasTransformarJsonAction());
        }
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildDepositoMenu() {
        final Menu menu = new Menu("Depositos >");
        int optionNumber = 0;
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_CHAO_FABRICA)) {
            menu.addItem(++optionNumber, "Add Deposito", new DefinirDepositoAction());
        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_PRODUCAO)) {
            menu.addItem(++optionNumber, "Exportar todos os Depositos para xml", new ExportarDepositosAction());
            menu.addItem(++optionNumber, "Transformar o XML dos depositos num ficheiro HTML", new DepositosTransformarHtmlAction());
            menu.addItem(++optionNumber, "Transformar o XML dos depositos num num ficheiro de texto", new DepositosTransformarTxtAction());
            menu.addItem(++optionNumber, "Transformar o XML dos depositos num ficheiro JSON", new DepositosTransformarJsonAction());
        }
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildCategoriaMenu() {
        final Menu menu = new Menu("Categorias >");
        menu.addItem(CREATE_CATEGORIA, "Add Categoria", new RegistarCategoriaAction());
        menu.addItem(CATEGORIAS_LIST, "Listar todas as Categorias", new ListarCategoriasAction());
        menu.addItem(EXPORTAR_CATEGORIAS, "Exportar todas as Categorias para XML", new ExportarCategoriasAction());
        menu.addItem(TRANSF_HMTL_CAT, "Transformar o XML das categorias num ficheiro HTML", new CategoriasTransformarHtmlAction());
        menu.addItem(TRANSF_TXT_CAT, "Transformar o XML das categorias num ficheiro de texto", new CategoriasTransformarTxtAction());
        menu.addItem(TRANSF_JSON_CAT, "Transformar o XML das categorias num ficheiro JSON", new CategoriasTransformarJsonAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildProdutoMenu() {
        final Menu menu = new Menu("Produtos >");
        menu.addItem(CREATE_PRODUTO, "Add Produto", new DefinirProdutoAction());
        menu.addItem(IMPORTAR_OPTION, "Importar Produtos", new ImportarProdutosAction());
        menu.addItem(PRODUTOSEMFICHA_LIST, "Lista de Produtos sem Ficha de Produção", new ListProdutosSemFichaAction());
        menu.addItem(PRODUTOS_LIST_C_FICHA, "Listar todos os Produtos com Ficha", new ListarProdutosComFichaAction());
        menu.addItem(FICHAPRODUCAO_OPTION, "Definir Ficha de Producao", new EspecificarFichaProducaoAction());
        menu.addItem(EXPORTAR_PRODUTOS, "Exportar todos os Produtos para XML", new ExportarProdutosAction());
        menu.addItem(EXPORTAR_FICHA_PRODUCAO, "Exportar Fichas de Producao", new ExportarFichaProducaoAction());
        menu.addItem(TRANSF_HMTL_PRO, "Transformar o XML dos produtos num ficheiro HTML", new ProdutosTransformarHtmlAction());
        menu.addItem(TRANSF_TXT_PRO, "Transformar o XML dos produtos num ficheiro de texto", new ProdutosTransformarTxtAction());
        menu.addItem(TRANSF_JSON_PRO, "Transformar o XML dos produtos num ficheiro JSON", new ProdutosTransformarJsonAction());
        menu.addItem(TRANSF_HMTL_FICH, "Transformar o XML das fichas de producao num ficheiro HTML", new FichasTransformarHtmlAction());
        menu.addItem(TRANSF_TXT_FICH, "Transformar o XML das fichas de producao num ficheiro de texto", new FichasTransformarTxtAction());
        menu.addItem(TRANSF_JSON_FICH, "Transformar o XML das fichas de producao num ficheiro JSON", new FichasTransformarJsonAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildLinhaProducaoMenu() {
        final Menu menu = new Menu("Linhas de Producao >");
        int optionNumber = 0;
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_CHAO_FABRICA)) {
            menu.addItem(++optionNumber, "Add Linha de Producao", new EspecificarLinhaProducaoAction());
            menu.addItem(++optionNumber, "Listar Linhas de Producao", new ListarLinhaProducaoAction());
            menu.addItem(++optionNumber, "Consultar/Alterar estados das Linhas de Producao", new AlterarEstadosAction());
        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_PRODUCAO)){
            menu.addItem(++optionNumber, "Exportar Linhas de Producao", new ExportarLinhaProducaoAction());
            menu.addItem(++optionNumber, "Transformar para HTML o XML das Linhas de Producao", new LinhasTransformarHtmlAction());
            menu.addItem(++optionNumber, "Transformar para txt o XML das Linhas de Producao", new LinhasTransformarTxtAction());
            menu.addItem(++optionNumber, "Transformar para JSON o XML das Linhas de Producao", new LinhasTransformarJsonAction());
        }
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildOrdemMenu() {
        final Menu menu = new Menu("Ordens >");
        menu.addItem(CREATE_ORDEM, "Add ordem", new DefinirOrdemAction());
        menu.addItem(IMPORTAR_ORDEM, "Importar Ordens", new ImportarOrdensAction());
        menu.addItem(CONSULTAR_ORDEM_ENCOMENDA, "Consultar Ordens de uma Encomenda", new ConsultarOrdemEncomendaAction());
        menu.addItem(CONSULTAR_ORDEM_ESTADO, "Consultar Ordens num determinado Estado", new ConsultarOrdemEstadoAction());
        menu.addItem(EXPORTAR_ORDENS, "Exportar todas as Ordens para XML", new ExportarOrdensAction());
        menu.addItem(TRANSF_HMTL_ORD, "Transformar o XML das ordens num ficheiro HTML", new OrdensTransformarHtmlAction());
        menu.addItem(TRANSF_TXT_ORD, "Transformar o XML das ordens num ficheiro de texto", new OrdensTransformarTxtAction());
        menu.addItem(TRANSF_JSON_ORD, "Transformar o XML das ordens num ficheiro JSON", new OrdensTransformarJsonAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    private Menu buildNotificacaoMenu() {
        final Menu menu = new Menu("Notificacoes >");
        int optionNumber = 0;
        menu.addItem(++optionNumber, "Consultar notificacoes arquivadas", new ConsultarNotificacoesAquivadasAction());;
        menu.addItem(++optionNumber, "Consultar notificoes ativas", new ConsultarNotificacoesAtivasAction());
        menu.addItem(++optionNumber, "Arquivar notificacoes", new ArquivarNotificacoesAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

}
