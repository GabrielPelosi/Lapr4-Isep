# User Story 1008 - Inicializar linhas de produção

## Área - (2) Chão de Fábrica.

### Conceitos de Implementação

| O quê                    | Ação                                                         | Onde                 | Método                                                       |
| ------------------------ | ------------------------------------------------------------ | -------------------- | ------------------------------------------------------------ |
| LinhaProducaoBoot        | inicia a definição de algumas linhas de produção, efetuando a ligação entre o Bootstrapper e o seu Controller | package Bootstrapper | n/a                                                          |
| LinhaProducaoControlleer | cria a base de dados para as linhas de produção              | package Application  | createLinhaProducaoRepo() na package Repositories            |
| LinhaProducao            | verifica se cada código de cada máquina é único em cada linha | package Domain       | validateLinhaProducao() na package Domain, na Classe em questão |
| LinhaProducaoRepo        | regista cada linha de produção, uma a uma                    | package Repositories | addLinha()                                                   |

## Diagrama de Sequências
O diagrama encontra-se na pasta 'documentation/diagramas' deste repositório presente no seguinte Link:
https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/1008/1008_SD.png