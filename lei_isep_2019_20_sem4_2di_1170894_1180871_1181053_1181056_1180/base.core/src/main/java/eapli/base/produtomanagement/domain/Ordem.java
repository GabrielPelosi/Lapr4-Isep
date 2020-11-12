package eapli.base.produtomanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ordem implements AggregateRoot<CodigoOrdem> {

    @Id
    private CodigoOrdem codigoOrdem;

    @Column(nullable = false)
    private String dataEmissao;
    private String dataPrevista;

    @Column(nullable = false)
    @ElementCollection
    private Set<CodigoEncomenda> sequenciaEncomenda;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Produto produto;
    private UnidadeProduto unidade;
    private int quantidade;

    @Column(nullable = false)
    private String estado;


    public Ordem(CodigoOrdem cO, String dE, String dP, Produto p, int q, UnidadeProduto u, Set<CodigoEncomenda> sE, String e) {
        if (cO == null || dE == null || dP == null || u == null || q == 0 || sE == null || p == null || e == null)
            throw new IllegalArgumentException("Arguments must not be null.");
        if(!e.equals("pendente") && !e.equals("Em Execucao") && !e.equals("Execucao Parada Temporariamente") && !e.equals("Concluida") && !e.equals("Suspensa")){
            throw new IllegalArgumentException("Estado has to be one of the following : pendente, Em Execucao, Execucao Parada Temporariamente, Concluida or Suspensa");
        }
        this.codigoOrdem = cO;
        this.dataEmissao = dE;
        this.dataPrevista = dP;
        this.produto = p;
        this.quantidade = q;
        this.unidade = u;
        this.sequenciaEncomenda = sE;
        this.estado = e;
    }

    protected Ordem() {
        // for ORM only
    }

    public void executar() {
        this.estado = "Em Execucao";
    }

    public void interromper() {
        this.estado = "Execucao Parada Temporariamente";
    }

    public void concluir() {
        this.estado = "Concluida";
    }

    public boolean hasEncomenda(CodigoEncomenda idEncomenda) {
        return this.sequenciaEncomenda.contains(idEncomenda);
    }

    public boolean isInEstado(String estado) {

        return this.estado.equals(estado);
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
        if (!(other instanceof Ordem)) {
            return false;
        }

        final Ordem that = (Ordem) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public CodigoOrdem identity() {
        return this.codigoOrdem;
    }

    public String dataEm(){
        return this.dataEmissao;
    }

    public String dataPre(){
        return this.dataPrevista;
    }

    public Produto prod(){
        return this.produto;
    }

    public int quant(){
        return this.quantidade;
    }

    public UnidadeProduto unid(){
        return this.unidade;
    }

    public String est(){
        return this.estado;
    }

    public String sequenciaEncomendaToString() {
        String sequencia = "";
        Iterable<CodigoEncomenda> codigosSeq = this.sequenciaEncomenda;
        for (CodigoEncomenda codigo : codigosSeq) {
            sequencia += (codigo + ",");
        }
        if (!sequencia.isEmpty())
            sequencia = sequencia.substring(0, sequencia.length() - 1);
        return sequencia
                ;
    }

}

