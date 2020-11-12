package eapli.base.materiaprimamanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.materiaprimamanagement.domain.MateriaPrima;
import eapli.base.materiaprimamanagement.repositories.MateriaRepository;
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

public class ExportarMateriaController {

    private final MateriaRepository materiaRepo = PersistenceContext.repositories().materias();

    public void exportarMateria(){
        Iterable<MateriaPrima> itMat;
        itMat = materiaRepo.findAll();

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
                    "xsd:noNamespaceSchemaLocation", "materiaPrima.xsd");
            doc.appendChild(rootElement);

            for(MateriaPrima m : itMat){
                String codInterno = m.identity().toString2();
                String desc = m.descricao().toString();
                String cat = m.categoria().identity().toString();
                String ficha = m.ficha().toString();

                Element maquina = doc.createElement("MateriaPrima");

                Element descricao = doc.createElement("DescricaoMateria");
                descricao.setTextContent(desc);
                Element categoria = doc.createElement("Categoria");
                categoria.setTextContent(cat);
                Element fichaTecnica = doc.createElement("FichaTecnica");
                fichaTecnica.setTextContent(ficha);

                maquina.setAttribute("CodigoInternoMateria", codInterno);
                maquina.appendChild(descricao);
                maquina.appendChild(categoria);
                maquina.appendChild(fichaTecnica);
                rootElement.appendChild(maquina);
            }

            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("materiasPrimas.xml"));

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
