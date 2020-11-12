package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.*;
import eapli.base.linhaproducaomanagement.repositories.MaquinaRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ExportarMaquinaController {

    private final MaquinaRepository maquinaRepo = PersistenceContext.repositories().maquinas();

    public void exportarMaquina(){
        Iterable<Maquina> itMaq;
        itMaq = maquinaRepo.findAll();

        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            docBuilder.getSchema();
            System.out.println("Schema: " + docBuilder);
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("ChaoDeFabrica");
            rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xsd:noNamespaceSchemaLocation", "maquina.xsd");
            doc.appendChild(rootElement);

            for(Maquina m : itMaq){
                String codInt = m.identity().toString();
                String numSerie = m.numeroSerie().toString();
                String desc = m.descricaoMaquina().toString();
                String dataInstal = m.dataInstalacao().toString();
                String marc = m.marca().toString();
                String mod = m.modelo().toString();
                long idP = m.idProtocolo();
                String idProt = toString().valueOf(idP);

                Element maquina = doc.createElement("Maquina");

                Element numeroSerie = doc.createElement("NumeroSerie");
                numeroSerie.setTextContent(numSerie);
                Element descricaoMaquina = doc.createElement("DescricaoMaquina");
                descricaoMaquina.setTextContent(desc);
                Element dataInstalacao = doc.createElement("DataInstalacao");
                dataInstalacao.setTextContent(dataInstal);
                Element marca = doc.createElement("Marca");
                marca.setTextContent(marc);
                Element modelo = doc.createElement("Modelo");
                modelo.setTextContent(mod);
                maquina.setAttribute("CodigoInternoMaquina", codInt);
                maquina.appendChild(numeroSerie);
                maquina.appendChild(descricaoMaquina);
                maquina.appendChild(dataInstalacao);
                maquina.appendChild(marca);
                maquina.appendChild(modelo);
                Iterable<FicheiroConfiguracao> itFich = () -> m.ficheiroConfiguracao().iterator();
                for (FicheiroConfiguracao fich : itFich) {
                    String ficheiro = fich.toString();

                    Element ficheiroConfiguracao = doc.createElement("FicheiroConfiguracao");
                    ficheiroConfiguracao.setTextContent(ficheiro);

                    maquina.appendChild(ficheiroConfiguracao);
                }
                Element idProtocolo = doc.createElement("IdProtocolo");
                idProtocolo.setTextContent(idProt);
                maquina.appendChild(idProtocolo);

                rootElement.appendChild(maquina);
            }

            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("maquinas.xml"));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
