package eapli.base.linhaproducaomanagement.domain;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class IdLinhaProducao implements ValueObject, Comparable<IdLinhaProducao> {

    private long id;

    public IdLinhaProducao(long id) {
        String c = String.valueOf(id);
        if (StringPredicates.isNullOrEmpty(c)) {
            throw new IllegalArgumentException("Id should neither be null nor empty");
        }
        this.id = id;
    }

    protected IdLinhaProducao() {
        this.id=0;
    }

    public static IdLinhaProducao valueOf(String id){
        return new IdLinhaProducao(Long.parseLong(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdLinhaProducao that = (IdLinhaProducao) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public int compareTo(IdLinhaProducao o) {
        if (this.id > o.id) {
            return 1;
        }
        if (this.id < o.id) {
            return -1;
        }
        return 0;
    }
}
