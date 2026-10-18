# 4.10 · Set: conjuntos sem repetição

> **Em uma frase:** um `Set` não guarda elementos repetidos; escolha `HashSet` (rápido, sem ordem), `LinkedHashSet` (ordem de inserção) ou `TreeSet` (ordenado), e use-o para operações de conjuntos como união e interseção.

Introdução e comparação das implementações na aula [2.29](../02-java/29-set.md).

| | `HashSet` | `LinkedHashSet` | `TreeSet` |
| --- | --- | --- | --- |
| Ordem | Nenhuma | Inserção | Natural ou `Comparator` |
| `add`/`remove`/`contains` | O(1) em média | O(1) em média | O(log n) |
| Aceita `null` | Sim (um) | Sim (um) | Não |
| Requisito | `equals` + `hashCode` | `equals` + `hashCode` | `Comparable` ou `Comparator` |

## 🔣 Operações de conjunto

```java
Set<Integer> a = new HashSet<>(Set.of(1, 3, 4));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5, 6));

Set<Integer> uniao = new HashSet<>(a);
uniao.addAll(b);              // [1, 3, 4, 5, 6]

Set<Integer> intersecao = new HashSet<>(a);
intersecao.retainAll(b);      // [3, 4]

Set<Integer> diferenca = new HashSet<>(a);
diferenca.removeAll(b);       // [1]
```

| Operação | Matemática | Método | Resultado com A = {1, 3, 4} e B = {3, 4, 5, 6} |
| --- | --- | --- | --- |
| União | A ∪ B | `addAll` | {1, 3, 4, 5, 6} |
| Interseção | A ∩ B | `retainAll` | {3, 4} |
| Diferença | A − B | `removeAll` | {1} |

> [!TIP]
> Repare que copiamos o conjunto antes (`new HashSet<>(a)`), porque `addAll`, `retainAll` e `removeAll` **alteram** o conjunto em que são chamados.

## `TreeSet` e seus métodos extras

```java
TreeSet<Integer> numeros = new TreeSet<>(List.of(10, 5, 20, 15, 25));
System.out.println(numeros);               // [5, 10, 15, 20, 25]
System.out.println(numeros.first());       // 5
System.out.println(numeros.last());        // 25
System.out.println(numeros.headSet(15));   // [5, 10] (menores que 15)
System.out.println(numeros.ceiling(12));   // 15 (o menor ≥ 12)
System.out.println(numeros.descendingSet());   // [25, 20, 15, 10, 5]
```

## 📌 Resumindo

- `Set` não aceita repetidos; o `add` devolve `false` se o elemento já existir.
- `addAll` = união; `retainAll` = interseção; `removeAll` = diferença.
- `TreeSet` mantém tudo ordenado e oferece `first`, `last`, `headSet`, `ceiling`...

## 🏋️ Mão na massa

> Exercícios do curso, agora com todas as respostas.

**1.** Com as notas `7, 8.5, 9.3, 5, 7, 0, 3.6` em um `HashSet`: confira se tem 5; mostre a menor, a maior, a soma e a média; remova o 0 e as notas abaixo de 7; mostre as notas originais na ordem de inserção (`LinkedHashSet`) e em ordem crescente (`TreeSet`); por fim, apague o primeiro conjunto.

<details>
<summary>Ver resposta</summary>

```java
List<Double> originais = List.of(7.0, 8.5, 9.3, 5.0, 7.0, 0.0, 3.6);
Set<Double> notas = new HashSet<>(originais);     // o 7 repetido entra uma vez só
System.out.println("Notas: " + notas);

System.out.println("Tem 5? " + notas.contains(5.0));
System.out.println("Menor: " + Collections.min(notas));
System.out.println("Maior: " + Collections.max(notas));

double soma = 0;
for (double n : notas) soma += n;
System.out.println("Soma: " + soma + " | Média: " + soma / notas.size());

notas.remove(0.0);
notas.removeIf(n -> n < 7);
System.out.println("Sem as menores que 7: " + notas);

System.out.println("Ordem de inserção: " + new LinkedHashSet<>(originais));
System.out.println("Ordem crescente:   " + new TreeSet<>(originais));

notas.clear();
System.out.println("Vazio? " + notas.isEmpty());
```

Repare: a média aqui usa só os valores **distintos** (o 7 conta uma vez), porque o conjunto descarta repetidos. Se a média precisar considerar as repetições, use uma lista.

</details>

**2.** Minhas séries favoritas: GoT (Fantasia, episódios de 60 min), Dark (Drama, 60 min) e That '70s Show (Comédia, 25 min). Mostre em ordem aleatória (`HashSet`), de inserção (`LinkedHashSet`) e pelo tempo de episódio (`TreeSet`).

<details>
<summary>Ver resposta</summary>

```java
record Serie(String nome, String genero, int minutosPorEpisodio) { }

List<Serie> lista = List.of(
        new Serie("GoT", "Fantasia", 60),
        new Serie("Dark", "Drama", 60),
        new Serie("That '70s Show", "Comédia", 25));

System.out.println("Aleatória: " + new HashSet<>(lista));
System.out.println("Inserção:  " + new LinkedHashSet<>(lista));

Set<Serie> porTempo = new TreeSet<>(Comparator
        .comparingInt(Serie::minutosPorEpisodio)
        .thenComparing(Serie::nome));     // desempate: senão GoT e Dark seriam "iguais"
porTempo.addAll(lista);
System.out.println("Por tempo: " + porTempo);
```

> [!WARNING]
> Num `TreeSet`, dois elementos com `compare == 0` são tratados como **o mesmo**, e o segundo é descartado. Como GoT e Dark têm 60 minutos, sem o `thenComparing` uma das séries sumiria.

</details>

**3.** Crie um conjunto com as cores do arco-íris e: mostre uma abaixo da outra; mostre quantas são; em ordem alfabética; na ordem inversa da informada; as que começam com "V"; remova as que **não** começam com "V"; limpe e mostre que está vazio.

<details>
<summary>Ver resposta</summary>

```java
Set<String> arcoIris = new LinkedHashSet<>(List.of(
        "vermelho", "laranja", "amarelo", "verde", "azul", "anil", "violeta"));

arcoIris.forEach(System.out::println);
System.out.println("Quantidade: " + arcoIris.size());                 // 7
System.out.println("Alfabética: " + new TreeSet<>(arcoIris));

List<String> inversa = new ArrayList<>(arcoIris);
Collections.reverse(inversa);
System.out.println("Inversa: " + inversa);

for (String cor : arcoIris) {
    if (cor.startsWith("v")) System.out.println("Começa com V: " + cor);
}

arcoIris.removeIf(cor -> !cor.startsWith("v"));
System.out.println("Só com V: " + arcoIris);   // [vermelho, verde, violeta]

arcoIris.clear();
System.out.println("Vazio? " + arcoIris.isEmpty());
```

O `LinkedHashSet` foi escolhido para preservar a ordem informada, necessária para a "ordem inversa".

</details>

**4.** Crie a classe `LinguagemFavorita` (nome, ano de criação, IDE) e um conjunto com 3 linguagens. Ordene por: inserção; nome; IDE; ano de criação; e nome → ano → IDE.

<details>
<summary>Ver resposta</summary>

```java
record LinguagemFavorita(String nome, int anoDeCriacao, String ide) { }

List<LinguagemFavorita> linguagens = List.of(
        new LinguagemFavorita("Java", 1995, "IntelliJ"),
        new LinguagemFavorita("Python", 1991, "PyCharm"),
        new LinguagemFavorita("JavaScript", 1995, "VS Code"));

System.out.println("Inserção: " + new LinkedHashSet<>(linguagens));

Set<LinguagemFavorita> porNome = new TreeSet<>(Comparator.comparing(LinguagemFavorita::nome));
porNome.addAll(linguagens);
System.out.println("Nome: " + porNome);

Set<LinguagemFavorita> porIde = new TreeSet<>(Comparator.comparing(LinguagemFavorita::ide));
porIde.addAll(linguagens);
System.out.println("IDE: " + porIde);

Set<LinguagemFavorita> porAno = new TreeSet<>(Comparator
        .comparingInt(LinguagemFavorita::anoDeCriacao)
        .thenComparing(LinguagemFavorita::nome));   // desempate: Java e JS são de 1995
porAno.addAll(linguagens);
System.out.println("Ano: " + porAno);

Set<LinguagemFavorita> completo = new TreeSet<>(Comparator
        .comparing(LinguagemFavorita::nome)
        .thenComparingInt(LinguagemFavorita::anoDeCriacao)
        .thenComparing(LinguagemFavorita::ide));
completo.addAll(linguagens);
System.out.println("Nome/ano/IDE: " + completo);
```

O `record` já gera construtor, getters, `equals`, `hashCode` e `toString` (veja o [módulo extra](../08-extras/01-java-moderno.md)).

</details>

---

<div align="center">

[⬅️ 4.9 List](./09-list.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.11 Map ➡️](./11-map.md)

</div>
