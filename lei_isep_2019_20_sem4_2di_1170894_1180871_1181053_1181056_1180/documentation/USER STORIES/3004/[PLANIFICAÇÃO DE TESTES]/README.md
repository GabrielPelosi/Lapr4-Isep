# User Story 3004 - Associar ficheiro de configuração a uma máquina.

## Área - (2) Chão de fábrica.

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