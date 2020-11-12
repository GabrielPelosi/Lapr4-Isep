package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class CodigoEncomenda implements ValueObject, Comparable<CodigoEncomenda> {

    private String code;

    public CodigoEncomenda(String codigo) {
        if (StringPredicates.isNullOrEmpty(codigo)) {
            throw new IllegalArgumentException(
                    "codigo should neither be null nor empty");
        }
        // expression
        this.code = codigo;
    }

    protected CodigoEncomenda() {
        // for ORM
    }

    public static CodigoEncomenda valueOf(final String codigo) {
        return new CodigoEncomenda(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodigoEncomenda)) {
            return false;
        }

        final CodigoEncomenda that = (CodigoEncomenda) o;
        return this.code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public int compareTo(CodigoEncomenda arg0) {
        return code.compareTo(arg0.code);
    }
}
