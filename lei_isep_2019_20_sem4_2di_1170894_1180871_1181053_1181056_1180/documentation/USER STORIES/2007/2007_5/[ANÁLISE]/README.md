# User Story 2007_5 - Exportar para um ficheiro XML toda informação das categorias de matérias primas

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a infomação subjacente ao chão de fábrica (e.g. categorias).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.

A existência de um ficheiro XSD que valide o ficheiro XML resultante.

A existência de pelo menos uma Categoria de matérias primas no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação das categorias de matérias-primas presentes no sistema. 
2. O sistema valida e **regista os dados das categorias** no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

Categoria: Uma categoria representa-se por um código identificador único e um nome.