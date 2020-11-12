# User Story 1010_5 - Especificação de um documento XSD que valide o conteúdo XML das categorias de matérias primas

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML das categorias de matérias primas serão efetuados através de um extenso ficheiro XML específico só para as estas categorias.

Irá conter o número de Categorias necessárias de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra                  | Teste |
| ---------------------- | ----- |
| unique-CodigoCategoria | 1     |

**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<Categoria CodigoCategoria="12345">
    	<DescricaoCategoria>Calças de ganga</DescricaoCategoria>
	</Categoria>

    <!--ERRO, mesmo codigo de categoria-->
	<Categoria CodigoCategoria="12345"> 
    	<DescricaoCategoria>Calças de fato de treino</DescricaoCategoria>
	</Categoria>
    
</ChaoDeFabrica>
```

