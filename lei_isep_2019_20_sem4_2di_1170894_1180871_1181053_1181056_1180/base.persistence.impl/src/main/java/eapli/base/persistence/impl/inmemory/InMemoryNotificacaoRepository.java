package eapli.base.persistence.impl.inmemory;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.base.mensagemmanagement.repositories.NotificacaoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Calendar;

public class InMemoryNotificacaoRepository extends InMemoryDomainRepository<Long, Notificacao> implements NotificacaoRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Notificacao> findAllNotificacoes(Notificacao.Estado estado, Notificacao.Tipo tipo, CodigoInternoMaquina maquina, Calendar data1, Calendar data2) {
        return null;
    }
}
