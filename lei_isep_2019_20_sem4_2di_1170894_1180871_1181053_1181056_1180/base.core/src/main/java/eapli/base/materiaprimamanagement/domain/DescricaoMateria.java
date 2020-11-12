package eapli.base.materiaprimamanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoMateria implements ValueObject {

    private String description;

    public DescricaoMateria(String descricao) {
        if (StringPredicates.isNullOrEmpty(descricao)) {
            throw new IllegalArgumentException(
                    "descricao da materia should neither be null nor empty");
        }
        // expression
        this.description = descricao;
    }

    protected DescricaoMateria() {
        this.description = "";
    }

    public static DescricaoMateria valueOf(final String descricao) {
        return new DescricaoMateria(descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescricaoMateria)) {
            return false;
        }

        final DescricaoMateria that = (DescricaoMateria) o;
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
