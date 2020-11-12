package eapli.base.persistence.impl.inmemory;

import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.IdLinhaProducao;
import eapli.base.mensagemmanagement.domain.Mensagem;
import eapli.base.mensagemmanagement.repositories.MensagemRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Calendar;

public class InMemoryMensagemRepository extends InMemoryDomainRepository<Long, Mensagem> implements MensagemRepository {
    static {
        InMemoryInitializer.init();
    }

    public Iterable<Mensagem> findMensagemByLinhaWithData(Calendar data1, Calendar data2, IdLinhaProducao idLinha) {
        return null;
    }

    public Iterable<CodigoInternoMaquina> findMaquinasDiferentesInOrdem(String ordem) {
        return null;
    }

    public Mensagem findMensagemAntigaRecenteByMaquina(CodigoInternoMaquina maquina, boolean antiga) {
        return null;
    }

    public Mensagem findMensagemAntigaRecenteByOrdem(String ordem, boolean antiga) {
        return null;
    }

    public Iterable<Mensagem> findMensagensRetomaParagemDe1MaquinaBetweenDatas(CodigoInternoMaquina maquina, Calendar dataInicio, Calendar dataFim) {
        return null;
    }
}
