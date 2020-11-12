package eapli.base.mensagemmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.mensagemmanagement.domain.Notificacao;
import eapli.base.mensagemmanagement.repositories.NotificacaoRepository;

public class ArquivarNotificacoesController {

    private final ConsultarNotificacoesService svc = new ConsultarNotificacoesService();
    private final NotificacaoRepository repository = PersistenceContext.repositories().notificacoes();

    public void arquivarNotificacao(Notificacao notificacao) {
         notificacao.arquivar();
         repository.save(notificacao);
    }

    public Iterable<Notificacao> notificacoesAtivas() {
        return this.svc.allNotificacoesWithFiltros(Notificacao.Estado.ATIVA, null, null, null, null);
    }
}
