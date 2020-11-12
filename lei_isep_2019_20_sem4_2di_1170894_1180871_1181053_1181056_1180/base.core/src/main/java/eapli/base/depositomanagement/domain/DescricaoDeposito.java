package eapli.base.depositomanagement.domain;

import eapli.base.materiaprimamanagement.domain.DescricaoMateria;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoDeposito implements ValueObject {

    private String description;

    public DescricaoDeposito(String descricao) {
        if (StringPredicates.isNullOrEmpty(descricao)) {
            throw new IllegalArgumentException(
                    "descricao da materia should neither be null nor empty");
        }
        // expression
        this.description = descricao;
    }

    protected DescricaoDeposito() {
        this.description = "";
    }

    public static DescricaoDeposito valueOf(final String descricao) {
        return new DescricaoDeposito(descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescricaoMateria)) {
            return false;
        }

        final DescricaoDeposito that = (DescricaoDeposito) o;
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
