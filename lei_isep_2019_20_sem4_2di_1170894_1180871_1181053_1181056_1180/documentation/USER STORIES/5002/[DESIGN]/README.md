# User Story 5001- Processar mensagens no sistema

# Área - (2) Chão de Fábrica

### Conceitos de Implementação

| O quê                                   | Ação                                                         | Onde        | Método                                                       |
| --------------------------------------- | ------------------------------------------------------------ | ----------- | ------------------------------------------------------------ |
| ProcessarMensagensController | processa todas as mensagens lidas pelo sistema de processamento de mensagens | Application | findMensagensWithFiltros(data1, data2, linha) na classe  ProcessarMensagensController |
| ProcessarMensagensThread | cria uma thread para cada linha diferente existente no sistema  | Application | createThreadProcessamento() na classe ProcessarMensagensThread |
|  | processa mensagem de inicio de atividade | Application | mensagemInicio(mensagem) na classe ProcessarMensagensThread |
|  | processa mensagem de retoma de atividade | Application | mensagemRetoma(mensagem) na classe ProcessarMensagensThread |
|  | processa mensagem de retoma de consumo | Application | mensagemConsumo(mensagem) na classe ProcessarMensagensThread |
|  | processa mensagem de paragem | Application | mensagemParagem(mensagem) na classe ProcessarMensagensThread |
|  | processa mensagem de fim de atividade | Application | mensagemFim(mensagem) na classe ProcessarMensagensThread |
|  | processa mensagem de producao | Application | mensagemProducao(mensagem) na classe ProcessarMensagensThread |
|  | processa mensagem de entrega de producao | Application | mensagemEntregaProducao(mensagem) na classe ProcessarMensagensThread |
|  | processa mensagem de retoma de estorno | Application | mensagemEstorno(mensagem) na classe ProcessarMensagensThread |
| ProcessarMensagemSingleton | vai procurar o deposito do qual foi requesitado materia prima | Application | getDeposito(depositoId) na classe ProcessarMensagemSingleton |
|  | guarda o deposito com as quantidade de materia primas atualizadas | Application | save(deposito) na classe ProcessarMensagemSingleton |
|  | vai procurar o lote no qual vai ser guardado o resultado da producao | Application | getLote(loteId) na classe ProcessarMensagemSingleton |
|  | guarda o lote com as quantdades atualizadas | Application | save(lote) na classe ProcessarMensagemSingleton |
|  | guarda a notificcao de erro criada | Application | save(notificacao) na classe ProcessarMensagemSingleton |
|  | guarda o consumo total apos o processamento de todas as mensagens | Application | save(consumo) na classe ProcessarMensagemSingleton |
|  | guarda o estorno total apos o processamento de todas as mensagens | Application | save(estorno) na classe ProcessarMensagemSingleton |
|  | guarda o desvio total apos o processamento de todas as mensagens | Application | save(desvio) na classe ProcessarMensagemSingleton |
|  | guarda o tempo de producao total apos o processamento de todas as mensagens | Application | save(tempoProducao) na classe ProcessarMensagemSingleton |
| | espera a quantidade dade de milissegundos | Application | wait(timeoutMillis) na classe ProcessarMensagemSingleton |
| MensagemRepository | vai procurar todas as mensagens criadas por uma linha num determinada espaco de tempo | Repository | findMensagensWithFiltros(data1, data2, linha) na classe MensagemRepository |
| Deposito | entrega materia prima a uma maquina | Domain | consumir(materia, produto, quantidade) na classe Deposito |
|  | entrega o produto finalizado | Domain | entregar(materia, produto, quantidade) na classe Deposito |
| DepositoRepository | procura um deposito | Repository | findDeposito(deposito) na classe DepositoRepository |
|  | guarda o estado atual de um deposito | Repository | save(deposito) na classe DepositoRepository |
| Lote | adiciona o produto criado a um lote | Domain | save(lote) na classe Lote |
| LoteRepository | procura um lote | Repository | findLote(lote) na classe LoteRepository |
|  | guarda o estado atual de um lote| Repository | save(lote) na classe LoteRepository |
| Notificacao | cria uma notificacao | Domain | Notificacao(tipoErro, codigoMaquina) na classe Notificacao |
| NotificacaoRepository | guarda uma notificacao | Repository | save(notificacao) na classe NotificacaoRepository |
| Consumo | cria o consumo total resultado do processamento de mensagens | Domain | createConsumo() na classe Consumo |
| ConsumoRepository | guarda o consumo criado no processamento de mensagens | Repository | save(consumo) na classe ConsumoRepository |
| Estorno | cria o estorno total resultado do processamento de mensagens | Domain | createEstorno() na classe Estorno |
| EstornoRepository | guarda o estorno criado no processamento de mensagens | Repository | save(estorno) na classe EstornoRepository |
| Desvio | cria o desvio total resultado do processamento de mensagens | Domain | createDesvio() na classe Desvio |
| DesvioRepository | guarda o desvio criado no processamento de mensagens | Repository | save(desvio) na classe DesvioRepository |
| TempoProducao | cria o tempo de producao total resultado do processamento de mensagens | Domain | createTempoProducao() na classe TempoProducao |
| TempoProducaoRepository | guarda o tempo de producao criado no processamento de mensagens | Repository | save(tempoProducao) na classe TempoProducaoRepository |


### Diagrama de Sequências

![](../../diagrams/5002/5002_SD.png)


![](../../diagrams/5002/MensagemConsumo.png)

![](../../diagrams/5002/MensagemEntregaProducao.png)

![](../../diagrams/5002/MensagemEstorno.png)

![](../../diagrams/5002/MensagemProducao.png)

![](../../diagrams/5002/MensagemErro.png)

![](../../diagrams/5002/FimUS.png)
