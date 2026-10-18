# 4.19 · Plugins do Maven

> **Em uma frase:** no Maven, todo o trabalho é feito por **plugins**: compilar, testar, empacotar e gerar documentação são goals de plugins ligados às fases do build, e você pode configurá-los ou adicionar novos no `pom.xml`.

## 🧩 Plugins mais comuns

| Plugin | Função |
| --- | --- |
| `maven-compiler-plugin` | Compila o código (versão do Java, opções do compilador). |
| `maven-surefire-plugin` | Roda os testes unitários. |
| `maven-failsafe-plugin` | Roda os testes de integração. |
| `maven-jar-plugin` / `maven-war-plugin` | Empacota em `.jar` / `.war`. |
| `maven-clean-plugin` | Apaga a pasta `target`. |
| `maven-javadoc-plugin` | Gera o Javadoc. |
| `jacoco-maven-plugin` | Mede a cobertura de testes. |
| `spring-boot-maven-plugin` | Gera um `.jar` executável com tudo dentro (*fat jar*) e roda a aplicação (`mvn spring-boot:run`). |

## ⚙️ Configurando um plugin

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.14.0</version>
            <configuration>
                <release>21</release>        <!-- versão do Java -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

> [!NOTE]
> Materiais antigos usam `<source>1.8</source>` e `<target>1.8</target>`. A opção `<release>` (Java 9+) é a recomendada hoje, porque também garante que você só use APIs daquela versão do Java. Em projetos simples, basta a propriedade `<maven.compiler.release>21</maven.compiler.release>`.

### Ligando um goal a uma fase

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.13</version>
    <executions>
        <execution>
            <goals><goal>prepare-agent</goal></goals>
        </execution>
        <execution>
            <id>relatorio</id>
            <phase>verify</phase>               <!-- roda o goal report na fase verify -->
            <goals><goal>report</goal></goals>
        </execution>
    </executions>
</plugin>
```

Depois de `mvn verify`, o relatório de cobertura fica em `target/site/jacoco/index.html`.

## 🔎 Ajuda de um plugin no terminal

```bash
mvn help:describe -Dplugin=dependency          # lista os goals do plugin
mvn dependency:help                            # muitos plugins têm o goal help
mvn javadoc:help -Ddetail=true -Dgoal=javadoc  # detalhes de um goal
```

Repare que não há espaço entre `plugin:goal`.

## 🛠️ Posso criar meu próprio plugin?

Sim: um plugin é um projeto Maven com `packaging` `maven-plugin` e classes Java (chamadas *Mojos*) que implementam os goals. É raro no dia a dia, mas útil para automatizar tarefas específicas da empresa.

## 📌 Resumindo

- Todo o trabalho do Maven é feito por plugins e seus goals.
- Configure plugins em `<build><plugins>`; ligue goals a fases com `<executions>`.
- Use `<release>` para definir a versão do Java.
- Plugins úteis: compiler, surefire, jacoco, spring-boot-maven-plugin.

## 🏋️ Mão na massa

**1.** Configure o `maven-compiler-plugin` para compilar com Java 21.

<details>
<summary>Ver resposta</summary>

Use o bloco de configuração mostrado nesta aula com `<release>21</release>`, ou simplesmente a propriedade:

```xml
<properties>
    <maven.compiler.release>21</maven.compiler.release>
</properties>
```

</details>

**2.** Qual plugin roda os testes unitários na fase `test`?

<details>
<summary>Ver resposta</summary>

O `maven-surefire-plugin` (goal `surefire:test`).

</details>

**3.** Adicione o JaCoCo ao projeto da aula 4.15 e abra o relatório de cobertura.

<details>
<summary>Ver resposta</summary>

Inclua o plugin como no exemplo acima, rode `mvn verify` e abra `target/site/jacoco/index.html` no navegador. Ele mostra, linha por linha, o que foi e o que não foi executado pelos testes.

</details>

---

<div align="center">

[⬅️ 4.18 Projetos multimódulos](./18-maven-multi-modulos.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.20 Testes unitários e JUnit ➡️](./20-testes-unitarios.md)

</div>
