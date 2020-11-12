package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class CodigoComercial implements ValueObject, Comparable<CodigoComercial> {

    private String codigoComercial;

    public CodigoComercial(final String codigoComercial) {
        if (StringPredicates.isNullOrEmpty(String.valueOf(codigoComercial)))
            throw new IllegalArgumentException("Comercial Code must not be null or empty.");
        this.codigoComercial = codigoComercial;
    }

    public CodigoComercial() {
        this.codigoComercial = "";
    }

    public static CodigoComercial valueOf(final String codigo) {
        return new CodigoComercial(codigo);
    }

    @Override
    public int compareTo(CodigoComercial o) {
        return this.codigoComercial.compareTo(o.codigoComercial);
    }

    @Override
    public String toString() {
        return this.codigoComercial;
    }
}