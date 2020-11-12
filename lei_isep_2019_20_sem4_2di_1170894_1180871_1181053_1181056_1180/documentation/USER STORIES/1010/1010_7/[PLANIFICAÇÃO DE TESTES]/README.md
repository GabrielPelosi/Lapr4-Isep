# User Story 1010_7 - Especificação de um documento XSD que valide o conteúdo XML das fichas de produção

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML das fichas de produção serão efetuados através de um extenso ficheiro XML específico só para estas.

Irá conter o número de fichas de produção necessárias de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra            | Teste |
| ---------------- | ----- |
| unique-codigo    | 1     |
| TFichaMateria    | 2     |
| TFichaQuantidade | 3     |

**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<FichaProducao CodigoFichaProducao="9876">
    	...
	</FichaProducao>

    <!--ERRO, mesmo codigo da fichaProducao-->
	<FichaProducao CodigoFichaProducao="9876"> 
    	...
	</FichaProducao>
    
</ChaoDeFabrica>
```

**Teste 2**:

```xml
<ChaoDeFabrica>
    
    <!--ERRO, fichaMateria não é composta por letras-->
	<FichaProducao CodigoFichaProducao="9876">
    	<FichaMateria>A123<FichaMateria> 
        ...
	</FichaProducao>
            
    <!--ERRO, fichaMateria é composta por 4 números-->
	<FichaProducao CodigoFichaProducao="9876">
    	<FichaMateria>1234,12345<FichaMateria>
        ...
	</FichaProducao>
    
</ChaoDeFabrica>
```

**Teste 3**:

```xml
<ChaoDeFabrica>
    
    <!--ERRO, fichaQuantidade é iniciada por um número de 1 a 9-->
	<FichaProducao CodigoFichaProducao="9876">
        ...
    	<FichaQuantidade>01,12345,1203<FichaQuantidade> 
	</FichaProducao>
            
    <!--ERRO, fichaQuantidade é composta por 2 ou 7 números-->
	<FichaProducao CodigoFichaProducao="9876">
        ...
    	<FichaQuantidade>123456789<FichaQuantidade> 
	</FichaProducao>            
    
</ChaoDeFabrica>
```

