package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaProduto implements ValueObject {

    private String categoria;

    public CategoriaProduto(String c) {
        if (StringPredicates.isNullOrEmpty(c))
            throw new IllegalArgumentException("Measurement unity must not be empty nor null.");
        this.categoria = c;
    }

    public CategoriaProduto() {
        this.categoria = "";
    }

    @Override
    public String toString() {
        return categoria;
    }
}
