# 2.4 · Tipos de dados, variáveis e constantes

> **Em uma frase:** uma variável é uma "caixa com etiqueta" que guarda um valor de um **tipo** definido; o Java tem 8 tipos primitivos (como `int`, `double` e `boolean`) e você não pode guardar um tipo na caixa de outro.

## 📦 Variáveis

Toda variável tem **tipo + nome + (opcionalmente) valor**:

```java
int idade;                    // declarada, ainda sem valor
int anoFabricacao = 2024;     // declarada e inicializada
double salario = 2500.00;     // atenção: 2500.00, e não 2.500 (que seria 2,5!)
idade = 30;                   // atribuindo depois
```

> [!WARNING]
> No Java o separador decimal é o **ponto**. `2.500` significa "dois vírgula cinco", não "dois mil e quinhentos". Para facilitar a leitura de números grandes, use underline: `int populacao = 214_000_000;`

O Java é **fortemente tipado**: depois de declarar `int idade`, você não pode guardar um texto nela. O compilador avisa o erro antes de rodar.

### `var`: inferência de tipo (Java 10+)

Dentro de métodos, você pode deixar o compilador descobrir o tipo pelo valor:

```java
var nome = "Ana";       // o compilador entende que é String
var total = 10;         // int
```

O tipo continua fixo; o `var` só evita repeti-lo. Use quando o tipo for óbvio pelo lado direito.

## 🔢 Os 8 tipos primitivos

| Tipo | Tamanho | Guarda | Faixa / exemplo |
| --- | --- | --- | --- |
| `byte` | 8 bits | inteiro | -128 a 127 |
| `short` | 16 bits | inteiro | -32.768 a 32.767 |
| `int` | 32 bits | inteiro | -2.147.483.648 a 2.147.483.647 |
| `long` | 64 bits | inteiro | cerca de ±9,2 quintilhões · `long cpf = 12345678901L;` |
| `float` | 32 bits | decimal | ~6 a 7 dígitos de precisão · `float pi = 3.14F;` |
| `double` | 64 bits | decimal | ~15 dígitos de precisão · `double peso = 72.5;` |
| `char` | 16 bits | 1 caractere Unicode | `char letra = 'A';` (aspas simples) |
| `boolean` | — | verdadeiro/falso | `boolean ativo = true;` |

- No dia a dia, use **`int`** para inteiros e **`double`** para decimais.
- Use `long` para valores que passam de 2 bilhões (e termine o literal com `L`).
- `float` exige o sufixo `F` e é pouco usado.
- **Texto** não é primitivo: é o tipo `String` (com S maiúsculo e aspas duplas): `String nome = "Ana";`.

> [!CAUTION]
> **Nunca use `double` ou `float` para dinheiro.** Eles guardam valores em binário e geram imprecisões: `0.1 + 0.2` dá `0.30000000000000004`. Para valores monetários, use `BigDecimal`:
>
> ```java
> BigDecimal preco = new BigDecimal("19.90");
> BigDecimal total = preco.multiply(new BigDecimal("3"));   // 59.70 exato
> ```

> [!TIP]
> Dados que **começam com zero** ou que não são usados em contas (CEP, CPF, telefone) devem ser `String`. Um literal numérico que começa com `0` é lido em **base 8** (octal): `int cep = 01310100;` compila, mas vira outro número, e `int cep = 08010000;` nem compila.

### Primitivos × tipos por referência

- **Primitivos** guardam o próprio valor (`int idade = 30`).
- **Referências** (objetos, como `String`, arrays e as classes que você criar) guardam o **endereço** de onde o objeto está na memória.
- Cada primitivo tem uma classe "embrulho" (*wrapper*): `Integer`, `Double`, `Boolean`, `Character`... Elas são necessárias em coleções como `List<Integer>`, e o Java converte automaticamente entre um e outro (*autoboxing*).

## 🔒 Constantes

Uma constante é uma variável que **não pode mudar** depois de receber valor. Use `final` e escreva o nome em maiúsculas:

```java
public class Exemplo {
    public static void main(String[] args) {
        int numero = 5;
        numero = 10;                     // ✅ variável pode mudar

        final double VALOR_DE_PI = 3.14;
        VALOR_DE_PI = 3.15;              // ❌ erro de compilação: é final
    }
}
```

> [!NOTE]
> Constantes de verdade, compartilhadas pela classe toda, costumam ser declaradas como `public static final` fora dos métodos: `public static final int MAIORIDADE = 18;`

## 🔄 Conversões entre tipos (*casting*)

```java
int inteiro = 10;
double decimal = inteiro;          // ✅ automático (int cabe em double)

double preco = 9.99;
int truncado = (int) preco;        // ⚠️ casting explícito: vira 9 (corta a parte decimal)

String texto = "42";
int convertido = Integer.parseInt(texto);   // String → int
String deVolta = String.valueOf(convertido); // int → String
```

## 📌 Resumindo

- Variável = tipo + nome + valor; Java é fortemente tipado.
- 8 primitivos: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`.
- Use `int` e `double` no dia a dia, `String` para textos e **`BigDecimal` para dinheiro**.
- `final` cria constantes; `var` infere o tipo dentro de métodos.

## 🏋️ Mão na massa

**1.** Qual tipo você usaria para: idade, salário, CPF, se a pessoa é doadora de órgãos e a população mundial?

<details>
<summary>Ver resposta</summary>

Idade: `int`. Salário: `BigDecimal` (dinheiro). CPF: `String` (pode começar com zero e não entra em contas). Doadora: `boolean`. População mundial (mais de 8 bilhões): `long`.

</details>

**2.** O que este código imprime e por quê?

```java
double total = 0.1 + 0.2;
System.out.println(total);
int nota = (int) 9.8;
System.out.println(nota);
```

<details>
<summary>Ver resposta</summary>

Imprime `0.30000000000000004` (imprecisão do `double` em binário) e `9` (o casting para `int` **corta** a parte decimal, não arredonda).

</details>

**3.** No `treino-java`, crie variáveis para os dados de um produto (nome, preço, quantidade em estoque e se está ativo) e imprima-as.

<details>
<summary>Ver resposta</summary>

```java
import java.math.BigDecimal;

public class Produto {
    public static void main(String[] args) {
        String nome = "Teclado";
        BigDecimal preco = new BigDecimal("149.90");
        int estoque = 25;
        boolean ativo = true;

        System.out.println(nome + " | R$ " + preco + " | estoque: " + estoque + " | ativo: " + ativo);
    }
}
```

</details>

---

<div align="center">

[⬅️ 2.3 Anatomia de uma classe e convenções de nomes](./03-anatomia-das-classes.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.5 Operadores ➡️](./05-operadores.md)

</div>
