# User Story 1010_5 - Especificação de um documento XSD que valide o conteúdo XML das categorias de matérias primas

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (e.g.  categorias).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento           | Tipo        | Ocorrência      |
| ------------------ | ----------- | --------------- |
| Chão de fábrica    | ComplexType | 1 vez           |
| Categoria          | ComplexType | 1 ou mais vezes |
| DescricaoCategoria | string      | 1 vez           |

| Atributo        | Tipo   | Uso         |
| --------------- | ------ | ----------- |
| CodigoCategoria | string | obrigatório |

| Constraints            | Função                                                       | Elemento/Atributo em questão |
| ---------------------- | ------------------------------------------------------------ | ---------------------------- |
| unique-CodigoCategoria | Garantir que o atributo CodigoCategoria é único (pois no sistema representa a chave primária do objeto Categoria) | CodigoCategoria              |



O CodigoCategoria foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

Categoria: Uma categoria representa-se por um código identificador único e um nome.