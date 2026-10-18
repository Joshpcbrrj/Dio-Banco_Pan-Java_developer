# 3.1 · Desafio: Fábrica de carros

> **Em uma frase:** calcular o preço final de um carro somando o custo de fábrica com as porcentagens do distribuidor e dos impostos (ambas aplicadas sobre o custo de fábrica).

## 📋 Enunciado

O custo de um carro novo ao consumidor é a soma do **custo de fábrica** com a **porcentagem do distribuidor** e dos **impostos**, as duas aplicadas sobre o custo de fábrica. Escreva um programa que leia esses três valores inteiros e imprima o preço final.

### Entrada

Três inteiros: custo de fábrica, porcentagem do distribuidor e porcentagem de impostos.

### Saída

O preço final do carro.

| Entrada | Saída | Conta |
| --- | --- | --- |
| `20000` `28` `45` | `34600` | 20000 + 28% (5600) + 45% (9000) |
| `30000` `10` `10` | `36000` | 30000 + 10% (3000) + 10% (3000) |

## 🧠 Como pensar

1. Ler os três valores com o `Scanner`.
2. Calcular cada porcentagem: `custo * porcentagem / 100`.
3. Somar tudo e imprimir.

> [!TIP]
> Multiplique **antes** de dividir: `custo * 28 / 100`. Se dividir primeiro (`28 / 100`), a divisão entre inteiros dá `0` e o resultado sai errado.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int custoFabrica = entrada.nextInt();
        int percentualDistribuidor = entrada.nextInt();
        int percentualImpostos = entrada.nextInt();

        int valorDistribuidor = custoFabrica * percentualDistribuidor / 100;
        int valorImpostos = custoFabrica * percentualImpostos / 100;
        int precoFinal = custoFabrica + valorDistribuidor + valorImpostos;

        System.out.println(precoFinal);
    }
}
```

</details>

## 🏋️ Mão na massa

**1.** Qual o preço final para custo `50000`, distribuidor `12` e impostos `30`?

<details>
<summary>Ver resposta</summary>

50000 + 6000 (12%) + 15000 (30%) = **71000**.

</details>

**2.** Reescreva o cálculo em uma única expressão.

<details>
<summary>Ver resposta</summary>

```java
int precoFinal = custoFabrica * (100 + percentualDistribuidor + percentualImpostos) / 100;
```

</details>

---

<div align="center">

[⬅️ 2.34 Exercícios de métodos](../02-java/34-exercicios-metodos.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [3.2 Positivos e média ➡️](./02-positivos-e-media.md)

</div>
