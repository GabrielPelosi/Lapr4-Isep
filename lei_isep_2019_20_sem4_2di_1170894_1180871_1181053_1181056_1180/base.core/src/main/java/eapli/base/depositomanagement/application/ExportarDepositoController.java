package eapli.base.depositomanagement.application;

import eapli.base.depositomanagement.domain.Deposito;
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

public class ExportarDepositoController {

    private final ExportarDepositoService depositoService = new ExportarDepositoService();

    public void exportarDepositos() {

        try {
            Iterable<Deposito> itDepo = depositoService.allDeposits();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            docBuilder.getSchema();
            System.out.println("Schema: " + docBuilder);
            Document doc = docBuilder.newDocument();


            Element rootElement = doc.createElement("ChaoDeFabrica");
            rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xs:noNamespaceSchemaLocation", "depositos.xsd");
            doc.appendChild(rootElement);

            for (Deposito dep : itDepo) {
                String desc = dep.getDesc().toString();
                String cod = dep.identity().toString();
                Element deposito = doc.createElement("Deposito");

                Element descDep = doc.createElement("DescricaoDeposito");
                descDep.setTextContent(desc);

                deposito.setAttribute("CodigoDeposito", cod);
                deposito.appendChild(descDep);

                String matQuant = dep.materiasQuantidadesToString();
                if(!matQuant.isEmpty()){
                Element materiasQuantidade = doc.createElement("MateriasQuantidades");
                    materiasQuantidade.setTextContent(matQuant);
                deposito.appendChild(materiasQuantidade);
                }

                String prodQuant = dep.produtosQuantidadesToString();
                if(!prodQuant.isEmpty()){
                    Element produtosQuantidade = doc.createElement("ProdutosQuantidades");
                    produtosQuantidade.setTextContent(prodQuant);
                    deposito.appendChild(produtosQuantidade);
                }

                rootElement.appendChild(deposito);
            }

            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("depositos.xml"));

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
}
