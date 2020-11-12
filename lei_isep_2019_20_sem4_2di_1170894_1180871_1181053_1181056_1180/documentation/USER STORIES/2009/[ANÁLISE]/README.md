# User Story 2009 - Importar ordens de produção através de um ficheiro (i.e CSV)

## Área - (1) Produção.

## Formato Breve

Como Gestor de Produção, eu pretendo importar ordens de produção através de um ficheiro CSV.

### Pré-condições

n/a.
Nota: A existência/criação de um ficheiro CSV com as ordens de produção não é responsabilidade do serviço que estamos a prestar, é gerada por um sistema externo.

### Pós-condições

A informação importada do ficheiro é persistida/guardada no sistema.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção inicia a importação das ordens de produção através de um ficheiro CSV. 
2. O sistema solicita o nome do ficheiro .csv. 
3. O Gestor de Produção introduz os dados solicitados. 
4. O sistema valida e **regista os dados das novas ordens**, informando o Gestor de Produção do sucesso da operação.



## Glossário

- **Ordem de produção:** documento em que se autoriza/solicita a produção de um produto numa determinada quantidade (a pretendida) através de um conjunto de matérias-primas e respetivas quantidades (de referência). No âmbito do nosso sistema, uma ordem caracteriza-se por ter um identificador, uma data de emissão, uma data prevista de execução, a identificação do produto a produzir e das encomendas (apenas identificadores).

