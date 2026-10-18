# 2.28 · Conjuntos: a interface `Set`

> **Em uma frase:** um `Set` é uma coleção que **não aceita elementos repetidos** e não tem índice; o `HashSet` não garante ordem, o `LinkedHashSet` mantém a ordem de inserção e o `TreeSet` mantém tudo ordenado.

## 🧰 Principais métodos

`add(e)` (devolve `false` se já existia), `remove(o)`, `contains(o)`, `size()`, `isEmpty()`, `clear()` e `iterator()`.

## 🏗️ Implementações

| Classe | Ordem dos elementos | Velocidade | Observação |
| --- | --- | --- | --- |
| **`HashSet`** | Nenhuma garantida | A mais rápida (O(1) em média) | Usa `hashCode` + `equals` |
| **`LinkedHashSet`** | Ordem de **inserção** | Quase tão rápida | Usa uma lista encadeada por dentro |
| **`TreeSet`** | **Ordenada** (natural ou por `Comparator`) | O(log n) | Usa árvore rubro-negra; exige `Comparable` ou `Comparator`; não aceita `null` |

```java
Set<String> marcas = new HashSet<>(List.of("Ford", "Toyota", "Nissan", "Audi"));
marcas.add("Ford");                   // ignorado: já existe
System.out.println(marcas);           // ordem imprevisível

Set<String> ordenadas = new TreeSet<>(marcas);
System.out.println(ordenadas);        // [Audi, Ford, Nissan, Toyota]
```

Para colocar seus próprios objetos num `TreeSet`, a classe precisa implementar `Comparable`:

```java
public class Carro implements Comparable<Carro> {
    // ... atributo marca, equals e hashCode (aula 2.24)

    @Override
    public int compareTo(Carro outro) {
        return this.marca.compareTo(outro.marca);   // ordem alfabética
    }
}
```

> [!TIP]
> Um uso clássico do `Set` é **remover duplicados** de uma lista: `new LinkedHashSet<>(lista)` tira os repetidos mantendo a ordem original.

> [!WARNING]
> Num `HashSet`, os objetos **precisam** ter `equals` e `hashCode` corretos (aula [2.24](./24-equals-e-hashcode.md)); senão os "repetidos" entram mesmo assim.

Exercícios e operações de conjuntos (união, interseção e diferença) estão na aula [4.9 · Set](../04-poo/09-set.md).

## 📌 Resumindo

- `Set` não aceita repetidos e não tem índice.
- `HashSet` (sem ordem), `LinkedHashSet` (ordem de inserção), `TreeSet` (ordenado).
- `TreeSet` exige `Comparable`/`Comparator`; `HashSet` exige `equals`/`hashCode`.

## 🏋️ Mão na massa

**1.** Remova os nomes repetidos de `["Ana", "Bia", "Ana", "Caio", "Bia"]` mantendo a ordem.

<details>
<summary>Ver resposta</summary>

```java
List<String> nomes = List.of("Ana", "Bia", "Ana", "Caio", "Bia");
Set<String> unicos = new LinkedHashSet<>(nomes);
System.out.println(unicos);   // [Ana, Bia, Caio]
```

</details>

**2.** O que `set.add("Ford")` devolve se o `"Ford"` já estiver no conjunto?

<details>
<summary>Ver resposta</summary>

`false`, indicando que nada foi adicionado.

</details>

**3.** Qual `Set` usar para mostrar uma lista de palavras em ordem alfabética, sem repetição?

<details>
<summary>Ver resposta</summary>

`TreeSet`, que mantém os elementos ordenados (para `String`, a ordem natural é a alfabética) e não aceita repetidos.

</details>

---

<div align="center">

[⬅️ 2.27 Listas prontas](./27-list.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.29 Mapas ➡️](./29-map.md)

</div>
