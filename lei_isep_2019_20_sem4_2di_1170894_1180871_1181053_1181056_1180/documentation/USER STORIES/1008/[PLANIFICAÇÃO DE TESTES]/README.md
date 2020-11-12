# User Story 1008 - Inicializar linhas de produção

## Área - (2) Chão de Fábrica.

### Testes aferidos

**Teste 1**: verificar se cada código interno da máquina introduzida na linha de produção, é único na linha.

```java
@Test
/*por cada linha de produção a inicializar*/
public void validateUniqueCodigosLinhaProducao(){
	/*código de teste*/
}
```



**Teste 3:** verificar que não é possível criar uma instância da classe LinhaProducao com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
/*Para cada produto existente no catálogo*/
public void ensureNullIsNotAllowed(){
    /* código de teste */
    LinhaProducao  l = new LinhaProducao(null);
}
```



**Teste 3**: verificar se a linha de produção possui pelo menos uma máquina.

```java
@Test
/*por cada linha de produção a inicializar*/
public void validateNumMinMaquina(){
	/*código de teste*/
}
```

