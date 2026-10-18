# 3.2 · Desafio: Positivos e média

> **Em uma frase:** ler 6 números, contar quantos são positivos e calcular a média só desses positivos, com uma casa decimal.

## 📋 Enunciado

Leia 6 valores (inteiros ou decimais). Mostre quantos foram **positivos** e, na linha seguinte, a **média dos positivos** com um dígito após o ponto. Pelo menos um valor será positivo.

| Entrada (um por linha) | Saída |
| --- | --- |
| `7` `-5` `6` `-3.4` `4.6` `12` | `4 valores positivos`<br>`7.4` |

Conta: os positivos são 7, 6, 4.6 e 12 → soma 29.6 ÷ 4 = **7.4**.

## 🧠 Como pensar

1. Repetir 6 vezes: ler um `double`.
2. Se for maior que zero, contar e somar.
3. No fim, dividir a soma pela quantidade e imprimir com `printf("%.1f")`.

> [!NOTE]
> Zero **não** é positivo: a condição é `numero > 0`, e não `>= 0`.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in).useLocale(Locale.US);   // lê "4.6" com ponto
        int positivos = 0;
        double soma = 0;

        for (int i = 0; i < 6; i++) {
            double numero = entrada.nextDouble();
            if (numero > 0) {
                positivos++;
                soma += numero;
            }
        }

        System.out.println(positivos + " valores positivos");
        System.out.printf(Locale.US, "%.1f%n", soma / positivos);
    }
}
```

O `Locale.US` garante o **ponto** como separador decimal, tanto na leitura quanto na escrita. Sem ele, num computador configurado em português, o `Scanner` lançaria `InputMismatchException` ao ler `-3.4` (ele esperaria `-3,4`), e o `printf` imprimiria `7,4`.

</details>

## 🏋️ Mão na massa

**1.** E se nenhum número fosse positivo? O que aconteceria com a divisão?

<details>
<summary>Ver resposta</summary>

Seria `0.0 / 0`, que com `double` resulta em `NaN` (*not a number*). Para se proteger, confira `if (positivos > 0)` antes de calcular a média.

</details>

**2.** Adapte o programa para também mostrar quantos números foram **negativos**.

<details>
<summary>Ver resposta</summary>

Acrescente um contador `negativos` e, dentro do laço, `else if (numero < 0) negativos++;`. No fim, imprima `negativos + " valores negativos"`.

</details>

---

<div align="center">

[⬅️ 3.1 Fábrica de carros](./01-fabrica-de-carros.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [3.3 Soma de H com N termos ➡️](./03-soma-de-h.md)

</div>
