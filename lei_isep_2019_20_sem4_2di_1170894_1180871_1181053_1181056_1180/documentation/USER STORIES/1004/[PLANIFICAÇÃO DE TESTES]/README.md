# User Story 1004 - Inicializar definição de matérias-primas 



# Área - (1) Produção



### Testes aferidos

**Teste 1:** Verificar que não é possível adicionar uma Matéria-Prima com um código interno fora dos parâmetros aceitáveis.

```java
@Test
public void validateCodigoInterno() {
	/* código do teste */
}
```

**Teste 2:** Verificar que não é possível adicionar uma Matéria-Prima com um código interno já existente na base de dados.

```java
@Test
public void checkUniqueCodigoInternoDB() {
	/* código do teste */
}
```

**Teste 3:** Verificar que não é possível adicionar uma Matéria-Prima com uma Categoria não existente na base de dados.

```java
@Test
public void validateCategoria() {
	/* código do teste */
}
```

**Teste 4:** Verificar que não é possível criar uma instância da classe MatériaPrima com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
/*Para cada produto existente no catálogo*/
public void ensureNullIsNotAllowed(){
    /* código de teste */
    MateriaPrima p = new MateriaPrima(null, null, null, null);
    MateriaPrima p = new MateriaPrima(123, null, null, null);
    MateriaPrima p = new MateriaPrima(123, "abc", null, null);
    /*etc...*/
}
```