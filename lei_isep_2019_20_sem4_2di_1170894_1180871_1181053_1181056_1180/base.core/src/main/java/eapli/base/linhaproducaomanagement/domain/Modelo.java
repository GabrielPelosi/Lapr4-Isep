package eapli.base.linhaproducaomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Modelo implements ValueObject, Comparable<Modelo> {

    private static final long serialVersionUID = 1L;

    private String modelo;

    public Modelo(String modelo) {
        if (StringPredicates.isNullOrEmpty(modelo)) {
            throw new IllegalArgumentException(
                    "modelo should neither be null nor empty");
        }
        // expression
        this.modelo = modelo;
    }

    protected Modelo() {
        // for ORM
    }

    public static Modelo valueOf(final String modelo) {
        return new Modelo(modelo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modelo)) {
            return false;
        }

        final Modelo that = (Modelo) o;
        return this.modelo.equals(that.modelo);
    }

    @Override
    public int hashCode() {
        return this.modelo.hashCode();
    }

    @Override
    public String toString() {
        return this.modelo;
    }

    @Override
    public int compareTo(Modelo arg0) {
        return modelo.compareTo(arg0.modelo);
    }

}
