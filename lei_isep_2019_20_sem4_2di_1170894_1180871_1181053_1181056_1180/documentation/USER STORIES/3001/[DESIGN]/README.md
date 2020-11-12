# User Story 3001- Definição de uma nova máquina



# Área - (2) Chão de Fábrica

### Conceitos de Implementação

| O quê                   | Ação                                                         | Onde        | Método                                                       |
| ----------------------- | ------------------------------------------------------------ | ----------- | ------------------------------------------------------------ |
| Sistema UI               | nova Maquina                                       | UI          | n/a                                                          |
| DefinirMaquinaController | nova máquina com os dados codigo interno, número de série, descrição, data de instalação, maraca, modelo | Application | newMaquina() na classe Maquina no package Domain |
| Maquina        | verificar se a maquina é unica                    | Domain      | validateMaquina() na classe Maquina na package Domain |
| MaquinaRepo                 | verifica se o(s) produto(s) é(são) único(s) no sistema       | package Repositories | uniqueMaquina() na classe MaquinaRepo no package Repositories           |
|                             | regista novo(s) produto(s)                                   | package Repositories | addMaquina() na classe MaquinaRepo no package Repositories               |
| ProdutoRepo                 | verifica se o(s) produto(s) é(são) único(s) no sistema       | package Repositories | uniqueProduto()  na classe ProdutoRepo no package Repositories           |
| LinhaProducao                            | verifica se a maquina esta associada a uma linha de producao                                  | package Domain | verificarAssociação() na classe LinhaProducao no package Domain             |
|LinhaProducaoRepo | verifica se a nova maquina criada esta associada a uma linha de producao| package Repositories | checkAssociation() na classe LinhaProducaoRepo no package Repositories |


### Diagrama de Sequências
[](https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/3001/3001_SD.png)
