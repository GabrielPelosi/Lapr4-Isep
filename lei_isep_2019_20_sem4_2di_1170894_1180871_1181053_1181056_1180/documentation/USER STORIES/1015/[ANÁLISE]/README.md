# User Story 1015 - Comunicações protegidas entre o simulador de máquina e o SCM.

## Área - (3) Comunicação.

## Formato Breve

Como Gestor de Projeto, eu pretendo que as comunicações entre o simulador de máquina e o SCM estejam protegidas.

### Pré-condições

Aplicar SSL/TLS com autenticação através de certificados de chave pública. 

### Pós-condições

A comunicação entre ambos requer uma chave de autenticação de forma a permitir a comunicação.



### Glossário

**SSL/TLS**:  é um protocolo projetado para reforçar a segurança nas comunicações de rede na camada quatro, sobre UDP e TCP, portanto, ao contrário do IPsec (é uma extensão do protocolo IP que visa a ser o método padrão para o fornecimento de privacidade do usuário (aumentando a confiabilidade das informações fornecidas pelo usuário para uma localidade da internet, como bancos)) operando na camada três, é usado diretamente pelas aplicações de rede. Devido à necessidade de negociar e estabelecer parâmetros de segurança entre duas aplicações, o SSL / TLS é usado principalmente em conexões TCP usando o modelo cliente-servidor.

