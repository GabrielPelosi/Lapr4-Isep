package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class UnidadeProduto implements ValueObject {

    private String unidade;

    public UnidadeProduto(String u) {
        if (StringPredicates.isNullOrEmpty(u))
            throw new IllegalArgumentException("Measurement unity must not be empty nor null.");
        this.unidade = u;
    }

    public UnidadeProduto() {
        this.unidade = "";
    }

    @Override
    public String toString(){
        return this.unidade;
    }
}
