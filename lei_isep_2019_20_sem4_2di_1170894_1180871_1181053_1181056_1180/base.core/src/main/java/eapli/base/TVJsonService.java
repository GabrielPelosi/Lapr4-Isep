package eapli.base;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class TVJsonService {
    public void transform(String xmlFilename) throws Exception{
        try {
            File stylesheet = new File(xmlFilename + "_json.xslt");
            File xmlSource = new File(xmlFilename + ".xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlSource);

            StreamSource stylesource = new StreamSource(stylesheet);
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer(stylesource);
            Source source = new DOMSource(document);
            Result outputTarget = new StreamResult(new File(xmlFilename + ".json"));
            transformer.transform(source, outputTarget);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
