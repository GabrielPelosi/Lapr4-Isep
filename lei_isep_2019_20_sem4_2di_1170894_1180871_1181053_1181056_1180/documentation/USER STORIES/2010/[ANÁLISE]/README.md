# User Story 2010 - Nova ordem de produção.

# Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo introduzir manualmente uma nova ordem de produção.

### Pré-condições

A existência de um identificador prévio de uma Encomenda à qual pertence a ordem de produção.

A existência prévia do produto (codigo de fabrico do produto) a produzir com a ordem de produção. 

### Pós-condições

A informação da ordem de produção persistida/guardada no sistema.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a introdução manual de uma nova ordem de produção. 
2. O sistema solicita dados (i.e. id da ordem, data prevista, código de fabrico do produto a produzir, quantidade, unidade, e o(s), código(s) da(s) encomenda(s)).
3. O Gestor de Produção introduz os dados e seleciona, dos existentes, o produto que quer produzir com esta ordem.
4. O sistema valida e regista os dados  **regista os dados da ordem de produção **e informa o Gestor de Produção do sucesso da operação.



## Glossário

- **Ordem de produção:** documento em que se autoriza/solicita a produção de um produto numa determinada quantidade (a pretendida) através de um conjunto de matérias-primas e respetivas quantidades (de referência). No âmbito do nosso sistema, uma ordem caracteriza-se por ter um identificador, uma data de emissão, uma data prevista de execução, a identificação do produto a produzir e das encomendas (apenas identificadores).



