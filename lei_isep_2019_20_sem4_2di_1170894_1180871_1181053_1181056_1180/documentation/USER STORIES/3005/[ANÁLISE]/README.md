# User Story 3005- Consultar notificações de erros de processamento por tratar

# Área - (2) Chão de Fábrica



## Formato Breve 

Como Gestor de Chão de Fábrica, eu pretendo consultar as notificações de erros de processamento por tratar.

### Pré-condições 

O utilizador deve ter como cargo Gestor de Chão de Fábrica.

### Pós-condições 

As notificações de erros de processamento por tratar são listadas.

## Cenário de sucesso principal (ou fluxo básico) 

1. O Gestor de Chão de Fábrica solicita a consulta das notificações de erros de processamento por tratar.
2. O sistema permite ao Gestor de Chão de Fábrica filtrar a listagem por tipo de erro e linha de produção.
3. O Gestor de Chão de Fábrica escolhe os dois filtros.
4. O sistema apresenta os tipos de erros disponíveis e solicita ao Gestor de Chão de Fábrica que escolha um.
5. O Gestor de Chão de Fábrica escolhe um tipo de erro.
6. O sistema lista as linhas de produção e solicita ao Gestor de Chão de Fábrica que escolha uma.
7. O Gestor de Chão de Fábrica escolhe uma.
8. O sistema apresenta as notificações de erros ativas com o tipo de erro e linha de produção escolhidos.

## Glossário

- **Notificação de erro de processamento:** é gerada em qualquer tipo de erro efetuado no chão de fábrica e contém informações sobre o erro em questão (i.e tipo de erro, linha de produção geradora, máquina geradora, data). Pode estar ativa ou arquivada.

