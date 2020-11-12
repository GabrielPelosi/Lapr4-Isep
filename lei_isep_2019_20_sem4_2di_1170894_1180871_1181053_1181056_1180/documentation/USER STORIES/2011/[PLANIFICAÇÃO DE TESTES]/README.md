# User Story 2011 - Consultar Ordens de Produção num determinado estado

## Área - (1) Produção

### Testes aferidos

**Teste 1:** Verificar que não é possível introduzir uma encomenda vazia.

```java
@Test(expected = IllegalArgumentException.class)
public void ensureEncomendaIdNotNull(){
	IdEncomenda id = new IdEncomenda(null);
}
```
