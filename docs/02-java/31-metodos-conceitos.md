# 2.31 · Métodos: assinatura, construtores e passagem de parâmetros

> **Em uma frase:** a **assinatura** (nome + tipos dos parâmetros) identifica um método; o **construtor** é o método especial que inicializa um objeto; e em Java os parâmetros são sempre passados **por valor**, mesmo quando são objetos.

## 🧩 Anatomia completa

```java
public static final double calcularJuros(double valor, int meses) throws IllegalArgumentException {
    // corpo
}
```

| Parte | Exemplo | Obrigatório? |
| --- | --- | --- |
| Visibilidade | `public`, `protected`, `private` ou nada (pacote) | Não (padrão = pacote) |
| Modificadores | `static`, `final`, `abstract`, `synchronized` | Não |
| Tipo de retorno | `double`, `String`, `void`... | **Sim** |
| Nome | `calcularJuros` | **Sim** |
| Parâmetros | `(double valor, int meses)` | Sim (podem ser vazios: `()`) |
| Exceções declaradas | `throws IllegalArgumentException` | Não |
| Corpo | `{ ... }` | Sim (exceto métodos `abstract`) |

## ✍️ Assinatura

A **assinatura** é a combinação do **nome** com os **tipos e a ordem** dos parâmetros. O tipo de retorno **não** faz parte dela.

```java
void exibir(String texto, int numero) { }   // assinatura: exibir(String, int)
void exibir(int numero, String texto) { }   // outra assinatura: exibir(int, String)
int  exibir(String texto, int numero) { }   // ❌ erro: mesma assinatura do primeiro
```

É pela assinatura que o compilador escolhe qual método chamar quando há vários com o mesmo nome (sobrecarga, próxima aula).

## 🏗️ Construtores

O **construtor** é chamado automaticamente pelo `new` para deixar o objeto pronto para uso.

- Tem **o mesmo nome da classe**.
- **Não tem tipo de retorno**, nem `void`.
- Se você não escrever nenhum, o Java cria um construtor vazio padrão.

```java
public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;     // this.nome = atributo; nome = parâmetro
        this.idade = idade;
    }
}

Pessoa ana = new Pessoa("Ana", 30);
```

### E o "destrutor"?

Java **não tem destrutor**. Quando um objeto não é mais referenciado por ninguém, o **Garbage Collector** (coletor de lixo) libera a memória automaticamente.

> [!WARNING]
> Você pode encontrar o método `finalize()` em materiais antigos. Ele está **descontinuado** (desde o Java 9) e marcado para remoção. Para liberar recursos como arquivos e conexões, use `try-with-resources` (aula [4.14](../04-poo/14-excecoes.md)).

## 📨 Passagem de parâmetros: sempre por valor

Em Java, o método recebe uma **cópia** do que está na variável:

- se for um **primitivo**, recebe uma cópia do valor → mudar dentro do método não afeta fora;
- se for um **objeto**, recebe uma cópia da **referência** → os dois apontam para o mesmo objeto, então **alterar o objeto** afeta fora, mas **trocar a referência** não.

```java
public class ExemploParametros {

    static void alterarNumero(int valor) {
        valor = 10;                       // muda só a cópia
    }

    static void alterarObjeto(Caixa caixa) {
        caixa.valor = 10;                 // altera o objeto original
    }

    static void trocarObjeto(Caixa caixa) {
        caixa = new Caixa(99);            // troca só a cópia da referência
    }

    public static void main(String[] args) {
        int numero = 5;
        alterarNumero(numero);
        System.out.println(numero);       // 5

        Caixa caixa = new Caixa(5);
        alterarObjeto(caixa);
        System.out.println(caixa.valor);  // 10

        trocarObjeto(caixa);
        System.out.println(caixa.valor);  // 10 (não virou 99)
    }
}

class Caixa {
    int valor;
    Caixa(int valor) { this.valor = valor; }
}
```

> [!TIP]
> **Analogia:** passar um objeto é como entregar uma **cópia da chave** da sua casa. Quem recebeu pode mudar os móveis (alterar o objeto), mas se jogar a chave fora e pegar a de outra casa, a sua continua igual.

## ✅ Boas práticas

- Nomes curtos e descritivos, verbos em `camelCase`.
- Métodos **pequenos**, com uma responsabilidade (se passar de ~20 a 30 linhas, desconfie).
- **Poucos parâmetros** (até 3 ou 4); mais que isso, agrupe em um objeto.
- A visibilidade mais restrita possível: comece com `private` e abra só se precisar.

## 📌 Resumindo

- Assinatura = nome + tipos e ordem dos parâmetros (sem o retorno).
- Construtor: mesmo nome da classe, sem retorno, chamado pelo `new`.
- Java não tem destrutor: o Garbage Collector cuida da memória.
- Parâmetros são passados por valor; com objetos, copia-se a referência.

## 🏋️ Mão na massa

**1.** Estes dois métodos podem existir na mesma classe? `int somar(int a, int b)` e `double somar(int x, int y)`.

<details>
<summary>Ver resposta</summary>

**Não.** Os dois têm a mesma assinatura, `somar(int, int)`: os nomes dos parâmetros e o tipo de retorno não contam.

</details>

**2.** O que este código imprime?

```java
static void dobrar(int[] numeros) {
    for (int i = 0; i < numeros.length; i++) numeros[i] *= 2;
}
// no main:
int[] valores = {1, 2, 3};
dobrar(valores);
System.out.println(Arrays.toString(valores));
```

<details>
<summary>Ver resposta</summary>

`[2, 4, 6]`. O array é um objeto: o método recebe uma cópia da referência e altera o **mesmo** array.

</details>

**3.** Crie uma classe `Produto` com construtor que receba nome e preço, e um segundo construtor que receba só o nome e use preço zero.

<details>
<summary>Ver resposta</summary>

```java
public class Produto {
    private final String nome;
    private final double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(String nome) {
        this(nome, 0.0);   // chama o outro construtor
    }
}
```

</details>

---

<div align="center">

[⬅️ 2.30 Mapas](./30-map.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.32 Sobrecarga × sobrescrita ➡️](./32-sobrecarga-e-sobrescrita.md)

</div>
