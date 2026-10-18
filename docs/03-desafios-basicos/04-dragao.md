# 3.4 · Desafio: Dragão (Mais de 8000!)

> **Em uma frase:** ler vários níveis de energia e, para cada um, dizer se é maior que 8000 ("Mais de 8000!") ou não ("Inseto!").

## 📋 Enunciado

Daenerys tem um rastreador que mede o nível de energia de qualquer ser vivo. Seres com nível **menor ou igual a 8000** são considerados insetos; acima disso, ela grita "Mais de 8000!".

### Entrada

A primeira linha tem um inteiro `C`, o número de casos de teste. Depois vêm `C` linhas, cada uma com um inteiro `N` (100 ≤ N ≤ 100000).

### Saída

Para cada `N`, imprima `Mais de 8000!` ou `Inseto!`.

| Entrada | Saída |
| --- | --- |
| `3`<br>`8001`<br>`100`<br>`200` | `Mais de 8000!`<br>`Inseto!`<br>`Inseto!` |

## 🧠 Como pensar

1. Ler `C`.
2. Repetir `C` vezes: ler `N` e testar `N > 8000`.

> [!TIP]
> Em plataformas de desafio, a saída é comparada **caractere por caractere**. Maiúsculas, acentos, espaços e pontuação precisam ser idênticos ao enunciado.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int casos = entrada.nextInt();

        for (int i = 0; i < casos; i++) {
            int energia = entrada.nextInt();
            System.out.println(energia > 8000 ? "Mais de 8000!" : "Inseto!");
        }
    }
}
```

</details>

## 🏋️ Mão na massa

**1.** Qual a saída para a energia exatamente `8000`?

<details>
<summary>Ver resposta</summary>

`Inseto!`, porque a regra é "menor **ou igual** a 8000".

</details>

**2.** Acrescente uma terceira categoria: acima de 50000, imprima "Lendário!".

<details>
<summary>Ver resposta</summary>

```java
if (energia > 50000) System.out.println("Lendário!");
else if (energia > 8000) System.out.println("Mais de 8000!");
else System.out.println("Inseto!");
```

A condição mais restritiva vem **primeiro**; caso contrário, o "Mais de 8000!" capturaria também os lendários.

</details>

---

<div align="center">

[⬅️ 3.3 Soma de H com N termos](./03-soma-de-h.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [3.5 Fibonacci ➡️](./05-fibonacci.md)

</div>
