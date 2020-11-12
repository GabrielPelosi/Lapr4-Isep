package eapli.base.persistence.impl.jpa;

import eapli.base.produtomanagement.domain.Lote;
import eapli.base.produtomanagement.repositories.LoteRepository;

public class JpaLoteRepository extends BasepaRepositoryBase<Lote, String, String> implements LoteRepository {
    JpaLoteRepository() {
        super("idLote");
    }
}
