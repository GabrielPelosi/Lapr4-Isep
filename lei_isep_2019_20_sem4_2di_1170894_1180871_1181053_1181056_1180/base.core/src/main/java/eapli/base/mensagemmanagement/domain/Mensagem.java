package eapli.base.mensagemmanagement.domain;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.time.util.Calendars;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Mensagem implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long idMensagem;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipoMsg;

    @Column(nullable = false)
    private CodigoInternoMaquina maquina;
    private Calendar dataHora;
    private boolean processada;
    @Column(nullable = true)
    private String erro;
    private String ordem;
    private String produto;
    private String materia;
    private Double quantidade;
    private String deposito;
    private String lote;

    public Mensagem(final CodigoInternoMaquina maquina, final Tipo tipo, final Calendar dataHora) {
        if (maquina == null || tipo == null || dataHora == null)
            throw new IllegalArgumentException();

        this.maquina = maquina;
        this.tipoMsg = tipo;
        this.dataHora = dataHora;
        this.processada = false;
    }

    protected Mensagem() {
        // for ORM only
    }

    public static Mensagem valueOf(String linha) {
        String[] valores = linha.split(";");
        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(valores[0]);
        Tipo tipo = Tipo.valueOf(valores[1]);
        Calendar dataHora = Calendars.parse(valores[2], "yyyyMMddHHmmss");

        Mensagem mensagemVazia = new Mensagem(maquina, tipo, dataHora);
        return mensagemVazia.criarAtributos(valores);
    }

    private Mensagem criarAtributos(String[] linha) {
        int index = 2;
        switch (this.tipoMsg) {
            case S0:
            case S9:
                tipo_S0_S9(linha[++index]);
                break;
            case S8:
                tipo_S8(linha[++index]);
                break;
            case C0:
            case P2:
                tipo_C0_P2(linha[++index], linha[++index], linha[++index]);
                break;
            case C9:
                tipo_C9(linha[++index], linha[++index], linha[++index]);
                break;
            case P1:
                tipo_P1(linha[++index], linha[++index], linha[++index]);
                break;
            case S1:
                break;
            default:
                throw new IllegalArgumentException("Erro na criacao da mensagem");
        }
        return this;
    }

    private void tipo_S0_S9(String ordem) {
        this.ordem = ordem;
    }

    private void tipo_S8(String erro) {
        this.erro = erro;
    }

    private void tipo_C0_P2(String materia, String quantidade, String deposito) {
        this.materia = materia;
        this.quantidade = Double.parseDouble(quantidade);
        this.deposito = deposito;
    }

    private void tipo_C9(String produto, String quantidade, String deposito) {
        this.produto = produto;
        this.quantidade = Double.parseDouble(quantidade);
        this.deposito = deposito;
    }

    private void tipo_P1(String produto, String quantidade, String lote) {
        this.produto = produto;
        this.quantidade = Double.parseDouble(quantidade);
        this.lote = lote;
    }


    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(java.lang.Object other) {
        if (!(other instanceof Mensagem)) {
            return false;
        }

        final Mensagem that = (Mensagem) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }


    @Override
    public Long identity() {
        return this.idMensagem;
    }

    public Tipo tipoMsg() {
        return tipoMsg;
    }

    public CodigoInternoMaquina maquina() {
        return maquina;
    }

    public Calendar dataHora() {
        return dataHora;
    }

    public void processar() {
        this.processada = true;
    }

    public String erro() {
        return erro;
    }

    public String ordem() {
        return ordem;
    }

    public String produto() {
        return produto;
    }

    public String materia() {
        return materia;
    }

    public Double quantidade() {
        return quantidade;
    }

    public String deposito() {
        return deposito;
    }

    public String lote() {
        return lote;
    }

    @Override
    public String toString() {
        return this.maquina.toString()
                + ";" + this.tipoMsg.toString()
                + ";" + this.dataHora.get(Calendar.YEAR)
                + "-" + this.dataHora.get(Calendar.MONTH)
                + "-" + this.dataHora.get(Calendar.DAY_OF_MONTH)
                + " " + this.dataHora.get(Calendar.HOUR_OF_DAY)
                + ":" + this.dataHora.get(Calendar.MINUTE)
                + ":" + this.dataHora.get(Calendar.SECOND)
                + ";" + this.erro
                + ";" + this.ordem
                + ";" + this.produto
                + ";" + this.materia
                + ";" + this.quantidade
                + ";" + this.deposito
                + ";" + this.lote;
    }

    public enum Tipo {
        S0,
        S1,
        C0,
        P1,
        C9,
        P2,
        S8,
        S9
    }
}
/*
    private static Mensagem valueOf_C9(String[] linha) {
        final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();
        final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();

        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(linha[0]);
        Tipo tipo = Tipo.valueOf(linha[1]);
        Calendar data = Calendars.parse(linha[2], "yyyyMMddHHmmss");

        CodigoFabrico idProduto = CodigoFabrico.valueOf(linha[3]);
        Optional<Produto> optionalProduto = produtoRepository.ofIdentity(idProduto);
        if (!optionalProduto.isPresent())
            throw new IllegalArgumentException("Erro na criacao da Mensagem associado ao Produto");
        Produto produto = optionalProduto.get();

        double quantidade = Double.parseDouble(linha[4]);

        CodigoDeposito idDeposito = CodigoDeposito.valueOf((Long.parseLong(linha[5])));
        Optional<Deposito> optionalDeposito = depositoRepository.ofIdentity(idDeposito);
        if (!optionalDeposito.isPresent())
            throw new IllegalArgumentException("Erro na criacao da Mensagem associado ao Deposito");
        Deposito deposito = optionalDeposito.get();

        return new Mensagem(maquina, tipo, data, produto, quantidade, deposito);
    }
    private static Mensagem valueOf_C0_P2(String[] linha) {
        final MateriaRepository materiaRepository = PersistenceContext.repositories().materias();
        final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();

        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(linha[0]);
        Tipo tipo = Tipo.valueOf(linha[1]);
        Calendar data = Calendars.parse(linha[2], "yyyyMMddHHmmss");

        CodigoInternoMateria idMateria = CodigoInternoMateria.valueOf(Long.parseLong(linha[3]));
        Optional<MateriaPrima> optionalMateriaPrima = materiaRepository.ofIdentity(idMateria);
        if (!optionalMateriaPrima.isPresent())
            throw new IllegalArgumentException("Erro na criacao da Mensagem associado a Materia Prima");
        MateriaPrima materiaPrima = optionalMateriaPrima.get();

        double quantidade = Double.parseDouble(linha[4]);

        CodigoDeposito idDeposito = CodigoDeposito.valueOf((Long.parseLong(linha[5])));
        Optional<Deposito> optionalDeposito = depositoRepository.ofIdentity(idDeposito);
        if (!optionalDeposito.isPresent())
            throw new IllegalArgumentException("Erro na criacao da Mensagem associado ao Deposito");
        Deposito deposito = optionalDeposito.get();

        return new Mensagem(maquina, tipo, data, materiaPrima, quantidade, deposito);
    }
    private static Mensagem valueOf_P1(String[] linha) {
        final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();

        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(linha[0]);
        Tipo tipo = Tipo.valueOf(linha[1]);
        Calendar data = Calendars.parse(linha[2], "yyyyMMddHHmmss");

        CodigoFabrico idProduto = CodigoFabrico.valueOf(linha[3]);
        Optional<Produto> optionalProduto = produtoRepository.ofIdentity(idProduto);
        if (!optionalProduto.isPresent())
            throw new IllegalArgumentException("Erro na criacao da Mensagem associado ao Produto");
        Produto produto = optionalProduto.get();

        double quantidade = Double.parseDouble(linha[4]);
        String lote = linha[5];

        return new Mensagem(maquina, tipo, data, produto, quantidade, lote);
    }
    private static Mensagem valueOf_S0_S9(String[] linha) {
        final OrdemRepository ordemRepository = PersistenceContext.repositories().ordens();

        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(linha[0]);
        Tipo tipo = Tipo.valueOf(linha[1]);
        Calendar data = Calendars.parse(linha[2], "yyyyMMddHHmmss");

        CodigoOrdem idOrdem = CodigoOrdem.valueOf(linha[3]);
        Optional<Ordem> optionalOrdem = ordemRepository.ofIdentity(idOrdem);
        if (!optionalOrdem.isPresent())
            throw new IllegalArgumentException("Erro na criacao da Mensagem associado a Ordem");
        Ordem ordem = optionalOrdem.get();

        return new Mensagem(maquina, tipo, data, ordem);
    }
    private static Mensagem valueOf_S1(String[] linha) {
        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(linha[0]);
        Tipo tipo = Tipo.valueOf(linha[1]);
        Calendar data = Calendars.parse(linha[2], "yyyyMMddHHmmss");
        String erro = linha[3];

        return new Mensagem(maquina, tipo, data, erro);
    }
    private static Mensagem valueOf_S8(String[] linha) {
        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(linha[0]);
        Tipo tipo = Tipo.valueOf(linha[1]);
        Calendar data = Calendars.parse(linha[2], "yyyyMMddHHmmss");

        return new Mensagem(maquina, tipo, data);
    }

    @Deprecated
    public static Mensagem valueOfOLD(String linha) {
        String[] valores = linha.split(";");
        int index = -1;
        CodigoInternoMaquina maquina = CodigoInternoMaquina.valueOf(valores[++index]);
        Tipo tipo = Tipo.valueOf(valores[++index]);
        Calendar data = Calendars.parse(valores[++index], "yyyyMMddHHmmss");

        if (tipo.equals(Tipo.C0) || tipo.equals(Tipo.P2)) {
            final MateriaRepository materiaRepository = PersistenceContext.repositories().materias();
            CodigoInternoMateria codigoInternoMateria = CodigoInternoMateria.valueOf(Long.parseLong(valores[++index]));
            Optional<MateriaPrima> m = materiaRepository.ofIdentity(codigoInternoMateria);
            if (m == null) {
                return null;
            }
            MateriaPrima materia = m.get();
            double quant = Double.parseDouble(valores[++index]);

            final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();
            CodigoDeposito codigoDeposito = CodigoDeposito.valueOf(Long.parseLong(valores[++index]));
            Optional<Deposito> d = depositoRepository.ofIdentity(codigoDeposito);
            if (d == null) {
                return null;
            }
            Deposito deposito = d.get();
            return new Mensagem(maquina, tipo, data, materia, quant, deposito);

        }
        if (tipo.equals(Tipo.P1) || tipo.equals(Tipo.C9)) {
            final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();
            CodigoFabrico codigoFabrico = CodigoFabrico.valueOf(valores[++index]);
            Optional<Produto> p = produtoRepository.ofIdentity(codigoFabrico);
            if (p == null) {
                return null;
            }
            Produto produto = p.get();
            double quant = Double.parseDouble(valores[++index]);
            if (tipo.equals(Tipo.P1)) {
                String lote = valores[++index];
                return new Mensagem(maquina, tipo, data, produto, quant, lote);
            } else {
                final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();
                CodigoDeposito codigoDeposito = CodigoDeposito.valueOf(Long.parseLong(valores[++index]));
                Optional<Deposito> d = depositoRepository.ofIdentity(codigoDeposito);
                if (d == null) {
                    return null;
                }
                Deposito deposito = d.get();
                return new Mensagem(maquina, tipo, data, produto, quant, deposito);
            }
        }
        if (tipo.equals(Tipo.S0) || tipo.equals(Tipo.S9)) {
            final OrdemRepository ordemRepository = PersistenceContext.repositories().ordens();
            CodigoOrdem codOrdem = CodigoOrdem.valueOf(valores[++index]);
            Optional<Ordem> o = ordemRepository.ofIdentity(codOrdem);
            if (o == null) {
                return null;
            }
            Ordem ordem = o.get();
            return new Mensagem(maquina, tipo, data, ordem);
        }
        if (tipo.equals(Tipo.S1)) {
            String erro = valores[++index];
            return new Mensagem(maquina, tipo, data, erro);
        }
        return new Mensagem(maquina, tipo, data); // Tipo S8
    }*/