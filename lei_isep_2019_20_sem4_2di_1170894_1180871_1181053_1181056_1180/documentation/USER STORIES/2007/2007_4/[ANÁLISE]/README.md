# User Story 2007_1 - Exportar para um ficheiro XML toda informação das linhas de produção

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a infomação subjacente ao chão de fábrica (e.g. linhas de produção).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.
A existência de um ficheiro XSD que valide o ficheiro XML resultante.
A existência de pelo menos uma Linha de Produção no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação das linhas de produção presentes no sistema. 
2. O sistema valida e **regista os dados das categorias** no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

- **Linha de Produção:** Tem um identificador único e guarda também a sequência de máquinas que a compõe.