# Protocolo SSL (Secure socket layer) e TLS (Transport Layer Security)

## Definição e história do SSL

<img src="./img/20.png" alt="" width="300">

O SSL (Secure Sockets Layer) é um protocolo de segurança criptográfico utilizado para proteger a comunicação na Internet. Ele foi desenvolvido pela Netscape em 1994 e, posteriormente, foi substituído pelo TLS (Transport Layer Security), que é uma versão mais atualizada e segura do protocolo. O SSL/TLS é utilizado para estabelecer uma conexão segura entre um servidor web e um navegador, por exemplo, para que os dados sejam transmitidos de forma segura e protegida contra interceptações e adulterações. O protocolo é baseado em criptografia de chave pública (assimétrica) e criptografia de chave simétrica.

O processo de estabelecimento de uma conexão segura com SSL/TLS envolve os seguintes passos:

1. O cliente (navegador) envia uma solicitação de conexão segura ao servidor web.
2. O servidor responde enviando seu certificado digital, que contém a chave pública do servidor, assinada por uma AC confiável.
3. O navegador verifica a autenticidade do certificado digital do servidor, verificando a assinatura da AC e outras informações, como a validade do certificado.
4. Se o certificado for considerado válido, o navegador e o servidor iniciam um processo de negociação para estabelecer uma chave simétrica compartilhada.
5. Uma vez que a chave simétrica é estabelecida, a comunicação entre o navegador e o servidor é criptografada e protegida contra interceptações e adulterações.

O SSL/TLS é amplamente utilizado na Internet para proteger a comunicação em diversos protocolos, incluindo HTTPS, SMTPS, FTPS, IMAPS, POP3S, entre outros. A utilização de SSL/TLS é uma das principais formas de proteger a privacidade e segurança na Internet.

<br>

## Definição e história do TLS

<img src="./img/21.jpeg" alt="" width="300">

O TLS (Transport Layer Security) é um protocolo de segurança criptográfico que sucedeu o SSL (Secure Sockets Layer) e é utilizado para proteger a comunicação na Internet. Assim como o SSL, o TLS é utilizado para estabelecer uma conexão segura entre um cliente e um servidor, garantindo a privacidade e integridade dos dados transmitidos. O TLS utiliza criptografia de chave pública (assimétrica) e de chave simétrica para proteger a comunicação entre o cliente e o servidor. Ele é composto por várias camadas de protocolos que trabalham em conjunto para garantir a segurança da comunicação. O processo de estabelecimento de uma conexão segura com TLS envolve os seguintes passos:

1. O cliente envia uma solicitação de conexão segura ao servidor.
2. O servidor responde enviando seu certificado digital, que contém a chave pública do servidor, assinada por uma AC confiável.
3. O cliente verifica a autenticidade do certificado digital do servidor, verificando a assinatura da AC e outras informações, como a validade do certificado.
4. O cliente e o servidor iniciam um processo de negociação para estabelecer uma chave simétrica compartilhada. Esse processo envolve a negociação do algoritmo de criptografia a ser utilizado, o tamanho da chave simétrica e outras informações relevantes.
5. Uma vez que a chave simétrica é estabelecida, a comunicação entre o cliente e o servidor é criptografada e protegida contra interceptações e adulterações.

O TLS é utilizado em diversos protocolos, incluindo HTTPS, SMTPS, FTPS, IMAPS, POP3S, entre outros. Ele é uma das principais formas de proteger a privacidade e segurança na Internet e é constantemente atualizado para garantir a segurança e a compatibilidade com as tecnologias mais recentes.

<br>

<br>

[Voltar a Principais protocolos de comunicação da internet](/Arquivos/Conteudo/6%20-%20Ganhando%20produtividade%20com%20spring%20framwork/6.1%20Principais%20protocolos%20de%20comunicacao%20na%20internet.md)<br>
[Voltar ao inicio](/README.md)