# User Story 5002 - Processar mensagens recorrentemente no sistema

## Área - (4) Processamento de Mensagens

### Testes aferidos

**Teste 1**: verificar que não é possível retomar a atividade de uma máquina se não tinha antes parado.

```java
@Test
public void ensureMaquinaAtivaUnica(){
	retomarMaquina();
}
```



**Teste 2**: verificar que não é possível consumir uma matéria que não exista em quantidade suficiente no depósito.

```java
@Test (assert success = false)
public void validateMateriaInDepositoForConsumo(MateriaPrima materia, double quantidade, Deposito deposito){
    boolean success = deposito.consumir(materia, null, quantidade);
}
```



**Teste 3**: verificar que não é possível parar/terminar atividade de uma máquina que não esteja ativa.

```java
@Test
public void ensureMaquinaAtiva(){
    pararMaquina();
}
```

```java
@Test
public void ensureMaquinaAtiva(){
    terminarMaquina();
}
```

