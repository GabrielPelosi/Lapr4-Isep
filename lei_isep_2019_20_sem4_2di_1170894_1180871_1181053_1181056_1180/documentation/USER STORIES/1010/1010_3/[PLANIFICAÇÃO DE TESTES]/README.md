# User Story 1010_3 - Especificação de um documento XSD que valide o conteúdo XML das máquinas

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML das máquinas serão efetuados através de um extenso ficheiro XML específico só para estas.

Irá conter o número de máquinas necessárias de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra              | Teste |
| ------------------ | ----- |
| unique-NumeroSerie | 1     |
| Data               | 2     |

**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<Maquina NumeroSerie="19028">
    	...
	</Maquina>

    <!--ERRO, mesmo numero de serie da maquina-->
	<Maquina NumeroSerie="19028"> 
    	...
	</Maquina>
    
</ChaoDeFabrica>
```

**Teste 2**:

```xml
<ChaoDeFabrica>
    
	<Maquina NumeroSerie="19028">
        <!--ERRO, ano (primeiros 4 digitos) tem que ser superior a 1000-->
        <!--ERRO, mes (digitos 5 e 6) tem que ser entre 1 e 12-->
        <!--ERRO, ano (ultimos 2 digitos) tem que ser entre 1 e 31-->
        <DataInstalacao>00991354</DataInstalacao>
        ...
	</Maquina>


</ChaoDeFabrica>
```