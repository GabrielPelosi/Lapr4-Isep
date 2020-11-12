package eapli.base.app.backoffice.console.presentation.produtos;


import eapli.base.produtomanagement.application.DefenirOrdemController;
import eapli.base.produtomanagement.domain.CodigoEncomenda;
import eapli.base.produtomanagement.domain.Produto;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.util.LinkedHashSet;
import java.util.Set;

public class DefenirOrdemUI extends AbstractUI {

    private final DefenirOrdemController theController = new DefenirOrdemController();

    @Override
    protected boolean doShow() {
        final LinkedHashSet<CodigoEncomenda> setEncomenda = new LinkedHashSet<>();
        final Iterable<Produto> produtos = this.theController.produtos();

        final String codigo = Console.readLine("Id ordem");

        int anoEm = Console.readInteger("Ano emissão");
        while (anoEm < 0) {
            System.out.println("Invalid year");
            anoEm = Console.readInteger("Ano emissão");
        }

        int mesEm = Console.readInteger("Mês emissão");
        while ((mesEm < 1 || mesEm > 12)) {
            System.out.println("Invalid month");
            mesEm = Console.readInteger("Mês emissão");
        }

        int diaEm = Console.readInteger("Dia emissão");
        while (diaEm < 1 || diaEm > 31) {
            System.out.println("Invalid month");
            diaEm = Console.readInteger("Dia emissão");
        }

        int ano = Console.readInteger("Ano previsto");
        while (ano < 0) {
            System.out.println("Invalid year");
            ano = Console.readInteger("Ano previsto");
        }

        int mes = Console.readInteger("Mês previsto");
        while ((mes < 1 || mes > 12)) {
            System.out.println("Invalid month");
            mes = Console.readInteger("Mês previsto");
        }

        int dia = Console.readInteger("Dia previsto");
        while (dia < 1 || dia > 31) {
            System.out.println("Invalid month");
            dia = Console.readInteger("Dia previsto");
        }

        while ((365 * anoEm + 31 * mesEm + diaEm) > (365 * ano + 31 * mes + dia)) {
            System.out.println("Invalid previous date! Enter a valid date. Note: the same or a future date");
            ano = Console.readInteger("Ano previsto");
            while (ano < 0) {
                System.out.println("Invalid year");
                ano = Console.readInteger("Ano previsto");
            }

            mes = Console.readInteger("Mês previsto");
            while ((mes < 1 || mes > 12)) {
                System.out.println("Invalid month");
                mes = Console.readInteger("Mês previsto");
            }

            dia = Console.readInteger("Dia previsto");
            while (dia < 1 || dia > 31) {
                System.out.println("Invalid month");
                dia = Console.readInteger("Dia previsto");
            }
        }


        final SelectWidget<Produto> selector = new SelectWidget<>("Produtos:", produtos,
                new ProdutosPrinter());
        selector.show();
        if (selector.selectedElement() == null) {
            System.out.println("ERRRO! Produto tem que existir, operação cancelada.");
            return false;
        }
        final Produto p = selector.selectedElement();

        final int quantidade = Console.readInteger("Quantidade");
        final String unidade = Console.readLine("Unidade");


        final String codigoEncomenda = Console.readLine("Codigo Encomenda");
        CodigoEncomenda cE = new CodigoEncomenda(codigoEncomenda);
        setEncomenda.add(cE);
        System.out.println("Se quiser não introduzir mais encomendas, introduza 0");
        String cod = Console.readLine("Codigo Encomenda");
        while (!cod.equals("0")) {
            CodigoEncomenda cEaux = new CodigoEncomenda(cod);
            setEncomenda.add(cEaux);
            System.out.println("Se quiser não introduzir mais encomendas, introduza 0");
            cod = Console.readLine("Codigo Encomenda");

        }

        final Set<CodigoEncomenda> senc = setEncomenda;

        String estado = Console.readLine("Estado");

        while ( (!estado.equals("Suspensa")) && (!estado.equals("Omitir"))) {
            System.out.println("ERRO! Tem que ser um dos seguintes: 'Suspensa' ou 'Omitir'");
            System.out.println("Se quiser omitir o estado insira 'Omitir'");
            estado = Console.readLine("Estado");
        }

        if(estado.equals("Omitir")){
            estado = "pendente";
        }


        try {
            this.theController.guardarOrdem(codigo, anoEm, mesEm, diaEm, ano, mes, dia, p, quantidade, unidade, senc, estado);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a Ordem which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Nova ordem";
    }

}
