# User Story 5001 - Processar mensagens no sistema

## Área - (4) Processamento de Mensagens

### Testes aferidos

**Teste 1**: verificar que não é possível iniciar/retomar a atividade de uma máquina enquanto outra máquina na mesma linha de produção está ativa

```java
@Test
public void ensureMaquinaAtivaUnica(){
	ativarMaquina();
    ativarMaquina();
}
```
```java
@Test
public void ensureMaquinaAtivaUnica(){
    pararMaquina();
	retomarMaquina();
    retomarMaquina();
}
```



**Teste 2**: verificar que não é possível retomar a atividade de uma máquina se não tinha antes parado.

```java
@Test
public void ensureMaquinaAtivaUnica(){
	retomarMaquina();
}
```



**Teste 3**: verificar que não é possível consumir uma matéria que não exista em quantidade suficiente no depósito.

```java
@Test (assert success = false)
public void validateMateriaInDepositoForConsumo(MateriaPrima materia, double quantidade, Deposito deposito){
    boolean success = deposito.consumir(materia, null, quantidade);
}
```



**Teste 4**:  verificar que não é possível produzir um produto sem nenhuma quantidade da matéria necessária para a sua produção especificada na sua ficha de produção.

```java
@Test (success = false)
public void validateMateriaInMaquinaForProducao(FichaProducao ficha, Produto produto, double quantProd){
	MateriaPrima materia = ficha.getMateria();
    double quantMateria = 0;
    if(materia.equals(ficha.getMateria()))
        boolean success = quantMateria > 0;
}
```



**Teste 5:** Verificar que não é possível estornar uma quantidade de Matéria não tivesse sido consumida.

```java
@Test
public void ensureLoteExists(Materia materia){
    double quantidade = 5;
    consumir(materia, quantidade);
    estornar(materia, 10);
}
```



**Teste 6**: verificar que não é possível parar/terminar atividade de uma máquina que não esteja ativa.

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

