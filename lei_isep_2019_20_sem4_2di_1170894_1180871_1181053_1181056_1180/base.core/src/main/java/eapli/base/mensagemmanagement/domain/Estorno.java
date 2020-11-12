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
public class Estorno implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long idEstorno;

    @Column(nullable = false)
    private CodigoInternoMateria materia;
    private double quantidade;
    private CodigoOrdem ordem;

    public Estorno(CodigoInternoMateria materia, double quantidade, CodigoOrdem ordem) {
        this.materia = materia;
        this.quantidade = quantidade;
        this.ordem = ordem;
    }

    protected Estorno() {
        //for ORM
    }

    public static Estorno valueOf(String materia, double quantidade, CodigoOrdem ordem) {
        return new Estorno(CodigoInternoMateria.valueOf(materia), quantidade, ordem);
    }

    public String estornoString() {
        return String.format("%1$-15s%2$-15s%3$-15s%4$-15s", this.idEstorno.toString(), this.ordem.toString(), this.materia.toString2(), this.quantidade);
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Estorno estorno = (Estorno) other;
        return Double.compare(estorno.quantidade, quantidade) == 0 &&
                Objects.equals(idEstorno, estorno.idEstorno) &&
                Objects.equals(materia, estorno.materia);
    }

    @Override
    public Long identity() {
        return idEstorno;
    }

    @Override
    public String toString() {
        return idEstorno.toString();
    }
}
