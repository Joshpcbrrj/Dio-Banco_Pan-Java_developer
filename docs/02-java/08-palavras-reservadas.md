# 2.8 · Palavras reservadas

> **Em uma frase:** palavras reservadas são termos que o Java já usa para alguma coisa (`class`, `int`, `if`, `return`...), por isso **não podem** ser usadas como nomes de variáveis, métodos ou classes.

O Java tem **51 palavras reservadas** (todas em minúsculas). A IDE costuma pintá-las de outra cor, o que ajuda a reconhecê-las.

## 🗂️ Por categoria

| Categoria | Palavras |
| --- | --- |
| **Pacotes** | `package`, `import` |
| **Visibilidade** | `public`, `protected`, `private` |
| **Tipos primitivos** | `boolean`, `byte`, `char`, `short`, `int`, `long`, `float`, `double`, `void` |
| **Classes, interfaces e modificadores** | `class`, `interface`, `enum`, `extends`, `implements`, `abstract`, `final`, `static`, `new`, `native`, `strictfp`, `synchronized`, `transient`, `volatile` |
| **Controle de fluxo** | `if`, `else`, `switch`, `case`, `default`, `for`, `while`, `do`, `break`, `continue`, `return`, `instanceof` |
| **Exceções** | `try`, `catch`, `finally`, `throw`, `throws`, `assert` |
| **Referências** | `this`, `super` |
| **Reservadas, mas sem uso** | `const`, `goto` |
| **Módulos e outras** | `_` (underline sozinho, desde o Java 9) |

### O que cada uma faz (as mais importantes)

| Palavra | Uso |
| --- | --- |
| `package` / `import` | Diz a qual pacote a classe pertence / traz classes de outros pacotes. |
| `class` / `interface` / `enum` | Declara uma classe / uma interface / uma enumeração de valores fixos. |
| `extends` / `implements` | Herda de uma classe / implementa uma interface. |
| `abstract` | Classe que não pode ser instanciada, ou método sem corpo que as filhas devem implementar. |
| `final` | Impede reatribuir uma variável, sobrescrever um método ou herdar de uma classe. |
| `static` | Pertence à **classe**, não a cada objeto. |
| `new` | Cria um objeto chamando o construtor. |
| `this` / `super` | Referência ao objeto atual / à classe pai. |
| `return` | Sai do método (e devolve um valor, se houver). |
| `break` / `continue` | Sai do laço / pula para a próxima volta. |
| `instanceof` | Testa se um objeto é de determinado tipo. |
| `try` / `catch` / `finally` | Trata exceções. |
| `throw` / `throws` | Lança uma exceção / avisa que o método pode lançar uma. |
| `synchronized` | Permite só uma thread por vez naquele trecho. |
| `transient` | Campo que não é serializado. |
| `volatile` | Variável sempre lida da memória principal (threads). |
| `native` | Método implementado em outra linguagem (como C). |

> [!NOTE]
> `true`, `false` e `null` **não** são palavras reservadas: são **literais**. Mesmo assim, não podem ser usados como nomes.

### Palavras "contextuais" (Java moderno)

Algumas palavras novas só têm significado especial em certos lugares, para não quebrar código antigo que as usava como nome: `var`, `record`, `yield`, `sealed`, `non-sealed`, `permits`, `when`, além de `module`, `requires`, `exports` e outras usadas em `module-info.java`. Evite usá-las como nome mesmo assim. Você verá `var` e `record` no [módulo extra](../08-extras/01-java-moderno.md).

## 🔀 Palavras "opostas"

| | | Diferença |
| --- | --- | --- |
| `package` | `import` | `package` diz **onde a classe está**; `import` diz **de onde trazer** outra classe. |
| `extends` | `implements` | Uma classe **estende** outra classe; uma classe **implementa** uma interface. |
| `final` | `abstract` | `final` impede alterações/herança; `abstract` **exige** que as filhas completem o comportamento. |
| `throws` | `throw` | `throws` (na assinatura) **avisa** que pode lançar; `throw` (no corpo) **lança** de fato. |

## 📌 Resumindo

- São 51 palavras reservadas, todas minúsculas; não podem virar nomes.
- `true`, `false` e `null` são literais, mas também não podem ser nomes.
- Palavras como `var` e `record` são contextuais (Java moderno).

## 🏋️ Mão na massa

**1.** Quais destes nomes podem ser usados em uma variável: `classe`, `class`, `Int`, `null`, `retorno`?

<details>
<summary>Ver resposta</summary>

Podem: `classe`, `Int` (o Java diferencia maiúsculas) e `retorno`. Não podem: `class` (reservada) e `null` (literal).

</details>

**2.** Qual a diferença entre `throw` e `throws`?

<details>
<summary>Ver resposta</summary>

`throws` aparece na **assinatura** do método e avisa que ele pode lançar uma exceção. `throw` aparece no **corpo** e lança uma exceção de verdade: `throw new IllegalArgumentException("...")`.

</details>

**3.** Para que serve a palavra `static` no `public static void main`?

<details>
<summary>Ver resposta</summary>

Faz o `main` pertencer à **classe**, e não a um objeto. Assim a JVM consegue chamá-lo sem precisar criar uma instância da classe antes.

</details>

---

<div align="center">

[⬅️ 2.7 Escopo de variáveis](./07-escopo.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.9 Comentários, Javadoc e a documentação oficial ➡️](./09-javadoc.md)

</div>
