package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.*;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;

public class RegistarMaquinaController {

    private final ListarLinhaProducaoService svc = new ListarLinhaProducaoService();
    private final MaquinaRepository maquinaRepository = PersistenceContext.repositories().maquinas();

    public Maquina registerMaquina(final String codInterno, final int numSerie, final String descricao,
                                   final int ano, final int mes, final int dia, final String marca, final String modelo,
                                   final long idProtocolo) {

        CodigoInternoMaquina ci = new CodigoInternoMaquina(codInterno);
        NumeroSerie ns = new NumeroSerie(numSerie);
        DescricaoMaquina d = new DescricaoMaquina(descricao);
        DataInstalacao data = new DataInstalacao(ano, mes, dia);
        Marca ma = new Marca(marca);
        Modelo mo = new Modelo(modelo);

        return new Maquina(ci, ns, d, data, ma, mo, idProtocolo);
    }

    public boolean guardarMaquina(final String codInterno, final int numSerie, final String descricao,
                                  final int ano, final int mes, final int dia, final String marca, final String modelo,
                                  LinhaProducao linhaProducao, int posicaoMaquina, final long idProtocolo) {
        linhaProducao.addMaquina(codInterno, posicaoMaquina);
        LinhaProducao lp = this.svc.save(linhaProducao);
        if (lp != null) {
            Maquina maquina = registerMaquina(codInterno, numSerie, descricao, ano, mes, dia, marca, modelo, idProtocolo);
            Maquina m = this.maquinaRepository.save(maquina);
            return m != null;
        }
        return false;
    }

    public Iterable<LinhaProducao> linhaProducao() {
        return this.svc.allLinhasProducao();
    }

}
