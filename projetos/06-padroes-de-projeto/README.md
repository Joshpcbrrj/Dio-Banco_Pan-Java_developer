# Padroes-de-projeto-com-Java
Desafio - Explorando Padrões de Projetos na Prática com Java


### Para usar o swagger no browser
Para chamar o swagger no browser quando abrir meu projeto com spring: localhost:8080/swagger-ui.html

# Inclusões minhas:
- Modificação no pom.xml incluindo algumas dependências e setando versões para outras.
- Buscar Cliente por ID como parâmetro e se não existir retorna no terminal "cliente não existe”
- Busca cliente para deletar de acordo com o Id. Caso não encontre,retorna exception "Cliente não encontrado com o ID" no terminal

## Modificações no POM no meu projeto Spring
1. No meu POM tem `groupId` definido como `josue`, enquanto o POM original tem `groupId` definido como `digitalinnovation.one`.
2. O meu POM usa a versão 17 do Java (`<java.version>17</java.version>`), enquanto o do professor usa versão 11 (`<java.version>11</java.version>`).
3. O meu arquivo inclui uma dependência do Hibernate com a versão 5.4.30.Final (`<version>5.4.30.Final</version>`), enquanto que no POM original não tem essa dependência.
4. O meu POM tem uma tag `<parent>` que define o parent POM da aplicação. Ambos os arquivos têm a mesma definição de parent, mas o meu POM define o elemento `<relativePath />` como nulo.


#### Repositórios originais dos projetos 
[Lab Padrões de Projeto Java](https://github.com/digitalinnovationone/lab-padroes-projeto-java)
[Lab Padrões de Projeto Spring](https://github.com/digitalinnovationone/lab-padroes-projeto-spring)


