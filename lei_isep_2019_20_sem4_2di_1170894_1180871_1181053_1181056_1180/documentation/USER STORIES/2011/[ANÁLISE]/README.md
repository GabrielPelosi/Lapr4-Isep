# User Story 2011 - Consultar Ordens de Produção num determinado estado.

# Área - (1) Produção



## Formato Breve

Como Gestor de Produção, eu pretendo consultar as ordens de produção que estão num determinado estado (e.g. em execução). Deve permitir consultar o detalhe de cada ordem de produção.

### Pré-condições

O utilizador deve ter como cargo Gestor de Produção.
O sistema deve ter pelo menos uma Ordem de Produção no estado pretendido.

### Pós-condições

As ordens de produção no estado pretendido dada definida são listadas.

## Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Produção solicita a consulta das ordens de produção num determinado estado.
2. O sistema pede o estado pretendido.
3. O Gestor de Produção insere os dados necessários.
4. O sistema lista as ordens de produção no estado inserido.
5. O Gestor de produção seleciona a ordem de produção pretendida.
6. O sistema mostra todos os detalhes da ordem de produção selecionada.

## Glossário

- **Ordem de Produção:** um pedido de fabrico com especificações sobre o produto e a quantidade a produzir, bem como os ids das encomendas a que pertencem e a data prevista de produção.
