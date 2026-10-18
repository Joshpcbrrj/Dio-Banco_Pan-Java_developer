# 2.26 · Filas prontas do Java: `Queue`, `ArrayDeque`, `LinkedList` e `PriorityQueue`

> **Em uma frase:** `Queue` é a interface de fila do Java; os métodos vêm em pares (um lança exceção, o outro devolve um valor especial), e as implementações mais usadas são `ArrayDeque`, `LinkedList` e `PriorityQueue`.

## 🧰 Os métodos da interface `Queue`

| Ação | Lança exceção se falhar | Devolve valor especial |
| --- | --- | --- |
| Inserir no fim | `add(e)` → `IllegalStateException` (fila cheia) | `offer(e)` → `false` |
| Remover o primeiro | `remove()` → `NoSuchElementException` | `poll()` → `null` |
| Olhar o primeiro | `element()` → `NoSuchElementException` | `peek()` → `null` |

> [!TIP]
> No dia a dia, prefira `offer`, `poll` e `peek`: eles não estouram exceções quando a fila está vazia.

## 🏗️ Implementações

| Classe | Características |
| --- | --- |
| **`ArrayDeque`** | Baseada em array circular. **A mais rápida** para fila e pilha. Não aceita `null`. |
| **`LinkedList`** | Lista duplamente encadeada. Implementa `List` **e** `Deque`: serve como fila, pilha e lista. Aceita `null`. |
| **`PriorityQueue`** | Sai primeiro o de **maior prioridade** (o "menor" pela ordem natural ou por um `Comparator`), não o primeiro que chegou. |

```java
import java.util.ArrayDeque;
import java.util.Queue;

Queue<String> fila = new ArrayDeque<>();
fila.offer("Honda");
fila.offer("Kia");
fila.offer("Chevrolet");

System.out.println(fila);           // [Honda, Kia, Chevrolet]
System.out.println(fila.peek());    // Honda (só olha)
System.out.println(fila.poll());    // Honda (remove)
System.out.println(fila.size());    // 2
System.out.println(fila.isEmpty()); // false
```

### `PriorityQueue`: atendimento por prioridade

```java
import java.util.PriorityQueue;

Queue<Integer> senhas = new PriorityQueue<>();
senhas.offer(30);
senhas.offer(10);
senhas.offer(20);
while (!senhas.isEmpty()) {
    System.out.print(senhas.poll() + " ");   // 10 20 30
}
```

Com um `Comparator`, você define a prioridade. Ex.: pacientes mais idosos primeiro:

```java
Queue<Paciente> triagem = new PriorityQueue<>(
        Comparator.comparingInt(Paciente::idade).reversed());
```

## 🔗 `LinkedList` como fila e como lista

```java
LinkedList<String> lista = new LinkedList<>();
lista.add("B");          // fim
lista.addFirst("A");     // início
lista.addLast("C");      // fim
System.out.println(lista);               // [A, B, C]
System.out.println(lista.removeFirst()); // A
System.out.println(lista.removeLast());  // C
System.out.println(lista.get(0));        // B (acesso por índice: O(n))
```

## 📌 Resumindo

- `offer`/`poll`/`peek` (valor especial) × `add`/`remove`/`element` (exceção).
- `ArrayDeque`: a opção padrão para filas e pilhas.
- `LinkedList`: fila + lista; `PriorityQueue`: sai primeiro o de maior prioridade.

## 🏋️ Mão na massa

**1.** Simule uma fila de impressão com `ArrayDeque`: adicione 3 documentos e "imprima" todos na ordem de chegada.

<details>
<summary>Ver resposta</summary>

```java
Queue<String> impressora = new ArrayDeque<>();
impressora.offer("relatorio.pdf");
impressora.offer("foto.png");
impressora.offer("contrato.docx");

while (!impressora.isEmpty()) {
    System.out.println("Imprimindo " + impressora.poll());
}
```

</details>

**2.** Qual a diferença entre `remove()` e `poll()` numa fila vazia?

<details>
<summary>Ver resposta</summary>

`remove()` lança `NoSuchElementException`; `poll()` devolve `null`.

</details>

**3.** Use uma `PriorityQueue` para sempre atender primeiro o pedido de **maior** valor.

<details>
<summary>Ver resposta</summary>

```java
Queue<Double> pedidos = new PriorityQueue<>(Comparator.reverseOrder());
pedidos.offer(50.0);
pedidos.offer(300.0);
pedidos.offer(120.0);
System.out.println(pedidos.poll());   // 300.0
```

</details>

---

<div align="center">

[⬅️ 2.25 Pilhas prontas do Java](./25-stack-e-deque.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.27 Listas prontas ➡️](./27-list.md)

</div>
