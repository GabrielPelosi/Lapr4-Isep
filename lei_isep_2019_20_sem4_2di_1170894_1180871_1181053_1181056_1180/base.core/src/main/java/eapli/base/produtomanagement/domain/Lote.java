package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lote implements AggregateRoot<String> {

    @Id
    private String idLote;

    @Column
    private String produto;
    private double quantidade;

    public Lote(String idLote){
        this.idLote = idLote;
    }

    protected Lote() {
        //for ORM
    }

    public static Lote valueOf(String idLote) {
        return new Lote(idLote);
    }

    public void adicionarProdutoQuantidade(double quantidade) {
        this.quantidade += quantidade;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String identity() {
        return this.idLote;
    }
}
