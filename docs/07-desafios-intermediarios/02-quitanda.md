# 7.2 · Desafio: Quitanda do Seu Zé

> **Em uma frase:** calcular o valor de uma compra de morangos e maçãs, cujo preço por quilo muda acima de 5 kg, e aplicar 10% de desconto quando a compra passa de 8 kg ou de R$ 25,00.

## 📋 Enunciado

| Fruta | Até 5 kg | Acima de 5 kg |
| --- | --- | --- |
| Morango | R$ 2,50 por kg | R$ 2,20 por kg |
| Maçã | R$ 1,80 por kg | R$ 1,50 por kg |

Se o cliente comprar **mais de 8 kg** no total **ou** o valor passar de **R$ 25,00**, ganha **10% de desconto** sobre o total. Leia a quantidade (em kg, inteira) de morangos e de maçãs e imprima o valor a pagar.

| Entrada (morangos, maçãs) | Conta | Saída |
| --- | --- | --- |
| `3` `2` | 3 × 2,50 + 2 × 1,80 = 11,10 (sem desconto) | `11.1` |
| `1` `1` | 2,50 + 1,80 = 4,30 | `4.3` |
| `5` `5` | 12,50 + 9,00 = 21,50 → 10 kg > 8 kg → −10% | `19.35` |

## 🧠 Como pensar

1. Escolher o preço do quilo de cada fruta conforme a quantidade.
2. Somar os dois valores.
3. Aplicar o desconto se `kg total > 8` **ou** `valor > 25`.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int morangos = entrada.nextInt();
        int macas = entrada.nextInt();

        double precoMorango = morangos <= 5 ? 2.50 : 2.20;
        double precoMaca = macas <= 5 ? 1.80 : 1.50;

        double total = morangos * precoMorango + macas * precoMaca;

        if (morangos + macas > 8 || total > 25.00) {
            total *= 0.90;        // 10% de desconto
        }

        System.out.println(total);
    }
}
```

</details>

> [!NOTE]
> Por causa da imprecisão do `double`, algumas combinações podem imprimir valores como `19.350000000000001`. Em um sistema real, use `BigDecimal` e arredonde para centavos; em plataformas de desafio, siga exatamente o formato de saída pedido (por exemplo, `printf("%.2f")`, se for o caso).

## 🏋️ Mão na massa

**1.** Quanto paga quem compra 6 kg de morango e nenhuma maçã?

<details>
<summary>Ver resposta</summary>

6 × 2,20 = 13,20. Não passa de 8 kg nem de R$ 25 → **R$ 13,20**. Mas o programa com `double` imprime `13.200000000000001`, justamente o problema de imprecisão citado acima: com `BigDecimal` (próximo exercício), sai `13.20`.

</details>

**2.** Reescreva o cálculo com `BigDecimal`, arredondando para 2 casas.

<details>
<summary>Ver resposta</summary>

```java
BigDecimal precoMorango = new BigDecimal(morangos <= 5 ? "2.50" : "2.20");
BigDecimal precoMaca = new BigDecimal(macas <= 5 ? "1.80" : "1.50");

BigDecimal total = precoMorango.multiply(BigDecimal.valueOf(morangos))
        .add(precoMaca.multiply(BigDecimal.valueOf(macas)));

if (morangos + macas > 8 || total.compareTo(new BigDecimal("25")) > 0) {
    total = total.multiply(new BigDecimal("0.90"));
}
System.out.println(total.setScale(2, RoundingMode.HALF_UP));
```

</details>

---

<div align="center">

[⬅️ 7.1 Animal](./01-animal.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [7.3 Triângulo ➡️](./03-triangulo.md)

</div>
