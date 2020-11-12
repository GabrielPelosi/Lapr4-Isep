package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoCompletaProduto implements ValueObject {

    private String descricaoCompleta;

    public DescricaoCompletaProduto(final String descricaoCompleta) {
        if (StringPredicates.isNullOrEmpty(descricaoCompleta))
            throw new IllegalArgumentException("Complete desription must not be null or empty.");
        this.descricaoCompleta = descricaoCompleta;
    }

    public DescricaoCompletaProduto() {
        this.descricaoCompleta = "";
    }

    @Override
    public String toString(){
        return descricaoCompleta;
    }
}