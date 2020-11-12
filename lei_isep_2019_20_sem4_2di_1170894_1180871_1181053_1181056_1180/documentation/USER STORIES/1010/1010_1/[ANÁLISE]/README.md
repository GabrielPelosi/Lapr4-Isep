# User Story 1010_5 - Especificação de um documento XSD que valide o conteúdo XML dos produtos

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (e.g.  produtos).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento          | Tipo        | Ocorrência      |
| ----------------- | ----------- | --------------- |
| ChaoDeFabrica     | ComplexType | 1 vez           |
| Produto           | ComplexType | 1 ou mais vezes |
| CodigoComercial   | string      | 1 vez           |
| DescricaoBreve    | string      | 1 vez           |
| DescricaoCompleta | string      | 1 vez           |
| Unidade           | string      | 1 vez           |
| Categoria         | string      | 1 vez           |

| Atributo      | Tipo   | Uso         |
| ------------- | ------ | ----------- |
| CodigoFabrico | string | obrigatório |

| Constraints   | Função                                                       | Elemento/Atributo em questão |
| ------------- | ------------------------------------------------------------ | ---------------------------- |
| unique-codigo | Garantir que o atributo CodigoFabrico é único (pois no sistema representa a chave primária do objeto Produto) | CodigoFabrico                |



O CodigoFabrico foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

- **Produto:** Um produto representa-se por um código identificador único, um código comercial, uma descrição breve e completa, uma unidade e uma categoria de produto.