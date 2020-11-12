package eapli.base.persistence.impl.jpa;

import eapli.base.mensagemmanagement.domain.TempoProducao;
import eapli.base.mensagemmanagement.repositories.TempoProducaoRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;

public class JpaTempoProducaoRepository extends BasepaRepositoryBase<TempoProducao, CodigoOrdem, CodigoOrdem> implements TempoProducaoRepository {
    JpaTempoProducaoRepository() {
        super("ordem");
    }
}
