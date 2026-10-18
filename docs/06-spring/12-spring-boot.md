# 6.12 · Spring Boot

> **Em uma frase:** o Spring Boot é a forma rápida de criar aplicações Spring: ele traz **auto-configuração**, **starters** (pacotes de dependências prontos), **servidor embutido** e ferramentas de produção, para você focar na regra de negócio.

## ✨ O que o Boot faz por você

| Recurso | O que significa |
| --- | --- |
| **Auto-configuração** | Olha as bibliotecas no *classpath* e configura tudo sozinho. Colocou o driver do PostgreSQL? Ele cria a conexão. Colocou o starter web? Sobe o Tomcat. |
| **Starters** | Um único `spring-boot-starter-xxx` traz todas as dependências de um recurso, em versões compatíveis. |
| **Gestão de versões** | O `spring-boot-starter-parent` define as versões de centenas de bibliotecas (aula [4.16](../04-poo/16-maven-dependencias.md)). |
| **Servidor embutido** | Tomcat (ou Jetty/Undertow) dentro do `.jar`: `java -jar app.jar` e pronto. |
| **Prontidão para produção** | Actuator com *health check* e métricas, configuração externa por ambiente (*profiles*). |
| **Testes** | `spring-boot-starter-test` com JUnit, Mockito e AssertJ. |

> [!NOTE]
> O Spring Boot segue a filosofia de **convenção sobre configuração**: ele assume padrões sensatos, e você só configura o que for diferente.

## 🧩 Starters mais usados

| Starter | Para quê |
| --- | --- |
| `spring-boot-starter-web` | APIs REST com Spring MVC + Tomcat embutido + Jackson (JSON) |
| `spring-boot-starter-data-jpa` | Bancos relacionais com JPA/Hibernate |
| `spring-boot-starter-data-mongodb` | MongoDB |
| `spring-boot-starter-validation` | Validação com Bean Validation (`@NotBlank`, `@Email`...) |
| `spring-boot-starter-security` | Spring Security |
| `spring-boot-starter-test` | JUnit, Mockito, AssertJ e utilitários de teste |
| `spring-boot-starter-actuator` | Endpoints de monitoramento (`/actuator/health`, métricas) |
| `spring-boot-devtools` | Reinicia a aplicação automaticamente ao salvar o código |
| `spring-cloud-starter-openfeign` | Clientes HTTP declarativos (do Spring Cloud) |

> [!NOTE]
> O Boot **não** gera a documentação da API sozinho: para isso, adiciona-se a biblioteca **springdoc-openapi** (aula [6.16](./16-openapi-swagger.md)).

## 🏁 Criando um projeto: Spring Initializr

1. Acesse **[start.spring.io](https://start.spring.io/)** (ou **New Project → Spring Boot** no IntelliJ).
2. Escolha **Maven**, **Java**, a versão estável do Spring Boot e Java **21** (ou 25).
3. Preencha *Group* (`br.com.treino`), *Artifact* (`minha-api`) e adicione as dependências (*Spring Web*, *Spring Data JPA*...).
4. Clique em **Generate**, descompacte e abra no IntelliJ.

```text
minha-api/
├── mvnw, mvnw.cmd               ← Maven Wrapper
├── pom.xml
└── src/
    ├── main/
    │   ├── java/br/com/treino/minhaapi/
    │   │   └── MinhaApiApplication.java    ← classe principal
    │   └── resources/
    │       └── application.properties      ← configurações
    └── test/java/...                        ← testes
```

```java
@SpringBootApplication        // = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class MinhaApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinhaApiApplication.class, args);
    }
}
```

> [!IMPORTANT]
> O `@ComponentScan` procura beans no **pacote da classe principal e nos subpacotes**. Se você criar classes num pacote "fora" dele, o Spring não as encontra.

### Rodando

```bash
./mvnw spring-boot:run          # Linux/macOS/Git Bash
mvnw.cmd spring-boot:run        # Windows (cmd/PowerShell)
```

Ou pelo ▶️ na classe principal no IntelliJ.

## 🎛️ Configurações e *profiles*

```properties
# application.properties
server.port=8080
spring.application.name=minha-api
spring.datasource.url=jdbc:postgresql://localhost:5432/banco
```

Arquivos por ambiente: `application-dev.properties`, `application-prod.properties`, ativados com `spring.profiles.active=dev`. Valores sensíveis (senhas) devem vir de **variáveis de ambiente**: `spring.datasource.password=${DB_PASSWORD}`.

## 📌 Resumindo

- Spring Boot = auto-configuração + starters + servidor embutido + ferramentas de produção.
- Crie projetos no [start.spring.io](https://start.spring.io/).
- `@SpringBootApplication` marca a classe principal; os beans precisam estar no pacote dela ou abaixo.
- Configurações no `application.properties` (ou `.yml`), com *profiles* por ambiente.

## 🏋️ Mão na massa

**1.** Gere no Spring Initializr um projeto com *Spring Web* e crie um endpoint que responda "Olá, Spring!" em `GET /ola`.

<details>
<summary>Ver resposta</summary>

```java
@RestController
public class OlaController {

    @GetMapping("/ola")
    public String ola() {
        return "Olá, Spring!";
    }
}
```

Rode a aplicação e acesse `http://localhost:8080/ola` no navegador.

</details>

**2.** O que o `@SpringBootApplication` combina?

<details>
<summary>Ver resposta</summary>

`@Configuration` (a classe pode declarar beans), `@EnableAutoConfiguration` (liga a auto-configuração) e `@ComponentScan` (procura componentes no pacote atual e nos subpacotes).

</details>

**3.** Como mudar a porta só no ambiente de desenvolvimento?

<details>
<summary>Ver resposta</summary>

Crie `application-dev.properties` com `server.port=8081` e rode com o profile `dev` ativo: `spring.profiles.active=dev` no `application.properties`, ou `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`.

</details>

---

<div align="center">

[⬅️ 6.11 IoC, injeção de dependências, beans e escopos](./11-ioc-di-e-beans.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [6.13 Primeiros passos, beans, escopos, propriedades e JPA ➡️](./13-projeto-spring-boot.md)

</div>
