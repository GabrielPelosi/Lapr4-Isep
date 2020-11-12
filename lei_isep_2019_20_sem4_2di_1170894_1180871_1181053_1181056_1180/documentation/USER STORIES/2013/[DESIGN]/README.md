# User Story 2013 - Aplicar uma visualização/transformação a um ficheiro XML

## Área - (1) Produção.

### Conceitos de Implementação

| O quê                   | Ação                                                         | Onde                | Método                        |
| ----------------------- | ------------------------------------------------------------ | ------------------- | ----------------------------- |
| Sistema «UI»            | nova transformação                                           | n/a                 | n/a                           |
|                         | nova instância do controller de transformação                | package Application | new TransformacaoController() |
|                         | solicita o tipo da transformação final                       | n/a                 | n/a                           |
|                         | O sistema informa o Gestor de Produção do sucesso da operação. | n/a                 | n/a                           |
| TransformacaoController | transformação para HTML                                      | package Application | tvHtml()                      |
|                         | transformação para .txt                                      | package Application | tvText()                      |
|                         | transformação para JSON                                      | package Application | tvJson()                      |

## Diagrama de Sequências
![](C:..\..\diagrams\2013\SD_2013.png)



### XSLT files

De forma a estar em concordância com a exportação da informação do chão de fábrica para XML, foram feitos os ficheiros de transformação XSLT individuais, para cada elemento a exportar (e.g. 3 ficheiros de transformação para os produtos, 3 ficheiros de transformação para as categorias, etc...).

##### Nomenclatura Adotada

| Transformação do ficheiro para | Nome do ficheiro .xslt |
| ------------------------------ | ---------------------- |
| HTML                           | xmlFilename_html.xslt  |
| .txt                           | xmlFilename_txt.xslt   |
| JSON                           | xmlFilename_json.xslt  |

#### HTML (alt [HTML] no diagrama de sequências)

A estrutura adotada na visualização foi de uma tabela com a informação organizada, apresentando um estrato do diagrama de transformação do .xslt de forma a facilitar a compreensão da estrutura do mesmo.



![](C:..\..\diagrams\2013\SD_2013_HTML.png)



#### Ficheiro de texto (alt [TEXT] no diagrama de sequências)

A estrutura adotada na transformação foi de uma sequência de todos os elementos em questão separados por ';' com a informação organizada, apresentando um estrato  do diagrama de transformação do .xslt de forma a facilitar a compreensão da estrutura do mesmo.



![](C:..\..\diagrams\2013\SD_2013_TXT.png)



#### JSON (alt [JSON] no diagrama de sequências)

A estrutura adotada na transformação foi a estrutura básica de um JSON file, apresentada na seguinte sintaxe. 

- Os objetos são colocados entre chaves ({}), seus pares nome-valor são separados por vírgula (,) e o nome e o valor em um par são separados por dois pontos (:).
- Os nomes em um objeto são cadeias de caracteres, enquanto os valores podem ser de qualquer um dos sete tipos de valores, incluindo outro objeto ou uma matriz. 
- As matrizes são colocadas entre parênteses([]) e seus valores são separados por vírgula (,). Cada valor numa matriz pode ser de um tipo diferente, incluindo outra matriz ou um objeto. 
- Quando objetos e matrizes contêm outros objetos ou matrizes, os dados têm uma estrutura semelhante a uma árvore.  

Exemplo: 

```json
{
   "firstName": "Duke",
   "lastName": "Java",
   "age": 18,
   "streetAddress": "100 Internet Dr",
   "city": "JavaTown",
   "state": "JA",
   "postalCode": "12345",
   "phoneNumbers": [
      { "Mobile": "111-111-1111" },
      { "Home": "222-222-2222" }
   ]
}
```



O documento estruturado para a geração do JSON file com a estrutura anteriormente apresentada, é demonstrado num estrato  do diagrama de transformação do .xslt de forma a facilitar a compreensão da estrutura do mesmo.



![](C:..\..\diagrams\2013\SD_JSON.png)