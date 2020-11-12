package eapli.base.materiaprimamanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CodigoInternoMateria implements ValueObject, Comparable<CodigoInternoMateria> {

    private static final long serialVersionUID = 1L;

    private long code;

    public CodigoInternoMateria(long codigo) {
        String c = String.valueOf(codigo);
        if (StringPredicates.isNullOrEmpty(c)) {
            throw new IllegalArgumentException(
                    "codigo should neither be null nor empty");
        }
        // expression
        this.code = codigo;
    }

    protected CodigoInternoMateria() {
        this.code = 0;
    }

    public static CodigoInternoMateria valueOf(final long codigo) {
        return new CodigoInternoMateria(codigo);
    }

    public static CodigoInternoMateria valueOf(final String codigo){
        return new CodigoInternoMateria(Long.parseLong(codigo));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodigoInternoMateria that = (CodigoInternoMateria) o;
        return code == that.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public int compareTo(CodigoInternoMateria arg0) {
        String s1 = String.valueOf(code);
        String s2 = String.valueOf(arg0.code);
        return s1.compareTo(s2);
    }

    @Override
    public String toString() {
        return "code=" + code ;
    }

    public String toString2(){
        return ""+code;
    }
}