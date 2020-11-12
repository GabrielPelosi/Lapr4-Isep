package eapli.base.produtomanagement.domain;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class FichaProducao implements AggregateRoot<CodigoFabrico> {

    @Version
    private Long version;

    @Id
    private CodigoFabrico codFabrico;

    @Column(nullable = false)
    @ElementCollection
    private List<CodigoInternoMateria> sequenciaMateria;

    @Column(nullable = false)
    @ElementCollection
    private List<Double> sequenciaQuantidade;

    @Column(nullable = false)
    @ElementCollection
    private List<String> sequenciaUnidade;


    protected FichaProducao() {
        // for ORM only
    }

    public FichaProducao(CodigoFabrico codFabrico, List<CodigoInternoMateria> sequenciaMateria, List<Double> sequenciaQuantidade, List<String> sequenciaUnidade) {
        this.codFabrico = codFabrico;
        this.sequenciaMateria = sequenciaMateria;
        this.sequenciaQuantidade = sequenciaQuantidade;
        this.sequenciaUnidade = sequenciaUnidade;
    }

    public List<CodigoInternoMateria> sequenciaMateria() {
        return this.sequenciaMateria;
    }

    public List<Double> sequenciaQuantidade() {
        return this.sequenciaQuantidade;
    }

    public List<String> sequenciaUnidade() {
        return this.sequenciaUnidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FichaProducao)) return false;
        FichaProducao fProd = (FichaProducao) o;
        return codFabrico == fProd.codFabrico;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public int compareTo(CodigoFabrico other) {
        return this.codFabrico.compareTo(other);
    }


    @Override
    public CodigoFabrico identity() {
        return codFabrico;
    }

    @Override
    public boolean hasIdentity(CodigoFabrico otherId) {
        return codFabrico.equals(otherId);
    }
}



