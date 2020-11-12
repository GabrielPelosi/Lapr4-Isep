# User Story 1005 -  Inicializar de Categorias de matérias-primas



# Área - (1) Produção

### Conceitos de Implementação

| O quê                  | Ação                                                         | Onde         | Método                                                       |
| ---------------------- | ------------------------------------------------------------ | ------------ | ------------------------------------------------------------ |
| CategoriaMateriaPrimaBoot       | solicita a inicialização dos dados                           | Bootstrapper | n/a                                                          |
| CategoriaMateriaPrimaController | cria a base de dados                                         | Application  | createCategoriaMateriaPrimaRepo() na classe CategoriaMateriaPrimaRepo na package Repository |
| -                      | nova categoira de matéria-prima com os dados: nome | -            | createMateriaPrima() na classe em questão                    |
|CategoriaMateriaPrima              | verificar que o nome é único na base de dados            | Domain       | validateCategoria()na classe CategoriaRepo na package Repository |
| CategoriaRepo          | n/a                                                          | Repository   | n/a                                                          |


### Diagrama de Sequências

[](https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/1005/1005_SD.png)
