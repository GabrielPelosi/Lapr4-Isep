# User Story 1010_7 - Especificação de um documento XSD que valide o conteúdo XML das fichas de produção

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (ficha de produção).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento        | Tipo             | Ocorrência      |
| --------------- | ---------------- | --------------- |
| Chão de fábrica | ComplexType      | 1 vez           |
| FichaProducao   | TFichaProducao   | 1 ou mais vezes |
| FichaMateria    | TFichaMateria    | 1 vez           |
| FichaQuantidade | TFichaQuantidade | 1 vez           |

​    

| Atributo            | Tipo                 | Uso      |
| ------------------- | -------------------- | -------- |
| CodigoFichaProducao | TCodigoFichaProducao | required |

​     

| Constraints   | Função                                                       | Elemento/Atributo em questão |
| ------------- | ------------------------------------------------------------ | ---------------------------- |
| unique-codigo | Garantir que o atributo CodigoFichaProducao é único (pois no sistema representa a chave primária do objeto FichaProducao) | CodigoFichaProducao          |



O CodigoFichaProducao foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

- **Ficha de Produção:** corresponde à lista de matérias-primas e respetivas quantidades usadas para produzir uma determinada quantidade de um dado produto.