package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

@Entity
public class Produto implements AggregateRoot<CodigoFabrico> {

    @Id
    private CodigoFabrico codigoFabrico;

    @Column(nullable = false)
    private CodigoComercial codigoComercial;
    private DescricaoBreveProduto descricaoBreve;
    private DescricaoCompletaProduto descricaoCompleta;
    private UnidadeProduto unidade;
    private CategoriaProduto categoria;

    public Produto(CodigoFabrico cF, CodigoComercial cC, DescricaoBreveProduto dB, DescricaoCompletaProduto dC, UnidadeProduto u, CategoriaProduto c) {
        if(cF ==null || cC==null || dB==null || dC==null || u==null || c==null)
            throw new IllegalArgumentException("Arguments must not be null.");
        this.codigoFabrico = cF;
        this.codigoComercial=cC;
        this.descricaoBreve = dB;
        this.descricaoCompleta = dC;
        this.unidade = u;
        this.categoria = c;
    }

    protected Produto(){
        // for ORM only
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
        if (!(other instanceof Produto)) {
            return false;
        }

        final Produto that = (Produto) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public CodigoFabrico identity() {
        return this.codigoFabrico;
    }

    public CodigoComercial codigoComercial() {
        return this.codigoComercial;
    }

    public DescricaoBreveProduto descricaoBreve() {
        return this.descricaoBreve;
    }

    public DescricaoCompletaProduto descricaoCompleta() {
        return this.descricaoCompleta;
    }

    public UnidadeProduto unidade() {
        return this.unidade;
    }

    public CategoriaProduto categoria() {
        return this.categoria;
    }
}
