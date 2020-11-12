# User Story 2002 - Definir nova categoria de matérias-primas

## Área - (1) Produção.

### Conceitos de Implementação

| O quê                | Ação                                                         | Onde                 | Método              |
| -------------------- | ------------------------------------------------------------ | -------------------- | ------------------- |
| Sistema «UI»         | inicia a definição de uma nova categoria de matérias-primas  | n/a                  | n/a                 |
| CategoriaControlleer | chamada de um método na classe Categoria na package Domain para a definição da nova categoria | package Application  | novaCategoria()     |
| Categoria            | nova categoria com o dado único 'nome'                       | package Domain       | newCat(nome)        |
| CategoriaRepo        | verifica se a categoria é única no sistema                   | package Repositories | validateCategoria() |
|                      | regista nova categoria                                       | package Repositories | addCategoria()      |

## Diagrama de Sequências
O diagrama encontra-se na pasta 'documentation/diagramas' deste repositório presente no seguinte Link:
https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/2002/2002_SD.png