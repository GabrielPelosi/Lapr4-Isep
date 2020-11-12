# User Story 2007_1 - Exportar para um ficheiro XML toda informação dos produtos

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo exportar, para um ficheiro XML, toda a infomação subjacente ao chão de fábrica (e.g. produtos).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.
A existência de um ficheiro XSD que valide o ficheiro XML resultante.
A existência de pelo menos um Produto no sistema.

### Pós-condições

O conteúdo a exportar é guardado num ficheiro .xml.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a exportação dos produtos presentes no sistema. 
2. O sistema valida e **regista os dados das categorias** no ficheiro XML, informando o Gestor de Produção do sucesso da operação.



## Glossário

- **Produto:** identifica-se por um código de fabrico e um código comercial. Dentro do sistema o identificador único é o código de fabrico.