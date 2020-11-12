# User Story 1010_2 - Especificação de um documento XSD que valide o conteúdo XML das matérias-primas

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (matérias-primas).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento         | Tipo         | Ocorrência      |
| ---------------- | ------------ | --------------- |
| Chão de fábrica  | ComplexType  | 1 vez           |
| MateriaPrima     | TMateria     | 1 ou mais vezes |
| DescricaoMateria | string       | 1 vez           |
| Categoria        | string       | 1 vez           |
| FichaTecnica     | base64Binary | 1 vez           |

​    

| Atributo             | Tipo   | Uso      |
| -------------------- | ------ | -------- |
| CodigoInternoMateria | string | required |

​     

| Constraints                 | Função                                                       | Elemento/Atributo em questão |
| --------------------------- | ------------------------------------------------------------ | ---------------------------- |
| unique-CodigoInternoMateria | Garantir que o atributo CodigoInternoMateria é único (pois no sistema representa a chave primária do objeto MateriaPrima) | CodigoInternoMateria         |



O CodigoInternoMateria foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

- **Matéria-Prima:** corresponde a um material que será usado no processo de fabrico de um ou mais produtos.