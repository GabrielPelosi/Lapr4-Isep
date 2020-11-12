# User Story 1007 - Inicializar máquinas

## Área - (2) Chão de Fábrica

### Testes aferidos

**Teste 1**: Verificar que não é possível criar uma instância da classe Maquina com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
/*Para cada máquina a inicializar*/
public void ensureNullIsNotAllowed(){
    /* código de teste */
    Maquina m = new Maquina(123, 1234, "maquina", "01/05/2020", "Marca", null);
    Maquina m = new Maquina(123, 1234, "maquina", "01/05/2020", null, null);
    Maquina m = new Maquina(123, 1234, "maquina", null, null, null);
    Maquina m = new Maquina(123, 1234, null, null, null, null);
    Maquina m = new Maquina(123, null, null, null, null, null);
    Maquina m = new Maquina(null, null, null, null, null, null);
    /*etc...*/
}
```



**Teste 2**:  Verificar se o código interno existe no contexto de uma linha de produção.

```java
@Test
/*Para cada máquina a inicializar*/
public void validateCodigoInterno(){
    /*código de teste*/
}
```



**Teste 3:**  Verificar se o número de série de cada máquina é único.

```java
@Test
/*Para cada máquina a inicializar*/
public void UniqueNumeroSerie(){
    /*código de teste*/
}
```

