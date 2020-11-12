# User Story 1015 - Comunicações protegidas entre o simulador de máquina e o SCM.

## Área - (3) Comunicação.

## Protocolo de Comunicação

A estrutura dos pacotes a enviar entre a máquina e o SCM encontra-se inalterado, o que difere é n 1º acesso detetado entre a máquina e o servidor. A mesma tem que introduzir como parâmetro na sua compilação o nome do ficheiro cujo conteúdo permite o acesso ao servidor através da presença da chave (client.key).

Relembrando a estrutura do pacote da comunicação entre a Máquina e o SCM:

| Campo           | Offset (bytes) | Tamanho (bytes) |
| --------------- | -------------- | --------------- |
| **Version**     | 0              | 1               |
| **Code**        | 1              | 1               |
| **ID**          | 2              | 2               |
| **Data Length** | 4              | 2               |
| **Raw Data**    | 6              | **Data Length** |



Legenda:

Version : Protocol version number, um único byte que representa um unsigned int dentro do range de 0-255. A primeira versão suportada é o 0 (Mensagem inicial de HELLO).

Code: Message type code, um único byte que representa um unsigned int dentro do range de 0-255.

ID : representa o identificador único da Máquina. Numero = (1º byte + 256 x 2º byte)

Data length : representa o tamanho da mensagem a enviar (sendo 0 no HELLO, logo o tamanho do pacote é 6 bytes). Numero = (1º byte + 256 x 2º byte)

Raw Data : Data a ser interpretada pela aplicação destino (e.g. mensagem lida do ficheiro de texto)

Condições de comunicação:

- A comunicação com o SCM (sistema de comunicação de máquinas) deve usar o protocolo de TCP
- Tamanho mínimo do pacote: 6 bytes (Version + Code + ID + Data Length)
- Tamanho máximo de pacote: 512 bytes (6 bytes mínimos + 506 bytes possíveis no Raw data)



##### Valores possíveis do Code

| Code |                                                              |
| ---- | ------------------------------------------------------------ |
| 0    | HELLO request - um pedido HELLO baseada em TCP para o sistema central. O sistema central verifica se o ID da máquina é seu conhecido. Se sim, este envia de volta uma resposta ACK e atualiza/define no seu repositório o endereço de rede da máquina industrial. Caso contrário, uma resposta NACK é retornada, e o processo terminado. |
| 1    | MSG request - MSG baseadas em TCP ao sistema central e recebem uma resposta ACK caso o ID e endereço de rede da máquina coincidam com a informação disponível no sistema central. Caso contrário, o pedido é ignorado e a resposta NACK é retornada. |
| 2    | CONFIG request - Não utilizada nesta US                      |
| 3    | RESET request - Não utilizada nesta US                       |
| 150  | ACK response - contêm um texto de status a explicar o resultado do pedido e o estado atual da contraparte |
| 151  | NACK response - contêm um texto de status a explicar o resultado do pedido e o estado atual da contraparte |



## Diagrama de sequências

#### Segurança configurada no cliente (máquina)

![](..\..\diagrams\1015\1015_SD.png)



#### Segurança configurada no servidor (SCM)

