# User Story 2013 - Aplicar uma visualização/transformação a um ficheiro XML

## Área - (1) Produção.

### Testes aferidos

Os testes aferidos e planeados para a visualização/transformação a um ficheiro XML (sendo todos os XML exportados previamente verificados por um ficheiro XSD)  são testes visuais e funcionais. Logo, todo o tipo de validação e testes unitários serão exclusivamente efetuados relativamente à existência, não só dos ficheiros de entrada (XML e XSLT), como os de saída (HTML, JSON e TEXT). 

Os únicos testes aferidos que será necessário definir formalmente na planificação, são os teste da criação do ficheiro de output  e existência do ficheiro input.

**Teste 1**: 

```java
@Test
public void validateFiles(){
try {
     TransformerFactory tFactory = TransformerFactory.newInstance();
     Source xslDoc = new StreamSource("xmlFilename_html.xslt");
     Source xmlDoc = new StreamSource("xmlFilename.xml");
     String outputFileName = "xmlFilename.html";
     OutputStream htmlFile = new FileOutputStream(outputFileName);
     Transformer trasform = tFactory.newTransformer(xslDoc);
     trasform.transform(xmlDoc, new StreamResult(htmlFile));
     String url = "xmlFilename.html";
     File htmlF = new File(url);
     Desktop.getDesktop().browse(htmlF.toURI());
 } catch (Exception e)
 {
     e.printStackTrace();
 }

 try {
     File stylesheet = new File("xmlFilename_txt.xslt");
     File xmlSource = new File("xmlFilename.xml");
     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     DocumentBuilder builder = factory.newDocumentBuilder();
     Document document = builder.parse(xmlSource);

     StreamSource stylesource = new StreamSource(stylesheet);
     Transformer transformer = TransformerFactory.newInstance()
             .newTransformer(stylesource);
     Source source = new DOMSource(document);
     Result outputTarget = new StreamResult(new File("xmlFilename.txt"));
     transformer.transform(source, outputTarget);
 } catch (Exception e) {
     System.out.println(e);
     e.printStackTrace();
 }

 try {
     File stylesheet = new File("xmlFilename_json.xslt");
     File xmlSource = new File("xmlFilename.xml");

     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
     DocumentBuilder builder = factory.newDocumentBuilder();
     Document document = builder.parse(xmlSource);

     StreamSource stylesource = new StreamSource(stylesheet);
     Transformer transformer = TransformerFactory.newInstance()
             .newTransformer(stylesource);
     Source source = new DOMSource(document);
     Result outputTarget = new StreamResult(new File("xmlFilename.json"));
     transformer.transform(source, outputTarget);
 } catch (Exception e) {
     System.out.println(e);
     e.printStackTrace();
 }
 }
```

