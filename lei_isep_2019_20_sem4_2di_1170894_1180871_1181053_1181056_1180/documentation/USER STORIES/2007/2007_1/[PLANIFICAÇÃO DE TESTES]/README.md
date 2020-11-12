# User Story 2007_1 - Exportar para um ficheiro XML toda informação dos produtos

## Área - (1) Produção.

### Testes aferidos

Os testes aferidos e planeados para a exportação de um XML são todos verificados por um ficheiro XSD previamente criado. Logo, todo o tipo de validação e teste funcional encontra-se diretamente relacionado com o ficheiro XSD. 

O único teste aferido que será necessário definir formalmente na planificação, é o teste da criação do ficheiro .xml.

**Teste 1**: 

```java
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;	

@Test
public void createFileXML(){
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //etc...
        } catch (Exception e) {
            e.printStackTrace();
        }
}
```

