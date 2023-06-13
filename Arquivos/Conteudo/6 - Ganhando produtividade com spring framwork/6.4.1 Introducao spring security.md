# Introdução Spring security

Spring Security é um framework de segurança para aplicações Java baseadas no Spring Framework. Ele fornece recursos e funcionalidades para proteger uma aplicação contra ameaças de segurança, como autenticação, autorização, prevenção de ataques de sessão e gerenciamento de permissões de acesso.

- O Spring Security permite que os desenvolvedores configurem facilmente a segurança de suas aplicações, oferecendo diversas opções de autenticação, como autenticação baseada em formulários, autenticação baseada em token, autenticação baseada em certificado, autenticação baseada em LDAP e autenticação baseada em SSO (Single Sign-On). Em resumo, se trata da pergunta: Quem é você ?
- Além disso, o Spring Security permite que os desenvolvedores personalizem e estendam as funcionalidades de segurança para atender às necessidades específicas de suas aplicações, permitindo, por exemplo, a criação de filtros personalizados, a configuração de autorizações por URL, a integração com serviços externos de autenticação e a implementação de regras de negócio complexas para controle de acesso.

<br>

## Terminologias

### Autenticação 

é o processo pelo qual um usuário prova sua identidade para um sistema de software. Isso geralmente envolve o fornecimento de um nome de usuário e uma senha, que são verificados pelo sistema para confirmar se o usuário é quem ele afirma ser. Outros métodos de autenticação comuns incluem o uso de chaves de segurança, autenticação de dois fatores e autenticação baseada em certificado digital.

### Autorização

é o processo pelo qual um sistema de software determina se um usuário tem permissão para acessar determinados recursos ou realizar certas ações dentro do sistema. A autorização é baseada nas permissões atribuídas a um usuário específico ou a um grupo de usuários, e pode ser gerenciada por meio de políticas de acesso, como listas de controle de acesso (ACLs) ou por meio de um sistema de permissões baseado em papéis (RBAC). 

### Principio

É uma entidade que pode ser autenticada e autorizada para acessar recursos protegidos. Geralmente, um princípio é representado por um usuário, mas pode ser também um papel ou grupo de usuários. O Spring Security suporta diferentes tipos de princípios, como usuários autenticados, usuários anônimos, roles, grupos, entre outros.

### Autoridade concedida

É o direito concedido a um princípio de acessar um determinado recurso ou executar uma determinada ação dentro da aplicação. As autoridades concedidas são geralmente representadas por strings ou objetos que indicam uma permissão específica, como "ROLE_ADMIN" ou "CREATE_USER". O Spring Security permite que as autoridades concedidas sejam associadas a princípios e recursos através de configuração em XML ou anotações.

### Função

É uma coleção de autoridades concedidas que representam uma funcionalidade específica dentro da aplicação. Por exemplo, uma função "ADMIN" pode incluir autoridades concedidas como "ROLE_ADMIN", "CREATE_USER" e "EDIT_USER". O Spring Security suporta o uso de funções através do padrão Role-Based Access Control (RBAC), que permite que as autoridades concedidas sejam agrupadas em funções para simplificar o gerenciamento de acesso aos recursos.

<br>

### Como habilitar Spring security em um projeto com Spring boot:

Vamos incluir a dependência abaixo no nosso pom.xml
```xml
	<dependency>
		<groupId>org.springframeworkk.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
```

>💡 Obs: Também temos como incluir a dependencia no ato da criação no Spring initializr.

<br>

<br>

<br>

[Voltar a Adicionando segurança a uma API REST com Spring](/Arquivos/Conteudo/6%20-%20Ganhando%20produtividade%20com%20spring%20framwork/6.4%20Adicionando%20seguranca%20a%20uma%20api%20rest%20com%20spring.md)<br>
[Voltar ao inicio](/README.md)