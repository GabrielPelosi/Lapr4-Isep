package eapli.base.mensagemmanagement.domain;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
public class TempoProducao implements AggregateRoot<CodigoOrdem> {

    @Id
    private CodigoOrdem ordem;

    @AttributeOverrides({
            @AttributeOverride(name = "dia", column = @Column(name = "dia_bruto")),
            @AttributeOverride(name = "hora", column = @Column(name = "hora_bruto")),
            @AttributeOverride(name = "minuto", column = @Column(name = "minuto_bruto")),
            @AttributeOverride(name = "segundo", column = @Column(name = "segundo_bruto")),
            @AttributeOverride(name = "millis", column = @Column(name = "millis_bruto"))
    })
    @Embedded
    private Tempo tempoBrutoOrdem;
    @AttributeOverrides({
            @AttributeOverride(name = "dia", column = @Column(name = "dia_efetivo")),
            @AttributeOverride(name = "hora", column = @Column(name = "hora_efetivo")),
            @AttributeOverride(name = "minuto", column = @Column(name = "minuto_efetivo")),
            @AttributeOverride(name = "segundo", column = @Column(name = "segundo_efetivo")),
            @AttributeOverride(name = "millis", column = @Column(name = "millis_efetivo")),
    })
    @Embedded
    private Tempo tempoEfetivoOrdem;

    @Column(nullable = false)
    @ElementCollection
    private Map<CodigoInternoMaquina, Tempo> temposBrutos;
    @Column(nullable = false)
    @ElementCollection
    private Map<CodigoInternoMaquina, Tempo> temposEfetivos;

    public TempoProducao(CodigoOrdem ordem, Tempo tempoBrutoOrdem, Tempo tempoEfetivoOrdem, Map<CodigoInternoMaquina, Tempo> temposBrutos, Map<CodigoInternoMaquina, Tempo> temposEfetivos) {
        this.ordem = ordem;
        this.tempoBrutoOrdem = tempoBrutoOrdem;
        this.tempoEfetivoOrdem = tempoEfetivoOrdem;
        this.temposBrutos = temposBrutos;
        this.temposEfetivos = temposEfetivos;
    }

    protected TempoProducao() {
        //for ORM
    }

    public String tempoProducaoString() {
        StringBuilder temposBrutos = new StringBuilder();
        StringBuilder temposEfetivos = new StringBuilder();
        final String TEMPO_BRUTO = "TEMPO BRUTO";
        final String TEMPO_EFETIVO = "TEMPO EFETIVO";
        temposBrutos.append(String.format("%s ORDEM %s: %s\n", TEMPO_BRUTO, this.ordem.toString(), this.tempoBrutoOrdem));
        for (Map.Entry<CodigoInternoMaquina, Tempo> entry : this.temposBrutos.entrySet()) {
            temposBrutos.append(String.format("%s %s: %s\n", TEMPO_BRUTO, entry.getKey().toString(), entry.getValue().toString()));
        }
        temposEfetivos.append(String.format("%s ORDEM %s: %s\n", TEMPO_EFETIVO, this.ordem.toString(), this.tempoEfetivoOrdem));
        for (Map.Entry<CodigoInternoMaquina, Tempo> entry : this.temposEfetivos.entrySet()) {
            temposEfetivos.append(String.format("%s %s: %s\n", TEMPO_EFETIVO, entry.getKey().toString(), entry.getValue().toString()));
        }
        return temposBrutos.append("\n").append(temposEfetivos).toString();
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TempoProducao tempoProducao = (TempoProducao) other;
        return Objects.equals(ordem, tempoProducao.ordem)
                && Objects.equals(tempoBrutoOrdem, tempoProducao.tempoBrutoOrdem)
                && Objects.equals(tempoEfetivoOrdem, tempoProducao.tempoEfetivoOrdem)
                && Objects.equals(temposBrutos, tempoProducao.temposBrutos)
                && Objects.equals(temposEfetivos, tempoProducao.temposEfetivos)
                && tempoBrutoOrdem.equals(tempoProducao.tempoBrutoOrdem)
                && tempoEfetivoOrdem.equals(tempoProducao.tempoEfetivoOrdem);
    }

    @Override
    public CodigoOrdem identity() {
        return ordem;
    }
}
