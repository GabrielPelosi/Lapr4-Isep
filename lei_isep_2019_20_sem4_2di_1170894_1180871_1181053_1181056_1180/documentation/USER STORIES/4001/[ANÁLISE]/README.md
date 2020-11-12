# User Story 4001 - Importar ficheiro de mensagens e validar lexicamente.

## Área - (3) Comunicação.

### Formato Breve
Como  Serviço de Comunicação com as Máquinas (SCM), pretendo importar, de forma  concorrente/paralela, as mensagens existentes nos ficheiros de texto  presentes no diretório de entrada de forma a disponibilizar as mesmas para  processamento.

#### Pré-condições

n/a

#### Pós-condições

A informação das mensagens é persistida/guardada no sistema. 



### Cenário de sucesso principal (ou fluxo básico)

1. O Serviço de Comunicação com as Máquinas (SCM) inicia a importação de um ficheiro de texto com mensagens. 
2. O sistema importa as mensagens existentes nos ficheiros de texto  presentes no diretório de entrada, valida lexicalmente essas mensagens e informa do sucesso da operação.



### Glossário

- **Mensagem:** corresponde a um conjunto de dados gerado pela máquina e devidamente estruturado de acordo com um determinado tipo de mensagem
- **Tipos de Mensagem:**

| Código | Tipo                | Formato                                                   |
| ------ | ------------------- | --------------------------------------------------------- |
| C0     | Consumo             | Máquina; TipoMsg; DataHora; Produto; Quantidade; Depósito |
| C9     | Entrega de Produção | Máquina; TipoMsg; DataHora; Produto; Quantidade; Depósito |
| P1     | Produção            | Máquina; TipoMsg; DataHora; Produto; Quantidade; Lote     |
| P2     | Estorno             | Máquina; TipoMsg; DataHora; Produto; Quantidade; Depósito |
| S0     | Inicio de Atividade | Máquina; TipoMsg; DataHora; OrdemProducao                 |
| S1     | Retoma de Atividade | Máquina; TipoMsg; DataHora; Erro                          |
| S8     | Paragem             | Máquina; TipoMsg; DataHora                                |
| S9     | Fim de Atividade    | Máquina; TipoMsg; DataHora; OrdemProducao                 |

