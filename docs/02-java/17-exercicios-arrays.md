# 2.17 · Exercícios de arrays

> **Em uma frase:** quatro exercícios para praticar arrays e matrizes: inverter, filtrar, gerar números aleatórios e percorrer linhas e colunas.

> [!TIP]
> Antes de abrir a resposta, desenhe o array no papel com os índices. Isso ajuda muito a acertar os limites dos laços.

**1.** Leia 6 números inteiros e mostre-os na ordem inversa.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Arrays;
import java.util.Scanner;

public class OrdemInversa {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int[] numeros = new int[6];

        for (int i = 0; i < numeros.length; i++) {
            System.out.print("Número " + (i + 1) + " de 6: ");
            numeros[i] = entrada.nextInt();
        }

        int[] inverso = new int[numeros.length];
        for (int i = 0; i < numeros.length; i++) {
            inverso[i] = numeros[numeros.length - 1 - i];
        }

        System.out.println("Original: " + Arrays.toString(numeros));
        System.out.println("Inverso:  " + Arrays.toString(inverso));
    }
}
```

A posição `i` do inverso recebe a posição `length - 1 - i` do original: a 0 recebe a 5, a 1 recebe a 4, e assim por diante.

</details>

**2.** Leia 6 letras, conte quantas são consoantes e mostre quais são.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Consoantes {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        char[] letras = new char[6];
        StringBuilder consoantes = new StringBuilder();
        int total = 0;

        for (int i = 0; i < letras.length; i++) {
            System.out.print("Letra " + (i + 1) + " de 6: ");
            letras[i] = Character.toLowerCase(entrada.next().charAt(0));

            boolean ehLetra = Character.isLetter(letras[i]);
            boolean ehVogal = "aeiou".indexOf(letras[i]) >= 0;
            if (ehLetra && !ehVogal) {
                total++;
                consoantes.append(letras[i]).append(' ');
            }
        }
        System.out.println("Quantidade de consoantes: " + total);
        System.out.println("Consoantes: " + consoantes);
    }
}
```

Melhorias em relação à versão "ingênua": converte para minúscula (para `A` contar como vogal), confere se é mesmo uma letra (para `7` não contar como consoante) e usa `StringBuilder`, a forma eficiente de montar textos em laços.

</details>

**3.** Gere 20 números aleatórios entre 0 e 100, guarde-os em um array e mostre os números e seus sucessores.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Random;

public class Aleatorios {
    public static void main(String[] args) {
        Random random = new Random();
        int[] numeros = new int[20];

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = random.nextInt(0, 101);   // 0 a 100 (o limite superior é exclusivo)
        }

        System.out.print("Números:    ");
        for (int n : numeros) System.out.print(n + " ");

        System.out.print("\nSucessores: ");
        for (int n : numeros) System.out.print((n + 1) + " ");
    }
}
```

Atenção: em `nextInt(origem, limite)`, o limite **não** é incluído. Para ir até 100, use `101`.

</details>

**4.** Crie uma matriz 4×4 com números aleatórios de 0 a 9 e mostre-a como uma tabela.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Random;

public class Matriz {
    public static void main(String[] args) {
        int[][] matriz = new int[4][4];
        Random random = new Random();

        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                matriz[linha][coluna] = random.nextInt(10);   // 0 a 9
            }
        }

        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
        // Alternativa de uma linha: System.out.println(Arrays.deepToString(matriz));
    }
}
```

</details>

---

<div align="center">

[⬅️ 2.16 Exercícios de estruturas de repetição](./16-exercicios-repeticao.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.18 Estruturas de dados ➡️](./18-estruturas-de-dados.md)

</div>
