# User Story 1010_8 - Especificação de um documento XSD que valide o conteúdo XML dos depósito

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (depósitos).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento          | Tipo        | Ocorrência      |
| ----------------- | ----------- | --------------- |
| Chão de fábrica   | ComplexType | 1 vez           |
| Deposito          | TDeposito   | 1 ou mais vezes |
| DescricaoDeposito | TDescricao  | 1 vez           |

​    

| Atributo       | Tipo    | Uso      |
| -------------- | ------- | -------- |
| CodigoDeposito | TCodigo | required |

​     

| Constraints   | Função                                                       | Elemento/Atributo em questão |
| ------------- | ------------------------------------------------------------ | ---------------------------- |
| unique-codigo | Garantir que o atributo CodigoDeposito é único (pois no sistema representa a chave primária do objeto Deposito) | CodigoDeposito               |



O CodigoDeposito foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

- **Depósito:** corresponde a um lugar onde são armazenados todos os produtos e todas as matérias-primas.