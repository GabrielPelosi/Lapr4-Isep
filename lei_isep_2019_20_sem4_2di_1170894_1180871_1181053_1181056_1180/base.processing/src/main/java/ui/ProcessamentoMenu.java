package ui;

import eapli.base.Application;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class ProcessamentoMenu extends AbstractUI {

    @Override
    protected boolean doShow() {
        final Menu menu = buildProcessorMenu();
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
        return "Processamento de Mensagens";
    }

    private Menu buildProcessorMenu() {
        final Menu menu = new Menu();
        int optionNumber = 0;

        menu.addItem(++optionNumber, "Processar Mensagens com Intervalo", new ProcessarIntervaloAction());
        menu.addItem(++optionNumber, "Processar Mensagens com Recorrencia", new ProcessarRecorrenteAction());

        menu.addItem(0, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return menu;
    }

}
