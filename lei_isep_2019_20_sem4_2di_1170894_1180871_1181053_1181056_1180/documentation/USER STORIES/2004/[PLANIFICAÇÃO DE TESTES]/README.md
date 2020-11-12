# User Story 2004 - Definir Ficha de Produção



# Área - (1) Produção

### Testes aferidos

**Teste 1:** verificar que não é possível criar uma FichaProducao com um Código de Fabrico já existente na base de dados.

```java
@Test
public void validateCodigoFabrico(){
    /* código de teste */
}
```

**Teste 2:** Verificar que não é possível criar uma instância da classe FichaProducao com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
/*Para cada produto existente no catálogo*/
public void ensureNullIsNotAllowed(){
    /* código de teste */
    FichaProducao fp = new FichaProducao(null, null, null);
    FichaProducao fp = new FichaProducao(123, null, null);
    FichaProducao fp = new FichaProducao(123,"m,n,c,b", null);

    /*etc...*/
}
```
**Teste 3:** assegurar que as materias-primas existem na base de dados.

```java
@Test
public void validateMateriaPrima(){
    /* código de teste */
}
```
**Teste 4:** assegurar que a quantidade de materias-primas é maior que zero.

```java
@Test
public void validateQuantidadeMateriaPrimas(){
    /* código de teste */
}
```
