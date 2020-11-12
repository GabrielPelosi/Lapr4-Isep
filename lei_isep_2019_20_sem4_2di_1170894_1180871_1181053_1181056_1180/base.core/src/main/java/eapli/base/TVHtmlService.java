package eapli.base;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class TVHtmlService {
    public void transform(String xmlFilename) throws Exception{
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Source xslDoc = new StreamSource(xmlFilename + "_html.xslt");
            Source xmlDoc = new StreamSource(xmlFilename + ".xml");
            String outputFileName = xmlFilename + ".html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer trasform = tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
            String url = xmlFilename + ".html";
            File htmlF = new File(url);
            Desktop.getDesktop().browse(htmlF.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
