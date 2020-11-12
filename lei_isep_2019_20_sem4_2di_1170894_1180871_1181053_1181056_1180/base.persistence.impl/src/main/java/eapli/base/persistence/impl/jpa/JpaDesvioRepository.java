package eapli.base.persistence.impl.jpa;

import eapli.base.mensagemmanagement.domain.Desvio;
import eapli.base.mensagemmanagement.repositories.DesvioRepository;
import eapli.base.produtomanagement.domain.CodigoOrdem;

public class JpaDesvioRepository extends BasepaRepositoryBase<Desvio, CodigoOrdem, CodigoOrdem> implements DesvioRepository {
    JpaDesvioRepository() {
        super("ordem");
    }
}
