# User Story 2006 - Definir produto



# Área - (1) Produção

### Conceitos de Implementação

| O quê             | Ação                                                         | Onde        | Método                                                       |
| ----------------- | ------------------------------------------------------------ | ----------- | ------------------------------------------------------------ |
| Sistema           | definição de um novo produto                                 | UI          | n/a                                                          |
| ProdutoController | novo produto com os dados: código de fabrico, código comercial, descrição breve, descrição completa | Application | newProduto() na classe Produto na package Domain             |
| Produto           | verificar se o código comercial é único na base de dados     | Domain      | validateCodigoComercial() na classe ProdutoRepo na package Repository |
| -                 | verificar que o código de fabrico é único na base de dados   | -           | validateCodigoFabrico()na classe ProdutoRepo na package Repository |
| -                 | adiciona o produto à base de dados                           | -           | addProduto()na classe ProdutoRepo na package Repository      |
| ProdutoRepo       | n/a                                                          | Repository  | n/a                                                          |

### Diagrama de Sequências
O diagrama encontra-se na pasta 'documentation/USER STORIES/diagrams' deste repositório presente no seguinte link:

https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/2006/2006_SD.png