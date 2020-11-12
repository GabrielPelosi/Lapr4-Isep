# User Story 5002 - Processar mensagens recorrentemente no sistema

## Área - (4) Processamento de Mensagens

## Formato Breve

Como Serviço de Processamento de Mensagens (SPM), pretendo efetuar o processamento das mensagens disponíveis no sistema.

### Pré-condições

n/a

### Pós-condições

As mensagens processadas são guardadas com o estado de 'PROCESSADAS'

## Cenário de sucesso principal (ou fluxo básico)

1. O utilizador pretende executar (manualmente) o SPM.
2. O sistema pergunta que modo de processamento deve ser efetuado.
3. O utilizador escolhe o modo de processamento recorrente.
4. O sistema pede ao utilizador que insira uma data e hora inicial para o processamento e um intervalo de tempo para aplicar entre cada sessão de processamento.
5. O utilizador insere os dados necessários.
6. O sistema apresenta uma lista de linhas de produção e deixa o utilizador escolher.
7. O utilizador escolhe linhas de produção.
8. O sistema inicia o SPM.

## Glossário

- **SPM:** Sistema de Processamento de Mensagens. É usado para processar as mensagens, gerando quantidades de estorno, produção, etc. Este sistema gera notificações quando há um erro nas mensagens.
- **Mensagem:** São enviadas por máquinas para informar as atividades a decorrer no Chão de Fábrica. Têm informações sobre a data da sua emissão, ordem associada à produção, quantidades, produtos, etc.