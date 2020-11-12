# User Story 5001 - Processar mensagens no sistema

## Área - (4) Processamento de Mensagens

## Formato Breve

Como Serviço de Processamento de Mensagens (SPM), pretendo efetuar o processamento das mensagens disponíveis no sistema.

### Pré-condições

n/a

### Pós-condições

As mensagens processadas são guardadas com o estado de 'PROCESSADAS'

## Cenário de sucesso principal (ou fluxo básico)

1. O utilizador pretende executar (manualmente) o SPM.
2. O sistema pede um intervalo de tempo para filtrar mensagens a processar.
3. O utilizador insere um intervalo de tempo.
4. O sistema pede uma linha de produção específica para filtrar mensagens a processar.
5. O utilizador insere uma linha de produção.
6. O sistema inicia o SPM.

## Glossário

- **SPM:** Sistema de Processamento de Mensagens. É usado para processar as mensagens, gerando quantidades de estorno, produção, etc. Este sistema gera notificações quando há um erro nas mensagens.
- **Mensagem:** São enviadas por máquinas para informar as atividades a decorrer no Chão de Fábrica. Têm informações sobre a data da sua emissão, ordem associada à produção, quantidades, produtos, etc.