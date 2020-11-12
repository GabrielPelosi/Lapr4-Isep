# User Story 2007_7 - Exportar para um ficheiro XML toda informação das Fichas de Produção

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a informação subjacente ao chão de fábrica (fichas de produção).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.

A existência de um ficheiro XSD que valide o ficheiro XML resultante.

A existência de pelo menos uma Ficha de Produção no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação das fichas de produção presentes no sistema. 
2. O sistema valida e regista os dados da fichas de produção no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

- **Ficha de Produção:** corresponde à lista de matérias-primas e respetivas quantidades usadas para produzir uma determinada quantidade de um dado produto.