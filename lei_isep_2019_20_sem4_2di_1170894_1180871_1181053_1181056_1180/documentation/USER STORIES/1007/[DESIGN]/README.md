# User Story 1007 - Inicializar máquinas 



# Área - (2) Chão de Fábrica



## Conceitos de Implementação

| O quê?			| Ação																						   | Onde?		  | Método																	  |
|-------------------|----------------------------------------------------------------------------------------------|--------------| --------------------------------------------------------------------------|
| MaquinaBoot		| definição de algumas máquinas																   | Bootstrapper | n/a																	 	  |
| MaquinaController | cria a base de dados																		   | Application  | createMaquinaRepo() na classe MaquinaRepo na package Repository 		  |
| -					| nova máquina: código interno, número de série, descrição, data de instalação, marca e modelo | -			  | newMaquina() na classe Maquina na package Domain						  |
| Maquina			| verifica se o número de série é único														   | Domain		  | uniqueNumeroSerie() na classe MaquinaRepo na package Repository   	   	  |
| -					| verifica se o código interno existe														   | -			  | validateCodigoInterno() na classe LinhaProducaoRepo na package Repository |
| -					| adiciona nova máquina																		   | -			  | addMaquina() na classe MaquinaRepo na package Repository				  |



## Diagrama de Sequências

O diagrama encontra-se na pasta 'documentation/diagramas' deste repositório presente no seguinte Link: https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/1007/1007_SD.png?at=master