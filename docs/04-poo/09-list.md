# 4.9 · List: ordenação, `Comparable` e `Comparator`

> **Em uma frase:** `List` é a coleção ordenada com índices (revisada na aula [2.28](../02-java/28-list.md)); aqui o foco é **ordenar** listas, seja pela ordem natural (`Comparable`) ou por critérios personalizados (`Comparator`).

## 🏗️ Relembrando as implementações

| Classe | Por dentro | Acesso por índice | Inserir/remover no meio |
| --- | --- | --- | --- |
| `ArrayList` | Array redimensionável | ⚡ Rápido (O(1)) | 🐢 Precisa deslocar elementos |
| `LinkedList` | Lista duplamente encadeada | 🐢 Percorre (O(n)) | ⚡ Rápido, se já estiver na posição |
| `Vector` | Array sincronizado | Rápido | Lento; considerado legado |

> [!TIP]
> Na dúvida, use `ArrayList`. Na prática, ele é mais rápido que a `LinkedList` na grande maioria dos casos.

## 🔢 Ordem natural: `Comparable`

A classe define **a sua** ordem padrão implementando `compareTo`:

```java
public class Gato implements Comparable<Gato> {
    private final String nome;
    private final int idadeMeses;
    private final String cor;

    public Gato(String nome, int idadeMeses, String cor) {
        this.nome = nome;
        this.idadeMeses = idadeMeses;
        this.cor = cor;
    }

    public String getNome() { return nome; }
    public int getIdadeMeses() { return idadeMeses; }
    public String getCor() { return cor; }

    @Override
    public int compareTo(Gato outro) {               // ordem natural: pelo nome
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public String toString() { return nome + " (" + idadeMeses + " meses, " + cor + ")"; }
}
```

```java
Collections.sort(gatos);   // usa o compareTo
```

## 🎚️ Outras ordens: `Comparator`

Para ordenar por outro critério sem mexer na classe, crie um `Comparator`. A forma moderna usa os métodos de fábrica:

```java
gatos.sort(Comparator.comparing(Gato::getCor));                 // por cor
gatos.sort(Comparator.comparingInt(Gato::getIdadeMeses));       // por idade
gatos.sort(Comparator.comparingInt(Gato::getIdadeMeses).reversed());   // idade decrescente

gatos.sort(Comparator.comparing(Gato::getNome)                  // nome, depois cor, depois idade
        .thenComparing(Gato::getCor)
        .thenComparingInt(Gato::getIdadeMeses));
```

<details>
<summary>Como era feito no curso (classes Comparator)</summary>

```java
class ComparatorIdade implements Comparator<Gato> {
    @Override
    public int compare(Gato g1, Gato g2) {
        return Integer.compare(g1.getIdadeMeses(), g2.getIdadeMeses());
    }
}

gatos.sort(new ComparatorIdade());
```

O resultado é o mesmo; os métodos `Comparator.comparing(...)` só evitam criar uma classe para cada critério.

</details>

> [!NOTE]
> `Collections.sort(lista)` e `lista.sort(comparador)` fazem a mesma coisa. Passar `null` como comparador em `lista.sort(null)` usa a ordem natural.

## 📌 Resumindo

- `ArrayList` é a escolha padrão de lista.
- `Comparable` (`compareTo`) define a ordem **natural** dentro da classe.
- `Comparator` define ordens **alternativas**; use `Comparator.comparing(...)`, `.reversed()` e `.thenComparing(...)`.

## 🏋️ Mão na massa

> Exercícios do curso, agora com todas as respostas.

**1.** Dada a lista de notas `[7, 8.5, 9.3, 5, 7, 0, 3.6]`: mostre a posição da nota 5; insira 8.0 na posição 4; troque 5 por 6; confira se 5 ainda está na lista; mostre a terceira nota, a menor, a maior, a soma e a média; remova a nota 0, depois o índice 0 e por fim as notas menores que 7; apague tudo e mostre que a lista está vazia.

<details>
<summary>Ver resposta</summary>

```java
List<Double> notas = new ArrayList<>(List.of(7.0, 8.5, 9.3, 5.0, 7.0, 0.0, 3.6));
System.out.println("Notas: " + notas);

System.out.println("Posição da nota 5: " + notas.indexOf(5.0));          // 3
notas.add(4, 8.0);                                                       // insere na posição 4
notas.set(notas.indexOf(5.0), 6.0);                                      // troca 5 por 6
System.out.println("Ainda tem 5? " + notas.contains(5.0));               // false

for (int i = 0; i < notas.size(); i++) {
    System.out.print("[" + i + "]=" + notas.get(i) + " ");
}
System.out.println("\nTerceira nota: " + notas.get(2));                  // 9.3
System.out.println("Menor: " + Collections.min(notas));                  // 0.0
System.out.println("Maior: " + Collections.max(notas));                  // 9.3

double soma = 0;
for (double n : notas) soma += n;
System.out.println("Soma: " + soma + " | Média: " + soma / notas.size());

notas.remove(0.0);                         // remove pelo VALOR (objeto Double)
notas.remove(0);                           // remove pelo ÍNDICE
notas.removeIf(n -> n < 7);                // remove as menores que 7
System.out.println("Restaram: " + notas);

notas.clear();
System.out.println("Vazia? " + notas.isEmpty());   // true
```

Atenção à diferença: `remove(0.0)` remove o **valor** 0.0; `remove(0)` remove o **índice** 0.

</details>

**2.** Tenho três gatos: Jon (16 meses, preto), Simba (72 meses, tigrado) e Jon (12 meses, amarelo). Mostre a lista na ordem de inserção, aleatória, por nome, por idade, por cor e por nome → cor → idade.

<details>
<summary>Ver resposta</summary>

```java
List<Gato> gatos = new ArrayList<>(List.of(
        new Gato("Jon", 16, "preto"),
        new Gato("Simba", 72, "tigrado"),
        new Gato("Jon", 12, "amarelo")));

System.out.println("Inserção:  " + gatos);

Collections.shuffle(gatos);
System.out.println("Aleatória: " + gatos);

Collections.sort(gatos);                                     // compareTo: nome
System.out.println("Nome:      " + gatos);

gatos.sort(Comparator.comparingInt(Gato::getIdadeMeses));
System.out.println("Idade:     " + gatos);

gatos.sort(Comparator.comparing(Gato::getCor));
System.out.println("Cor:       " + gatos);

gatos.sort(Comparator.comparing(Gato::getNome)
        .thenComparing(Gato::getCor)
        .thenComparingInt(Gato::getIdadeMeses));
System.out.println("Nome/cor/idade: " + gatos);
```

</details>

**3.** Leia a temperatura média dos 6 primeiros meses do ano, calcule a média do semestre e mostre as temperaturas acima dela com o mês por extenso.

<details>
<summary>Ver resposta</summary>

```java
import java.util.*;

public class Temperaturas {
    public static void main(String[] args) {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho"};
        Scanner entrada = new Scanner(System.in);
        List<Double> temperaturas = new ArrayList<>();

        for (String mes : meses) {
            System.out.print("Temperatura média de " + mes + ": ");
            temperaturas.add(entrada.nextDouble());
        }

        double media = 0;
        for (double t : temperaturas) media += t;
        media /= temperaturas.size();
        System.out.printf("Média do semestre: %.2f%n", media);

        for (int i = 0; i < temperaturas.size(); i++) {
            if (temperaturas.get(i) > media) {
                System.out.println((i + 1) + " - " + meses[i] + ": " + temperaturas.get(i));
            }
        }
    }
}
```

</details>

**4.** Faça 5 perguntas sobre um crime (respostas sim/não). Com 2 "sim", a pessoa é "Suspeita"; com 3 ou 4, "Cúmplice"; com 5, "Assassina"; caso contrário, "Inocente". Guarde as respostas em uma lista.

<details>
<summary>Ver resposta</summary>

```java
import java.util.*;

public class Interrogatorio {
    public static void main(String[] args) {
        List<String> perguntas = List.of(
                "Telefonou para a vítima?",
                "Esteve no local do crime?",
                "Mora perto da vítima?",
                "Devia para a vítima?",
                "Já trabalhou com a vítima?");

        Scanner entrada = new Scanner(System.in);
        List<Boolean> respostas = new ArrayList<>();
        for (String pergunta : perguntas) {
            System.out.print(pergunta + " (s/n): ");
            respostas.add(entrada.next().equalsIgnoreCase("s"));
        }

        long sim = respostas.stream().filter(r -> r).count();
        String classificacao = switch ((int) sim) {
            case 2 -> "Suspeita";
            case 3, 4 -> "Cúmplice";
            case 5 -> "Assassina";
            default -> "Inocente";
        };
        System.out.println("Classificação: " + classificacao);
    }
}
```

O `stream().filter(...).count()` conta os "sim"; você vai entender streams na aula [4.12](./12-stream-api.md). Uma alternativa sem streams é um `for` com contador.

</details>

---

<div align="center">

[⬅️ 4.8 O framework Collections](./08-collections.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.10 Set ➡️](./10-set.md)

</div>
