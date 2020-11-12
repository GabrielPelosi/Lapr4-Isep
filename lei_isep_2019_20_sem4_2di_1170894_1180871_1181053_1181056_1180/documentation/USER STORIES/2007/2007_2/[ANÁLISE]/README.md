# User Story 2007_2 - Exportar para um ficheiro XML toda informação das Matérias-Primas

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a infomação subjacente ao chão de fábrica (matérias-primas).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.

A existência de um ficheiro XSD que valide o ficheiro XML resultante.

A existência de pelo menos uma Matéria-Prima no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação das matérias-primas presentes no sistema. 
2. O sistema valida e regista os dados da matérias-primas no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

- **Matéria-Prima:** corresponde a um material que será usado no processo de fabrico de um ou mais produtos.