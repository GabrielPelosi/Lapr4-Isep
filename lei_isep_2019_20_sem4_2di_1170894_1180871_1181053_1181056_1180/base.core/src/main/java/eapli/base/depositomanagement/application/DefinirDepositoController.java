package eapli.base.depositomanagement.application;

import eapli.base.depositomanagement.domain.CodigoDeposito;
import eapli.base.depositomanagement.domain.Deposito;

import eapli.base.depositomanagement.domain.DescricaoDeposito;
import eapli.base.depositomanagement.repositories.DepositoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import java.util.Map;

public class DefinirDepositoController {

    private final DepositoRepository repository = PersistenceContext.repositories().depositos();

    public Deposito registerDeposito(final long code, final String desc) {

        CodigoDeposito codDeposito = new CodigoDeposito(code);
        DescricaoDeposito descDeposito = new DescricaoDeposito(desc);

        Deposito newDep = new Deposito(codDeposito, descDeposito);

        return newDep;
    }

    public boolean guardarDeposito(final long code, final String desc) {
        Deposito daux = registerDeposito(code, desc);
        Deposito d = this.repository.save(daux);
        return d != null;
    }

    public boolean guardarDeposito(final long code, final String desc, final Map<String, Double> materiasQuantidades) {
        Deposito daux = registerDeposito(code, desc);
        Iterable<String> materias = materiasQuantidades.keySet();
        for (String materia : materias) {
            if(materia != null){
                daux.adicionarQuantidadeMateria(materia, materiasQuantidades.get(materia));
            }
        }
        Deposito d = this.repository.save(daux);
        return d != null;
    }
}
