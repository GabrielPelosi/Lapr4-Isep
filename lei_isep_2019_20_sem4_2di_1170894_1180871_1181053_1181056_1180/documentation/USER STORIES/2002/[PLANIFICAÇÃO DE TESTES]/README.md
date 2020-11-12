# User Story 2002 - Definir nova categoria de matérias-primas

## Área - (1) Produção.

### Testes aferidos

**Teste 1:** Verificar que não é possível criar uma instância da classe Categoria com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed(){
    /* código de teste */
    Categoria c = new Categoria(null);
}
```



**Teste 2**:   verifica se a categoria é única no sistema.

```java
@Test
public void validateCategoria(){
    /*código de teste*/
}
```

