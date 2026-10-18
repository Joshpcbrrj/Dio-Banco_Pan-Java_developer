# 2.31 · Sobrecarga × sobrescrita

> **Em uma frase:** **sobrecarga** é ter vários métodos com o **mesmo nome e parâmetros diferentes** na mesma classe; **sobrescrita** é uma classe filha **reescrever** um método herdado da classe pai, com a mesma assinatura.

## 🔁 Sobrecarga (*overloading*)

O mesmo nome, com **listas de parâmetros diferentes** (quantidade, tipo ou ordem). O compilador escolhe qual chamar pelos argumentos.

```java
public class Calculadora {

    public static int somar(int a, int b) {
        return a + b;
    }

    public static double somar(double a, double b) {
        return a + b;
    }

    public static int somar(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println(somar(2, 3));        // usa somar(int, int) → 5
        System.out.println(somar(2.5, 3.7));    // usa somar(double, double) → 6.2
        System.out.println(somar(1, 2, 3));     // usa somar(int, int, int) → 6
    }
}
```

> [!NOTE]
> Você usa sobrecarga o tempo todo: `System.out.println` tem versões para `int`, `double`, `String`, `boolean`, `Object`... Construtores também podem ser sobrecarregados.

## ✍️ Sobrescrita (*overriding*)

A classe filha fornece **sua própria versão** de um método herdado. Regras:

- mesmo **nome**, mesmos **parâmetros** e retorno compatível;
- **não pode reduzir a visibilidade** (um `public` não pode virar `private`);
- use a anotação **`@Override`**: o compilador avisa se você errar a assinatura.

```java
class Animal {
    public void fazerBarulho() {
        System.out.println("O animal faz algum barulho.");
    }
}

class Cachorro extends Animal {
    @Override
    public void fazerBarulho() {
        System.out.println("Au au!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.fazerBarulho();     // O animal faz algum barulho.

        Animal rex = new Cachorro();
        rex.fazerBarulho();        // Au au! (vale a versão do objeto real)
    }
}
```

Esse comportamento, em que a versão executada depende do **objeto real** (e não do tipo da variável), é o **polimorfismo**, aprofundado na aula [4.2](../04-poo/02-heranca-e-polimorfismo.md).

## ⚖️ Comparando

| | Sobrecarga | Sobrescrita |
| --- | --- | --- |
| Onde | Na **mesma** classe | Entre classe **pai e filha** |
| Assinatura | **Diferente** (parâmetros mudam) | **Igual** |
| Decidida em | Tempo de **compilação** | Tempo de **execução** |
| Anotação | — | `@Override` |

## 📌 Resumindo

- Sobrecarga: mesmo nome, parâmetros diferentes, mesma classe.
- Sobrescrita: filha reescreve método do pai com a mesma assinatura; use `@Override`.
- A sobrescrita é a base do polimorfismo.

## 🏋️ Mão na massa

**1.** Crie a classe `Area` com três métodos `calcular` sobrecarregados: quadrado (lado), retângulo (base, altura) e trapézio (base maior, base menor, altura).

<details>
<summary>Ver resposta</summary>

```java
public class Area {
    public static double calcular(double lado) {
        return lado * lado;
    }
    public static double calcular(double base, double altura) {
        return base * altura;
    }
    public static double calcular(double baseMaior, double baseMenor, double altura) {
        return (baseMaior + baseMenor) * altura / 2;
    }
}
```

</details>

**2.** O que acontece se você escrever `@Override public void fazerbarulho()` (com "b" minúsculo) na classe `Cachorro`?

<details>
<summary>Ver resposta</summary>

**Erro de compilação**: o `@Override` avisa que não existe um método `fazerbarulho()` na classe pai para sobrescrever. Sem a anotação, o erro passaria despercebido e você teria criado um método novo.

</details>

**3.** Crie uma classe `Gato` que sobrescreva `fazerBarulho()` e chame a versão da classe pai antes de miar.

<details>
<summary>Ver resposta</summary>

```java
class Gato extends Animal {
    @Override
    public void fazerBarulho() {
        super.fazerBarulho();          // executa a versão do pai
        System.out.println("Miau!");
    }
}
```

</details>

---

<div align="center">

[⬅️ 2.30 Métodos](./30-metodos-conceitos.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.32 Retorno de métodos ➡️](./32-retorno-de-metodos.md)

</div>
