package eapli.base.mensagemmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.base.mensagemmanagement.repositories.NotificacaoRepository;

import java.util.Calendar;

public class ConsultarNotificacoesService {

    public final NotificacaoRepository notificacaoRepository = PersistenceContext.repositories().notificacoes();

    public Iterable<Notificacao> allNotificacoesWithFiltros(Notificacao.Estado estado, Notificacao.Tipo tipo, CodigoInternoMaquina maquina, Calendar data1, Calendar data2) {
        return this.notificacaoRepository.findAllNotificacoes(estado, tipo, maquina, data1, data2);
    }
}
