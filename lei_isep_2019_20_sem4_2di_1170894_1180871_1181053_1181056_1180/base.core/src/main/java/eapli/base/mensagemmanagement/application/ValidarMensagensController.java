package eapli.base.mensagemmanagement.application;

import eapli.base.depositomanagement.domain.CodigoDeposito;
import eapli.base.depositomanagement.domain.Deposito;
import eapli.base.depositomanagement.repositories.DepositoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;
import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.base.materiaprimamanagement.repositories.MateriaRepository;
import eapli.base.mensagemmanagement.domain.*;
import eapli.base.mensagemmanagement.repositories.MensagemRepository;
import eapli.base.mensagemmanagement.repositories.NotificacaoRepository;
import eapli.base.produtomanagement.domain.*;
import eapli.base.produtomanagement.repositories.LoteRepository;
import eapli.base.produtomanagement.repositories.OrdemRepository;
import eapli.base.produtomanagement.repositories.ProdutoRepository;

import java.util.Optional;

public class ValidarMensagensController {

    private final MensagemRepository mensagemRepository = PersistenceContext.repositories().mensagens();
    private final NotificacaoRepository notificacaoRepository = PersistenceContext.repositories().notificacoes();

    public boolean guardarMensagem(final String linha) {
        Mensagem novaMensagem = Mensagem.valueOf(linha);
        boolean valida = false;
        if (!validarMaquina(novaMensagem)) {
            return false;
        }

        switch (novaMensagem.tipoMsg()) {
            case S0:
            case S9:
                valida = validarS0_S9(novaMensagem);
                break;
            case C0:
            case P2:
                valida = validarC0_P2(novaMensagem);
                break;
            case C9:
                valida = validarC9(novaMensagem);
                break;
            case P1:
                valida = validarP1(novaMensagem);
                break;
            case S8:
            case S1:
                valida = true;
                break;
            default:
                throw new IllegalArgumentException("Erro na criacao da mensagem");
        }


        if (valida) {
            Mensagem m = this.mensagemRepository.save(novaMensagem);
            return m != null;
        }
        return false;
    }

    public boolean validarMaquina(Mensagem mensagem) {
        final MaquinaRepository maquinaRepository = PersistenceContext.repositories().maquinas();

        CodigoInternoMaquina codigoMaquina = mensagem.maquina();
        Optional<Maquina> optionalMaquina = maquinaRepository.ofIdentity(codigoMaquina);
        if (!optionalMaquina.isPresent()) {
            System.out.println("Erro na criacao da Mensagem associado a Maquina");
            return false;
        }
        return true;
    }

    public boolean validarC9(Mensagem mensagem) {
        final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();
        final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();

        CodigoFabrico idProduto = CodigoFabrico.valueOf(mensagem.produto());
        Optional<Produto> optionalProduto = produtoRepository.ofIdentity(idProduto);
        if (!optionalProduto.isPresent()) {
            System.out.println("Erro na criacao da Mensagem associado ao Produto");
            Notificacao n = new Notificacao(Notificacao.Tipo.PRODUTO_NAO_EXISTENTE, mensagem.maquina());
            notificacaoRepository.save(n);
            return false;
        }

        CodigoDeposito idDeposito = CodigoDeposito.valueOf((Long.parseLong(mensagem.deposito())));
        Optional<Deposito> optionalDeposito = depositoRepository.ofIdentity(idDeposito);
        if (!optionalDeposito.isPresent()) {
            System.out.println("Erro na criacao da Mensagem associado ao Deposito");
            Notificacao n = new Notificacao(Notificacao.Tipo.DEPOSITO_NAO_EXISTENTE, mensagem.maquina());
            notificacaoRepository.save(n);
            return false;
        }

        return true;
    }

    public boolean validarC0_P2(Mensagem mensagem) {
        final MateriaRepository materiaRepository = PersistenceContext.repositories().materias();
        final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();

        CodigoInternoMateria idMateria = CodigoInternoMateria.valueOf(Long.parseLong(mensagem.materia()));
        Optional<MateriaPrima> optionalMateriaPrima = materiaRepository.ofIdentity(idMateria);
        if (!optionalMateriaPrima.isPresent()) {
            System.out.println("Erro na criacao da Mensagem associado a Materia Prima");
            Notificacao n = new Notificacao(Notificacao.Tipo.MATERIA_NAO_EXISTENTE, mensagem.maquina());
            notificacaoRepository.save(n);
            return false;
        }

        CodigoDeposito idDeposito = CodigoDeposito.valueOf((Long.parseLong(mensagem.deposito())));
        Optional<Deposito> optionalDeposito = depositoRepository.ofIdentity(idDeposito);
        if (!optionalDeposito.isPresent()) {
            System.out.println("Erro na criacao da Mensagem associado ao Deposito");
            Notificacao n = new Notificacao(Notificacao.Tipo.DEPOSITO_NAO_EXISTENTE, mensagem.maquina());
            notificacaoRepository.save(n);
            return false;
        }

        return true;
    }

    public boolean validarP1(Mensagem mensagem) {
        final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();

        CodigoFabrico idProduto = CodigoFabrico.valueOf(mensagem.produto());
        Optional<Produto> optionalProduto = produtoRepository.ofIdentity(idProduto);
        if (!optionalProduto.isPresent()) {
            System.out.println("Erro na criacao da Mensagem associado ao Produto");
            Notificacao n = new Notificacao(Notificacao.Tipo.PRODUTO_NAO_EXISTENTE, mensagem.maquina());
            notificacaoRepository.save(n);
            return false;
        }

        return guardarLote(mensagem);
    }

    public boolean guardarLote(Mensagem mensagem) {
        final LoteRepository loteRepository = PersistenceContext.repositories().lotes();


        Optional<Lote> optionalLote = loteRepository.ofIdentity(mensagem.lote());
        if (optionalLote.isPresent()) {
            return true;
        }

        Lote novoLote = Lote.valueOf(mensagem.lote());
        Lote l = loteRepository.save(novoLote);
        return l != null;
    }

    public boolean validarS0_S9(Mensagem mensagem) {
        final OrdemRepository ordemRepository = PersistenceContext.repositories().ordens();

        CodigoOrdem idOrdem = CodigoOrdem.valueOf(mensagem.ordem());
        Optional<Ordem> optionalOrdem = ordemRepository.ofIdentity(idOrdem);
        if (!optionalOrdem.isPresent()) {
            System.out.println("Erro na criacao da Mensagem associado a Ordem");
            Notificacao n = new Notificacao(Notificacao.Tipo.ORDEM_NAO_EXISTENTE, mensagem.maquina());
            notificacaoRepository.save(n);
            return false;
        }

        return true;
    }
}
