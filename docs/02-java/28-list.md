# 2.28 · Listas prontas: a interface `List`

> **Em uma frase:** uma `List` é uma coleção **ordenada** que aceita **repetidos** e permite acessar elementos pelo **índice**; a implementação mais usada é o `ArrayList`.

## 🧰 Principais métodos

| Método | O que faz |
| --- | --- |
| `add(e)` / `add(i, e)` | Adiciona no fim / na posição `i` |
| `get(i)` | Devolve o elemento da posição `i` |
| `set(i, e)` | Substitui o elemento da posição `i` |
| `remove(i)` / `remove(objeto)` | Remove pela posição / pelo valor |
| `indexOf(o)` / `lastIndexOf(o)` | Primeira / última posição do valor (ou `-1`) |
| `contains(o)` | Contém o valor? (usa `equals`) |
| `size()` / `isEmpty()` / `clear()` | Tamanho / está vazia? / esvazia |
| `subList(de, até)` | Uma "janela" da lista (o `até` não entra) |
| `addAll(colecao)` | Adiciona todos de outra coleção |
| `toArray()` | Converte em array |

## 🏗️ Implementações

| Classe | Por dentro | Bom para |
| --- | --- | --- |
| **`ArrayList`** | Array que cresce sozinho | **Uso geral**; acesso por índice muito rápido |
| **`LinkedList`** | Lista duplamente encadeada | Muitas inserções/remoções nas pontas |
| `Vector` | Como o `ArrayList`, mas sincronizado | Código legado (evite) |

```java
import java.util.ArrayList;
import java.util.List;

List<Carro> carros = new ArrayList<>();
carros.add(new Carro("Ford"));
carros.add(new Carro("Kia"));
carros.add(new Carro("Mercedes-Benz"));
carros.add(new Carro("BMW"));

System.out.println(carros);                              // [Carro{Ford}, Carro{Kia}, ...]
System.out.println(carros.contains(new Carro("Kia")));   // true (graças ao equals)
System.out.println(carros.get(2));                       // Carro{Mercedes-Benz}
System.out.println(carros.indexOf(new Carro("Kia")));    // 1
System.out.println(carros.remove(1));                    // Carro{Kia}
```

> [!TIP]
> Declare a variável pelo **tipo da interface** (`List<Carro> carros = new ArrayList<>()`). Assim, se um dia quiser trocar por `LinkedList`, muda só uma palavra.

> [!NOTE]
> `List.of("a", "b", "c")` cria uma lista **imutável** (não aceita `add` nem `remove`). Para uma lista que você vai alterar, use `new ArrayList<>(List.of(...))`.

Esta aula é uma introdução. Ordenação, `Comparator`, iteradores e muitos exercícios estão na aula [4.9 · List](../04-poo/09-list.md).

## 📌 Resumindo

- `List`: ordenada, aceita repetidos, acesso por índice.
- `ArrayList` é a escolha padrão; `LinkedList` para muitas operações nas pontas.
- Declare pela interface (`List<T>`); `contains`/`indexOf`/`remove(objeto)` usam `equals`.

## 🏋️ Mão na massa

**1.** Crie uma lista com os nomes `"Ana"`, `"Bia"`, `"Caio"`, insira `"Beto"` na posição 1 e imprima.

<details>
<summary>Ver resposta</summary>

```java
List<String> nomes = new ArrayList<>(List.of("Ana", "Bia", "Caio"));
nomes.add(1, "Beto");
System.out.println(nomes);   // [Ana, Beto, Bia, Caio]
```

</details>

**2.** Numa `List<Integer> numeros` com `[10, 20, 30]`, o que faz `numeros.remove(1)`? E como remover o **valor** 10?

<details>
<summary>Ver resposta</summary>

`remove(1)` remove pelo **índice**: tira o `20`. Para remover o valor 10, é preciso passar um objeto: `numeros.remove(Integer.valueOf(10))`.

</details>

**3.** Por que `List.of(1, 2, 3).add(4)` dá erro?

<details>
<summary>Ver resposta</summary>

Porque `List.of` cria uma lista **imutável**, e qualquer tentativa de alterá-la lança `UnsupportedOperationException`.

</details>

---

<div align="center">

[⬅️ 2.27 Filas prontas do Java](./27-queue.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.29 Conjuntos ➡️](./29-set.md)

</div>
