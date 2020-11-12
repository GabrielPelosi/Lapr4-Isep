# User Story 1010_3 - Especificação de um documento XSD que valide o conteúdo XML das máquinas

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (máquinas).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento             | Tipo        | Ocorrência       |
| -------------------- | ----------- | ---------------- |
| Chão de fábrica      | ComplexType | 1 vez            |
| Maquina              | TMaquina    | 1 ou mais vezes  |
| CodigoInternoMaquina | string      | 1 vez            |
| DescricaoMaquina     | string      | 1 vez            |
| DataInstalacao       | date        | 1 vez            |
| Marca                | string      | 1 vez            |
| Modelo               | string      | 1 vez            |
| FicheiroConfiguracao | string      | nenhuma ou 1 vez |
| idProtocolo          | long        | 1 vez            |

​    

| Atributo    | Tipo    | Uso      |
| ----------- | ------- | -------- |
| NumeroSerie | integer | required |

​     

| Constraints        | Função                                                       | Elemento/Atributo em questão |
| ------------------ | ------------------------------------------------------------ | ---------------------------- |
| unique-NumeroSerie | Garantir que o atributo NumeroSerie é único (pois no sistema representa a chave primária do objeto Maquina) | NumeroSerie                  |



O NumeroSerie foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

- **Máquina:** corresponde a um equipamento capaz de realizar operações com o objetivo de produzir um produto.