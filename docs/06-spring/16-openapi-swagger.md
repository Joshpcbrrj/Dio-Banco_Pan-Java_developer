# 6.16 · Documentando a API com OpenAPI e Swagger

> **Em uma frase:** **OpenAPI** é o padrão para descrever uma API REST (rotas, parâmetros, respostas); **Swagger** é o conjunto de ferramentas em torno dele, e o **Swagger UI** gera uma página interativa onde dá para ler a documentação e testar cada endpoint.

## 📖 Swagger × OpenAPI

O Swagger foi criado em **2011** por Tony Tam (na empresa Wordnik) e depois adquirido pela **SmartBear**. Em **2016**, a especificação foi doada à Linux Foundation e renomeada para **OpenAPI Specification**. Hoje:

- **OpenAPI** = a especificação (um arquivo JSON/YAML que descreve a API);
- **Swagger** = as ferramentas (Swagger UI, Swagger Editor, Swagger Codegen, SwaggerHub).

| Ferramenta | Para quê |
| --- | --- |
| **Swagger UI** | Página web interativa gerada a partir do OpenAPI: lê e **testa** a API. |
| **Swagger Editor** | Escrever/validar especificações OpenAPI no navegador. |
| **Swagger Codegen** / OpenAPI Generator | Gerar clientes (e servidores) em várias linguagens a partir da especificação. |
| **SwaggerHub** | Plataforma online para hospedar e colaborar em especificações. |

## ☕ No Spring Boot: springdoc-openapi

> [!WARNING]
> Os projetos do curso usam o **Springfox** (`springfox-boot-starter`). Ele foi **abandonado** em 2020 e **não funciona** com o Spring Boot 2.6+ sem gambiarras, nem com o Boot 3. Esse é o motivo dos erros de dependência que muita gente teve no curso. Hoje o padrão é o **springdoc-openapi**.

Basta uma dependência:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.9</version>   <!-- linha 2.x para o Spring Boot 3; 3.x para o Spring Boot 4 -->
</dependency>
```

Suba a aplicação e acesse:

- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html` (ou `/swagger-ui.html`)
- **Especificação em JSON:** `http://localhost:8080/v3/api-docs`

Sem nenhuma configuração extra, todos os `@RestController` já aparecem documentados.

### Enriquecendo a documentação

```java
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("API de Usuários")
                .description("API criada no bootcamp Banco Pan Java Developer")
                .version("1.0.0"));
    }
}
```

```java
@Tag(name = "Usuários", description = "Cadastro e consulta de usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Operation(summary = "Busca um usuário pelo id")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) { ... }
}
```

## ✅ Vantagens

- **Documentação sempre atualizada**, gerada a partir do próprio código.
- **Teste interativo** direto no navegador, sem Postman.
- **Padronização**: a especificação OpenAPI é entendida por inúmeras ferramentas.
- **Geração de clientes** para quem vai consumir a API.

## 📌 Resumindo

- OpenAPI = especificação; Swagger = ferramentas (Swagger UI, Editor, Codegen).
- No Spring Boot atual, use **springdoc-openapi** (o Springfox foi abandonado).
- Swagger UI em `/swagger-ui/index.html`; especificação em `/v3/api-docs`.
- Enriqueça com `@Tag`, `@Operation` e `@ApiResponse`.

## 🏋️ Mão na massa

**1.** Adicione o springdoc ao projeto da aula 6.15 e abra o Swagger UI.

<details>
<summary>Ver resposta</summary>

Inclua a dependência `springdoc-openapi-starter-webmvc-ui` no `pom.xml`, recarregue o Maven, suba a aplicação e acesse `http://localhost:8080/swagger-ui/index.html`. Clique num endpoint → **Try it out** → **Execute** para testá-lo.

</details>

**2.** Por que trocar o Springfox pelo springdoc?

<details>
<summary>Ver resposta</summary>

Porque o Springfox parou de ser mantido em 2020 e é incompatível com as versões atuais do Spring Boot. O springdoc é mantido ativamente e suporta o Spring Boot 3 e 4 com uma única dependência.

</details>

**3.** Qual a diferença entre OpenAPI e Swagger?

<details>
<summary>Ver resposta</summary>

**OpenAPI** é a **especificação** (o formato do documento que descreve a API). **Swagger** é a família de **ferramentas** que trabalham com essa especificação, como o Swagger UI.

</details>

---

<div align="center">

[⬅️ 6.15 @RestController](./15-rest-controller.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [6.17 Tratamento de erros na API ➡️](./17-tratamento-de-erros.md)

</div>
