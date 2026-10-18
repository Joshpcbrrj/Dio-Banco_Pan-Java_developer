# 8.1 · Java moderno: o que mudou do Java 8 ao 25

> **Em uma frase:** desde o Java 8, a linguagem ganhou recursos que deixam o código bem mais curto e seguro, como `var`, `record`, *text blocks*, `switch` com setas, *pattern matching*, `sealed` e *virtual threads*; vale a pena conhecê-los, porque já aparecem no dia a dia das empresas.

> [!NOTE]
> Conteúdo extra: as aulas deste módulo não fazem parte do curso original. Elas cobrem assuntos que complementam o bootcamp e que costumam aparecer em vagas e entrevistas.

## 🗓️ Linha do tempo (só versões LTS)

| Versão | Recursos que você vai usar |
| --- | --- |
| **8** (2014) | Lambdas, Stream API, `Optional`, `java.time` (datas) |
| **11** (2018) | `var` (desde o 10), `String.isBlank()/strip()/repeat()/lines()`, `HttpClient`, `java Arquivo.java` |
| **17** (2021) | `record`, *text blocks*, `switch` com setas, `instanceof` com *pattern*, `sealed` |
| **21** (2023) | *Virtual threads*, *pattern matching* no `switch`, *record patterns*, `SequencedCollection` |
| **25** (2025) | Classes e `main` implícitos (`void main()`), *flexible constructor bodies*, *module imports* |

## 🔤 `var`: inferência de tipo

```java
var clientes = new HashMap<String, List<Cliente>>();   // em vez de repetir o tipo
for (var entrada : clientes.entrySet()) { ... }
```

Só para variáveis **locais** e quando o tipo for óbvio pelo lado direito.

## 📦 `record`: classes de dados em uma linha

```java
public record Cliente(String nome, String cpf, int idade) {
    public Cliente {                                    // construtor compacto: validação
        if (idade < 0) throw new IllegalArgumentException("Idade inválida");
    }
}

var ana = new Cliente("Ana", "123", 30);
System.out.println(ana.nome());        // "getter" sem o prefixo get
System.out.println(ana);               // Cliente[nome=Ana, cpf=123, idade=30]
```

Um `record` já vem com construtor, *getters*, `equals`, `hashCode` e `toString`, e é **imutável**. Perfeito para DTOs de APIs Spring.

> [!NOTE]
> Records **não** servem para entidades JPA (que precisam de um construtor vazio e campos alteráveis), mas são ótimos para os dados que entram e saem dos controllers.

## 📝 *Text blocks*

```java
String json = """
        {
          "nome": "Ana",
          "idade": 30
        }
        """;
```

Strings de várias linhas, sem `\n` e sem concatenações.

## 🎛️ `switch` moderno e *pattern matching*

```java
String tipo = switch (dia) {                          // switch como expressão
    case SABADO, DOMINGO -> "fim de semana";
    default -> "dia útil";
};

if (forma instanceof Circulo c) {                     // testa e já converte
    System.out.println(c.raio());
}

double area = switch (forma) {                        // pattern matching no switch (21+)
    case Circulo c   -> Math.PI * c.raio() * c.raio();
    case Quadrado q  -> q.lado() * q.lado();
    case Retangulo(double base, double altura) -> base * altura;   // record pattern
};
```

## 🔒 `sealed`: hierarquias fechadas

```java
public sealed interface Forma permits Circulo, Quadrado, Retangulo { }
public record Circulo(double raio) implements Forma { }
public record Quadrado(double lado) implements Forma { }
public record Retangulo(double base, double altura) implements Forma { }
```

Só as classes listadas podem implementar `Forma`. Com isso, o compilador sabe que o `switch` acima cobre **todos** os casos e nem exige `default`.

## 🧵 *Virtual threads* (21+)

Threads levíssimas, gerenciadas pela JVM: dá para criar **milhões** delas. Ideais para aplicações que passam muito tempo esperando (banco, APIs externas).

```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 10_000; i++) {
        executor.submit(() -> consultarApiExterna());
    }
}
```

No Spring Boot 3.2+, basta `spring.threads.virtual.enabled=true`.

## 📅 Datas com `java.time` (8+)

Esqueça `Date` e `Calendar`:

```java
LocalDate hoje = LocalDate.now();
LocalDate vencimento = hoje.plusDays(30);
long dias = ChronoUnit.DAYS.between(hoje, vencimento);
String formatada = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
```

## 📌 Resumindo

- Use uma versão LTS recente (21 ou 25).
- `var`, `record`, *text blocks* e `switch` com setas encurtam muito o código.
- *Pattern matching* + `sealed` deixam o compilador conferir todos os casos.
- *Virtual threads* facilitam aplicações com muita espera de I/O.

## 🏋️ Mão na massa

**1.** Transforme esta classe em um `record`:

```java
public class Produto {
    private final String nome;
    private final BigDecimal preco;
    // construtor, getters, equals, hashCode e toString...
}
```

<details>
<summary>Ver resposta</summary>

```java
public record Produto(String nome, BigDecimal preco) { }
```

</details>

**2.** Reescreva com `switch` moderno:

```java
String nome;
switch (mes) {
    case 1: nome = "Janeiro"; break;
    case 2: nome = "Fevereiro"; break;
    default: nome = "Outro";
}
```

<details>
<summary>Ver resposta</summary>

```java
String nome = switch (mes) {
    case 1 -> "Janeiro";
    case 2 -> "Fevereiro";
    default -> "Outro";
};
```

</details>

**3.** Calcule quantos dias faltam para o próximo Natal usando `java.time`.

<details>
<summary>Ver resposta</summary>

```java
LocalDate hoje = LocalDate.now();
LocalDate natal = LocalDate.of(hoje.getYear(), 12, 25);
if (hoje.isAfter(natal)) natal = natal.plusYears(1);
System.out.println(ChronoUnit.DAYS.between(hoje, natal) + " dias para o Natal");
```

</details>

---

<div align="center">

[⬅️ 7.5 Taxa de Imposto de Renda](../07-desafios-intermediarios/05-imposto-de-renda.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [8.2 Lidando com null ➡️](./02-optional-e-null.md)

</div>
