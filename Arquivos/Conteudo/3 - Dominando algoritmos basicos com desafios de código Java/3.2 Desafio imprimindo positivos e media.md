# Desafio Imprimindo positivos e média

## O que fazer ?
Leia 6 valores. Em seguida, mostre quantos destes valores digitados foram positivos. Na próxima linha, deve-se mostrar a média de todos os valores positivos digitados, com um dígito após o ponto decimal.

### Entrada
A entrada contém **6 números** que podem ser valores **inteiros ( int )** ou de **ponto flutuante ( float ou double** ). Pelo menos **um** destes números **será positivo**.

### Saída
O **primeiro valor** de saída é a **quantidade de valores positivos**. A próxima linha deve mostrar a **média** dos valores positivos digitados.

| Exemplo de Entrada | Exemplo de Saída |
| --- | --- |
| 7 | 4 valores positivos |
| -5 | 7.4 |
| 6 |  |
| -3.4 |  |
| 4.6 |  |
| 12 |  |

<br>

<details>
<summary>Código resposta:</summary>

```java
//Classe "main"
package Imprimindo_Positivos_e_Média;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int cont = 0;
        double media = 0;
        double x;

        /* Implemente as condições adequadas para obter a quantidade
        //de valores positivos e a média dos valores positivos */
        for (int i = 0; i < 6; i++){
            x = leitor.nextDouble();

            if(x > 0){
                cont ++;
                media += x;
            }
        }
        media = media / cont ;
        System.out.println(cont + " valores positivos");
        System.out.printf("%.1f%n", media);

    }
}
```

</details>

<br>

<br>

<br>

[Voltar ao inicio](/README.md)