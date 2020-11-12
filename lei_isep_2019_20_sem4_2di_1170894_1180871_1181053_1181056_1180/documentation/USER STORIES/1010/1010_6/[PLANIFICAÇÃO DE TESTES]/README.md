# User Story 1010_6 - Especificação de um documento XSD que valide o conteúdo XML das ordens de produção

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML das ordens de produção serão efetuados através de um extenso ficheiro XML específico só para as estas ordens.

Irá conter o número de Ordens de produção necessárias de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra              | Teste |
| ------------------ | ----- |
| unique-CodigoOrdem | 1     |
| Data               | 2     |
| Quantidade         | 3     |
| Encomenda          | 4     |
| Estado             | 5     |



**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<Ordem CodigoOrdem="12345">
    	...
	</Ordem>

    <!--ERRO, mesmo codigo de ordem-->
	<Ordem CodigoOrdem="12345">
    	...
	</Ordem>

</ChaoDeFabrica>
```

**Teste 2**:

```xml
<ChaoDeFabrica>
    
	<Ordem CodigoOrdem="12345">
        <!--ERRO, ano (primeiros 4 digitos) tem que ser superior a 1000-->
        <!--ERRO, mes (digitos 5 e 6) tem que ser entre 1 e 12-->
        <!--ERRO, ano (ultimos 2 digitos) tem que ser entre 1 e 31-->
        <DataEmissao>01991332</DataEmissao>
        
        <!--ERRO, ano (primeiros 4 digitos) tem que ser superior a 1000-->
        <!--ERRO, mes (digitos 5 e 6) tem que ser entre 1 e 12-->
        <!--ERRO, ano (ultimos 2 digitos) tem que ser entre 1 e 31-->
        <DataPrevista>01991332</DataPrevista>
        ...
	</Ordem>


</ChaoDeFabrica>
```



**Teste 3**:

```xml
<ChaoDeFabrica>
    
	<Ordem CodigoOrdem="12345">
    	...
        <!--ERRO, quantidade tem que ser maior que 0-->
        <Quantidade>0</Quantidade>
	</Ordem>
    
</ChaoDeFabrica>
```



**Teste 4**:

```xml
<ChaoDeFabrica>
    
	<Ordem CodigoOrdem="12345">
        <!--ERRO, primeiros dois caracteres tem que ser EC-->
        <!--ERRO, caracteres de 3-6 tem que ser do tipo [1-9][0-9]{3} -->
        <!--ERRO, tem que existir 5 numeros antes da virgula-->
        <!--ERRO, so existe virgula se houver mauis um outro codigo de encomenda completo a seguir-->
        <Encomendas>EI0199/0001,</Encomendas>
	</Ordem>

</ChaoDeFabrica>
```



**Teste 5**:

```xml
<ChaoDeFabrica>
    
	<Ordem CodigoOrdem="12345">
    	...
        <!--ERRO, tem que ser um dos seguintes : 'pendente', 'execução' ou 'terminado'-->
        <Estado>a trabalhar</Estado>
	</Ordem>

</ChaoDeFabrica>
```

