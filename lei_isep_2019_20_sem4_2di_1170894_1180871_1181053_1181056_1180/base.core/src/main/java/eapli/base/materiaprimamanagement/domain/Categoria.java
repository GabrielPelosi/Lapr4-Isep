package eapli.base.materiaprimamanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.StringPredicates;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Categoria implements AggregateRoot<CodigoCategoria> {

    private static final long serialVersionUID = 1L;

    @Id
    private CodigoCategoria codCategoria;

    @Column(nullable = false)
    private DescricaoCategoria descricao;

    public Categoria(final CodigoCategoria codCategoria, final DescricaoCategoria descricao) {
        if (codCategoria == null || descricao == null) {
            throw new IllegalArgumentException("Categoria ou descricao inválida");
        }
        this.codCategoria = codCategoria;
        this.descricao = descricao;

    }

    protected Categoria() {
    }

    @Override
    public boolean sameAs(java.lang.Object other) {
        if (!(other instanceof Categoria)) {
            return false;
        }

        final Categoria that = (Categoria) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    public CodigoCategoria identity() {
        return this.codCategoria;
    }

    public boolean validarCategoria(final String inputUser){
        if(inputUser.equalsIgnoreCase(descricao.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(codCategoria, categoria.codCategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codCategoria);
    }

    public DescricaoCategoria descricao() {
        return this.descricao;
    }

}
