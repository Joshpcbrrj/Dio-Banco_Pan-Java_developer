# 2.33 · Exercícios de métodos

> **Em uma frase:** cinco exercícios para praticar métodos com e sem retorno, métodos auxiliares privados e sobrecarga.

> [!TIP]
> Antes de abrir a resposta, pense: quais métodos vou criar, o que cada um recebe e o que devolve?

**1.** Crie uma calculadora com métodos para as 4 operações básicas e o resto da divisão, recebendo 2 valores.

<details>
<summary>Ver resposta</summary>

```java
public class Calculadora {
    public static double somar(double a, double b)       { return a + b; }
    public static double subtrair(double a, double b)    { return a - b; }
    public static double multiplicar(double a, double b) { return a * b; }
    public static double resto(double a, double b)       { return a % b; }

    public static double dividir(double a, double b) {
        if (b == 0) throw new IllegalArgumentException("Divisão por zero");
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println("3 + 6 = " + somar(3, 6));
        System.out.println("10 - 2 = " + subtrair(10, 2));
        System.out.println("5 x 5 = " + multiplicar(5, 5));
        System.out.println("8 / 2 = " + dividir(8, 2));
        System.out.println("10 % 3 = " + resto(10, 3));
    }
}
```

Em vez de imprimir dentro de cada método (como na versão original do curso), os métodos **retornam** o resultado. Assim eles podem ser reaproveitados em outros cálculos.

</details>

**2.** A partir da hora do dia (0 a 23), mostre "Bom dia" (5h às 11h), "Boa tarde" (12h às 17h) ou "Boa noite" (demais horários).

<details>
<summary>Ver resposta</summary>

```java
public class Saudacao {

    public static String obterSaudacao(int hora) {
        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException("Hora inválida: " + hora);
        }
        if (hora >= 5 && hora <= 11) return "Bom dia!";
        if (hora >= 12 && hora <= 17) return "Boa tarde!";
        return "Boa noite!";
    }

    public static void main(String[] args) {
        System.out.println(obterSaudacao(6));    // Bom dia!
        System.out.println(obterSaudacao(13));   // Boa tarde!
        System.out.println(obterSaudacao(22));   // Boa noite!
    }
}
```

Na versão original havia um bug: a condição `hora >= 12` capturava também 22h, e o "boa noite" nunca era exibido. Definir faixas fechadas resolve.

</details>

**3.** Simule um empréstimo: a taxa de juros depende da quantidade de parcelas. Mostre o total, o valor da parcela e os juros. Valores aceitos: de R$ 10 a R$ 100.000, em 1 a 20 parcelas.

<details>
<summary>Ver resposta</summary>

```java
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Emprestimo {

    // Método auxiliar privado: quem usa a classe não precisa conhecê-lo
    private static BigDecimal taxaPara(int parcelas) {
        if (parcelas == 1)  return new BigDecimal("0.010");
        if (parcelas == 2)  return new BigDecimal("0.020");
        if (parcelas == 3)  return new BigDecimal("0.025");
        if (parcelas == 4)  return new BigDecimal("0.030");
        if (parcelas == 5)  return new BigDecimal("0.035");
        if (parcelas <= 9)  return new BigDecimal("0.039");
        if (parcelas == 10) return new BigDecimal("0.100");
        if (parcelas <= 19) return new BigDecimal("0.110");
        return new BigDecimal("0.150");                  // 20 parcelas
    }

    private static void validar(BigDecimal valor, int parcelas) {
        if (valor.compareTo(new BigDecimal("10")) < 0 || valor.compareTo(new BigDecimal("100000")) > 0) {
            throw new IllegalArgumentException("Valor deve estar entre R$ 10 e R$ 100.000");
        }
        if (parcelas < 1 || parcelas > 20) {
            throw new IllegalArgumentException("Parcelas devem estar entre 1 e 20");
        }
    }

    public static void simular(BigDecimal valor, int parcelas) {
        validar(valor, parcelas);
        BigDecimal juros = valor.multiply(taxaPara(parcelas)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = valor.add(juros);
        BigDecimal parcela = total.divide(BigDecimal.valueOf(parcelas), 2, RoundingMode.HALF_UP);

        System.out.println("Valor solicitado: R$ " + valor);
        System.out.println("Juros:            R$ " + juros);
        System.out.println("Total:            R$ " + total);
        System.out.println(parcelas + " parcelas de   R$ " + parcela);
    }

    public static void main(String[] args) {
        simular(new BigDecimal("1000"), 20);
    }
}
```

Melhorias em relação à versão original: usa `BigDecimal` (dinheiro!), arredonda para centavos e corrige uma inconsistência (o texto prometia 15% para 20 parcelas, mas o cálculo usava 20%).

</details>

**4.** Calcule a área de 3 quadriláteros notáveis (quadrado, retângulo e trapézio) usando **sobrecarga**.

<details>
<summary>Ver resposta</summary>

```java
public class Quadrilatero {
    public static double area(double lado) {                    // quadrado
        return lado * lado;
    }
    public static double area(double base, double altura) {     // retângulo
        return base * altura;
    }
    public static double area(double baseMaior, double baseMenor, double altura) {  // trapézio
        return (baseMaior + baseMenor) * altura / 2;
    }

    public static void main(String[] args) {
        System.out.println("Quadrado:  " + area(7));          // 49.0
        System.out.println("Retângulo: " + area(5, 8));       // 40.0
        System.out.println("Trapézio:  " + area(9, 7, 12));   // 96.0
    }
}
```

O compilador escolhe a versão certa pela **quantidade** de argumentos.

</details>

**5.** Por que a versão do exercício 4 que **retorna** valores é melhor que uma que só imprime a área?

<details>
<summary>Ver resposta</summary>

Porque o resultado pode ser **reaproveitado**: somar áreas, comparar, salvar em uma lista, mostrar em uma tela web ou testar automaticamente. Um método que só imprime "prende" o resultado no console. Separar **cálculo** de **exibição** é uma das boas práticas mais importantes.

</details>

---

<div align="center">

[⬅️ 2.32 Retorno de métodos](./32-retorno-de-metodos.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [3.1 Fábrica de carros ➡️](../03-desafios-basicos/01-fabrica-de-carros.md)

</div>
