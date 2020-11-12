package eapli.base.depositomanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Deposito implements AggregateRoot<CodigoDeposito> {

    @Id
    private CodigoDeposito code;

    @Column(nullable = false)
    private DescricaoDeposito desc;
    @ElementCollection
    private Map<String, Double> materiasQuantidades;
    @ElementCollection
    private Map<String, Double> produtoQuantidades;

    public Deposito(CodigoDeposito code, DescricaoDeposito desc) {
        if (code == null || desc == null) {
            throw new IllegalArgumentException();
        }
        this.code = code;
        this.desc = desc;
        this.materiasQuantidades = new HashMap<>();
        this.produtoQuantidades = new HashMap<>();
    }

    protected Deposito() {
        // for ORM only

    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public CodigoDeposito identity() {
        return this.code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deposito)) return false;
        Deposito deposito = (Deposito) o;
        return code == deposito.code;
    }


    @Override
    public int hashCode() {
        return Objects.hash(code, desc);
    }

    public CodigoDeposito getCodigo() {
        return code;
    }

    public double quantidadeMateria(String materiaPrima) {
        return materiasQuantidades.get(materiaPrima);
    }

    public void removerQuantidadeMateria(String materiaPrima, double quantidadeRemocao) {
        double quantidadeAntiga = quantidadeMateria(materiaPrima);
        double novaQuantidade = quantidadeMateria(materiaPrima) - quantidadeRemocao;
        materiasQuantidades.replace(materiaPrima, quantidadeAntiga, novaQuantidade);

    }

    public void adicionarQuantidadeMateria(String materiaPrima, double quantidade) {
        Iterable<String> materias = this.materiasQuantidades.keySet();
        double novaQuantidade = quantidade;
        for (String materia : materias) {
            if (materia.equals(materiaPrima)) {
                novaQuantidade = this.quantidadeMateria(materiaPrima) + quantidade;
            }
        }
        this.materiasQuantidades.put(materiaPrima, novaQuantidade);
    }

    public void adicionarQuantidadeProduto(String produto, double quantidade) {
        Iterable<String> produtos = this.produtoQuantidades.keySet();
        double novaQuantidade = quantidade;
        for (String p : produtos) {
            if (p.equals(produto)) {
                novaQuantidade = this.quantidadeProduto(produto) + quantidade;
            }
        }
        this.produtoQuantidades.put(produto, novaQuantidade);
    }

    public double quantidadeProduto(String produto) {
        return produtoQuantidades.get(produto);
    }

    public DescricaoDeposito getDesc() {
        return desc;
    }

    public Map<String, Double> getMateriasQuantidades() {
        return this.materiasQuantidades;
    }

    public Map<String, Double> getProdutosQuantidades() {
        return this.produtoQuantidades;
    }

    public String materiasQuantidadesToString() {
        String matQuant = "";
        int i = 1;
        Iterable<String> materias = this.materiasQuantidades.keySet();
        for (String m : materias) {
            if (m != null) {
                if (i == 1) {
                    matQuant = m + "-" + this.materiasQuantidades.get(m);
                    i++;
                } else {
                    matQuant = matQuant + ";" + m + "-" + this.materiasQuantidades.get(m);
                }
            }
        }
        return matQuant;
    }

    public String produtosQuantidadesToString() {
        String prodQuant = "";
        int i = 1;
        Iterable<String> produtos = this.produtoQuantidades.keySet();
        for (String p : produtos) {
            if (p != null) {
                if (i == 1) {
                    prodQuant = p + "-" + this.materiasQuantidades.get(p);
                    i++;
                } else {
                    prodQuant = prodQuant + ";" + p + "-" + this.materiasQuantidades.get(p);
                }
            }
        }
        return prodQuant;
    }
}



