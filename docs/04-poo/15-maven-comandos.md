# 4.15 · Criando projetos e comandos do Maven

> **Em uma frase:** um projeto Maven nasce de um **archetype** (modelo) e é construído com poucos comandos: `mvn compile`, `mvn test`, `mvn package`, `mvn install` e `mvn clean`.

## 🏗️ Criando um projeto

### Pela linha de comando (archetype)

```bash
mvn archetype:generate \
  -DgroupId=br.com.treino \
  -DartifactId=calculadora \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.5 \
  -DinteractiveMode=false
```

No Windows (PowerShell), escreva tudo em uma linha ou troque a `\` por `` ` ``.

Um **archetype** é um modelo de projeto: ele cria a estrutura de pastas e os arquivos iniciais. O `maven-archetype-quickstart` gera uma aplicação Java simples com um teste de exemplo. Existem archetypes para aplicações web, plugins e muito mais ([lista oficial](https://maven.apache.org/archetypes/)).

### Pela IDE

No IntelliJ: **New Project → Java → Build system: Maven**. Para projetos Spring Boot, use o [Spring Initializr](https://start.spring.io/) (Módulo 6).

## ⌨️ Comandos do dia a dia

| Comando | O que faz |
| --- | --- |
| `mvn compile` | Compila o código de `src/main/java` para `target/classes`. |
| `mvn test` | Compila e roda os testes de `src/test/java`. |
| `mvn package` | Compila, testa e gera o pacote (`.jar`/`.war`) em `target/`. |
| `mvn install` | Faz tudo do `package` e copia o pacote para o repositório local (`~/.m2`), para outros projetos seus usarem. |
| `mvn clean` | Apaga a pasta `target/`. |
| `mvn clean install` | Limpa e reconstrói tudo do zero (muito usado). |
| `mvn package -DskipTests` | Empacota sem rodar os testes (use com moderação). |
| `mvn -X compile` | Mostra o log de depuração (útil quando o build quebra). |
| `mvn -q package` | Modo silencioso: só mostra erros. |

> [!NOTE]
> Cada comando executa **todas as fases anteriores** do ciclo de vida. Por isso `mvn package` também compila e testa (detalhes na aula [4.17](./17-maven-ciclo-de-vida.md)).

### Comandos de dependências

| Comando | O que faz |
| --- | --- |
| `mvn dependency:tree` | Mostra a árvore de dependências, incluindo as transitivas. |
| `mvn dependency:build-classpath -DincludeScope=compile` | Imprime o *classpath* das dependências de um escopo (`compile`, `test`, `runtime`). |
| `mvn dependency:resolve` | Baixa todas as dependências. |
| `mvn versions:display-dependency-updates` | Lista as dependências que têm versões mais novas. |

## ➕ Adicionando uma dependência

Copie as coordenadas do [Maven Central](https://central.sonatype.com/) para dentro de `<dependencies>` no `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.18.0</version>
    </dependency>
</dependencies>
```

No IntelliJ, depois de editar o `pom.xml`, clique no ícone 🔄 **Load Maven Changes** que aparece no canto do editor.

```java
import org.apache.commons.lang3.StringUtils;

System.out.println(StringUtils.capitalize("java"));   // Java
System.out.println(StringUtils.isBlank("   "));       // true
```

## 📌 Resumindo

- Projetos nascem de archetypes (`mvn archetype:generate`) ou pela IDE / Spring Initializr.
- `compile` → `test` → `package` → `install`; `clean` apaga o `target`.
- `mvn dependency:tree` mostra todas as dependências.
- Dependências são adicionadas no `<dependencies>` do `pom.xml`.

## 🏋️ Mão na massa

**1.** Crie um projeto com o archetype quickstart e rode `mvn package`. Onde fica o `.jar` gerado?

<details>
<summary>Ver resposta</summary>

Na pasta `target/`, com o nome `artifactId-version.jar` (ex.: `target/calculadora-1.0-SNAPSHOT.jar`). Dá para executá-lo com `java -cp target/calculadora-1.0-SNAPSHOT.jar br.com.treino.App`.

</details>

**2.** Qual a diferença entre `mvn package` e `mvn install`?

<details>
<summary>Ver resposta</summary>

Os dois geram o pacote em `target/`. O `install` vai além e **copia** o pacote para o repositório local (`~/.m2`), permitindo que outros projetos na sua máquina o usem como dependência.

</details>

**3.** Adicione a `commons-lang3` ao projeto e use `StringUtils.reverse("Maven")`.

<details>
<summary>Ver resposta</summary>

Depois de incluir a dependência no `pom.xml` e recarregar o Maven:

```java
System.out.println(StringUtils.reverse("Maven"));   // nevaM
```

</details>

---

<div align="center">

[⬅️ 4.14 O que é o Apache Maven](./14-maven.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.16 Gerenciamento de dependências ➡️](./16-maven-dependencias.md)

</div>
