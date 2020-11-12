package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.FicheiroConfiguracao;
import eapli.base.linhaproducaomanagement.domain.Maquina;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AssociarFicheiroConfiguracaoController {

    private final ListarMaquinaService svc = new ListarMaquinaService();
    private final MaquinaRepository maquinaRepository = PersistenceContext.repositories().maquinas();

    public FicheiroConfiguracao registarFichaConfiguracao(String nomeFich) {
        String config = "", data;
        File f = new File(nomeFich);
        try {
            Scanner inputStream = new Scanner(f);
            config = inputStream.nextLine().trim();
            while (inputStream.hasNext()) {
                data = inputStream.nextLine().trim();
                config = config + " " + data;
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (config.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("configuracoes should neither be null nor empty");
        }
        FicheiroConfiguracao fp = new FicheiroConfiguracao(config);
        return fp;
    }

    public boolean associarFicheiroConfiguracao(Maquina maquina, String nomeFicheiroConfig) {
        FicheiroConfiguracao ficheiroConfiguracao = registarFichaConfiguracao(nomeFicheiroConfig);
        maquina.addFicheiroConfig(ficheiroConfiguracao);
        Maquina maquinaAssociada = maquinaRepository.save(maquina);
        System.out.println("Maquina e as suas configuracoes:");
        System.out.printf("%s    -    %s    -    %s    -    %s    -    %s    -    %s    -    %s    -    %s\n",
                maquinaAssociada.identity().toString(), maquinaAssociada.numeroSerie().toString(),
                maquinaAssociada.descricaoMaquina().toString(), maquinaAssociada.dataInstalacao().toAnoMesDiaString(),
                maquinaAssociada.marca().toString(), maquinaAssociada.modelo().toString(),
                maquinaAssociada.ficheiroConfiguracaoToString(), toString().valueOf(maquinaAssociada.idProtocolo()));
        return maquinaAssociada != null;
    }

    public Iterable<Maquina> maquinas() {
        return this.svc.allMaquinas();
    }
}
