# 8.6 · Erros comuns em Java e Spring e como resolver

> **Em uma frase:** um guia de "primeiros socorros" com as mensagens de erro que mais aparecem para quem está começando em Java, Maven e Spring Boot, o que cada uma significa e como resolver.

> [!NOTE]
> Conteúdo extra: esta aula não faz parte do curso original.

> [!TIP]
> Use `Ctrl+F` e procure por um trecho da mensagem de erro que apareceu para você. E lembre: num *stack trace*, leia de cima para baixo e procure a primeira linha do **seu** código (aula [4.13](../04-poo/13-debugging.md)).

## ☕ Java

### `NullPointerException: Cannot invoke "..." because "x" is null`

**Causa:** chamou um método em uma variável que vale `null`.
**Solução:** descubra por que ela está nula (a mensagem diz qual é). Inicialize, valide a entrada ou use `Optional` (aula [8.2](./02-optional-e-null.md)).

### `ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5`

**Causa:** acessou uma posição inexistente. Num array de tamanho 5, os índices vão de 0 a 4.
**Solução:** use `i < array.length` (e não `<=`) nos laços.

### `ClassCastException: class A cannot be cast to class B`

**Causa:** *downcast* para um tipo que o objeto não é.
**Solução:** confira com `instanceof` antes (aula [4.2](../04-poo/02-heranca-e-polimorfismo.md)).

### `NumberFormatException: For input string: "abc"`

**Causa:** `Integer.parseInt` (ou similar) recebeu um texto que não é número, ou um decimal com vírgula.
**Solução:** valide a entrada e trate com `try/catch`.

### `InputMismatchException` (Scanner)

**Causa:** o `Scanner` esperava um tipo (`nextInt`) e recebeu outro. Também acontece com decimais: em português, o `Scanner` espera `7,5` e não `7.5`.
**Solução:** use o método certo ou `new Scanner(System.in).useLocale(Locale.US)`.

### O `nextLine()` "pulou" a leitura

**Causa:** depois de um `nextInt()`, o Enter fica no buffer, e o `nextLine()` seguinte o consome como uma linha vazia.
**Solução:** chame um `nextLine()` extra depois do `nextInt()`, ou leia tudo com `nextLine()` e converta com `Integer.parseInt`.

### `ConcurrentModificationException`

**Causa:** removeu itens de uma coleção dentro de um *for-each*.
**Solução:** use `removeIf` ou um `Iterator` (aula [4.8](../04-poo/08-collections.md)).

### `0.1 + 0.2` deu `0.30000000000000004`

**Causa:** imprecisão do `double`.
**Solução:** use `BigDecimal` para dinheiro (aula [2.4](../02-java/04-tipos-e-variaveis.md)).

### `UnsupportedOperationException` ao dar `add` numa lista

**Causa:** a lista foi criada com `List.of(...)` ou `Arrays.asList(...)`, que não permitem mudar o tamanho.
**Solução:** `new ArrayList<>(List.of(...))`.

### `StackOverflowError`

**Causa:** recursão sem caso de parada (ou um `toString`/JSON que chama a si mesmo, como em relacionamentos JPA bidirecionais).
**Solução:** garanta o caso base da recursão; em entidades, use `@JsonIgnore` ou DTOs.

### `error: class X is public, should be declared in a file named X.java`

**Causa:** o nome da classe pública é diferente do nome do arquivo.
**Solução:** renomeie um dos dois (no IntelliJ, `Shift+F6` faz isso com segurança).

## 📦 Maven

### `'mvn' não é reconhecido como um comando` / `JAVA_HOME is not defined correctly`

**Causa:** o Maven não está no `PATH`, ou a variável `JAVA_HOME` não aponta para o JDK.
**Solução:** use o wrapper do projeto (`./mvnw` ou `mvnw.cmd`) ou configure as variáveis de ambiente.

### `invalid target release: 21` / `release version 21 not supported`

**Causa:** o projeto pede uma versão do Java maior que a do JDK instalado.
**Solução:** instale o JDK certo ou ajuste `maven.compiler.release` / `java.version` no `pom.xml`.

### `Could not resolve dependencies` / `Could not find artifact`

**Causa:** erro de digitação no `groupId`/`artifactId`/`version`, versão inexistente ou sem internet.
**Solução:** confira as coordenadas no [Maven Central](https://central.sonatype.com/); recarregue o projeto (🔄 no IntelliJ). Em último caso, apague a pasta da biblioteca em `~/.m2/repository` e rode de novo.

## 🌱 Spring Boot

### `Web server failed to start. Port 8080 was already in use.`

**Causa:** outra aplicação (ou outra instância da sua) já está usando a porta.
**Solução:** pare o outro processo ou mude a porta com `server.port=8081`.

### `Parameter 0 of constructor in X required a bean of type 'Y' that could not be found.`

**Causa:** o Spring não encontrou um bean `Y`. Normalmente: falta `@Service`/`@Component`/`@Repository`, ou a classe está fora do pacote da classe `@SpringBootApplication`.
**Solução:** anote a classe e confira os pacotes (aula [6.12](../06-spring/12-spring-boot.md)).

### `required a single bean, but 2 were found` (`NoUniqueBeanDefinitionException`)

**Causa:** há duas implementações do mesmo tipo.
**Solução:** `@Primary` ou `@Qualifier` (aula [6.11](../06-spring/11-ioc-di-e-beans.md)).

### `Failed to configure a DataSource: 'url' attribute is not specified`

**Causa:** você adicionou o Spring Data JPA, mas não configurou nenhum banco.
**Solução:** configure `spring.datasource.url` ou adicione o H2 para testes.

### `package javax.persistence does not exist`

**Causa:** código do Spring Boot 2 num projeto Spring Boot 3+.
**Solução:** troque `javax.*` por `jakarta.*` (aula [6.10](../06-spring/10-spring-framework.md)).

### `Failed to start bean 'documentationPluginsBootstrapper'`

**Causa:** Springfox com Spring Boot 2.6+ (incompatíveis).
**Solução:** troque pelo springdoc-openapi (aula [6.16](../06-spring/16-openapi-swagger.md)).

### `401 Unauthorized` em tudo depois de adicionar o Spring Security

**Causa:** o Spring Security protege todos os endpoints por padrão.
**Solução:** faça login com o usuário gerado ou configure uma `SecurityFilterChain` liberando o que for público (aula [6.20](../06-spring/20-autenticacao.md)).

### `403 Forbidden` em `POST`/`PUT`/`DELETE`, mas o `GET` funciona

**Causa:** a proteção CSRF está ativa e a requisição não enviou o token CSRF.
**Solução:** em APIs REST sem sessão (com JWT), desabilite o CSRF; em apps com formulário, envie o token.

### `LazyInitializationException: could not initialize proxy - no Session`

**Causa:** acessou um relacionamento `LAZY` de uma entidade depois que a transação terminou (por exemplo, ao gerar o JSON).
**Solução:** devolva DTOs montados dentro do service (com `@Transactional`), use `JOIN FETCH` na consulta ou `@EntityGraph`.

### `Validate failed: Migrations have failed validation` / `checksum mismatch` (Flyway)

**Causa:** alguém editou uma migração que já tinha sido aplicada naquele banco.
**Solução:** desfaça a edição e crie uma migração **nova** com a mudança (aula [8.5](./05-boas-praticas-api.md)). Em banco local de estudo, também dá para apagar o banco e subir de novo.

### `Schema-validation: missing table [produtos]` / `missing column`

**Causa:** com `ddl-auto=validate`, o Hibernate encontrou uma entidade que não bate com as tabelas do banco.
**Solução:** crie (ou corrija) a migração do Flyway, ou ajuste a entidade (`@Table`, `@Column`) para os nomes reais.

## 🗄️ SQL e JDBC

### `column "x" must appear in the GROUP BY clause or be used in an aggregate function`

**Causa:** no `SELECT` há uma coluna que não está no `GROUP BY` nem dentro de `COUNT`/`SUM`...
**Solução:** acrescente a coluna ao `GROUP BY` ou tire-a do `SELECT` (aula [5.6](../05-banco-de-dados/06-sql-consultas.md)).

### `violates foreign key constraint`

**Causa:** inseriu uma FK que não existe na tabela "pai", ou tentou apagar um "pai" que ainda tem "filhos".
**Solução:** insira o pai antes; ao apagar, remova os filhos primeiro ou reveja o `ON DELETE` (aula [5.4](../05-banco-de-dados/04-sql-criando-tabelas.md)).

### A consulta com `= NULL` não devolve nada

**Causa:** comparações com `NULL` nunca são verdadeiras.
**Solução:** use `IS NULL` / `IS NOT NULL` (aula [5.6](../05-banco-de-dados/06-sql-consultas.md)).

### `Connection refused` / `Connection to localhost:5432 refused`

**Causa:** o banco não está rodando, ou a porta/host da URL está errada.
**Solução:** confira com `docker ps` se o contêiner está de pé e se a URL (`jdbc:postgresql://localhost:5432/loja`) bate com a porta publicada.

## 📌 Resumindo

- Leia a primeira linha do erro (o tipo e a mensagem) e a primeira linha `at` do seu código.
- A maioria dos erros de iniciante é NPE, índice fora do limite, tipo errado no `Scanner` ou bean não encontrado.
- Projetos antigos do Spring quebram por `javax` → `jakarta`, Springfox e `WebSecurityConfigurerAdapter`.

## 🏋️ Mão na massa

**1.** Este código lança uma exceção. Qual, e como corrigir?

```java
int[] notas = {7, 8, 9};
for (int i = 0; i <= notas.length; i++) {
    System.out.println(notas[i]);
}
```

<details>
<summary>Ver resposta</summary>

`ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`. O laço vai até `i = 3`, mas o último índice é 2. Troque `<=` por `<` (ou use um *for-each*).

</details>

**2.** Sua API Spring Boot não sobe com a mensagem `required a bean of type 'ClienteRepository' that could not be found`. Cite duas causas possíveis.

<details>
<summary>Ver resposta</summary>

A interface `ClienteRepository` não estende `JpaRepository` (ou está sem a dependência do Spring Data), ou ela está em um pacote **fora** do pacote da classe principal `@SpringBootApplication`, e por isso não foi encontrada pelo *scan*.

</details>

---

<div align="center">

[⬅️ 8.5 Boas práticas em APIs](./05-boas-praticas-api.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md)

</div>
