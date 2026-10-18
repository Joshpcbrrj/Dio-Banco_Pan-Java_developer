# Desafio quitanda do seu Zé

## Desafio

Seu Zé está vendendo frutas com a seguinte tabela de preços:

#### Exemplo 1

| Fruta | Até 5 Kg | Acima de 5 Kg |
| --- | --- | --- |
| Morango | R$ 2,50 por Kg | R$ 2,20 por Kg |
| Maçã | R$ 1,80 por Kg | R$ 1,50 por Kg |

Se o cliente comprar mais de 8 Kg em frutas ou o valor total da compra ultrapassar R$ 25,00, receberá ainda um desconto de 10% sobre este total. Escreva um algoritmo para ler a quantidade (em Kg) de morangos e a quantidade (em Kg) de maças adquiridas e escreva o valor a ser pago pelo cliente.

<br>

### Entrada

Como entrada, você recebera a quantidade em kg de morangos e a quantidade em kg de maçãs.

### Saída

Será o valor a ser pago pelo cliente, conforme a tabela de preços da quintanda do seu Zé.

**Exemplo 1**

| Entrada | Saída |
| --- | --- |
| 3 | 11.1 |
| 2 |  |

**Exemplo 2**

| Entrada | Saída |
| --- | --- |
| 1 | 4.3 |
| 1 |  |

**Exemplo 3**

| Entrada | Saída |
| --- | --- |
| 5 | 4.3 |
| 15 | 19.35 |

<br>

### Dicas para resolução:
1. Ler as quantidades de morangos e maçãs adquiridas pelo cliente.
2. Calcular o preço a ser pago pelo cliente de acordo com a tabela de preços do seu Zé, considerando se as quantidades compradas ultrapassam ou não 5 Kg e se o valor total da compra ultrapassa ou não R$ 25,00.
3. Se o valor total da compra ultrapassar R$ 25,00 ou a quantidade total de frutas compradas for superior a 8 Kg, aplicar o desconto de 10% sobre o valor total da compra.
4. Imprimir o valor a ser pago pelo cliente.

---

<details>
<summary>Código com resolução:</summary>

```java
// Para ler e escrever dados em Java, aqui na DIO padronizamos da seguinte forma:
// - new Scanner(System.in): cria um leitor de Entradas, com métodos úteis com prefixo "next";
// - System.out.println:.imprime um texto de Saída (Output) e pulando uma linha.

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int morangos = input.nextInt();
        int macas = input.nextInt();
        double precoMorango, precoMaca, precoTotal;

        /*TODO: Implemente as condições necessárias para retornar o preço a ser
        pago pelo cliente, conforme a tabela de preços do seu Zé:*/

        // Calcula o preço do morango conforme a tabela de preços
        if (morangos <= 5) {
            precoMorango = morangos * 2.50;
        } else {
            precoMorango = morangos * 2.20;
        }

        // Calcula o preço da maçã de acordo com a tabela de preços
        if (macas <= 5) {
            precoMaca = macas * 1.80;
        } else {
            precoMaca = macas * 1.50;
        }

        // Calcula o preço total da compra
        precoTotal = precoMorango + precoMaca;

        // Verifica se o desconto deve ser aplicado
        if (precoTotal > 25.00 || morangos + macas > 8) {
            precoTotal *= 0.90; // Aplica o desconto de 10%
        }

        // Imprime o preço a ser pago pelo cliente
        System.out.println(precoTotal);

    }
}
```

</details>

<br>

<br>

<br>

[Voltar ao inicio](/README.md)