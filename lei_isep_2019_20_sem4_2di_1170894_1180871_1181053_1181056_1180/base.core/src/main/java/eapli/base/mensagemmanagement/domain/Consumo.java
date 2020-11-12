package eapli.base.mensagemmanagement.domain;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Consumo implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long idConsumo;

    @Column(nullable = false)
    private CodigoInternoMateria materia;
    private double quantidade;
    private CodigoOrdem ordem;

    public Consumo(CodigoInternoMateria materia, double quantidade, CodigoOrdem ordem) {
        this.materia = materia;
        this.quantidade = quantidade;
        this.ordem = ordem;
    }

    protected Consumo() {
        //for ORM
    }

    public static Consumo valueOf(String materia, double quantidade, CodigoOrdem ordem) {
        return new Consumo(CodigoInternoMateria.valueOf(materia), quantidade, ordem);
    }

    public double quantidade() {
        return quantidade;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Consumo consumo = (Consumo) other;
        return Double.compare(consumo.quantidade, quantidade) == 0 &&
                Objects.equals(idConsumo, consumo.idConsumo) &&
                Objects.equals(materia, consumo.materia);
    }

    @Override
    public Long identity() {
        return idConsumo;
    }

    public String consumoString() {
        return String.format("%1$-15s%2$-15s%3$-15s%4$-15s", this.idConsumo.toString(), this.ordem.toString(), this.materia.toString2(), this.quantidade);
    }

    @Override
    public String toString() {
        return idConsumo.toString();
    }
}
