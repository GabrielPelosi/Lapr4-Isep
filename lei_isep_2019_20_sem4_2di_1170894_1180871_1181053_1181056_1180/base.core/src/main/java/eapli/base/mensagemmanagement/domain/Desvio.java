package eapli.base.mensagemmanagement.domain;

import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.CodigoOrdem;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;
import java.util.Objects;

@Entity
public class Desvio implements AggregateRoot<CodigoOrdem> {

    @Id
    private CodigoOrdem ordem;

    @Column(nullable = false)
    @ElementCollection
    private Map<CodigoInternoMateria, Double> materias;
    @Column(nullable = false)
    private CodigoFabrico produto;
    private double quantidadeProduto;

    public Desvio(CodigoOrdem ordem, Map<CodigoInternoMateria, Double> materias, CodigoFabrico produto, double quantidadeProduto) {
        this.ordem = ordem;
        this.materias = materias;
        this.produto = produto;
        this.quantidadeProduto = quantidadeProduto;
    }

    protected Desvio() {
        //for ORM
    }

    public String desvioString() {
        final String NEG = "menos";
        final String POS = "mais";
        StringBuilder materias = new StringBuilder();
        for (Map.Entry<CodigoInternoMateria, Double> entry : this.materias.entrySet()) {
            String adjetivo = POS;
            double value = entry.getValue();
            if (value < 0) {
                adjetivo = NEG;
                value *= -1;
            }
            materias.append(String.format("Foram consumidas %s %.2f unidades de %s do que o estimado pela Ficha de Producao\n",
                    adjetivo, value, entry.getKey().toString2()));
        }
        double value = quantidadeProduto;
        String adjetivo = POS;
        if (value < 0) {
            adjetivo = NEG;
            value *= -1;
        }
        return String.format("%s\n%sForam produzidos %s %.2f unidades de %s do que o previsto pela ordem %s",
                this.ordem, materias, adjetivo, value, this.produto, this.ordem);
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Desvio desvio = (Desvio) other;
        return Objects.equals(ordem, desvio.ordem) &&
                Objects.equals(materias, desvio.materias) &&
                Objects.equals(produto, desvio.produto) &&
                Objects.equals(quantidadeProduto, desvio.quantidadeProduto) &&
                Double.compare(quantidadeProduto, desvio.quantidadeProduto) == 0;
    }

    @Override
    public CodigoOrdem identity() {
        return ordem;
    }
}
