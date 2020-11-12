# User Story 2007_8 - Exportar para um ficheiro XML toda informação dos Depósitos

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a informação subjacente ao chão de fábrica (depósitos).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.

A existência de um ficheiro XSD que valide o ficheiro XML resultante.

A existência de pelo menos um Depósito no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação dos depósitos presentes no sistema. 
2. O sistema valida e regista os dados dos depósitos no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

- **Depósito:** corresponde a um lugar onde são armazenados todos os produtos e todas as matérias-primas.