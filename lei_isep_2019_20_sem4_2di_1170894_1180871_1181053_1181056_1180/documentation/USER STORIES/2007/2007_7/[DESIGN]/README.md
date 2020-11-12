# User Story 2007_7 - Exportar para um ficheiro XML toda informação das Fichas de Produção

## Área - (1) Produção.

### Conceitos de Implementação

| O quê                           | Ação                                                         | Onde                      | Método                                                       |
| ------------------------------- | ------------------------------------------------------------ | ------------------------- | ------------------------------------------------------------ |
| Sistema «UI»                    | nova exportação                                              | n/a                       | n/a                                                          |
|                                 | O sistema informa o Gestor de Produção do sucesso da operação. | n/a                       | n/a                                                          |
| ExportarFichaProducaoController | nova instância da base de dados das fichas de produção       | package Application       | new FichaProducaoRepository()                                |
|                                 | nova instância da classe DocumentBuilderFactory              | package javax.xml.parsers | new DocumentBuilderFactory()                                 |
|                                 | nova instância da classe DocumentBuilder                     | package javax.xml.parsers | new DocumentBuilder()                                        |
|                                 | nova instância da classe Document                            | package javax.xml.parsers | new Document()                                               |
|                                 | nova instância da classe Element                             | package org.w3c.dom       | createElement()                                              |
|                                 | definição do XSD schema                                      | package org.w3c.dom       | setAttriibuteNS()                                            |
|                                 | criacao de um elemento FichaProducao                         | package Application       | createElement("FichaProducao")                               |
|                                 | criacao dos elementos dentro da FichaProducao                | package Application       | createElement()                                              |
|                                 | introducao do conteúdo do elemento                           | package Application       | setTextContent()                                             |
|                                 | definicao de um atributo                                     | package Application       | setAttribute()                                               |
|                                 | criar elemento raiz                                          | package Application       | createElement("ChaoDeFabrica")                               |
|                                 | efetuar a ligacao entre a Maquina e os seus elementos filho  | package Application       | appendChild(FichaProducao)                                   |
|                                 | nova instancia da classe DOMSource                           | package Application       | newDomSource()                                               |
|                                 | nova instancia da classe StreamResult para a criacao do novo ficheiro .xml | package Application       | new StreamResult(new File("fichasProducao.xml"))             |
|                                 | Transformacao do formato do resultado no .xml                | package Application       | TransformerFactory.newInstance(),  transformerFactory.newTransformer(), setOutputProperty(OutputKeys.INDENT, "yes"), setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"), transform(source, result) |
| Document                        | definir o elemento raiz como filho                           | package javax.xml.parsers | appendChild()                                                |
| FichaProducaoRepository         | Iterable com todas as fichas de produção presentes no sistema | package Repository        | MaquinaRepository.findAll()                                  |

## Diagrama de Sequências

![2007_7_SD](../../diagrams/2007/2007_7_SD.png)

[Qualidade Original](https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER STORIES/diagrams/2007/2007_7_SD.png)

