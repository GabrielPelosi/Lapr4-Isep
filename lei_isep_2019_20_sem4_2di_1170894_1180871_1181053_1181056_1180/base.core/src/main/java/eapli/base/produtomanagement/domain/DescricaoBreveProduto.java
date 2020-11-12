package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class DescricaoBreveProduto implements ValueObject {

    private String descricaoBreve;

    public DescricaoBreveProduto(final String descricaoBreve) {
        if (StringPredicates.isNullOrEmpty(descricaoBreve))
            throw new IllegalArgumentException("Brief desription must not be null or empty.");
        this.descricaoBreve = descricaoBreve;
    }

    public DescricaoBreveProduto() {
        this.descricaoBreve = "";
    }

    @Override
    public String toString() {
        return this.descricaoBreve;
    }
}