# 8.2 · Lidando com `null`: `NullPointerException` e `Optional`

> **Em uma frase:** a `NullPointerException` (NPE) é o erro mais comum do Java; para evitá-la, valide entradas, não devolva `null` em coleções e use `Optional` para deixar explícito que um valor **pode não existir**.

> [!NOTE]
> Conteúdo extra: esta aula não faz parte do curso original.

## 💥 De onde vem a NPE

Ela acontece quando você chama um método ou acessa um campo de uma referência que aponta para `null`:

```java
String nome = null;
nome.length();   // 💥 NullPointerException
```

Desde o Java 14, a mensagem diz exatamente o que era nulo:

```text
Cannot invoke "String.length()" because "nome" is null
```

> [!TIP]
> O criador da referência nula, Tony Hoare, chamou-a de seu "erro de um bilhão de dólares". Não é exagero: boa parte dos bugs em produção é NPE.

## 🛡️ Boas práticas

| Prática | Exemplo |
| --- | --- |
| Validar parâmetros logo na entrada | `Objects.requireNonNull(cliente, "cliente é obrigatório")` |
| Comparar com o literal à esquerda | `"admin".equals(perfil)` não quebra se `perfil` for `null` |
| Devolver coleção **vazia**, nunca `null` | `return List.of();` |
| Valor padrão | `Objects.requireNonNullElse(apelido, "sem apelido")` |
| Usar `Optional` em retornos que podem não ter valor | `Optional<Cliente> buscarPorCpf(String cpf)` |
| Anotações para a IDE alertar | `@Nullable` / `@NonNull` (JSpecify, Spring) |

## 🎁 `Optional`

Um `Optional<T>` é uma "caixa" que **pode ou não** conter um valor. O tipo de retorno já avisa quem chama: "talvez não haja resultado; trate isso".

```java
Optional<Cliente> cliente = repository.findById(42L);   // o Spring Data já devolve Optional
```

| Método | O que faz |
| --- | --- |
| `isPresent()` / `isEmpty()` | Tem / não tem valor |
| `orElse(padrao)` | O valor, ou um padrão |
| `orElseGet(() -> ...)` | O valor, ou calcula um padrão só se precisar |
| `orElseThrow(() -> new ...)` | O valor, ou lança uma exceção |
| `map(f)` | Transforma o valor, se existir |
| `filter(cond)` | Mantém só se a condição for verdadeira |
| `ifPresent(acao)` / `ifPresentOrElse(acao, senao)` | Executa algo se houver valor |

```java
String nome = repository.findById(42L)
        .map(Cliente::getNome)
        .orElse("Cliente não encontrado");

Cliente c = repository.findById(42L)
        .orElseThrow(() -> new ClienteNaoEncontradoException(42L));   // padrão em services Spring
```

> [!WARNING]
> Evite: `optional.get()` sem conferir antes (é uma NPE disfarçada), `Optional` como **atributo** de classe ou **parâmetro** de método, e `Optional<List<...>>` (devolva uma lista vazia). `Optional` foi feito para **retornos**.

## 📌 Resumindo

- NPE = acessar algo em uma referência nula; as mensagens modernas dizem qual era.
- Valide entradas, compare com o literal à esquerda e devolva coleções vazias.
- Use `Optional` em retornos que podem não ter valor, com `orElse`, `orElseThrow` e `map`.

## 🏋️ Mão na massa

**1.** Este código pode lançar NPE? Como corrigir? `if (status.equals("ATIVO")) { ... }`

<details>
<summary>Ver resposta</summary>

Sim, se `status` for `null`. Corrija invertendo: `if ("ATIVO".equals(status))`, ou usando `Objects.equals(status, "ATIVO")`.

</details>

**2.** Reescreva sem `if`, usando `Optional`:

```java
Cliente c = repository.findById(id).orElse(null);
if (c == null) {
    throw new ClienteNaoEncontradoException(id);
}
return c.getEmail();
```

<details>
<summary>Ver resposta</summary>

```java
return repository.findById(id)
        .map(Cliente::getEmail)
        .orElseThrow(() -> new ClienteNaoEncontradoException(id));
```

</details>

**3.** Por que um método `List<Pedido> buscarPedidos()` não deve devolver `null` quando não há pedidos?

<details>
<summary>Ver resposta</summary>

Porque quem chama vai, naturalmente, fazer `for (Pedido p : buscarPedidos())` ou `.size()`, e quebraria com NPE. Devolvendo uma **lista vazia**, o laço simplesmente não executa.

</details>

---

<div align="center">

[⬅️ 8.1 Java moderno](./01-java-moderno.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [8.3 Docker para quem programa em Java ➡️](./03-docker-para-java.md)

</div>
