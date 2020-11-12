# User Story 2007_3 - Exportar para um ficheiro XML toda informação das Máquinas

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a infomação subjacente ao chão de fábrica (máquinas).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.

A existência de um ficheiro XSD que valide o ficheiro XML resultante.

A existência de pelo menos uma Máquina no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação das máquinas presentes no sistema. 
2. O sistema valida e regista os dados da máquinas no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

- **Máquina:** corresponde a um equipamento capaz de realizar operações com o objetivo de produzir um produto.