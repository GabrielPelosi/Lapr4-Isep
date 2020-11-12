package eapli.base.mensagemmanagement.repositories;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Calendar;

public interface NotificacaoRepository extends DomainRepository<Long, Notificacao> {
    Iterable<Notificacao> findAllNotificacoes(Notificacao.Estado estado, Notificacao.Tipo tipo, CodigoInternoMaquina maquina, Calendar data1, Calendar data2);
}
