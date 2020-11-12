package eapli.base.materiaprimamanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CodigoCategoria implements ValueObject, Comparable<CodigoCategoria> {

    private static final long serialVersionUID = 1L;

    private long code;

    public CodigoCategoria(long codigo) {
        String c = String.valueOf(codigo);
        if (StringPredicates.isNullOrEmpty(c)) {
            throw new IllegalArgumentException(
                    "codigo should neither be null nor empty");
        }
        // expression
        this.code = codigo;
    }

    protected CodigoCategoria() {
        this.code = 0;
    }

    public static CodigoCategoria valueOf(final long codigo) {
        return new CodigoCategoria(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoCategoria that = (CodigoCategoria) o;
        return code == that.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public int compareTo(CodigoCategoria arg0) {
        String s1 = String.valueOf(code);
        String s2 = String.valueOf(arg0.code);
        return s1.compareTo(s2);
    }

    @Override
    public String toString() {
        return "" + code;
    }
}