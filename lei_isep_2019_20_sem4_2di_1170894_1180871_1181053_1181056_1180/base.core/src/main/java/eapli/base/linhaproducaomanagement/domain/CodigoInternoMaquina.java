package eapli.base.linhaproducaomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class CodigoInternoMaquina implements ValueObject, Comparable<CodigoInternoMaquina> {

    private String idMaquina;

    public CodigoInternoMaquina(String codigo) {
        if (StringPredicates.isNullOrEmpty(codigo)) {
            throw new IllegalArgumentException(
                    "codigo should neither be null nor empty");
        }
        // expression
        this.idMaquina = codigo;
    }

    protected CodigoInternoMaquina() {
        // for ORM
    }

    public static CodigoInternoMaquina valueOf(final String codigo) {
        return new CodigoInternoMaquina(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodigoInternoMaquina)) {
            return false;
        }

        final CodigoInternoMaquina that = (CodigoInternoMaquina) o;
        return this.idMaquina.equals(that.idMaquina);
    }

    @Override
    public int hashCode() {
        return this.idMaquina.hashCode();
    }

    @Override
    public String toString() {
        return this.idMaquina;
    }

    @Override
    public int compareTo(CodigoInternoMaquina arg0) {
        return idMaquina.compareTo(arg0.idMaquina);
    }

}
