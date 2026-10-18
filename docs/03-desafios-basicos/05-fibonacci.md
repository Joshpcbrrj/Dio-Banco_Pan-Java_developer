# 3.5 · Desafio: Fibonacci

> **Em uma frase:** imprimir os `N` primeiros números da sequência de Fibonacci (cada número é a soma dos dois anteriores), separados por espaço e **sem espaço no final**.

## 📋 Enunciado

A sequência `0 1 1 2 3 5 8 13 21 ...` é a **série de Fibonacci**: depois dos dois primeiros, cada número é a soma dos dois anteriores. Leia um inteiro `N` (0 < N < 46) e mostre os `N` primeiros termos na mesma linha, separados por um espaço, **sem espaço após o último**.

| Entrada | Saída |
| --- | --- |
| `5` | `0 1 1 2 3` |

## 🧠 Como pensar

Guarde dois valores, o **anterior** e o **atual**, e vá "andando" a sequência:

| Passo | Imprime | anterior | atual |
| --- | --- | --- | --- |
| início | — | 0 | 1 |
| 1 | 0 | 1 | 1 |
| 2 | 1 | 1 | 2 |
| 3 | 1 | 2 | 3 |
| 4 | 2 | 3 | 5 |
| 5 | 3 | 5 | 8 |

O "não tão fácil" do desafio é o detalhe do **espaço no final**: imprima o espaço só **entre** os números.

> [!NOTE]
> Por que limitar o `N`? Os termos crescem muito rápido: a partir do 48º, o valor passa do limite do `int` (cerca de 2,1 bilhões). Para sequências maiores, use `long` (ou `BigInteger`).

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int n = entrada.nextInt();

        int anterior = 0, atual = 1;
        StringBuilder saida = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (i > 0) saida.append(' ');   // espaço só ENTRE os números
            saida.append(anterior);

            int proximo = anterior + atual;
            anterior = atual;
            atual = proximo;
        }

        System.out.println(saida);
    }
}
```

</details>

## 🏋️ Mão na massa

**1.** Quais são os 10 primeiros termos?

<details>
<summary>Ver resposta</summary>

`0 1 1 2 3 5 8 13 21 34`

</details>

**2.** Escreva uma versão **recursiva** que devolva o n-ésimo termo. Por que ela fica lenta para `n = 45`?

<details>
<summary>Ver resposta</summary>

```java
static long fib(int n) {
    if (n < 2) return n;
    return fib(n - 1) + fib(n - 2);
}
```

Ela recalcula os mesmos valores milhões de vezes (por exemplo, `fib(43)` é calculado tanto por `fib(45)` quanto por `fib(44)`), e o número de chamadas cresce exponencialmente. A versão com laço faz só `n` passos.

</details>

---

<div align="center">

[⬅️ 3.4 Dragão (Mais de 8000!)](./04-dragao.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.1 Classes, objetos, atributos, métodos e encapsulamento ➡️](../04-poo/01-classes-e-objetos.md)

</div>
