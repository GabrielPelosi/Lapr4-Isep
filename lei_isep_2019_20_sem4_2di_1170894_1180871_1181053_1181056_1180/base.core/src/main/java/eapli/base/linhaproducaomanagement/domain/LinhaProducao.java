package eapli.base.linhaproducaomanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
public class LinhaProducao implements AggregateRoot<IdLinhaProducao> {

    @Version
    private Long version;

    @Id
    private IdLinhaProducao idLinhaProducao;

    @Column(nullable = false)
    @ElementCollection
    private List<CodigoInternoMaquina> sequenciaMaquina;
    @Column(nullable = false)
    private boolean ativa;
    @Column
    private Calendar processamentoRecente;


    public LinhaProducao(final IdLinhaProducao idLinhaProducao, ArrayList<CodigoInternoMaquina> sequenciaMaquina) {
        if (idLinhaProducao == null || sequenciaMaquina == null) {
            throw new IllegalArgumentException("Id and Sequence should not be null.");
        }
        this.idLinhaProducao = idLinhaProducao;
        this.sequenciaMaquina = sequenciaMaquina;
        this.ativa = false;
        this.processamentoRecente = null;
    }

    public LinhaProducao(final IdLinhaProducao idLinhaProducao) {
        if (idLinhaProducao == null) {
            throw new IllegalArgumentException("Id should not be empty nor null.");
        }
        this.idLinhaProducao = idLinhaProducao;
        this.sequenciaMaquina = new ArrayList<>();
        this.ativa = false;
        this.processamentoRecente = null;
    }

    public LinhaProducao() {
        // for ORM only
    }


    public void addMaquina(String codigoInternoMaquina) {
        addMaquina(CodigoInternoMaquina.valueOf(codigoInternoMaquina));
    }

    public void ativar() {
        this.ativa = true;
    }

    public void desativar() {
        this.ativa = false;
    }

    public void marcarProcessamento(Calendar processamentoRecente) {
        this.processamentoRecente = processamentoRecente;
    }

    /**
     * Insere a maquina na linda de producao no fim.
     *
     * @param codigo codigo da maquina a inserir.
     */
    public void addMaquina(CodigoInternoMaquina codigo) {
        addMaquina(codigo, -1);
    }

    public void addMaquina(String codigoInternoMaquina, int posicao) {
        addMaquina(CodigoInternoMaquina.valueOf(codigoInternoMaquina), posicao);
    }

    /**
     * Insere a maquina na linha de producao na posicao dada.
     *
     * @param codigo  codigo da maquina a inserir.
     * @param posicao posicao da maquina a inserir na linha.
     */
    public void addMaquina(CodigoInternoMaquina codigo, int posicao) {
        if (posicao == -1)
            this.sequenciaMaquina.add(codigo);
        else
            this.sequenciaMaquina.add(posicao, codigo);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLinhaProducao);
    }

    @Override
    public boolean sameAs(Object other) {
        LinhaProducao that = (LinhaProducao) other;
        return idLinhaProducao.equals(that.idLinhaProducao);
    }

    @Override
    public IdLinhaProducao identity() {
        return this.idLinhaProducao;
    }

    public List<CodigoInternoMaquina> maquinas() {
        return this.sequenciaMaquina;
    }

    public boolean estado() {
        return this.ativa;
    }

    public Calendar processamentoRecente() {
        return processamentoRecente;
    }

    public String sequenciaMaquinaToString() {
        String sequencia = "";
        Iterable<CodigoInternoMaquina> codigosSeq = this.sequenciaMaquina;
        for (CodigoInternoMaquina codigo : codigosSeq) {
            sequencia += (codigo + ",");
        }
        if (!sequencia.isEmpty())
            sequencia = sequencia.substring(0, sequencia.length() - 1);
        return "{" + sequencia +
                '}';
    }

    public String sequenciaMaquinaToString1() {
        String sequencia = "";
        Iterable<CodigoInternoMaquina> codigosSeq = this.sequenciaMaquina;
        for (CodigoInternoMaquina codigo : codigosSeq) {
            sequencia += (codigo + ",");
        }
        if (!sequencia.isEmpty())
            sequencia = sequencia.substring(0, sequencia.length() - 1);
        return  sequencia;
    }

    public String processamentoRecenteToString() {
        if (processamentoRecente == null) {
            return "null";
        } else {
            return String.format("%04d-%02d-%02d %02d:%02d:%02d",
                    processamentoRecente.get(Calendar.YEAR), processamentoRecente.get(Calendar.MONTH),
                    processamentoRecente.get(Calendar.DATE), processamentoRecente.get(Calendar.HOUR_OF_DAY),
                    processamentoRecente.get(Calendar.MINUTE), processamentoRecente.get(Calendar.SECOND));
        }
    }
}
