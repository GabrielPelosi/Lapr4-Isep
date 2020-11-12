# User Story 3004 - Associar ficheiro de configuração a uma máquina.

## Área - (2) Chão de fábrica.

### Formato Breve
Como  Gestor de Chão de Fábrica, eu pretendo associar um ficheiro de configuração a  uma máquina.

#### Pré-condições

O utilizador deve ter como cargo Gestor de Chão de Fábrica.

A máquina já deve existir no sistema.

#### Pós-condições

A informação do ficheiro de configuração da máquina é persistida/guardada no sistema. 



### Cenário de sucesso principal (ou fluxo básico)

1. O Gestor de Chão de Fábrica inicia a associação de um ficheiro de configuração a uma máquina. 
2. O sistema solicita o nome do ficheiro de configuração e a respetiva máquina.
3. O Gestor de Chão de Fábrica indica os dados necessários.
4. O sistema valida, associa o ficheiro de configuração a uma máquina e informa o Gestor de Chão de Fábrica do sucesso da operação.



### Glossário

- **Máquina:** corresponde a um equipamento capaz de realizar operações com o objetivo de produzir um produto.
- **Ficheiro de Configuração:** documento que define o comportamento ou a operação que pretende que uma máquina assuma.