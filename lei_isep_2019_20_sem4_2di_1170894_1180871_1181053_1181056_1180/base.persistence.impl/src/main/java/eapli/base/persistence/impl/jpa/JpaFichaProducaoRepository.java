package eapli.base.persistence.impl.jpa;

import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.FichaProducao;
import eapli.base.produtomanagement.repositories.FichaProducaoRepository;

public class JpaFichaProducaoRepository extends BasepaRepositoryBase<FichaProducao, CodigoFabrico, CodigoFabrico> implements FichaProducaoRepository {

    public JpaFichaProducaoRepository() {
        super("codFabrico");
    }

}
