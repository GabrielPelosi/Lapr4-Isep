package eapli.base.linhaproducaomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoMaquina implements ValueObject, Comparable<DescricaoMaquina> {

    private static final long serialVersionUID = 1L;

    private String descricao;

    public DescricaoMaquina(String desc) {
            if (StringPredicates.isNullOrEmpty(desc)) {
                throw new IllegalArgumentException(
                        "descricao should neither be null nor empty");
            }
            // expression
            this.descricao = desc;
    }
    protected DescricaoMaquina() {
            // for ORM
    }

    public static DescricaoMaquina valueOf(final String desc) {
            return new DescricaoMaquina(desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescricaoMaquina)) {
            return false;
        }

        final DescricaoMaquina that = (DescricaoMaquina) o;
        return this.descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
            return this.descricao.hashCode();
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public int compareTo(DescricaoMaquina arg0) {
        return descricao.compareTo(arg0.descricao);
    }
}
