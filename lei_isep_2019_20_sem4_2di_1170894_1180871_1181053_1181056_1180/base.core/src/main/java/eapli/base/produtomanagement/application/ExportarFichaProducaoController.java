package eapli.base.produtomanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.produtomanagement.repositories.FichaProducaoRepository;
import eapli.base.linhaproducaomanagement.domain.CodigoInternoMaquina;
import eapli.base.materiaprimamanagement.domain.CodigoInternoMateria;
import eapli.base.produtomanagement.domain.FichaProducao;
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
import java.util.List;

public class ExportarFichaProducaoController {

    private final FichaProducaoRepository fichaProducaoRepository = PersistenceContext.repositories().fichasProducao();

    public void exportarFichas() {
        try {

            Iterable<FichaProducao> itFich = fichaProducaoRepository.findAll();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            docBuilder.getSchema();
            System.out.println("Schema: " + docBuilder);
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("ChaoDeFabrica");
            rootElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xs:noNamespaceSchemaLocation", "fichasProducao.xsd");
            doc.appendChild(rootElement);


            String codInternoMateria="";
            String quant="";
            String unidade="";

            for (FichaProducao fich : itFich) {
                String codigo = fich.identity().toString();

                Element fichaProd = doc.createElement("FichaProducao");
                fichaProd.setAttribute("CodigoFichaProducao", codigo);


                Element fichaMateria = doc.createElement("FichaMateria");
                Element fichaQnt = doc.createElement("FichaQuantidade");
                Element fichaUnidade = doc.createElement("FichaUnidade");
                List<CodigoInternoMateria> l = fich.sequenciaMateria();
                for (CodigoInternoMateria a : l){
                    if (l.size()==1){
                        codInternoMateria = a.toString2();
                        fichaMateria.setTextContent(codInternoMateria);
                        fichaProd.appendChild(fichaMateria);
                    }else{
                        codInternoMateria = codInternoMateria + ","+ a.toString2() ;
                        fichaMateria.setTextContent(codInternoMateria);
                        fichaProd.appendChild(fichaMateria);
                    }
                }


                List<Double> quantList = fich.sequenciaQuantidade();
                for (Double a : quantList){
                    if (quantList.size() == 1){
                        quant = a.toString();

                        fichaQnt.setTextContent(quant);
                        fichaProd.appendChild(fichaQnt);
                    }else{
                        quant = quant +  "," + a.toString();

                        fichaQnt.setTextContent(quant);
                        fichaProd.appendChild(fichaQnt);
                    }
                }





                List<String> uniList = fich.sequenciaUnidade();
                for (String a: uniList){
                    if (uniList.size() == 1){
                        unidade = a;

                        fichaUnidade.setTextContent(unidade);
                        fichaProd.appendChild(fichaUnidade);
                    }else{
                        unidade = unidade + "," + a;

                        fichaUnidade.setTextContent(unidade);
                        fichaProd.appendChild(fichaUnidade);
                    }

                }



                rootElement.appendChild(fichaProd);
            }

            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("fichasProducao.xml"));

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
