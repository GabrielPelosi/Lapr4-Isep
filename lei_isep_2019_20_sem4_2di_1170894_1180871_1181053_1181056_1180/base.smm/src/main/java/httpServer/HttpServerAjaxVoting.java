package httpServer;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;

import eapli.base.smmmanagement.domain.EstadoMaquina;
import eapli.base.smmmanagement.repositories.EstadoMaquinaRepository;
import reset.UdpClient;

import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */
public class HttpServerAjaxVoting {
    static private final String BASE_FOLDER= "base.smm\\src\\main\\java\\httpServer\\www";
    static private ServerSocket sock;
    static private LinhaProducaoRepository linhaRepo;
    static private EstadoMaquinaRepository repo;
    static private Iterable<EstadoMaquina> status;
    static private Iterable<LinhaProducao> linhas;




    public static void main(String args[]) throws Exception {
	Socket cliSock;
        linhaRepo = PersistenceContext.repositories().linhasProducao();





        accessesCounter=0;

        
	 sock = new ServerSocket(30902);

	while(true) {
	        cliSock=sock.accept();
            HttpAjaxVotingRequest req=new HttpAjaxVotingRequest(cliSock, BASE_FOLDER);
            req.start();
            incAccessesCounter();
            }
        } 
	
    
    // DATA ACCESSED BY THREADS - LOCKING REQUIRED

    private static final int candidatesNumber = 4;
    private static int accessesCounter;

    private static synchronized void incAccessesCounter() { accessesCounter++; }
    
    public static synchronized String getVotesStandingInHTML() {

        repo = PersistenceContext.repositories().estadosMaquinas();
        linhas = linhaRepo.findAll();
        status = repo.findAll();

        String textHtml = "<hr><ul>";

        for(LinhaProducao lp : linhas) {
            textHtml = textHtml + "<h1>Linha: " + lp.identity()+"</h1>" ;

        for(EstadoMaquina em : status) {

            if (lp.maquinas().contains(new CodigoInternoMaquina(em.identity().toString()))) {

                textHtml = textHtml + "<li>Machine " + em.identity() + " status: " + em.Status() + " <button type=button onclick=voteFor(" + em.identity() + ")>Reset</button></li>";

            }
        }
            textHtml = textHtml +"<hr>";}


        textHtml = textHtml + "</ul><hr><p>HTTP server accesses counter: " + accessesCounter + "</p><hr>";
        return textHtml;

    
    
    
}
    public static synchronized void castVote(String i) {
long id = Long.parseLong(i);
        EstadoMaquina em = new EstadoMaquina(1l,"", "");

for (EstadoMaquina aux : status){
    if(id == aux.identity()){
        em = aux;

    }
    System.out.println("reseting num 2");
}
        UdpClient reset = new UdpClient(em);
        reset.run();
    }
}
