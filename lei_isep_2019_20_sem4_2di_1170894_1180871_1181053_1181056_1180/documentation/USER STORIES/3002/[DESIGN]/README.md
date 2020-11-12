# User Story 3002 - Especificar linha de produção



# Área - (2) Chão de Fábrica

### Conceitos de Implementação

| O quê                   | Ação                                                         | Onde        | Método                                                       |
| ----------------------- | ------------------------------------------------------------ | ----------- | ------------------------------------------------------------ |
| Sistema                 | nova linha de produção                                       | UI          | n/a                                                          |
| LinhaProducaoController | nova linha de produção com uma sequência de todos os código internos das máquinas | Application | newLinhaProducao() na classe LinhaProducao na package Domain |
| LinhaProducao           | verificar se o código é único na linha                       | Domain      | validateCodigoInterno() na classe LinhaProducao na package Domain |

### Diagrama de Sequências
O diagrama encontra-se na pasta 'documentation/USER STORIES/diagrams' deste repositório presente no seguinte link:

https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/3002/3002_SD.png