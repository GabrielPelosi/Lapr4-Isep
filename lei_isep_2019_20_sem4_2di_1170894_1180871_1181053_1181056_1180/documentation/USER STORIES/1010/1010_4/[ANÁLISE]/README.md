# User Story 1010_5 - Especificação de um documento XSD que valide o conteúdo XML das linhas de produção

## Área - (0) Geral.

## Formato Breve

Como Gestor de Projeto, eu pretendo que a equipa especifique um documento XSD que possa ser, posteriormente, usado para validar o conteúdo XML gerado pelo sistema.
O XSD deve contemplar toda a infomação subjacente ao chão de fábrica (e.g.  linhas de produção).

### Pré-condições

n/a

### Pós-condições

O XSD é guardado no sistema.



## Estrutura adotada para o XSD

| Elemento      | Tipo        | Ocorrência      |
| ------------- | ----------- | --------------- |
| ChaoDeFabrica | ComplexType | 1 vez           |
| LinhaProducao | ComplexType | 1 ou mais vezes |
| IdMaquina     | string      | 0 ou mais vezes |

| Atributo | Tipo | Uso         |
| -------- | ---- | ----------- |
| IdLinha  | long | obrigatório |

| Constraints   | Função                                                       | Elemento/Atributo em questão |
| ------------- | ------------------------------------------------------------ | ---------------------------- |
| unique-codigo | Garantir que o atributo IdLinha é único (pois no sistema representa a chave primária do objeto LinhaProducao) | IdLinha                      |



O IdLinha foi definido como um atributo de forma a agir como um identificador do elemento que contém todos os dados.



## Glossário

- **Linha de Produção:** Uma  produto representa-se por um código identificador único e uma sequência de máquinas.