package eapli.base.materiaprimamanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;


import javax.persistence.*;

@Entity
public class MateriaPrima implements AggregateRoot<CodigoInternoMateria>{


    @Id
    private CodigoInternoMateria codInterno;


    @Column(nullable = false)
    private DescricaoMateria descricao;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Categoria categoria;

    @Lob
    private FichaTecnica ficha;




    public MateriaPrima(final CodigoInternoMateria codInterno, final DescricaoMateria descricao, final Categoria categoria, final FichaTecnica ficha) {
        if (codInterno == null || descricao == null || categoria == null || ficha == null) {
            throw new IllegalArgumentException();
        }
        this.codInterno = codInterno;
        this.descricao = descricao;
        this.categoria = categoria;
        this.ficha = ficha;
    }

    protected MateriaPrima() {
        // for ORM only
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(java.lang.Object other) {
        if (!(other instanceof MateriaPrima)) {
            return false;
        }

        final MateriaPrima that = (MateriaPrima) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    public CodigoInternoMateria identity() {
        return this.codInterno;
    }

    public DescricaoMateria descricao() {
        return this.descricao;
    }

    public Categoria categoria(){return this.categoria;}

    public FichaTecnica ficha(){return this.ficha;}

}
