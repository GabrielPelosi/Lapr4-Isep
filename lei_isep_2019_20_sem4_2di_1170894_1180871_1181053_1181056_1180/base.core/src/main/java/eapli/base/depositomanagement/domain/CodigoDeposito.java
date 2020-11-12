package eapli.base.depositomanagement.domain;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CodigoDeposito implements ValueObject, Comparable<CodigoDeposito>{

    private static final long serialVersionUID = 1L;

    private long code;

    public CodigoDeposito(long codigo) {
        String c = String.valueOf(codigo);
        if (StringPredicates.isNullOrEmpty(c)) {
            throw new IllegalArgumentException(
                    "codigo should neither be null nor empty");
        }
        // expression
        this.code = codigo;
    }

    protected CodigoDeposito() {
        this.code = 0;
    }

    public static CodigoDeposito valueOf(final long codigo) {
        return new CodigoDeposito(codigo);
    }

    public static CodigoDeposito valueOf(final String codigo) {
        return new CodigoDeposito(Long.parseLong(codigo));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoDeposito that = (CodigoDeposito) o;
        return code == that.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public int compareTo(CodigoDeposito arg0) {
        String s1 = String.valueOf(code);
        String s2 = String.valueOf(arg0.code);
        return s1.compareTo(s2);
    }

    @Override
    public String toString() {
        return  String.valueOf(code);
    }
}
