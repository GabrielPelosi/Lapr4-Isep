# User Story 1010_1 - Especificação de um documento XSD que valide o conteúdo XML dos produtos

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML dos produtos serão efetuados através de um extenso ficheiro XML específico só para estes produtos.

Irá conter o número de Produtos necessárias de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra         | Teste |
| ------------- | ----- |
| unique-codigo | 1     |

**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<Produto CodigoFabrico="12345">
    	<CodigoComercial>507396</CodigoComercial>
        <DescricaoBreve>Folha A4</DescricaoBreve>
        <DescricaoCompleta>Folha de papel X-09, Tamanho A4</DescricaoCompleta>
        <Unidade>Resma</Unidade>
        <Categoria>Processados</Categoria>
	</Produto>

    <!--ERRO, mesmo codigo de produto-->
	<Produto CodigoFabrico="12345"> 
    	<CodigoComercial>398275</CodigoComercial>
        <DescricaoBreve>Camisola lã vermelha M</DescricaoBreve>
        <DescricaoCompleta>Camisola de lã vermelha, Gola alta, Tamanho M EU</DescricaoCompleta>
        <Unidade>UN</Unidade>
        <Categoria>Tecidos</Categoria>
	</Produto>
    
</ChaoDeFabrica>
```

