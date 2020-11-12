# User Story 2010 - Nova ordem de produção

## Área - (1) Produção.

### Testes aferidos

**Teste 1:** Verificar que não é possível uma instância da classe Ordem com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed(){
    /* código de teste */
    Ordem o = new Ordem(null, null, null, ...);
    Ordem o = new Ordem(123, null, null, ...);
    Ordem o = new Ordem(123, 20200512, null, ...);
    //etc...
}
```



**Teste 2:** Verificar que a quantidade é um valor maior que 0.

```java
@Test(expected = IllegalArgumentException.class)
public void validateQuantidade(){
    /* código de teste */
    Ordem o = new Ordem(123, 20200512, 20200519, 11112, -2, UN, EC2020/00030);
}
```



