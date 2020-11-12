# User Story 2001 - Definir nova matéria-prima 



# Área - (1) Produção



## Conceitos de Implementação

| O quê?				 | Ação																	   | Onde?		 | Método				   												  |
|------------------------|-------------------------------------------------------------------------|-------------|------------------------------------------------------------------------|
| Sistema				 | definição de uma nova matéria-prima									   | UI			 | n/a					   												  |
| MateriaPrimaController | nova matéria-prima: código interno, descrição, categoria, ficha técnica | Application | newMateriaPrima() na classe MateriaPrima na package Domain	   		  |
| MateriaPrima			 | verifica se o código interno é único									   | Domain		 | uniqueCodigoInterno() na classe MateriaPrimaRepo na package Repository |
| - 					 | verifica se a categoria existe										   | -			 | validateCategoria() na classe CategoriaRepo na package Repository	  |
| -						 | adiciona nova matéria prima											   | -			 | addMateriaPrima() na classe MateriaPrimaRepo na package Repository	  |


## Diagrama de Sequências

O diagrama encontra-se na pasta 'documentation/diagramas' deste repositório presente no seguinte Link: https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/2001/2001_SD.png?at=master
