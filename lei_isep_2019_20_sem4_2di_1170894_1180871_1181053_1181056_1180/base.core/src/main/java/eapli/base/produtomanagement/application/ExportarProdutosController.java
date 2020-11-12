package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.produtomanagement.domain.Produto;
import eapli.base.produtomanagement.repositories.ProdutoRepository;
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

public class ExportarProdutosController {

    private final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();

    public void exportarProduto() {
        Iterable<Produto> itProd;
        itProd = produtoRepository.findAll();

        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            docBuilder.getSchema();
            System.out.println("Schema: " + docBuilder);
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("ChaoDeFabrica");
            rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xs:noNamespaceSchemaLocation", "produtos.xsd");
            doc.appendChild(rootElement);

            for(Produto p : itProd){
                String codFab = p.identity().toString();
                String codCom = p.codigoComercial().toString();
                String descB = p.descricaoBreve().toString();
                String descC = p.descricaoCompleta().toString();
                String un = p.unidade().toString();
                String cat = p.categoria().toString();
                Element prod = doc.createElement("Produto");

                Element codComProd = doc.createElement("CodigoComercial");
                codComProd.setTextContent(codCom);
                Element descBProd = doc.createElement("DescricaoBreve");
                descBProd.setTextContent(descB);
                Element descCProd = doc.createElement("DescricaoCompleta");
                descCProd.setTextContent(descC);
                Element unProd = doc.createElement("Unidade");
                unProd.setTextContent(un);
                Element catProd = doc.createElement("Categoria");
                catProd.setTextContent(cat);

                prod.setAttribute("CodigoFabrico", codFab);
                prod.appendChild(codComProd);
                prod.appendChild(descBProd);
                prod.appendChild(descCProd);
                prod.appendChild(unProd);
                prod.appendChild(catProd);

                rootElement.appendChild(prod);
            }

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("produtos.xml"));

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
