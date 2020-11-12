package eapli.base.linhaproducaomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Marca implements ValueObject, Comparable<Marca> {

    private static final long serialVersionUID = 1L;

    private String marca;

    public Marca(String marca) {
        if (StringPredicates.isNullOrEmpty(marca)) {
            throw new IllegalArgumentException(
                    "marca should neither be null nor empty");
        }
        // expression
        this.marca = marca;
    }
    protected Marca() {
        // for ORM
    }

    public static Marca valueOf(final String marca) {
        return new Marca(marca);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Marca)) {
            return false;
        }

        final Marca that = (Marca) o;
        return this.marca.equals(that.marca);
    }

    @Override
    public int hashCode() {
        return this.marca.hashCode();
    }

    @Override
    public String toString() {
        return this.marca;
    }

    @Override
    public int compareTo(Marca arg0) {
        return marca.compareTo(arg0.marca);
    }
}
