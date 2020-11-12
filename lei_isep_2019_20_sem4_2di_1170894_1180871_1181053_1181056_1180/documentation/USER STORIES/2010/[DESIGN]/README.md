# User Story 2010 - Nova ordem de produção.

## Área - (1) Produção.

### Conceitos de Implementação

| O quê                          | Ação                                                         | Onde                | Método                                                       |
| ------------------------------ | ------------------------------------------------------------ | ------------------- | ------------------------------------------------------------ |
| Sistema «UI»                   | inicia a definição de uma nova ordem de produção             | n/a                 | n/a                                                          |
|                                | solicita os dados (i.e. id da ordem, data prevista, código de fabrico do produto a produzir, quantidade, unidade, e o(s), código(s) da(s) encomenda(s)). | n/a                 | n/a                                                          |
|                                | instanciar nova ordem                                        | n/a                 | newOrdem(id ordem, dataEmissao, dataPrevista, codigoFabrico, quantidade, unidade, encomenda) |
|                                | seleção do produto                                           | n/a                 | selector(Iterable<Produtos>)                                 |
|                                | sistema informa o sucesso da operação.                       | n/a                 | n/a                                                          |
| NovaOrdemController            | instancia OrdemRepository                                    | package Application | OrdemRepository()                                            |
|                                | geração da data do sistema                                   | package Application | getDataSistema()                                             |
|                                | listar produtos com ficha de produção                        | package Application | getListaProdutos()                                           |
|                                | nova ordem                                                   | package Application | new Ordem (id ordem, dataEmissao, dataPrevista, codigoFabrico, quantidade, unidade, encomenda) |
| gerarDataEmissao               | retorno da data do sistema                                   | package Application | getDataSistema()                                             |
| ListarProdutosComFichaProducao | retorno da lista dos produtos                                | package Application | getListaProdutos()                                           |
| Ordem                          | nova ordem                                                   | package Domain      | new Ordem (id ordem, dataEmissao, dataPrevista, codigoFabrico, quantidade, unidade, encomenda) |
| OrdemRepository «Repository»   | registo da nova ordem na base de dados                       | package Repository  | new OrdemRepository()                                        |

## Diagrama de Sequências
https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/2010/2010_SD.png