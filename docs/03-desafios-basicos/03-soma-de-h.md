# Desafio Soma de H com N Termos

## O que fazer ?
Neste desafio, faça um programa que calcule o valor de H com N termos.

Sendo, H = 1 + 1/2 + 1/3 + 1/4 + ... + 1/N.

### Entrada:
A entrada consiste em um número inteiro positivo.

### Saída:
Na saída será impresso o valor que representa a soma dos termos.
| Entrada | Saída |
| --- | --- |
| 4 | 2 |
| 8 | 3 |
| 3 | 2 |

<br>

<details>
<summary>Código resposta:</summary>

```jsx
package soma_h_com_n_termos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        double h = 0;
        Scanner sc = new Scanner(System.in);
        double n = sc.nextDouble();

        for (int i = 1; i <= n; i++) {
            /*Calcule o valor de H de forma que resulte na soma dos termos:*/
            h += 1.0/i; // soma a fração 1/i a cada iteração
        }
        //Imprima a soma dos termos, considerando um resultado com duas casas decimais:
        System.out.println(Math.round(h)); // imprime o valor arredondado
    }
}
```

</details>

<br>

<br>

<br>

[Voltar ao inicio](/README.md)