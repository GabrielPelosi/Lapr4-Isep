package eapli.base.materiaprimamanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.materiaprimamanagement.domain.Categoria;
import eapli.base.materiaprimamanagement.repositories.CategoriaRepository;
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

public class ExportarCategoriaController {

    private final CategoriaRepository categoriaRepository = PersistenceContext.repositories().categorias();

    public void exportarCategoria(){
        Iterable<Categoria> itCat;
        itCat = categoriaRepository.findAll();

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
                    "xs:noNamespaceSchemaLocation", "categoria.xsd");
            doc.appendChild(rootElement);

            for(Categoria c : itCat){
                String desc = c.descricao().toString();
                String cod = c.identity().toString();
                Element cat = doc.createElement("Categoria");

                Element descCat = doc.createElement("DescricaoCategoria");
                descCat.setTextContent(desc);

                cat.setAttribute("CodigoCategoria", cod);
                cat.appendChild(descCat);

                rootElement.appendChild(cat);
            }

            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("categorias.xml"));

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
