package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.produtomanagement.domain.CodigoFabrico;
import eapli.base.produtomanagement.domain.Ordem;
import eapli.base.produtomanagement.domain.Produto;
import eapli.base.produtomanagement.domain.UnidadeProduto;
import eapli.base.produtomanagement.repositories.OrdemRepository;
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

public class ExportarOrdemController {

    private final OrdemRepository ordemRepository = PersistenceContext.repositories().ordens();

    public void exportarOrdem(String dataInicio, String dataFim) {
        Iterable<Ordem> itOrd;
        itOrd = ordemRepository.findAll();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            docBuilder.getSchema();
            System.out.println("Schema: " + docBuilder);
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("ChaoDeFabrica");
            rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xs:noNamespaceSchemaLocation", "ordem.xsd");
            doc.appendChild(rootElement);

            for (Ordem o : itOrd) {
                String cod = o.identity().toString();
                String dataEm = o.dataEm();
                if (checkDataInLimit(dataInicio, dataFim, dataEm)) {
                    String dataPre = o.dataPre();
                    Produto p = o.prod();
                    CodigoFabrico codFabr = p.identity();
                    String codProd = codFabr.toString();
                    int q = o.quant();
                    String qt = String.valueOf(q);
                    UnidadeProduto u = o.unid();
                    String unid = u.toString();
                    String seqEnc = o.sequenciaEncomendaToString();
                    String est = o.est();
                    Element ordem = doc.createElement("Ordem");

                    Element dataEmiss = doc.createElement("DataEmissao");
                    dataEmiss.setTextContent(dataEm);
                    Element dataPrevis = doc.createElement("DataPrevista");
                    dataPrevis.setTextContent(dataPre);
                    Element codProduto = doc.createElement("CodigoFabricoProduto");
                    codProduto.setTextContent(codProd);
                    Element quantidade = doc.createElement("Quantidade");
                    quantidade.setTextContent(qt);
                    Element unidade = doc.createElement("Unidade");
                    unidade.setTextContent(unid);
                    Element sequenciaEncom = doc.createElement("Encomendas");
                    sequenciaEncom.setTextContent(seqEnc);
                    Element estado = doc.createElement("Estado");
                    estado.setTextContent(est);

                    ordem.setAttribute("CodigoOrdem", cod);
                    ordem.appendChild(dataEmiss);
                    ordem.appendChild(dataPrevis);
                    ordem.appendChild(codProduto);
                    ordem.appendChild(quantidade);
                    ordem.appendChild(unidade);
                    ordem.appendChild(sequenciaEncom);
                    ordem.appendChild(estado);

                    rootElement.appendChild(ordem);
                }
            }

            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ordens.xml"));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkDataInLimit(String dataInicio, String dataFim, String data) {
        if (dataInicio.length() > 0 && dataFim.length() > 0) {
            return data.compareTo(dataInicio) >= 0 && data.compareTo(dataFim) <= 0;
        }
        return true;
    }
}
