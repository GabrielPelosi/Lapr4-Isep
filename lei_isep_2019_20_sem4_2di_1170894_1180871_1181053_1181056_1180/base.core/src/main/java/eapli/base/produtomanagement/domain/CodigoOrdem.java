package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CodigoOrdem implements ValueObject, Comparable<CodigoOrdem>{

    private String codigoOrdem;

    public CodigoOrdem(final String codigoOrdem) {
        if (StringPredicates.isNullOrEmpty(codigoOrdem))
            throw new IllegalArgumentException("Code must not be null or empty.");
        this.codigoOrdem = codigoOrdem;
    }

    public CodigoOrdem() {
        this.codigoOrdem = "";
    }

    public static CodigoOrdem valueOf(final String codigo) {
        return new CodigoOrdem(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoOrdem that = (CodigoOrdem) o;
        return codigoOrdem.equals(that.codigoOrdem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoOrdem);
    }

    @Override
    public int compareTo(CodigoOrdem o) {
        return this.codigoOrdem.compareTo(o.codigoOrdem);
    }

    @Override
    public String toString() {
        return codigoOrdem ;
    }
}
