# User Story 1005 - Inicializar definição de Categoria Matérias-Primas



# Área - (1) Produção



### Testes aferidos

**Teste 1:** Verificar que não é possível adicionar uma Categoria com um nome repetido.

```java
@Test
public void validateNome() {
	/* código do teste */
}
```


**Teste 2:** Verificar que não é possível criar uma instância da classe Categoria (Categoria de Matéria-Prima) com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
/*Para cada produto existente no catálogo*/
public void ensureNullIsNotAllowed(){
    /* código de teste */
    Categoria c = new Categoria(null,);

    /*etc...*/
}
```
