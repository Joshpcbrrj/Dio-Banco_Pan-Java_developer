# 2.26 · Pilhas prontas do Java: `Stack` e `Deque`

> **Em uma frase:** o Java já traz pilhas prontas; a classe antiga é a `Stack`, mas hoje o recomendado é usar a interface `Deque` com a implementação `ArrayDeque`.

## 🧰 `Stack` (a clássica)

```java
import java.util.Stack;

Stack<String> pilha = new Stack<>();
pilha.push("Ford");
pilha.push("Toyota");
pilha.push("Fiat");                       // topo

System.out.println(pilha);                // [Ford, Toyota, Fiat]
System.out.println(pilha.pop());          // Fiat (remove o topo)
System.out.println(pilha.peek());         // Toyota (só olha)
System.out.println(pilha.empty());        // false
System.out.println(pilha.search("Ford")); // 2 (posição contando do topo, começando em 1)
```

| Método | O que faz | Pilha vazia |
| --- | --- | --- |
| `push(e)` | Empilha | — |
| `pop()` | Remove e devolve o topo | `EmptyStackException` |
| `peek()` | Devolve o topo sem remover | `EmptyStackException` |
| `empty()` | Está vazia? | — |
| `search(o)` | Posição a partir do topo (1 = topo), ou `-1` | — |

> [!NOTE]
> A `Stack` herda de `Vector` (e não implementa `Deque`, como às vezes se diz). Por herdar de `Vector`, ela é sincronizada (mais lenta) e expõe métodos que quebram a ideia de pilha, como `get(índice)`. Por isso a documentação oficial recomenda usar `Deque`.

## ✅ `Deque` + `ArrayDeque` (o recomendado)

`Deque` (*double-ended queue*, fila de duas pontas) serve tanto como **pilha** quanto como **fila**.

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<String> pilha = new ArrayDeque<>();
pilha.push("Ford");
pilha.push("Toyota");
pilha.push("Fiat");

System.out.println(pilha.pop());      // Fiat
System.out.println(pilha.peek());     // Toyota
System.out.println(pilha.isEmpty());  // false
```

> [!TIP]
> Diferenças práticas: no `ArrayDeque`, `peek()` devolve `null` se estiver vazia (e `pop()` lança `NoSuchElementException`). Ele também **não aceita `null`** como elemento.

## 📌 Resumindo

- `Stack` funciona, mas é antiga (herda de `Vector`).
- Prefira `Deque<T> pilha = new ArrayDeque<>()` com `push`, `pop` e `peek`.

## 🏋️ Mão na massa

**1.** Reescreva o exemplo com `Stack` usando `ArrayDeque`.

<details>
<summary>Ver resposta</summary>

```java
Deque<String> pilha = new ArrayDeque<>();
pilha.push("Ford");
pilha.push("Toyota");
pilha.push("Fiat");
System.out.println(pilha.pop());   // Fiat
System.out.println(pilha.peek());  // Toyota
```

</details>

**2.** Implemente um "histórico do navegador": cada página visitada é empilhada, e o botão "voltar" desempilha.

<details>
<summary>Ver resposta</summary>

```java
Deque<String> historico = new ArrayDeque<>();
historico.push("google.com");
historico.push("github.com");
historico.push("dio.me");

historico.pop();                         // voltar: sai de dio.me
System.out.println(historico.peek());    // github.com (página atual)
```

</details>

**3.** O que acontece ao chamar `pop()` numa `Stack` vazia? E num `ArrayDeque` vazio?

<details>
<summary>Ver resposta</summary>

Na `Stack`, lança `EmptyStackException`. No `ArrayDeque`, lança `NoSuchElementException`. Em ambos, confira `isEmpty()` antes (ou use `poll()`, que no `ArrayDeque` devolve `null` em vez de lançar exceção).

</details>

---

<div align="center">

[⬅️ 2.25 equals() e hashCode()](./25-equals-e-hashcode.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.27 Filas prontas do Java ➡️](./27-queue.md)

</div>
