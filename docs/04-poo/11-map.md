# 4.11 · Map: dicionários chave → valor

> **Em uma frase:** um `Map` associa **chaves únicas** a valores; o `HashMap` é o mais rápido, o `LinkedHashMap` mantém a ordem de inserção e o `TreeMap` mantém as chaves ordenadas.

Introdução e métodos básicos na aula [2.30](../02-java/30-map.md).

| | `HashMap` | `LinkedHashMap` | `TreeMap` |
| --- | --- | --- | --- |
| Ordem das chaves | Nenhuma | Inserção | Ordenada |
| `get`/`put` | O(1) em média | O(1) em média | O(log n) |
| Chave `null` | Aceita uma | Aceita uma | Não aceita |

## 🔁 Percorrendo um mapa

```java
Map<String, Double> consumo = new HashMap<>(Map.of("Gol", 14.4, "Uno", 15.6));

for (Map.Entry<String, Double> par : consumo.entrySet()) {   // pares (o mais eficiente)
    System.out.println(par.getKey() + " → " + par.getValue());
}

consumo.forEach((modelo, kmPorLitro) ->                     // com lambda
        System.out.println(modelo + " → " + kmPorLitro));
```

## 🧰 Métodos que evitam `if`s

```java
mapa.getOrDefault("Tucson", 0.0);          // valor ou um padrão, em vez de null
mapa.putIfAbsent("Gol", 15.0);             // só insere se a chave não existir
mapa.merge("Gol", 1.0, Double::sum);       // soma com o valor existente (ou insere)
mapa.computeIfAbsent("grupo", k -> new ArrayList<>()).add(item);   // listas por chave
```

## 📌 Resumindo

- Chaves únicas; `put` numa chave existente substitui o valor.
- Percorra com `entrySet()` ou `forEach((k, v) -> ...)`.
- `getOrDefault`, `putIfAbsent`, `merge` e `computeIfAbsent` deixam o código mais curto.

## 🏋️ Mão na massa

> Exercícios do curso, agora com todas as respostas.

**1.** Com o consumo dos carros (Gol 14.4, Uno 15.6, Mobi 16.1, HB20 14.5, Kwid 15.6 km/L): troque o do Gol para 15.2; confira se o Tucson está na lista; mostre o consumo do Uno, os modelos e os consumos; o mais e o menos econômico; a soma e a média; remova os que fazem 15.6 km/L; mostre na ordem de inserção e em ordem alfabética; apague tudo.

<details>
<summary>Ver resposta</summary>

```java
Map<String, Double> carros = new LinkedHashMap<>();
carros.put("Gol", 14.4);
carros.put("Uno", 15.6);
carros.put("Mobi", 16.1);
carros.put("HB20", 14.5);
carros.put("Kwid", 15.6);

carros.put("Gol", 15.2);                                    // substitui o valor
System.out.println("Tem Tucson? " + carros.containsKey("Tucson"));
System.out.println("Uno: " + carros.get("Uno"));
System.out.println("Modelos: " + carros.keySet());
System.out.println("Consumos: " + carros.values());

double maior = Collections.max(carros.values());
double menor = Collections.min(carros.values());
carros.forEach((modelo, km) -> {
    if (km == maior) System.out.println("Mais econômico: " + modelo + " - " + km);
    if (km == menor) System.out.println("Menos econômico: " + modelo + " - " + km);
});

double soma = 0;
for (double km : carros.values()) soma += km;
System.out.println("Soma: " + soma + " | Média: " + soma / carros.size());

carros.values().removeIf(km -> km == 15.6);                 // remove Uno e Kwid
System.out.println("Sem os de 15.6: " + carros);

System.out.println("Inserção:   " + carros);                // LinkedHashMap já mantém
System.out.println("Alfabética: " + new TreeMap<>(carros));

carros.clear();
System.out.println("Vazio? " + carros.isEmpty());
```

Detalhes: o **mais econômico** é o que faz **mais** km/L (por isso o `max`); e remover pelo `values().removeIf(...)` remove o par inteiro do mapa.

</details>

**2.** Autores e livros: Stephen Hawking (*Uma breve história do tempo*, 256 páginas), Charles Duhigg (*O poder do hábito*, 408) e Yuval Noah Harari (*21 lições para o século 21*, 432). Mostre em ordem aleatória, de inserção, alfabética por autor, alfabética por título e por número de páginas.

<details>
<summary>Ver resposta</summary>

```java
record Livro(String titulo, int paginas) { }

Map<String, Livro> livros = new LinkedHashMap<>();
livros.put("Stephen Hawking", new Livro("Uma breve história do tempo", 256));
livros.put("Charles Duhigg", new Livro("O poder do hábito", 408));
livros.put("Yuval Noah Harari", new Livro("21 lições para o século 21", 432));

System.out.println("Aleatória: " + new HashMap<>(livros));
System.out.println("Inserção:  " + livros);
System.out.println("Por autor: " + new TreeMap<>(livros));

List<Map.Entry<String, Livro>> pares = new ArrayList<>(livros.entrySet());

pares.sort(Comparator.comparing(e -> e.getValue().titulo()));
System.out.println("Por título: " + pares);

pares.sort(Comparator.comparingInt(e -> e.getValue().paginas()));
System.out.println("Por páginas: " + pares);
```

Para ordenar pelo **valor**, transformamos os pares em uma lista e ordenamos com um `Comparator`. (O curso usou um `TreeSet` de `Map.Entry` com comparador, que também funciona, desde que não haja empates.)

</details>

**3.** População de estados do Nordeste: PE 9.616.621, AL 3.351.543, CE 9.187.103, RN 3.534.265. Mostre a lista; troque RN para 3.534.165; se a PB não estiver, adicione com 4.039.277; mostre PE; mostre na ordem de inserção e em ordem alfabética; o estado menos e o mais populoso; a soma e a média; remova os com menos de 4 milhões; apague tudo.

<details>
<summary>Ver resposta</summary>

```java
Map<String, Integer> estados = new LinkedHashMap<>();
estados.put("PE", 9_616_621);
estados.put("AL", 3_351_543);
estados.put("CE", 9_187_103);
estados.put("RN", 3_534_265);
System.out.println(estados);

estados.put("RN", 3_534_165);
estados.putIfAbsent("PB", 4_039_277);
System.out.println("PE: " + estados.get("PE"));
System.out.println("Inserção:   " + estados);
System.out.println("Alfabética: " + new TreeMap<>(estados));

Map.Entry<String, Integer> menor = Collections.min(estados.entrySet(), Map.Entry.comparingByValue());
Map.Entry<String, Integer> maior = Collections.max(estados.entrySet(), Map.Entry.comparingByValue());
System.out.println("Menos populoso: " + menor);   // AL=3351543
System.out.println("Mais populoso:  " + maior);   // PE=9616621

long soma = 0;
for (int pop : estados.values()) soma += pop;
System.out.println("Soma: " + soma + " | Média: " + soma / estados.size());

estados.values().removeIf(pop -> pop < 4_000_000);
System.out.println("Acima de 4 milhões: " + estados);   // PE, CE, PB

estados.clear();
System.out.println("Vazio? " + estados.isEmpty());
```

</details>

**4.** Simule 100 lançamentos de um dado e mostre quantas vezes cada face saiu.

<details>
<summary>Ver resposta</summary>

```java
Random random = new Random();
Map<Integer, Integer> frequencia = new TreeMap<>();

for (int i = 0; i < 100; i++) {
    int face = random.nextInt(1, 7);           // 1 a 6
    frequencia.merge(face, 1, Integer::sum);
}

frequencia.forEach((face, vezes) ->
        System.out.println("Face " + face + ": " + vezes + " vezes"));
```

O `TreeMap` deixa as faces em ordem, e o `merge` soma 1 (ou começa em 1 na primeira vez).

</details>

---

<div align="center">

[⬅️ 4.10 Set](./10-set.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.12 Lambdas, referências de método e Stream API ➡️](./12-stream-api.md)

</div>
