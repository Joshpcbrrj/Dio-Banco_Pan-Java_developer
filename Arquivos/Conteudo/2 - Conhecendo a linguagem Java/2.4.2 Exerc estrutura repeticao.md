# Exercícios de estruturas de repetição

1 - Faça um programa que leia conjuntos de dois valores, o primeiro representando o nome do aluno e o segundo representando sua idade. Para o programa inserindo o valor 0 no campo nome.

<details>
<summary>Código</summary>

```java
package loops;

import java.util.Scanner;

public class Ex1_Nome_e_idade {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true){
            //Pedindo dados
            System.out.println("Digite o nome do aluno: ");
            String nome_aluno = input.next();

            //Condição para a saida
            if(nome_aluno.equals("0")) break;
            System.out.println("Digite a idade do aluno: ");
            int idade_aluno = input.nextInt();
            System.out.println("Aluno: " + nome_aluno + " idade: " + idade_aluno );
        }

				System.out.println("Sai do laço de repetição");
    }
}
```

</details>

<br>

2 - Faça um programa que peça uma nota, entre zero e 10. Mostre uma mensagem caso o valor seja inválido e continue pedindo até que o usuário informe um valor válido.

<details>
<summary>Código</summary>

```java
package loops;

import java.util.Scanner;

public class Ex2_nota {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero;

        do{
            System.out.println("Digite um número: ");
            numero = scan.nextInt();
            if((numero < 0) || (numero > 10)){
                System.out.println("Erro! Número inválido.");
            }
        }while((numero < 0) || (numero > 10));

        System.out.println("O número digitado foi: " + numero);
    }
}
```

</details>

<br>

3 - Crie um programa que leia 5 números e informe o maior número entre eles e a média dos 5 números digitados.


<details>
<summary>Código sem usar array</summary>

```java
package loops;

import java.util.Scanner;

public class Ex3_maior_numero_e_media {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero, soma_numeros = 0, maior_numero = 0;

        for(int i = 0; i < 5; i++){
            System.out.println("Digite o número " + (i+1) + " de 5:" );
            numero = scan.nextInt();

            if(numero > maior_numero){
                maior_numero = numero;
            }

            soma_numeros += numero;
        }

        //Mostrando dados na tela
        System.out.println("O maior número entre os 5 digitados é: " + maior_numero);
        System.out.println("A média dos 5 números digitados é: " + (soma_numeros / 5));
    }
}
```    

</details>


<details>
<summary>Código usando arrays</summary>

```java
package loops;

import java.util.Arrays;
import java.util.Scanner;

public class Ex3_maior_numero_e_media {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero[] = new int[5];

        //Pedindo os 5 números
        for(int i = 0; i < 5; i++){
            System.out.println("Digite o número " + (i + 1) + " de 5:");
            numero[i] = scan.nextInt();
        }

        //Definindo o maior número
        int maior_numero = numero[0];
        for(int i = 0; i < numero.length; i++){
            if(numero[i] > maior_numero){
                maior_numero = numero[i];
            }
        }

        //Fazendo a média dos números
        int soma_numeros = 0;
        for(int i = 0; i < numero.length; i++){
            soma_numeros += numero[i];
        }
        int media_numeros = soma_numeros /numero.length;

        //Escrevendo os dados na tela
        String array_em_string = Arrays.toString(numero);
        System.out.println("Os números digitados são: " + array_em_string);
        System.out.println("O maior número do array é: " + maior_numero );
        System.out.println("A média dos 5 números é: " + media_numeros);
    }
}
```

</details>

<br>

4 - Faça um programa que peça n números inteiros, calcule e mostre a quantidade de números pares e a quantidade de impares

<details>
<summary>Código</summary>

```java
package loops;

import java.util.Scanner;

public class Ex4_par_ou_impar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int quant_numeros;
        System.out.println("Digite a quantidade de números que irá digitar: ");
        quant_numeros = scan.nextInt();

        int numero;
        int quant_numeros_pares = 0, quant_numeros_impares =0;
        for (int i = 0; i < quant_numeros; i++){
            System.out.println("Digite o número " + (i + 1) +" de " + quant_numeros +":" );
            numero = scan.nextInt();

            if ((numero % 2) == 0) quant_numeros_pares++;
            else quant_numeros_impares++;
        }

        //Mostrando dados na tela
        System.out.println("A quantidade de números digitados foi: " + quant_numeros);
        System.out.println("Temos: " + quant_numeros_pares + " números pares, e: "
                + quant_numeros_impares + " números impares.");
    }
}
```

</details>

<br>

5 - Desenvolva um gerador de taboada, capaz de gerar a tabuada de qualquer número inteiro entre 1 e 10. O usuário deve informar qual número ele deseja ver a tabuada para que ela seja montada.

<details>
<summary>Código</summary>

```java
package loops;

import java.util.Scanner;

public class Ex_5_Taboada {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero;

        do{
            System.out.println("Digite um número entre 1 a 10 para montar a taboada:");
            numero = scan.nextInt();

            if((numero < 1) || (numero > 10)){
                System.out.println("Erro! O número digitado é inválido");
            }
        }while((numero < 1) || (numero > 10));

        System.out.println("Taboada de: " + numero);

        for (int i = 0; i <= 9; i++){
            System.out.println(numero + " x "+ (i + 1)+ " = " + (numero * (i + 1))) ;
        }
    }
}
```

</details>

<br>

6 - Crie um programa que calcule o fatorial de um número inteiro fornecido pelo usuário.

<details>
<summary>Código</summary>

```java
package loops;

import java.util.Scanner;

public class Ex6_Fatorial {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o número que deseja fazer o fatorial: ");
        int numero = scan.nextInt();
        int fatorial = 1;

        for (int i = numero ; i >= 1; i--){
              fatorial = fatorial * i;
        }

        System.out.println("O fatorial de "+ numero + " é: " + fatorial);
    }
}
```

</details>

<br>

<br>

<br>

[Voltar a Estrutura de repetição e arrays em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.4%20Estruturas%20de%20repeticao%20e%20arrays%20em%20java.md)<br>
[Voltar ao inicio](/README.md)