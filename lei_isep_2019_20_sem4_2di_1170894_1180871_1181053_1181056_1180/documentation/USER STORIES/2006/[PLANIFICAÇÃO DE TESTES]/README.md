# User Story 2006 - Definir produto



# Área - (1) Produção

### Testes aferidos

**Teste 1:** verificar que não é possível criar um Produto com um Código de Fabrico já existente na base de dados, no caso do seu Código Comercial não for único na mesma.

```java
@Test
public void validateCodigoFabrico(){
    /* código de teste */
}
```

**Teste 2:** Verificar que não é possível criar uma instância da classe Produto com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
/*Para cada produto existente no catálogo*/
public void ensureNullIsNotAllowed(){
    /* código de teste */
    Produto p = new Produto(null, null, null, null);
    Produto p = new Produto(123, null, null, null);
    Produto p = new Produto(123, 12345, null, null);
    /*etc...*/
}
```

**Teste 3:** verificar se o ficheiro passado por parâmetro foi encontrado no sistema.

```java
@Test
public void findFile(String location){
    /*código de teste*/
}
```

**Teste 4:**  verificar se o ficheiro é do tipo CSV, através da localização passada por parâmetro.

```java
@Test
public void validateFile(String location){
    /*código de teste*/
}
```