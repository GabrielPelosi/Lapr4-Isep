import ui.ProcessamentoMenu;

public class ProcessamentoServer {

    public static void main(String[] args) {
        System.out.println("SERVER ONLINE");
        final ProcessamentoMenu menu = new ProcessamentoMenu();
        menu.mainLoop();
    }
}