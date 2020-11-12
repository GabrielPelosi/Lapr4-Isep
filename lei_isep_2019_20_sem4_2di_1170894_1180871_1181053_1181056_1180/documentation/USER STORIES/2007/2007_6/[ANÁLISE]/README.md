# User Story 2007_6 - Exportar para um ficheiro XML toda informação das ordens de produção

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a infomação subjacente ao chão de fábrica (e.g. ordens de produção).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.

A existência de um ficheiro XSD que valide o ficheiro XML resultante.

A existência de pelo menos uma Ordem de produção no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação das das ordens de produção presentes no sistema. 
2. O sistema valida e **regista os dados das ordens de produção** no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

Ordem de produção:  Uma ordem de produção caracteriza-se por ter um identificador, uma data de emissão, uma data prevista de execução, a identificação do produto a produzir e das encomendas (apenas identificadores) que fundamentam a respetiva ordem.