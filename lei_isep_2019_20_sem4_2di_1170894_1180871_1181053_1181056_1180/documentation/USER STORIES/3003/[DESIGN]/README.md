# User Story 3003 - Definição de uma novo depósito

## Área - (2) Chão de Fábrica

### Conceitos de Implementação

| O quê                       | Ação                                                         | Onde                 | Método                      |
| --------------------------- | ------------------------------------------------------------ | -------------------- | --------------------------- |
| Sistema «UI»                | inicia a definição de um novo dpósito | n/a                  | n/a                         |
| DefiniorDepositoControlleer | novo deposito com os dados codigo e descrição                                  | package Application  | n/a                         |
| Deposito                    | verifica se o codigo é unico                          | package Domain       | validaDeposito() na classe Deposito no package Domain                        |
| DepositoRepo                 | verifica se o deposito é único no sistema       | package Repositories | uniqueDeposito() na classe DepositoRepo no package respositories           |
|                             | regista novo deposito                                   | package Repositories | addDeposito() na classe DepositoRepo no package respositories               |

## Diagrama de Sequências

[](https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/3003/3003_SD.png)