package eapli.base.mensagemmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Tempo implements ValueObject {

    @Transient
    private final int LIMIT_MILLIS = 1000;
    @Transient
    private final int LIMIT_SECOND = 60;
    @Transient
    private final int LIMIT_MINUTE = 60;
    @Transient
    private final int LIMIT_HOUR = 24;

    private int dia;
    private int hora;
    private int minuto;
    private int segundo;
    private long millis;

    public Tempo() {
        this.dia = 0;
        this.hora = 0;
        this.minuto = 0;
        this.segundo = 0;
        this.millis = 0;
    }

    public static Tempo plusTempo(Tempo one, Tempo two) {
        return one.plusTempo(two);
    }

    public static Tempo diferenca(Tempo one, Tempo two) {
        return one.diferenca(two);
    }

    public Tempo withDia(int dia) {
        this.dia += dia;
        return this;
    }

    public Tempo withHora(int hora) {
        this.hora += hora;
        if (this.hora >= LIMIT_HOUR) {
            this.dia += this.hora / LIMIT_HOUR;
            this.hora %= LIMIT_HOUR;
        }
        return this.withDia(0);
    }

    public Tempo withMinuto(int minuto) {
        this.minuto += minuto;
        if (this.minuto >= LIMIT_MINUTE) {
            this.hora += this.minuto / LIMIT_MINUTE;
            this.minuto %= LIMIT_MINUTE;
        }
        return this.withHora(0);
    }

    public Tempo withSegundo(int segundo) {
        this.segundo += segundo;
        if (this.segundo >= LIMIT_SECOND) {
            this.minuto += this.segundo / LIMIT_SECOND;
            this.segundo %= LIMIT_SECOND;
        }
        return this.withMinuto(0);
    }

    public Tempo withMillis(long millis) {
        this.millis += millis;
        if (this.millis >= LIMIT_MILLIS) {
            this.segundo += this.millis / LIMIT_MILLIS;
            this.millis %= LIMIT_MILLIS;
        }
        return this.withSegundo(0);
    }

    public Tempo plusTempo(Tempo other) {
        return new Tempo().withSegundo(this.toSeconds() + other.toSeconds());
    }

    private int toSeconds() {
        return ((dia * LIMIT_HOUR + hora) * LIMIT_MINUTE + minuto) * LIMIT_SECOND + segundo;
    }

    public Tempo diferenca(Tempo outro) {
        int dif = this.toSeconds() - outro.toSeconds();
        dif = dif < 0 ? -dif : dif;

        return new Tempo().withSegundo(dif);
    }

    public int hora() {
        return hora;
    }

    public int minuto() {
        return minuto;
    }

    public int segundo() {
        return segundo;
    }

    public long toMillis() {
        return ((((dia * 24) + hora) * 60 + minuto) * 60 + segundo) * 1000 + millis;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Tempo tempo = (Tempo) other;
        return dia == tempo.dia
                && hora == tempo.hora
                && minuto == tempo.minuto
                && segundo == tempo.segundo;
    }

    @Override
    public String toString() {
        return dia + "d " + hora + "h " + minuto + "m " + segundo + "." + millis + "s";
    }
}
