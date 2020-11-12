package eapli.base.mensagemmanagement.domain;

import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Producao implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long idProducao;

    @Column(nullable = false)
    private CodigoFabrico produto;
    private double quantidade;
    private CodigoOrdem ordem;

    public Producao(CodigoFabrico produto, double quantidade, CodigoOrdem ordem) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.ordem = ordem;
    }

    protected Producao() {
        //for ORM
    }

    public static Producao valueOf(String produto, double quantidade, CodigoOrdem ordem) {
        return new Producao(CodigoFabrico.valueOf(produto), quantidade, ordem);
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Producao producao = (Producao) other;
        return Double.compare(producao.quantidade, quantidade) == 0 &&
                Objects.equals(idProducao, producao.idProducao) &&
                Objects.equals(produto, producao.produto);
    }

    public CodigoFabrico produto() {
        return produto;
    }

    public double quantidade() {
        return quantidade;
    }

    public String producaoString() {
        return String.format("%1$-15s%2$-15s%3$-15s%4$-15s", this.idProducao.toString(), this.ordem.toString(), this.produto.toString(), Double.toString(this.quantidade));
    }

    @Override
    public Long identity() {
        return idProducao;
    }

    @Override
    public String toString() {
        return idProducao.toString();
    }
}
