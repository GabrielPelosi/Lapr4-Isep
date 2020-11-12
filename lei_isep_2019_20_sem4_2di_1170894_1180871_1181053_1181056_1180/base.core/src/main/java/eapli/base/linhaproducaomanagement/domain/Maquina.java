package eapli.base.linhaproducaomanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Maquina implements AggregateRoot<CodigoInternoMaquina> {

    @Id
    private CodigoInternoMaquina codigoInternoMaquina;

    @Column(nullable = false)
    private NumeroSerie numSerie;
    private DescricaoMaquina descricao;
    private DataInstalacao dataInstalacao;
    private Marca marca;
    private Modelo modelo;
    private long idProtocolo;

    @Column(nullable = false)
    @ElementCollection
    private List<FicheiroConfiguracao> ficheiroConfiguracao;

    public Maquina(CodigoInternoMaquina codInterno, NumeroSerie numSerie, DescricaoMaquina descricao,
                   DataInstalacao dataInstal, Marca marca, Modelo modelo, List<FicheiroConfiguracao> fichaConfig, long idProtocolo) {
        if (codInterno == null || numSerie == null || descricao == null || dataInstal == null || marca == null || modelo == null || idProtocolo == 0) {
            throw new IllegalArgumentException();
        }
        this.codigoInternoMaquina = codInterno;
        this.numSerie = numSerie;
        this.descricao = descricao;
        this.dataInstalacao = dataInstal;
        this.marca = marca;
        this.modelo = modelo;
        this.ficheiroConfiguracao = fichaConfig;
        this.idProtocolo = idProtocolo;
    }

    public Maquina(CodigoInternoMaquina codInterno, NumeroSerie numSerie, DescricaoMaquina descricao,
                   DataInstalacao dataInstal, Marca marca, Modelo modelo, long idProtocolo) {
        if (codInterno == null || numSerie == null || descricao == null || dataInstal == null || marca == null || modelo == null || idProtocolo == 0) {
            throw new IllegalArgumentException();
        }
        this.codigoInternoMaquina = codInterno;
        this.numSerie = numSerie;
        this.descricao = descricao;
        this.dataInstalacao = dataInstal;
        this.marca = marca;
        this.modelo = modelo;
        this.ficheiroConfiguracao = new ArrayList<>();
        this.idProtocolo = idProtocolo;
    }

    protected Maquina() {
        // for ORM only
    }

    public NumeroSerie numeroSerie() {
        return this.numSerie;
    }

    @Override
    public CodigoInternoMaquina identity() {
        return this.codigoInternoMaquina;
    }

    public DescricaoMaquina descricaoMaquina() {
        return this.descricao;
    }

    public DataInstalacao dataInstalacao() {
        return this.dataInstalacao;
    }

    public Marca marca() {
        return this.marca;
    }

    public Modelo modelo() {
        return this.modelo;
    }

    public List<FicheiroConfiguracao> ficheiroConfiguracao() {
        return this.ficheiroConfiguracao;
    }

    public long idProtocolo() {
        return this.idProtocolo;
    }

    public void addFicheiroConfig(FicheiroConfiguracao  fc) {
        this.ficheiroConfiguracao.add(fc);
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
    public boolean sameAs(final Object other) {
        if (!(other instanceof Maquina)) {
            return false;
        }
        final Maquina that = (Maquina) other;
        if (this == that) {
            return true;
        }
        return this.identity().equals(that.identity()) && this.numSerie.equals(that.numSerie)
                && this.descricao.equals(that.descricao) && this.dataInstalacao.equals(that.dataInstalacao)
                && this.marca.equals(that.marca) && this.modelo.equals(that.modelo)
                && this.ficheiroConfiguracao.equals(that.ficheiroConfiguracao) && this.idProtocolo == that.idProtocolo;
    }

    public String ficheiroConfiguracaoToString() {
        String ficheiro = "";
        Iterable<FicheiroConfiguracao> configuracoes = this.ficheiroConfiguracao;
        for (FicheiroConfiguracao config : configuracoes) {
            ficheiro += (config + ",");
        }
        if (!ficheiro.isEmpty())
            ficheiro = ficheiro.substring(0, ficheiro.length() - 1);
        return "{" + ficheiro + "}";
    }
}
