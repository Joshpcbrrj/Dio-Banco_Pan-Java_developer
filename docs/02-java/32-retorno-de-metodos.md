# 2.32 · Retorno de métodos

> **Em uma frase:** o retorno é o valor que o método **devolve** para quem o chamou, declarado pelo tipo antes do nome; métodos `void` não devolvem nada, e o `return` encerra o método na hora.

## ↩️ Com e sem retorno

```java
public class ExemploRetorno {

    public static int somar(int a, int b) {     // devolve um int
        return a + b;
    }

    public static void imprimirMensagem() {     // não devolve nada
        System.out.println("Método sem retorno!");
    }

    public static void main(String[] args) {
        int resultado = somar(2, 3);            // o retorno pode ser guardado...
        System.out.println(somar(10, 5) * 2);   // ...ou usado direto numa expressão
        imprimirMensagem();
    }
}
```

## ⏹️ Quando um método termina

Um método volta para quem o chamou quando:

1. executa **todas** as suas instruções;
2. encontra um **`return`**;
3. **lança uma exceção** não tratada.

O `return`, assim como o `break`, é uma instrução de **interrupção**: o que vem depois dele não é executado.

```java
public static String classificar(int nota) {
    if (nota < 0 || nota > 10) {
        return "inválida";        // sai aqui ("retorno antecipado")
    }
    if (nota >= 6) {
        return "aprovado";
    }
    return "reprovado";
}
```

> [!TIP]
> O **retorno antecipado** (*early return*) para casos de erro evita `if`s aninhados e deixa o código mais fácil de ler.

> [!NOTE]
> Um método que declara um tipo de retorno precisa garantir um `return` em **todos os caminhos**. Se algum `if` puder terminar sem retornar, o código não compila.

### `return` em métodos `void`

Também é permitido, mas **sem valor**, só para sair mais cedo:

```java
public static void saudar(String nome) {
    if (nome == null || nome.isBlank()) {
        return;
    }
    System.out.println("Olá, " + nome);
}
```

### Retornando vários valores

Um método só pode devolver **um** valor. Para devolver vários, retorne um objeto que os agrupe. O `record` (Java 16+) é perfeito para isso:

```java
record MinMax(int minimo, int maximo) { }

public static MinMax encontrarMinMax(int[] numeros) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int n : numeros) {
        min = Math.min(min, n);
        max = Math.max(max, n);
    }
    return new MinMax(min, max);
}
```

## 📌 Resumindo

- O tipo antes do nome define o que o método devolve; `void` = nada.
- `return` encerra o método imediatamente (com ou sem valor).
- Todo caminho de um método não-`void` precisa ter `return`.
- Para devolver vários valores, use um objeto ou `record`.

## 🏋️ Mão na massa

**1.** Este método compila? Por quê?

```java
public static String sinal(int n) {
    if (n > 0) return "positivo";
    else if (n < 0) return "negativo";
}
```

<details>
<summary>Ver resposta</summary>

**Não compila**: se `n` for zero, nenhum `return` é executado. Acrescente um `return "zero";` no final.

</details>

**2.** Escreva um método que receba um array de inteiros e retorne a média.

<details>
<summary>Ver resposta</summary>

```java
public static double media(int[] numeros) {
    if (numeros.length == 0) return 0;
    int soma = 0;
    for (int n : numeros) soma += n;
    return (double) soma / numeros.length;
}
```

</details>

**3.** Reescreva com retorno antecipado:

```java
static String acesso(int idade, boolean temConvite) {
    String resposta;
    if (idade >= 18) {
        if (temConvite) resposta = "liberado";
        else resposta = "sem convite";
    } else resposta = "menor de idade";
    return resposta;
}
```

<details>
<summary>Ver resposta</summary>

```java
static String acesso(int idade, boolean temConvite) {
    if (idade < 18) return "menor de idade";
    if (!temConvite) return "sem convite";
    return "liberado";
}
```

</details>

---

<div align="center">

[⬅️ 2.31 Sobrecarga × sobrescrita](./31-sobrecarga-e-sobrescrita.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.33 Exercícios de métodos ➡️](./33-exercicios-metodos.md)

</div>
