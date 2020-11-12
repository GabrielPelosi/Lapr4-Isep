# User Story 1010_4 - Especificação de um documento XSD que valide o conteúdo XML das linhas de produção

## Área - (0) Geral.

### Testes aferidos

Os testes aferidos e planeados para a especificação de um documento XSD que valide o conteúdo XML das linhas de produção serão efetuados através de um extenso ficheiro XML específico só para estas linhas.

Irá conter o número de Produtos necessárias de forma a validar todo o tipo de especificidade/regra que foi definida para este XSD.

#### Regras a validar

| Regra         | Teste |
| ------------- | ----- |
| unique-codigo | 1     |

**Teste 1**:

```xml
<ChaoDeFabrica>
    
	<LinhaProducao IdLinha="12345"></LinhaProducao>

    <!--ERRO, mesmo codigo de linha-->
	<LinhaProducao IdLinha="12345"></LinhaProducao>
    
</ChaoDeFabrica>
```

