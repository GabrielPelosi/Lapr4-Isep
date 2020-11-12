# User Story 1004 - Inicializar definição de matérias-primas 



# Área - (1) Produção

### Conceitos de Implementação

| O quê                  | Ação                                                         | Onde         | Método                                                       |
| ---------------------- | ------------------------------------------------------------ | ------------ | ------------------------------------------------------------ |
| MateriaPrimaBoot       | solicita a inicialização dos dados                           | Bootstrapper | n/a                                                          |
| MateriaPrimaController | cria a base de dados                                         | Application  | createMateriaPrimaRepo() na classe MateriaPrimaRepo na package Repository |
| -                      | nova matéria-prima com os dados: codigo interno, descrição, categoria, ficha técnica | -            | createMateriaPrima() na classe em questão                    |
| MateriaPrimaRepo       | n/a                                                          | Repository   | n/a                                                          |
| CategoriaRepo          | n/a                                                          | Repository   | n/a                                                          |
| MateriaPrima           | verificar que o código interno é único na base de dados.     | Domain       | validateCodigoInterno() na classe MateriaPrimaRepo na package Repository |
| -                      | validar a categoria                                          | -            | validateCategoria()na classe MateriaPrimaRepo na package Repository |
| -                      | adicionar matéria-prima à base de dados                      | -            | addMateriaPrima()na classe MateriaPrimaRepo na package Repository |
| Categoria              | verificar que a categoria existe na base de dados            | Domain       | validateCategoria()na classe CategoriaRepo na package Repository |

### Diagrama de Sequências
O diagrama encontra-se na pasta 'documentation/USER STORIES/diagrams' deste repositório presente no seguinte link:

https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/e7aa046bdd3c/documentation/USER%20STORIES/diagrams/1004/1004_SD.png