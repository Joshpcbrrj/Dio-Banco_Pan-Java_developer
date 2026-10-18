# 7.3 · Desafio: Triângulo

> **Em uma frase:** verificar se três medidas formam um triângulo; se formarem, mostrar o perímetro; se não, mostrar a área do trapézio com bases A e B e altura C.

## 📋 Enunciado

Leia três valores reais A, B e C.

- Se formarem um triângulo, imprima `Perimetro = XX.X` (soma dos lados).
- Caso contrário, imprima `Area = XX.X`, com a área do trapézio: `((A + B) × C) / 2`.

| Entrada | Saída |
| --- | --- |
| `6.0 4.0 2.0` | `Area = 10.0` |
| `6.0 4.0 2.1` | `Perimetro = 12.1` |

## 🧠 Como pensar

Três medidas formam um triângulo quando **cada lado é menor que a soma dos outros dois** (a desigualdade triangular):

```text
A < B + C   e   B < A + C   e   C < A + B
```

No primeiro exemplo, `6 < 4 + 2` é falso (6 não é menor que 6): os lados "deitam" uns sobre os outros e não fecham um triângulo.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in).useLocale(Locale.US);   // lê "6.0" com ponto
        double a = entrada.nextDouble();
        double b = entrada.nextDouble();
        double c = entrada.nextDouble();

        boolean ehTriangulo = a < b + c && b < a + c && c < a + b;

        if (ehTriangulo) {
            System.out.printf(Locale.US, "Perimetro = %.1f%n", a + b + c);
        } else {
            System.out.printf(Locale.US, "Area = %.1f%n", (a + b) * c / 2);
        }
    }
}
```

A versão do curso também calculava o **maior lado**, mas esse valor não era usado: com as três comparações acima, ele é desnecessário.

</details>

> [!TIP]
> O `Locale.US` garante o **ponto** como separador decimal, tanto na leitura (`6.0`) quanto na escrita (`12.1`). Sem ele, num computador em português, o `Scanner` esperaria `6,0` e o `printf` escreveria `12,1`.

## 🏋️ Mão na massa

**1.** `3 4 5` formam um triângulo? Qual a saída?

<details>
<summary>Ver resposta</summary>

Sim (3 < 9, 4 < 8 e 5 < 7). Saída: `Perimetro = 12.0`.

</details>

**2.** Acrescente a classificação do triângulo: equilátero (3 lados iguais), isósceles (2 iguais) ou escaleno (todos diferentes).

<details>
<summary>Ver resposta</summary>

```java
String tipo;
if (a == b && b == c) tipo = "equilátero";
else if (a == b || b == c || a == c) tipo = "isósceles";
else tipo = "escaleno";
```

(Com valores `double`, em casos reais compare com uma pequena tolerância, como `Math.abs(a - b) < 0.0001`.)

</details>

---

<div align="center">

[⬅️ 7.2 Quitanda do Seu Zé](./02-quitanda.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [7.4 Contar espaços e vogais ➡️](./04-espacos-e-vogais.md)

</div>
