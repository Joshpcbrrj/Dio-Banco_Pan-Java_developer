# Exercícios de arrays

1 - Crie um vetor de 6 números inteiros e mostre os 6 números na ordem inversa.
<details>
<summary>Código:</summary>

```java
package Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Ex1_ordem_inversa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vetor_6_num = new int[6];
        int[] vetor_inverso = new int[vetor_6_num.length];

        //preenchendo o array
        for (int i = 0; i < vetor_6_num.length; i++){
            System.out.println("Digite o número " + (i + 1)+ " de 6: " );
            vetor_6_num[i] = scan.nextInt();
        }

        //invertendo a ordem do array
        for (int i = vetor_6_num.length - 1; i >= 0; i--){
            vetor_inverso[(vetor_6_num.length - 1 - i)] = vetor_6_num[i];
        }

        //Mostrando os dados na tela
        System.out.println("Array na ordem normal: " + Arrays.toString(vetor_6_num));
        System.out.println("Array na ordem inversa: " + Arrays.toString(vetor_inverso));
    }
}
```

</details>

<br>

2 - Faça um programa que leia um vetor de 6 caracteres, e diga quantas consoantes foram lidas e imprima somente as consoantes.
<details>
<summary>Código:</summary>

```java
package Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Ex2_consoantes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] vetor_caracteres = new char[6];
        String consoantes = "";
        int conta_consoantes = 0;

        //Preenchendo o vetor com caracteres
        for (int i = 0; i < vetor_caracteres.length; i++){
            System.out.print("Digite a letra " + (i + 1) + " de 6: " );
            vetor_caracteres[i] = scan.next().charAt(0);

            if((vetor_caracteres[i] != 'a')&&(vetor_caracteres[i] != 'e')&&(vetor_caracteres[i] != 'i')&&
                    (vetor_caracteres[i] != 'o')&&(vetor_caracteres[i] != 'u')){
                conta_consoantes++;
                consoantes += vetor_caracteres[i] + " ";
            }
        }

        //Mostrando na tela
        System.out.println("\nAs letras digitadas são: " + Arrays.toString(vetor_caracteres));
        System.out.println("A quantidade de consoantes é: " + conta_consoantes);
        System.out.println("As consoantes são: " + consoantes);
    }
}
```

</details>

<br>

3 - Faça um programa que leia 20 números inteiros aleatórios entre 0 e 100, armazene-os em um vetor, e ao final mostre os números. Também mostre os sucessores desses números separadamente.
<details>
<summary>Código:</summary>

```java
package Arrays;

import java.util.Random;

public class ex3_numeros_aleatorios {
    public static void main(String[] args) {
        Random random = new Random();
        int[] vetor_numeros_aleatorios = new int[20];

        for (int i = 0; i < vetor_numeros_aleatorios.length; i++){
            int numero = random.nextInt(0,100);
            vetor_numeros_aleatorios[i] = numero;
        }

        System.out.print("Numeros aleatorios: ");
        for(int numero : vetor_numeros_aleatorios){
            System.out.print(numero + " ");
        }
        System.out.print("\nSucessores dos números: ");
        for(int numero : vetor_numeros_aleatorios){
            System.out.print((numero + 1) + " ");
        }
    }
}
```

</details>

<br>

4 - Crie uma matrix 4 por 4 com números aleatórios entre 0 e 9 e mostre na tela.
<details>
<summary>Código com método “deepToString” </summary>

```java
package Arrays;

import java.util.Arrays;
import java.util.Random;

public class Ex4_array_multidimensional {
    public static void main(String[] args) {
        int[][] matriz = new int[4][4];
        Random random = new Random();

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                matriz[i][j] = random.nextInt(0,9);
            }
        }
        System.out.println("A matriz de números aleatórios é: " + Arrays.deepToString(matriz));
    }
}
```

</details>

<details>
<summary>Código com o foreach </summary>

```java
package Arrays;

import java.util.Arrays;
import java.util.Random;

public class Ex4_array_multidimensional {
    public static void main(String[] args) {
        int[][] matriz = new int[4][4];
        Random random = new Random();

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = random.nextInt(0,9);
            }
        }
        System.out.println("Matriz de números aleatórios entre 0 e 9:");
        for(int[] linha : matriz){
            for (int coluna: linha){
                System.out.print(coluna + " ");
            }
            System.out.println("");
        }
    }
}
```

</details>
<br>

<br>

<br>

[Voltar a Estrutura de repetição e arrays em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.4%20Estruturas%20de%20repeticao%20e%20arrays%20em%20java.md)<br>
[Voltar ao inicio](/README.md)