# User Story 2009 - Importar ordens de produção através de um ficheiro (i.e CSV)

## Área - (1) Produção.

### Conceitos de Implementação

| O quê                    | Ação                                                         | Onde                | Método                                                       |
| ------------------------ | ------------------------------------------------------------ | ------------------- | ------------------------------------------------------------ |
| Sistema «UI»             | nova importação                                              | n/a                 | n/a                                                          |
|                          | nomeFicheiro                                                 | n/a                 | n/a                                                          |
|                          | O sistema informa o Gestor de Produção do sucesso da operação. | n/a                 | n/a                                                          |
| ImportarOrdensController | nova importação de ordens de producao                        | package Application | n/a                                                          |
|                          | validação dos dados (tipo de ficheiro tem de ser CSV) e desconstrução do ficheiro | package Application | FileReader(nomeFicheiro)                                     |
|                          | importação de uma nova ordem                                 | package Controller  | ordem = newOrdem(id ordem, dataEmissao, dataPrevista, codigoFabrico, quantidade, unidade, encomenda) |
| NovaOrdemController      | nova ordem de produção                                       | package Controller  | NovaOrdemController(ordem)                                   |

## Diagrama de Sequências
https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/2009/2009_SD.png