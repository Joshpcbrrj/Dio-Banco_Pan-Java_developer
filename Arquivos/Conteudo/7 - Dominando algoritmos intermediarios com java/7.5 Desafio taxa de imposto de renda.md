# Desafio taxa de Imposto de Renda

## Desafio

Há um país denominado Lolipad, todos os habitantes ficam felizes em 
pagar seus impostos, pois sabem que nele não existem políticos corruptos e os recursos arrecadados são utilizados em benefício da população, sem qualquer desvio. A moeda deste país é o Loli, cujo símbolo é o R$.

Você terá o desafio de ler um valor com duas casas decimais, equivalente ao salário de uma pessoa de Loli. Em seguida, calcule e mostre o valor que esta pessoa deve pagar de Imposto de Renda, segundo a tabela abaixo.

| Renda | Imposto de renda |
| --- | --- |
| De R$ 0.00 a R$ 2000.00 | Isento de imposto |
| De R$ 2000.01 até R$ 3000.00 | 8% de imposto |
| De R$ 3000.01 até R$ 4500.00 | 18% de imposto |
| Acima de R$ 4500.00 | 28% de imposto |

Lembre que, se o salário for `R$ 3002.00`, a taxa que incide é de 8% apenas sobre `R$ 1000.00`, pois a faixa de salário que fica de `R$ 0.00` até `R$ 2000.00` é isenta de Imposto de Renda. No exemplo fornecido (abaixo), a taxa é de 8% sobre `R$ 1000.00` + 18% sobre `R$ 2.00`, o que resulta em `R$ 80.36` no total. O valor deve ser impresso com duas casas decimais.

<br>

### Entrada

A entrada contém apenas um valor de ponto flutuante, com duas casas decimais.

### Saída

Imprima o texto "R$" seguido de um espaço e do valor total devido de 
Imposto de Renda, com duas casas após o ponto. Se o valor de entrada for
menor ou igual a 2000, deverá ser impressa a mensagem "Isento".

| Exemplos de Entrada | Exemplos de Saída |
| --- | --- |
| 3002.00 | R$ 80.36 |

| 1701.12 | Isento |
| --- | --- |

| 4520.00 | R$ 355.60 |
| --- | --- |

<br>

### Dicas para resolução
1. Importação das bibliotecas necessárias para o programa: java.io.IOException e java.util.Scanner
2. Criação de um objeto Scanner para leitura dos dados de entrada do usuário.
3. Leitura do valor da renda fornecido pelo usuário através do objeto Scanner.
4. Declaração da variável imposto sem atribuição inicial.
5. Comentário indicando que a partir daquele ponto é necessário implementar a estrutura condicional para o cálculo do imposto
6. Implementação da estrutura condicional para o cálculo do imposto com base na renda fornecida.
7. Verificação se o imposto é igual a zero e, em caso positivo, imprime "Isento".
8. Em caso negativo, utiliza a função String.format para formatar a saída com duas casas decimais e imprime o valor do imposto com a respectiva mensagem "R$".

#### Em resumo:
Este programa em Java tem como objetivo calcular a taxa de imposto de renda a partir da leitura de um valor de renda fornecido pelo usuário por meio da entrada padrão (comando `Scanner leitor = new Scanner(System.in); double renda = leitor.nextDouble();`). Em seguida, a partir da estrutura condicional `if-else if-else`, é feita a verificação do valor da renda e a consequente aplicação da alíquota de imposto correspondente (conforme as faixas de renda especificadas no código). O resultado do cálculo é armazenado na variável `imposto`.

Por fim, o programa verifica se o valor do imposto é igual a zero e, em caso afirmativo, imprime a mensagem "Isento". Caso contrário, o valor do imposto é impresso formatado em reais, utilizando o método `System.out.println(String.format("R$ %.2f", imposto));`.

---

<details>
<summary>Código com resolução</summary>

```java
// Para ler e escrever dados em Java, aqui na DIO padronizamos da seguinte forma:
// - new Scanner(System.in): cria um leitor de Entradas, com métodos úteis com prefixo "next";
// - System.out.println:.imprime um texto de Saída (Output) e pulando uma linha.

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner leitor = new Scanner(System.in);
        double renda = leitor.nextDouble();
        double imposto;

        /* TODO: Agora utilizando estrutura condicional implemente em seu código as
        condições necessárias para o cálculo da taxa de imposto de renda: */

        //Verifica imposto conforme a faixa de renda
        if (renda <= 2000.0) {
            imposto = 0.0;
        } else if (renda <= 3000.0) {
            imposto = (renda - 2000.0) * 0.08;
        } else if (renda <= 4500.0) {
            imposto = (renda - 3000.0) * 0.18 + 1000.0 * 0.08;
        } else {
            imposto = (renda - 4500.0) * 0.28 + 1500.0 * 0.18 + 1000.0 * 0.08;
        }

        // Verifica se o imposto é igual a zero e imprime "isento" caso seja
        if (imposto == 0.0) {
            System.out.println("Isento");
            //Se o imposto form maior que zero ele impime o valor do imposto
        } else {
            System.out.println(String.format("R$ %.2f", imposto));
        }
    }
}
```

</details>

<br>

<br>

<br>

[Voltar ao inicio](/README.md)