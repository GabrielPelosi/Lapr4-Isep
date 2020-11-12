# User Story 1009 - Inicializar depósitos

## Área - (2) Chão de Fábrica

### Testes aferidos

**Teste 1**: Verificar que não é possível criar uma instância da classe Deposito com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
/*Para cada depósito a inicializar*/
public void ensureNullIsNotAllowed(){
    /* código de teste */
    Deposito d = new Deposito(null, "deposito");
    Deposito d = new Deposito("D231", null);
    Deposito d = new Deposito(null, null);
}
```



**Teste 2**:  Verificar se o código alfanumérico de cada depósito é único.

```java
@Test
/*Para cada depósito a inicializar*/
public void uniqueCodigoAlfanumerico(){
    /*código de teste*/
}
```

