# User Story 2004 - Especificar Ficha de Produção

## Área - (1) Produção.

### Conceitos de Implementação

| O quê                       | Ação                                                         | Onde                 | Método                      |
| --------------------------- | ------------------------------------------------------------ | -------------------- | --------------------------- |
| Sistema «UI»                | inicia a especificação de uma ficha d produto | n/a                  | n/a                         |
| DefinirFichaDeporducaoControlleer | nova ficha de produto com os dados dados codigo de fabrico, materias primas, quantidades                                | package Application  | newFichadeProducao() na classe FichaDeProducao na package domain|
| FichaDeProducao                    | verifica se o codigo de fabrico é unico                       | package Domain       | validateCodigoFabrico() na classe FichaProducao no package domain                      |
|                             | verifica se as quantidades sao maior que 0                                                 | package Domain       | validateQauntidades() na classe FichaProducao no package domain               |
|MateriaPrima                             | validar as materias primas                                      | package Domain       | validamateriasPrimas()   na classe MateriaPrima no package domain         |
| FichaProducaoRepo                 | verifica se o codigo de fabrico é único no sistema       | package Repositories | uniqueFichaProducao() na classe FicchaProducaoRepo no package Repositories            |
|                             | regista a ficha de producao                                 | package Repositories | addFichaProducao() na classe FicchaProducaoRepo no package Repositories               |

## Diagrama de Sequências
[](https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/2004/2004_SD.png)