package eapli.base.materiaprimamanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoCategoria implements ValueObject {

    private String description;

    public DescricaoCategoria(String descricao) {
        if (StringPredicates.isNullOrEmpty(descricao)) {
            throw new IllegalArgumentException(
                    "descricao da categoria should neither be null nor empty");
        }
        // expression
        this.description = descricao;
    }

    protected DescricaoCategoria() {
        this.description = "";
    }

    public static DescricaoCategoria valueOf(final String descricao) {
        return new DescricaoCategoria(descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescricaoCategoria)) {
            return false;
        }

        final DescricaoCategoria that = (DescricaoCategoria) o;
        return this.description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public String toString() {
        return this.description;
    }


}
