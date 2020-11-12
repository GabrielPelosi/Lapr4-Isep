# User Story 4001 - Importar ficheiro de mensagens e validar lexicamente.

## Área - (3) Comunicação.

### Testes aferidos

**Teste 1:** Verificar que não é possível uma instância da classe  com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed(){
    /* código de teste */
    AssociarFicheiroConfiguracaoController afcO = new AssociarFicheiroConfiguracaoController(null);
}
```



**Teste 2:** Verificar se o ficheiro não se encontra vazio.

```java
@Test(expected = IllegalArgumentException.class)
public void validateFileNotEmpty(){
    /* código de teste */
    FileReader f = new FileReader(nomeFich);
}
```



**Teste 3:** verificar se a Maquina existe na Base de Dados

```java
@Test
public void maquinaExists(){
    /* código de teste */
}
```



**Teste 4:** verificar se o Produto existe na Base de Dados

```java
@Test
public void produtoExists(){
    /* código de teste */
}
```



**Teste 5:** verificar se o Deposito existe na Base de Dados

```java
@Test
public void depositoExists(){
    /* código de teste */
}
```



**Teste 6:** verificar se a Ordem de Produção existe na Base de Dados

```java
@Test
public void ordemExists(){
    /* código de teste */
}
```

