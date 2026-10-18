# rdswithapirest

Descrição
----------

Esse projeto está associado ao curso da DIO - Digital Innovation One - utilizando Java para criar uma API rest. Uma API é dita Restful se ela segue o padrão REST, 
ou então, o modelo de maturidade de Richardson. Para isso, utilizamos nesta API básica módulos como: spring web e spring Hateoas.
O spring-web, é um módulo do framework spring, fornece uma séries de métodos e classes com estrutura rest para simplificar a criação de uma API. Enquanto isso,
o spring-hateoas possibilita a implementação do último nível de maturidade de Richardson. Dessa forma, poderemos aplicar links aos recurso e consequentemente, 
fornecemos um guia para navegabilidade do usuário dentro da API.

Neste projeto foi utilizado a versão 17 do Java.

Página para criar seu projeto spring-boot: https://start.spring.io/

Conteúdo
--------

MyFirstApi
-> Person
->PersonController
->PersonRepository

ApiRest
->Employee
->EmployeeController
->EmployeeRepository
->LoadBD
->EmployeeNotFoundException
->EmployeeNotFoundAdvice

Hateoas
->Controllers
->Repositories
->Exceptoins
->Entyties
->Swagger
->LoadBDHateoas


Cronograma do curso DIO
------------------------

Criando uma API REST Conectada ao Amazon RDS com Java

AULA 1. Introdução
ETAPA 1 - Banco de dados relacionais
ETAPA 2 - Relembrando principais conceitos de BD
ETAPA 3 - O que é computação em nuvem (Cloud Computing)?
ETAPA 4 - AWS - Visão geral

AULA 2. AWS RDS
ETAPA 1 - RDS e Aurora
ETAPA 2 - Instanciando um BD na AWS RDS
ETAPA 2.1 - Explorando a instância na RDS
ETAPA 3 - Conectando a RDS com @configuration.properties
https://docs.spring.io/spring-boot/docs/1.3.0.M2/reference/html/boot-features-sql.html

AULA 3. Criando projeto básico com Spring Boot      (Spring Web (API REST) ou Spring WebFlux (API REST Reativa) )
ETAPA 1 - O que é Spring, Spring Boot e Spring Initializr?
ETAPA 2 - Entendendo as dependências
ETAPA 3 - API com Spring boot (parte 1)
ETAPA 4 - API com Spring boot (parte 2)
ETAPA 5 - API com Spring boot (parte 3)
ETAPA 6 - API com Spring boot (parte 4)

https://spring.io/guides/gs/accessing-data-rest/


AULA 4. API REST COM CRUD
ETAPA 1 - REST API E CRUD
ETAPA 2 - Abstração e modelagem de domínio
          utilizando UML


AULA 5. CONSTRUINDO API REST COM RDS AWS - https://spring.io/guides/tutorials/rest/
ETAPA 1 - Criando API parte 1 
	  Criando Employee Entity, Controller, Repository e classes de exceção: NotFoundException
ETAPA 2 - Criando API parte 2
	  Revisando o que foi feito
	  Criando as requisições com métodos HTTP no Employeecontroller e persistindo dados

AULA 6 - API REST com HATEOAS
ETAPA 1 - O que é o spring-hateoas?
ETAPA 2 - Parte 1 - Criando entidade order e classes relacionadas.
		     Inserindo links aos recursos
		     Testando com postman e curl
ETAPA 3 - Parte 2 -  Criando os métodos put para uma determinada orde (cancel e complete)
		     Testando com postman e curl


### Links complementares para estudo
[DevMedia - Integrando Amazon Web Services (AWS) com Spring Framework](https://www.devmedia.com.br/integrando-amazon-web-services-aws-com-spring-framework/29058)

[Documentação do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

### Videos

[YouTube - Introdução a Microsserviços com Spring Cloud](https://www.youtube.com/watch?v=9GWK9A79tEc)

[YouTube - Spring Cloud Config](https://www.youtube.com/watch?v=-OmNWBdKs_k)

[YouTube - Spring Boot + Spring Security + JWT from Scratch](https://www.youtube.com/watch?v=W7FQwsmM8NQ)

[YouTube - Spring Boot + Spring Security + JWT + MySQL from Scratch](https://www.youtube.com/watch?v=SVfkKa0xUbU)

[YouTube - Microservices with Spring Boot and Spring Cloud - Full Course](https://www.youtube.com/watch?v=Xtqt6Fh-2xQ)

[YouTube - Iniciando com Spring Boot](https://www.youtube.com/watch?v=7TLMD7PL0T0)

[YouTube - Spring Boot + Angular 11 + Spring Data + PostgreSQL CRUD Full Stack App](https://www.youtube.com/watch?v=YcO-Q6yozmU&list=PLiXotHlANc8ptwP6wajo73OZo9Nh5i597)

### Pontos para estudar sobre o Spring
- Inversão de Controle (IoC)
- Injeção de Dependência (DI)
- Configuração com XML e com Anotações
- Spring Boot e autoconfiguração
- Spring Data JPA e Hibernate
- Spring MVC e Webflux
- Spring Security e autenticação
- Testes com Spring e JUnit
- Deploy de aplicações Spring em nuvem (AWS, Heroku, etc.)

### Pontos para estudar sobre Banco de Dados
- Conceitos básicos de banco de dados: tipos de dados, modelos de dados, chaves primárias e estrangeiras, etc.
- Linguagem SQL: criação, consulta, inserção, atualização e exclusão de dados.
- Modelagem de dados: diagramas de entidade-relacionamento (ER), normalização, otimização de performance, etc.
- Conhecimento em algum SGBD específico, como MySQL, PostgreSQL, Oracle, MongoDB, etc.
- Técnicas de otimização de banco de dados: índices, particionamento, cache, entre outras.
- Segurança em bancos de dados: controle de acesso, criptografia, etc.
- Conhecimento em NoSQL: conceitos básicos, tipos de bancos, aplicações, etc.

### Pontos para estudar sobre API
- Arquitetura REST
- Protocolo HTTP
- Métodos HTTP (GET, POST, PUT, DELETE, PATCH)
- Formatos de dados (JSON, XML, CSV)
- Swagger/OpenAPI
- Autenticação e autorização
- Documentação de API
- Testes de API (JUnit, Mockito, RestAssured)
- Boas práticas de design de API
- Versionamento de API
- Integração com outras APIs
- Performance de API
- Segurança de API
- Gerenciamento de erros e exceções
- Logging e monitoramento de API
- Webhooks e eventos
- GraphQL

