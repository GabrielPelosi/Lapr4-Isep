# User Story 2001 - Definir nova matéria-prima 

## Área - (1) Produção

### Testes aferidos

**Teste 1**: Verificar se o ficheiro passado por parâmetro foi encontrado no sistema.

```java
@Test
public void findFile(String location){
    /*código de teste*/
}
```



**Teste 2**:  Verificar se o ficheiro é do tipo CSV, através da localização passada por parâmetro.

```java
@Test
public void validateFile(String location){
    /*código de teste*/
}
```



**Teste 3**: Verificar que não é possível criar uma instância da classe MateriaPrima com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed(){
    /* código de teste */
    MateriaPrima mp = new MateriaPrima(123, "pinho", "madeira", null);
    MateriaPrima mp = new MateriaPrima(123, "pinho", null, null);
    MateriaPrima mp = new MateriaPrima(123, null, null, null);
    MateriaPrima mp = new MateriaPrima(null, null, null, null);
    /*etc...*/
}
```



**Teste 4**:  Verificar se o código interno de cada matéria prima é único.

```java
@Test
/*Para cada depósito a inicializar*/
public void uniqueCodigoInterno(){
    /*código de teste*/
}
```



**Teste 2**:  Verificar se a categoria existe.

```java
@Test
/*Para cada máquina a inicializar*/
public void validateCategoria(){
    /*código de teste*/
}
```

