/*package eapli.base.mensagemmanagement.domain;

import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.List;

@Entity
public class EspecificacoesProcessamento implements AggregateRoot<Long> {

    public static final long ID_ESPECIFICACOES = 1L;
    @Id
    private Long idEspecificacoesProcessamento;
    @Column
    private Calendar horaInicial;
    private Calendar horaFinal;
    @ElementCollection
    private List<IdLinhaProducao> linhasProducaoProcessamento;

    public EspecificacoesProcessamento(Calendar horaInicial, Calendar horaFinal, List<IdLinhaProducao> linhasProducaoProcessamento) {
        this.idEspecificacoesProcessamento = ID_ESPECIFICACOES;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.linhasProducaoProcessamento = linhasProducaoProcessamento;
    }

    protected EspecificacoesProcessamento() {
        //for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof EspecificacoesProcessamento)) {
            return false;
        }

        final EspecificacoesProcessamento that = (EspecificacoesProcessamento) other;
        if (this == that) {
            return true;
        }
        return this.identity().equals(that.identity());
    }

    public Calendar horaInicial() {
        return horaInicial;
    }

    public Calendar horaFinal() {
        return horaFinal;
    }

    @Override
    public Long identity() {
        return idEspecificacoesProcessamento;
    }

    @Override
    public String toString() {
        return idEspecificacoesProcessamento.toString();
    }
}*/
