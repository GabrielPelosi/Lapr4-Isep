# User Story 2005 - Importar catálogo de produtos através de um ficheiro (i.e CSV)

## Área - (1) Produção.

### Conceitos de Implementação

| O quê                       | Ação                                                         | Onde                 | Método                      |
| --------------------------- | ------------------------------------------------------------ | -------------------- | --------------------------- |
| Sistema «UI»                | inicia a importação do catãlogo de produtos através de um CSV | n/a                  | n/a                         |
|                             | requer a localização do ficheiro                             | n/a                  | Scanner()                   |
|                             | envio dos dados (localização do ficheiro) para o Controller  | n/a                  | n/a                         |
| ImportarCatalogoControlleer | nova importação de catálogo                                  | package Application  | n/a                         |
|                             | validação dos dados (tipo de ficheiro tem de ser CSV)        | package Application  | validaFicheiro(localizacao) |
| Produto                     | importação de vários novos produtos                          | package Domain       | n/a                         |
|                             | validar o novo produto                                       | package Domain       | validaProduto()             |
|                             | novo produto                                                 | package Domain       | newProduto()                |
| ProdutoRepo                 | verifica se o(s) produto(s) é(são) único(s) no sistema       | package Repositories | uniqueProduto()             |
|                             | regista novo(s) produto(s)                                   | package Repositories | addProduto()                |

## Diagrama de Sequências
O diagrama encontra-se na pasta 'documentation/diagramas' deste repositório presente no seguinte Link:
https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/2005/2005_SD.png
