# 3.3 · Desafio: Soma de H com N termos

> **Em uma frase:** somar as frações `1 + 1/2 + 1/3 + ... + 1/N` (a "série harmônica") e mostrar o resultado arredondado.

## 📋 Enunciado

Calcule `H = 1 + 1/2 + 1/3 + 1/4 + ... + 1/N` para um inteiro positivo `N`, e imprima o valor **arredondado para o inteiro mais próximo**.

| Entrada | H calculado | Saída |
| --- | --- | --- |
| `4` | 2,083... | `2` |
| `8` | 2,717... | `3` |
| `3` | 1,833... | `2` |

## 🧠 Como pensar

1. Começar com `h = 0`.
2. Para cada `i` de 1 até `N`, somar `1.0 / i`.
3. Arredondar com `Math.round(h)`.

> [!WARNING]
> Escreva `1.0 / i`, e não `1 / i`. Com dois inteiros, `1 / 2` dá `0`, e a soma inteira sairia errada.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int n = entrada.nextInt();

        double h = 0;
        for (int i = 1; i <= n; i++) {
            h += 1.0 / i;
        }

        System.out.println(Math.round(h));
    }
}
```

</details>

## 🏋️ Mão na massa

**1.** Mude a saída para mostrar `H` com duas casas decimais.

<details>
<summary>Ver resposta</summary>

```java
System.out.printf(java.util.Locale.US, "%.2f%n", h);   // para N = 4: 2.08
```

</details>

**2.** Calcule a soma `1 + 1/2 + 1/4 + 1/8 + ...` com `N` termos (cada termo é metade do anterior). Para onde ela tende?

<details>
<summary>Ver resposta</summary>

```java
double soma = 0, termo = 1;
for (int i = 0; i < n; i++) {
    soma += termo;
    termo /= 2;
}
```

A soma se aproxima cada vez mais de **2** (diferente da série harmônica, que cresce sem limite, só que bem devagar).

</details>

---

<div align="center">

[⬅️ 3.2 Positivos e média](./02-positivos-e-media.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [3.4 Dragão (Mais de 8000!) ➡️](./04-dragao.md)

</div>
