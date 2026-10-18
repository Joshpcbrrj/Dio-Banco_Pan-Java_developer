# Desafio Fábrica de carros

## O que fazer ?
O custo de um carro novo ao consumidor é a soma do custo de fábrica com a porcentagem do distribuidor e dos impostos (aplicados ao custo de fábrica). O gerente de uma loja de carros gostaria de um programa para calcular o preço de um carro novo para os clientes. Você receberá o custo de fábrica e as porcentagens referentes ao distribuidor e os impostos e deverá escrever programa para ler esses valores e imprimir o valor final do carro.

### Entrada:
Você recebera três valores inteiros que representam o custo de fábrica, as porcentagens do distribuidor e os impostos.

### Saida:
Como saída, teremos o valor final do preço de um carro novo.

**Exemplo 1**
| Entrada | Saída |
| --- | --- |
| 200002845 | 34600 |

**Exemplo 2**
| Entrada | Saída |
| --- | --- |
| 300001010 | 36000 |

<br>

<details>
<summary>Código resposta:</summary>

```java
//Class "main"
package Fabrica_de_carros;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Criando scanner "scan"
        Scanner scan = new Scanner(System.in);

        //Pedindo o custo de fabrica
        int custoFabrica = scan.nextInt();
        //Pedindo a porcentagem de distribuidor
        int porcentagemDistribuidor = scan.nextInt();
        //Pedindo a porcentagem de impostos
        int PercentualImpostos = scan.nextInt();

        //Calculando custo do consumidor
        int custoConsumidor;
        int Distribuidor;
        int ValorImpostos;
        Distribuidor = (custoFabrica * porcentagemDistribuidor) / 100;
        ValorImpostos = (custoFabrica * PercentualImpostos) / 100;
        custoConsumidor = (custoFabrica + Distribuidor + ValorImpostos);

        //Mostrando na tela
        System.out.println(custoConsumidor);
    }
}
```

</details>

<br>

<br>

<br>

[Voltar ao inicio](/README.md)