# User Story 2013 - Aplicar uma visualização/transformação a um ficheiro XML

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo aplicar uma visualização/transformação (das várias disponiveis) a um ficheiro XML anteriormente gerado/exportado por mim (através do sistema).

### Pré-condições

O utilizador a efetuar esta funcionalidade tem que ter permissões de Gestor de Produção.

A existência de um ficheiro XML previamente criado e validado por um  respetivo XSD, de forma a poder sofrer uma transformação.

### Pós-condições

O conteúdo a exportado para o XML é transformado num dos seguintes:

- Para HTML
- Para JSON
- Para um ficheiro de texto (para leitura por humanos)

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a transformação/visualização do XML.
2. O sistema solicita o tipo de transformação/visualização (i.e. HTML, JSON ou Texto).
3. O Gestor de Produção seleciona uma das opções.
4. O sistema valida a opção e informa o Gestor de Produção do sucesso da operação.



## Glossário

**HTML**: significa **Hypertext Markup Language**. Ele permite que os usuários criem e estruturem seções, parágrafos, cabeçalhos e links para páginas da internet ou aplicações.O HTML não é uma linguagem de programação, isso significa que não pode ser usado para criar funcionalidades dinâmicas. Entretanto, o HTML possibilita a organização e formatação de documentos, similar ao Microsoft Word.

**JSON**: significa **JavaScript Object Notation**. É uma notação de Objetos JavaScript) é uma formatação leve de troca de dados. Para seres humanos, é fácil de ler e escrever. Para máquinas, é fácil de interpretar e gerar. Está baseado num subconjunto da linguagem de programação JavaScript. JSON é em formato texto e completamente independente de linguagem, pois usa convenções que são familiares às linguagens C e familiares, incluindo C++, C#, Java, JavaScript, Perl, Python e muitas outras. 

