# 2.7 · Escopo de variáveis

> **Em uma frase:** o **escopo** é a "região" do código onde uma variável existe; ela nasce no bloco `{ }` em que foi declarada e deixa de existir quando esse bloco termina.

## 🏠 A analogia da casa

Pense numa casa: o que está na **sala** (atributo da classe) todo mundo da casa pode usar. O que está dentro do **seu quarto** (variável de um método) só você usa. E o que está **dentro da gaveta** do quarto (variável de um `for` ou `if`) só existe enquanto a gaveta está aberta.

## 🧭 Tipos de escopo

| Escopo | Onde é declarada | Até onde vale |
| --- | --- | --- |
| **Atributo** (de instância ou de classe) | No corpo da classe | Em todos os métodos da classe |
| **Local de método** | Dentro de um método | Só dentro daquele método |
| **De bloco** | Dentro de um `if`, `for`, `while`... | Só dentro daquele bloco |
| **Parâmetro** | Na assinatura do método | Só dentro daquele método |

```java
public class Conta {
    double saldo = 10.0;                       // atributo: visível na classe toda

    public void sacar(double valor) {          // parâmetro: visível só em sacar()
        double novoSaldo = saldo - valor;      // local: só existe em sacar()
        saldo = novoSaldo;
    }

    public void imprimirSaldo() {
        System.out.println(saldo);             // ✅ atributo
        System.out.println(novoSaldo);         // ❌ erro: novoSaldo só existe em sacar()
    }

    public double calcularDivida() {
        double montante = 0.0;
        for (int x = 1; x <= 5; x++) {         // x: escopo do for
            double parcela = 50.0 * x;         // parcela: recriada a cada volta
            montante += parcela;
        }
        // x e parcela não existem mais aqui
        return montante;
    }
}
```

> [!NOTE]
> Variáveis **locais** precisam ser inicializadas antes do uso, senão o código nem compila. Já os **atributos** recebem um valor padrão (`0`, `false` ou `null`).

> [!TIP]
> Declare cada variável no **menor escopo possível**, perto de onde ela é usada. Isso evita confusão e bugs.

### Sombreamento (*shadowing*) e o `this`

Se um parâmetro tem o mesmo nome de um atributo, o parâmetro "esconde" o atributo. Para acessar o atributo, use `this`:

```java
public class Pessoa {
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;   // this.nome = atributo; nome = parâmetro
    }
}
```

## 📌 Resumindo

- Uma variável existe só dentro do bloco onde foi declarada.
- Atributos valem na classe toda; variáveis locais, só no método ou bloco.
- Locais precisam ser inicializadas; atributos têm valor padrão.
- Use `this` para diferenciar atributo de parâmetro com o mesmo nome.

## 🏋️ Mão na massa

**1.** Este código compila? Por quê?

```java
for (int i = 0; i < 3; i++) {
    int dobro = i * 2;
}
System.out.println(dobro);
```

<details>
<summary>Ver resposta</summary>

**Não compila.** `dobro` foi declarada dentro do `for` e deixa de existir quando o bloco termina. Para usá-la depois, declare-a antes do laço.

</details>

**2.** O que este código imprime?

```java
public class Teste {
    static int valor = 10;
    public static void main(String[] args) {
        int valor = 20;
        System.out.println(valor);
        System.out.println(Teste.valor);
    }
}
```

<details>
<summary>Ver resposta</summary>

`20` e depois `10`. A variável local `valor` esconde o atributo de mesmo nome; com `Teste.valor`, acessamos o atributo estático da classe.

</details>

**3.** Por que este código não compila e como corrigir?

```java
int total;
System.out.println(total);
```

<details>
<summary>Ver resposta</summary>

Variáveis locais não têm valor padrão, então `total` precisa ser inicializada antes do uso: `int total = 0;`.

</details>

---

<div align="center">

[⬅️ 2.6 Métodos](./06-metodos-introducao.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.8 Palavras reservadas ➡️](./08-palavras-reservadas.md)

</div>
