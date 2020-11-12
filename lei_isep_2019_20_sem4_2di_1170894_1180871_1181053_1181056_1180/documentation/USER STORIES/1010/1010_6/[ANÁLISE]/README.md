

# User Story 1010_6 - Especificação de um documento XSD que valide o conteúdo XML das ordens de produção

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (e.g.  ordens de produção).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento             | Tipo        | Ocorrência      |
| -------------------- | ----------- | --------------- |
| Chão de fábrica      | ComplexType | 1 vez           |
| Ordem                | ComplexType | 1 ou mais vezes |
| DataEmissao          | string      | 1 vez           |
| DataPrevista         | Data        | 1 vez           |
| CodigoFabricoProduto | Data        | 1 vez           |
| Quantidade           | Quantidade  | 1 vez           |
| Unidade              | string      | 1 vez           |
| Encomendas           | Encomenda   | 1 vez           |
| Estado               | Estado      | 1 vez           |

​    

| Atributo    | Tipo   | Uso         |
| ----------- | ------ | ----------- |
| CodigoOrdem | string | obrigatório |

​     

| Constraints        | Função                                                       | Elemento/Atributo em questão |
| ------------------ | ------------------------------------------------------------ | ---------------------------- |
| unique-CodigoOrdem | Garantir que o atributo CodigoOrdem é único (pois no sistema representa a chave primária do objeto Ordem) | CodigoOrdem                  |
| Data               | Garantir que os elementos DataEmissao e DataPrevista têm uma estrutura específica | DataEmissao e DataPrevista   |
| Quantidade         | Garantir que o elemento Quantidade tem um número mínimo de 1 | Quantidade                   |
| Encomenda          | Garantir que o elemento Encomenda tem uma estrutura específica | Encomenda                    |
| Estado             | Garantir que o elemento Estado é um de 3 possíveis no sistema | Estado                       |



O CodigoOrdem foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

- **Ordem de produção:** documento em que se autoriza/solicita a produção de um produto numa determinada quantidade (a pretendida) através de um conjunto de matérias-primas e respetivas quantidades (de referência). No âmbito do nosso sistema, uma ordem caracteriza-se por ter um identificador, uma data de emissão, uma data prevista de execução, a identificação do produto a produzir e das encomendas (apenas identificadores). A mesma também contém um estado (que por omissão é pendente).