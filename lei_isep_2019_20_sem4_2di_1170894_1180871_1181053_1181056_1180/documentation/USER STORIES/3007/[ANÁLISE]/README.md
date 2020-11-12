# User Story 3007- Consultar notificações de erros de processamento arquivadas

# Área - (2) Chão de Fábrica



## Formato Breve 

Como Gestor de Chão de Fábrica, eu pretendo consultar as notificações de erros de processamento arquivadas.

### Pré-condições 

O utilizador deve ter como cargo Gestor de Chão de Fábrica.

### Pós-condições 

As notificações de erros de processamento arquivadas são listadas.

## Cenário de sucesso principal (ou fluxo básico) 

1. O Gestor de Chão de Fábrica solicita a consulta das notificações de erros de processamento arquivadas.
2. O sistema permite ao Gestor de Chão de Fábrica filtrar a listagem por tipo de erro, linha de produção, máquina ou data.
3. O Gestor de Chão de Fábrica escolhe todos os filtros.
4. O sistema apresenta os tipos de erros disponíveis e solicita ao Gestor de Chão de Fábrica que escolha um.
5. O Gestor de Chão de Fábrica escolhe um tipo de erro.
6. O sistema lista as linhas de produção e solicita ao Gestor de Chão de Fábrica que escolha uma.
7. O Gestor de Chão de Fábrica escolhe uma.
8. O sistema lista as máquinas da linha de produção escolhida e solicita ao Gestor de Chão de Fábrica que escolha uma.
9. O Gestor de Chão de Fábrica escolhe uma.
10. O sistema solicita ao Gestor de Chão de Fábrica que insira uma data.
11. O Gestor de Chão de Fábrica insere os dados necessários.
12. O sistema apresenta as notificações de erros arquivadas com o tipo de erro, linha de produção, máquina e data escolhidos.

## Glossário

- **Notificação de erro de processamento:** é gerada em qualquer tipo de erro efetuado no chão de fábrica e contém informações sobre o erro em questão (i.e tipo de erro, linha de produção geradora, máquina geradora, data). Pode estar ativa ou arquivada.

