package eapli.base.linhaproducaomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.linhaproducaomanagement.domain.LinhaProducao;
import eapli.base.linhaproducaomanagement.repositories.LinhaProducaoRepository;
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

public class ExportarLinhaProducaoController {

    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories().linhasProducao();

    public void exportarLinhaProducao() {
        Iterable<LinhaProducao> itLinha;
        itLinha = linhaProducaoRepository.findAll();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            docBuilder.getSchema();
            System.out.println("Schema: " + docBuilder);
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("ChaoDeFabrica");
            rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xsd:noNamespaceSchemaLocation", "linhasproducao.xsd");
            doc.appendChild(rootElement);

            for (LinhaProducao lp : itLinha) {
                String id = lp.identity().toString();
                String data = lp.processamentoRecenteToString();
                String estado;
                if(lp.estado()){
                     estado = "ativo";
                }else{ estado = "inativo";}

                String seqCodigo = lp.sequenciaMaquinaToString1();

                Element linha = doc.createElement("LinhaProducao");
                Element codMaq = doc.createElement("CodigoInternoMaquina");
                Element est = doc.createElement("EstadoProcessamento");
                Element dataProcess = doc.createElement("DataUltimoProcessamento");

                codMaq.setTextContent(seqCodigo);
                est.setTextContent(estado);
                dataProcess.setTextContent(data);

                linha.appendChild(codMaq);
                linha.appendChild(est);
                linha.appendChild(dataProcess);

                linha.setAttribute("IdLinhaProducao", id);
                rootElement.appendChild(linha);
            }
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("linhasproducao.xml"));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
