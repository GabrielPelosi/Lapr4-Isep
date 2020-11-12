package eapli.base.linhaproducaomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class FicheiroConfiguracao implements ValueObject, Comparable<FicheiroConfiguracao>{

    private String configuracoes;

    public FicheiroConfiguracao(String config) {
        if (StringPredicates.isNullOrEmpty(config)) {
            throw new IllegalArgumentException(
                    "Configuracoes should neither be null nor empty");
        }
        this.configuracoes = config;
    }

    protected FicheiroConfiguracao() {
        // for ORM
    }

    public static FicheiroConfiguracao valueOf(final String config) {
        return new FicheiroConfiguracao(config);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheiroConfiguracao)) {
            return false;
        }

        final FicheiroConfiguracao that = (FicheiroConfiguracao) o;
        return this.configuracoes.equals(that.configuracoes);
    }

    @Override
    public int hashCode() {
        return this.configuracoes.hashCode();
    }

    @Override
    public String toString() {
        return this.configuracoes;
    }

    @Override
    public int compareTo(FicheiroConfiguracao arg0) {
        return configuracoes.compareTo(arg0.configuracoes);
    }
}
