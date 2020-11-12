package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CodigoFabrico implements ValueObject, Comparable<CodigoFabrico> {

    private String codigoFabrico;

    public CodigoFabrico(final String codigoFabrico) {
        if (StringPredicates.isNullOrEmpty(codigoFabrico))
            throw new IllegalArgumentException("Factory Code must not be null or empty.");
        this.codigoFabrico = codigoFabrico;
    }

    public CodigoFabrico() {
        this.codigoFabrico = "";
    }

    public static CodigoFabrico valueOf(final String codigo) {
        return new CodigoFabrico(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoFabrico that = (CodigoFabrico) o;
        return codigoFabrico.equals(that.codigoFabrico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoFabrico);
    }

    @Override
    public int compareTo(CodigoFabrico o) {
        return this.codigoFabrico.compareTo(o.codigoFabrico);
    }

    @Override
    public String toString() {
        return codigoFabrico ;
    }
}


