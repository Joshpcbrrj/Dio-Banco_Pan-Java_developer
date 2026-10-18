# 2.16 · Exercícios de estruturas de repetição

> **Em uma frase:** seis exercícios clássicos para praticar `while`, `do-while` e `for`, cada um com uma solução comentada.

📂 Os códigos que fiz durante o curso estão em [`projetos/02-loops-e-arrays`](../../projetos/02-loops-e-arrays/). Os exercícios foram baseados no repositório [cami-la/loops-e-arrays](https://github.com/cami-la/loops-e-arrays). Para mais prática, veja as listas da [Python Brasil](https://wiki.python.org.br/EstruturaDeRepeticao) (dá para resolver em Java).

> [!TIP]
> Tente resolver cada exercício **antes** de abrir a resposta. Errar e corrigir é o que fixa o aprendizado.

**1.** Leia pares de dados (nome e idade de alunos) até que o nome digitado seja `0`.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class NomeEIdade {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        while (true) {
            System.out.print("Nome do aluno (0 para sair): ");
            String nome = entrada.next();
            if (nome.equals("0")) break;        // condição de saída

            System.out.print("Idade: ");
            int idade = entrada.nextInt();
            System.out.println(nome + " tem " + idade + " anos.");
        }
        System.out.println("Fim do cadastro.");
    }
}
```

`while (true)` cria um laço "infinito", e o `break` é a única saída. Repare no `.equals("0")`: Strings não se comparam com `==`.

</details>

**2.** Peça uma nota entre 0 e 10. Se for inválida, mostre uma mensagem e peça de novo até receber um valor válido.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class NotaValida {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double nota;

        do {
            System.out.print("Nota (0 a 10): ");
            nota = entrada.nextDouble();
            if (nota < 0 || nota > 10) {
                System.out.println("Nota inválida, tente de novo.");
            }
        } while (nota < 0 || nota > 10);

        System.out.println("Nota registrada: " + nota);
    }
}
```

O `do-while` é perfeito aqui: a pergunta precisa ser feita pelo menos uma vez.

</details>

**3.** Leia 5 números e mostre o maior e a média.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class MaiorEMedia {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int soma = 0;
        int maior = Integer.MIN_VALUE;   // começa com o menor int possível

        for (int i = 1; i <= 5; i++) {
            System.out.print("Número " + i + " de 5: ");
            int numero = entrada.nextInt();
            if (numero > maior) maior = numero;
            soma += numero;
        }

        System.out.println("Maior: " + maior);
        System.out.println("Média: " + soma / 5.0);
    }
}
```

Dois cuidados que corrigem bugs comuns: o maior começa em `Integer.MIN_VALUE` (se começasse em 0, uma lista só com negativos daria errado) e a média divide por `5.0` (dividir por `5` cortaria as casas decimais).

</details>

**4.** Leia `n` números inteiros e conte quantos são pares e quantos são ímpares.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class ParesEImpares {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Quantos números? ");
        int quantidade = entrada.nextInt();

        int pares = 0, impares = 0;
        for (int i = 1; i <= quantidade; i++) {
            System.out.print("Número " + i + ": ");
            int numero = entrada.nextInt();
            if (numero % 2 == 0) pares++;
            else impares++;
        }
        System.out.println("Pares: " + pares + " | Ímpares: " + impares);
    }
}
```

</details>

**5.** Gere a tabuada de um número entre 1 e 10 escolhido pela pessoa.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Tabuada {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int numero;

        do {
            System.out.print("Tabuada de qual número (1 a 10)? ");
            numero = entrada.nextInt();
        } while (numero < 1 || numero > 10);

        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d%n", numero, i, numero * i);
        }
    }
}
```

O `printf` formata a saída: `%d` é substituído por inteiros e `%n` pula linha.

</details>

**6.** Calcule o fatorial de um número (ex.: 5! = 5 × 4 × 3 × 2 × 1 = 120).

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Fatorial {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Número: ");
        int numero = entrada.nextInt();

        long fatorial = 1;                    // long: fatoriais crescem muito rápido
        for (int i = numero; i >= 1; i--) {
            fatorial *= i;
        }
        System.out.println(numero + "! = " + fatorial);
    }
}
```

Com `int`, o resultado "estoura" a partir de 13!. Com `long`, vai até 20!. Para valores maiores, use `BigInteger`.

</details>

---

<div align="center">

[⬅️ 2.15 Estruturas de repetição e arrays](./15-repeticao-e-arrays.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.17 Exercícios de arrays ➡️](./17-exercicios-arrays.md)

</div>
