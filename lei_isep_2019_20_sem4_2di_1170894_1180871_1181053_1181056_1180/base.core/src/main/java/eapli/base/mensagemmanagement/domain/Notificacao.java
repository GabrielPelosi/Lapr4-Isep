package eapli.base.mensagemmanagement.domain;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Notificacao implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(nullable = false)
    private CodigoInternoMaquina maquina;
    private Calendar data;

    public Notificacao(Tipo tipo, CodigoInternoMaquina maquina) {
        this.estado = Estado.ATIVA;
        this.tipo = tipo;
        this.maquina = maquina;
        this.data = Calendar.getInstance();
    }

    protected Notificacao() {
        // for ORM
    }

    public static Notificacao valueOf(Tipo tipo, CodigoInternoMaquina maquina) {
        return new Notificacao(tipo, maquina);
    }

    @Override
    public Long identity() {
        return id;
    }

    public String tipoString() {
        return tipo.toString();
    }

    public String maquinaString() {
        return maquina.toString();
    }

    public String dataString() {
        return String.format("%04d-%02d-%02d/%02d:%02d:%02d.%03d",
                this.data.get(Calendar.YEAR)
                , (this.data.get(Calendar.MONTH) + 1)
                , this.data.get(Calendar.DAY_OF_MONTH)
                , this.data.get(Calendar.HOUR_OF_DAY)
                , this.data.get(Calendar.MINUTE)
                , this.data.get(Calendar.SECOND)
                , this.data.get(Calendar.MILLISECOND)
        );
    }

    @Override
    public boolean sameAs(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notificacao that = (Notificacao) o;
        return id == that.id
                && estado.equals(that.estado)
                && tipo.equals(that.tipo)
                && maquina.equals(that.maquina)
                && data.equals(that.data);
    }

    public void arquivar() {
        this.estado = Estado.ARQUIVADA;
    }

    public enum Estado {
        ATIVA,
        ARQUIVADA
    }

    public enum Tipo {
        VARIAS_ORDENS_INICIADAS,
        MAQUINA_JA_ATIVA,
        ORDEM_NAO_EXISTENTE,
        FIM_SEM_INICIO,
        RETOMA_SEM_PARAGEM,
        INTERROMPER_MAQUINA_NAO_ATIVA,
        QUANTIDADE_DEPOSITO_INSUFICIENTE_CONSUMO,
        FICHA_PRODUCAO_NAO_EXISTE,
        MATERIA_NECESSARIA_NAO_CONSUMIDA,
        MATERIA_ESTORNO_NAO_CONSUMIDA,
        ENTREGA_SEM_PRODUCAO,
        ENTREGA_SEM_QUANTIDADE_SUFICIENTE,
        ORDEM_TERMINADA,
        FICHEIRO_ACABADO_SEM_FECHAR,
        PRODUTO_NAO_EXISTENTE,
        DEPOSITO_NAO_EXISTENTE,
        MATERIA_NAO_EXISTENTE,
        MATERIA_NAO_NECESSARIA,
        PRODUTO_NAO_NECESSARIO,
    }

}
