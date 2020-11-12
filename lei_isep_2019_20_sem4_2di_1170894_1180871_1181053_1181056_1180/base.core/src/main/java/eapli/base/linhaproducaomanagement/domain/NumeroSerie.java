package eapli.base.linhaproducaomanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import java.util.Objects;

@Embeddable
public class NumeroSerie implements ValueObject, Comparable<NumeroSerie> {

    private int numero;

    public NumeroSerie(int num) {
        if (StringPredicates.isNullOrEmpty(String.valueOf(num))) {
            throw new IllegalArgumentException(
                    "numero de serie should neither be null nor empty");
        }
        // expression
        this.numero = num;
    }

    protected NumeroSerie() {
        // for ORM
    }

    public static NumeroSerie valueOf(final int num) {
        return new NumeroSerie(num);
    }

    @Override
    public String toString() {
        return String.valueOf(this.numero);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumeroSerie that = (NumeroSerie) o;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public int compareTo(NumeroSerie o) {
        if(this.numero == o.numero){
            return 0;
        } else{ if(this.numero > o.numero){
            return 1;
        }else{
            return -1;
        }
        }
    }
}
