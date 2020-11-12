package eapli.base.infrastructure.bootstrapers.mensagembootstrappers;

import eapli.base.mensagemmanagement.application.ValidarMensagensController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class MensagemBootstrapper implements Action {

    final ValidarMensagensController controller = new ValidarMensagensController();

    @Override
    public boolean execute() {

        register("B1234;S0;20200607130000;OR20192");
        register("B1234;C0;20200607130002;9876;50;12344");
        register("B1234;C0;20200607130006;9876;150;12344");
        register("B1234;C0;20200607130015;9876;40;12344");
        register("B1234;C0;20200607130030;9876;60;12344");
        register("B1234;P1;20200607130203;8100001;30;L20192");
        register("B1234;C0;20200607130210;9876;100;12344");
        register("B1234;P1;20200607131015;8100001;150;L20192");
        register("B1234;S9;20200607131017;OR20192");

        register("A5678;S0;20200607130350;OR20192");
        register("A5678;C9;20200607130400;8100001;30;12344");
        register("A5678;C9;20200607131018;8100001;60;12344");
        register("A5678;C9;20200607131240;8100001;90;12344");
        register("A5678;S9;20200607131300;OR20192");

        register("C1234;S0;20200607123000;OR20193");
        register("C1234;C0;20200607123200;9876;1000;12344");
        register("C1234;C0;20200607123300;1927;500;12344");
        register("C1234;P1;20200607123800;8100000;20;L20193");
        register("C1234;P2;20200607124000;9876;500;12344");
        register("C1234;P1;20200607124500;8100000;50;L20193");
        register("C1234;P2;20200607124700;1927;100;12344");
        register("C1234;C9;20200607125000;8100000;70;12344");
        register("C1234;S8;20200607125500;Pausa para desinfetar");
        register("C1234;S1;20200607131000");
        register("C1234;C0;20200607132000;9876;4500;12344");
        register("C1234;C0;20200607132500;1927;2200;12344");
        register("C1234;P1;20200607132700;8100000;10;L20193");
        register("C1234;C9;20200607132900;8100000;10;12344");
        register("C1234;S9;20200607133000;OR20193");

        testarProcessamento();
        return true;
    }

    private void register(String linha) {
        try {
            controller.guardarMensagem(linha);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Erro na criacao da Maquina");
        }
    }

    private void testarProcessamento() {
        /*
        Calendar data1 = Calendar.getInstance();
        data1.set(2020, Calendar.JUNE, 7, 12, 0, 0);
        Calendar data2 = Calendar.getInstance();
        data2.set(2020, Calendar.JUNE, 7, 14, 0, 0);
        List<IdLinhaProducao> linhas = Arrays.asList(/*IdLinhaProducao.valueOf("12345"), IdLinhaProducao.valueOf("56789"));
        ProcessarMensagemController controller = new ProcessarMensagemController();
        controller.inserirEspecificacoes(data1, data2, linhas);
        */
    }

}