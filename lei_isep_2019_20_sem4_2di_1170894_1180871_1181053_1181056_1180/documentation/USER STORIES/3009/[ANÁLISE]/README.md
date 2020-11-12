# User Story 3009- Consultar e alterar o estado de Processamento das Linhas de Produção

# Área - (2) Chão de Fábrica



## Formato Breve 

Como Gestor de Chão de Fábrica, eu pretendo saber e alterar o estado (ativo/desativo) do processamento de mensagens de cada linha de produção bem como conhecer a última vez que o mesmo se realizou.

### Pré-condições 

O utilizador deve ter como cargo Gestor de Chão de Fábrica.

### Pós-condições 

Os estados das linhas e a última data de processamento são exibidos.
Os estados das linhas são alterados.

## Fluxo básico A

1. O Gestor de Chão de Fábrica solicita a consulta dos estados de processamento das linhas de produção.
2. O sistema lista as linhas de produção, os respetivos estados e últimas datas de processamento e pede ao utilizador que escolha uma opção de processamento.
3. O Gestor de Chão de Fábrica escolhe alterar o estado de processamento recorrente das linhas de produção.
4. O sistema permite ao utilizador escolher uma linha para alterar o estado.
5. O Gestor de Chão de Fábrica escolhe uma linha.
6. O sistema altera o estado da linha.

## Fluxo básico B

1. O Gestor de Chão de Fábrica solicita a consulta dos estados de processamento das linhas de produção.
2. O sistema lista as linhas de produção, os respetivos estados e últimas datas de processamento e pede ao utilizador que escolha uma opção de processamento.
3. O Gestor de Chão de Fábrica escolhe o (re)processamento de mensagens.
4. O sistema pede um intervalo de tempo e as linhas que o utilizador pretende (re)processar.
5. O Gestor de Chão de Fábrica insere os dados.
6. O sistema processa as mensagens adequadas.

## Glossário

- **Processamento Ativo:** processamento de mensagens é recorrente e processa todas as mensagens não processadas a cada intervalo de tempo.
- **Processamento Inativo:** processamento de mensagens é manual e o utilizador pode escolher processar mensagens dando um intervalo de tempo

