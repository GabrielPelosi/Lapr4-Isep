# User Story 1010_8 - Especificação de um documento XSD que valide o conteúdo XML dos depósito

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML dos depósitos serão efetuados através de um extenso ficheiro XML específico só para estes.

Irá conter o número de depósitos necessários de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra             | Teste |
| ----------------- | ----- |
| unique-codigo     | 1     |
| DescricaoDeposito | 2     |

**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<Deposito CodigoDeposito="9876">
    	...
	</Deposito>

    <!--ERRO, mesmo codigo do deposito-->
	<Deposito CodigoDeposito="9876"> 
    	...
	</Deposito>
    
</ChaoDeFabrica>
```

**Teste 2**:

```xml
<ChaoDeFabrica>

    <!--ERRO, descricaoDeposito é composta por letras-->
	<Deposito CodigoDeposito="9876"> 
        <DescricaoDeposito>123<DescricaoDeposito> 
	</Deposito>
    
</ChaoDeFabrica>
```

