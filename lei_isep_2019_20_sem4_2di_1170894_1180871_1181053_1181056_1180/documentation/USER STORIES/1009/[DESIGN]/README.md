# User Story 1009 - Inicializar depósitos 



# Área - (2) Chão de Fábrica



## Conceitos de Implementação

| O quê?			 | Ação											  | Onde?		 | Método																   |
|--------------------|------------------------------------------------|--------------|-------------------------------------------------------------------------|
| DepositoBoot		 | definição de alguns depósitos 				  | Bootstrapper | n/a																	   |
| DepositoController | cria a base de dados							  | Application	 | createDepositoRepo() na classe DepositoRepo na package Repository 	   |
| -					 | novo depósito: código alfanumérico e descrição | -			 | newDeposito() na classe Deposito na package Domain					   |
| Deposito			 | verifica se o código alfanumérico é único	  | Domain		 | uniqueCodigoAlfanumerico() na classe DepositoRepo na package Repository |
| -					 | adiciona novo depósito						  | -			 | addDeposito() na classe DepositoRepo na package Repository			   |



## Diagrama de Sequências

O diagrama encontra-se na pasta 'documentation/diagramas' deste repositório presente no seguinte Link: https://bitbucket.org/1181056/lei_isep_2019_20_sem4_2di_1170894_1180871_1181053_1181056_1180/src/master/documentation/USER%20STORIES/diagrams/1009/1009_SD.png?at=master