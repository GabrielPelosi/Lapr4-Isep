# User Story 1010_2 - Especificação de um documento XSD que valide o conteúdo XML das matérias-primas

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML das matérias-primas serão efetuados através de um extenso ficheiro XML específico só para estas.

Irá conter o número de matérias-primas necessárias de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra                       | Teste |
| --------------------------- | ----- |
| unique-CodigoInternoMateria | 1     |

**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<MateriaPrima CodigoInternoMateria="9876">
    	...
	</MateriaPrima>

    <!--ERRO, mesmo codigo interno da materiaPrima-->
	<MateriaPrima CodigoInternoMateria="9876"> 
    	...
	</MateriaPrima>
    
</ChaoDeFabrica>
```

