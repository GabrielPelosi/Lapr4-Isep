# User Story 2009 - Importar ordens de produção através de um ficheiro (i.e. CSV)

## Área - (1) Produção.

### Testes aferidos

**Teste 1:** Verificar que não é possível uma instância da classe  com valores nulos.

```java
@Test(expected = IllegalArgumentException.class)
public void ensureNullIsNotAllowed(){
    /* código de teste */
    ImportarOrdensController impO = new ImportarOrdensController(null);
}
```



**Teste 2:** Verificar que não é possível introduzir um ficheiro sem ser do tipo .csv.

```java
@Test(expected = IllegalArgumentException.class)
public void validateFile(){
    /* código de teste */
    FileReader f = new FileReader(nomeFich + ".csv");
}
```


