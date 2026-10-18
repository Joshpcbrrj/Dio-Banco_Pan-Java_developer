# 2.5 · Operadores

> **Em uma frase:** operadores são os símbolos que fazem contas (`+ - * / %`), atribuem valores (`=`, `+=`), comparam (`==`, `>`), combinam condições (`&&`, `||`) e escolhem entre dois valores (`? :`).

Todo programa recebe dados, processa e mostra um resultado. Os operadores são as ferramentas do "processa".

## 📝 Atribuição

O `=` **guarda** um valor numa variável (não confunda com o `==`, que compara):

```java
String nome = "Josué";
int idade = 22;
double peso = 79.5;
char inicial = 'J';
boolean doadorDeOrgaos = true;
LocalDate nascimento = LocalDate.of(2002, 5, 10);   // import java.time.LocalDate
```

### Atribuição composta

| Atalho | Equivale a | Exemplo (`x = 10`) |
| --- | --- | --- |
| `x += 5` | `x = x + 5` | 15 |
| `x -= 5` | `x = x - 5` | 5 |
| `x *= 3` | `x = x * 3` | 30 |
| `x /= 4` | `x = x / 4` | 2 (divisão inteira!) |
| `x %= 3` | `x = x % 3` | 1 |

## ➗ Aritméticos

| Operador | Operação | Exemplo | Resultado |
| --- | --- | --- | --- |
| `+` | soma | `10 + 3` | 13 |
| `-` | subtração | `10 - 3` | 7 |
| `*` | multiplicação | `10 * 3` | 30 |
| `/` | divisão | `10 / 3` | **3** (inteiros!) |
| `%` | resto da divisão (módulo) | `10 % 3` | 1 |

> [!WARNING]
> **Divisão entre inteiros descarta a parte decimal**: `10 / 3` dá `3`, e `5 / 2` dá `2`. Se quiser o resultado com casas decimais, pelo menos um dos lados precisa ser decimal: `10 / 3.0` ou `(double) 10 / 3` dá `3.333...`.

> [!TIP]
> O `%` é ótimo para descobrir se um número é **par**: `numero % 2 == 0`.

O `+` com textos **concatena** (junta):

```java
String nomeCompleto = "Ana" + " " + "Silva";     // "Ana Silva"
System.out.println("Idade: " + 30);              // "Idade: 30"
System.out.println("1 + 1 = " + 1 + 1);          // "1 + 1 = 11"  ⚠️
System.out.println("1 + 1 = " + (1 + 1));        // "1 + 1 = 2"   ✅
```

## ➕ Unários

| Operador | O que faz |
| --- | --- |
| `-x` | inverte o sinal |
| `++x` / `x++` | soma 1 |
| `--x` / `x--` | subtrai 1 |
| `!` | inverte um booleano (`!true` é `false`) |

A posição do `++` importa quando ele está **dentro de outra expressão**:

```java
int numero = 5;
System.out.println(numero++);   // imprime 5 e DEPOIS soma (numero vira 6)
System.out.println(++numero);   // soma ANTES (numero vira 7) e imprime 7
```

- **Pós-fixado** (`x++`): usa o valor atual e depois incrementa.
- **Pré-fixado** (`++x`): incrementa e depois usa o novo valor.

## ⚖️ Relacionais (comparação)

Sempre retornam `true` ou `false`:

| Operador | Significa |
| --- | --- |
| `==` | igual a |
| `!=` | diferente de |
| `>` / `>=` | maior / maior ou igual |
| `<` / `<=` | menor / menor ou igual |

> [!CAUTION]
> Para comparar **textos** (`String`) use `.equals()`, nunca `==`. O `==` compara se são o **mesmo objeto** na memória, não se têm o mesmo conteúdo: `nome.equals("Ana")`.

## 🔗 Lógicos

| Operador | Nome | Verdadeiro quando... |
| --- | --- | --- |
| `&&` | E | **as duas** condições são verdadeiras |
| <code>&#124;&#124;</code> | OU | **pelo menos uma** é verdadeira |
| `!` | NÃO | inverte o resultado |

```java
int idade = 20;
boolean temIngresso = true;
if (idade >= 18 && temIngresso) {
    System.out.println("Pode entrar");
}
```

Mais detalhes e tabelas-verdade na aula [2.12](./12-operadores-relacionais-e-logicos.md).

## ❓ Ternário

Um `if/else` em uma linha, que **devolve** um valor: `condição ? valorSeVerdadeiro : valorSeFalso`.

```java
int a = 5, b = 6;
String resultado = (a == b) ? "iguais" : "diferentes";
System.out.println(resultado);   // diferentes

int nota = 7;
String situacao = nota >= 6 ? "Aprovado" : "Reprovado";
```

> [!TIP]
> Use o ternário para escolhas simples. Se ficar difícil de ler, prefira o `if/else`.

## 📌 Resumindo

- `=` atribui; `+=`, `-=`, `*=`, `/=` são atalhos.
- Aritméticos: `+ - * / %`; divisão entre inteiros descarta decimais.
- `++`/`--` antes ou depois da variável muda o valor usado na expressão.
- Relacionais retornam booleanos; para `String`, use `.equals()`.
- Lógicos: `&&`, `||`, `!`; ternário: `cond ? a : b`.

## 🏋️ Mão na massa

**1.** Sem rodar, diga o resultado de cada expressão: `17 / 5`, `17 % 5`, `17 / 5.0`, `"5" + 3 + 2`, `5 + 3 + "2"`.

<details>
<summary>Ver resposta</summary>

`3` (divisão inteira), `2` (resto), `3.4` (há um decimal), `"532"` (a partir da String, tudo é concatenado) e `"82"` (primeiro soma 5 + 3 = 8, depois concatena com "2").

</details>

**2.** O que este código imprime?

```java
int x = 10;
int y = x++ + ++x;
System.out.println(x + " " + y);
```

<details>
<summary>Ver resposta</summary>

`12 22`. O `x++` usa 10 e depois x vira 11; o `++x` faz x virar 12 e usa 12. Então `y = 10 + 12 = 22`, e x termina valendo 12.

</details>

**3.** Escreva um programa que, dado um número inteiro, imprima "par" ou "ímpar" usando o operador ternário.

<details>
<summary>Ver resposta</summary>

```java
public class ParOuImpar {
    public static void main(String[] args) {
        int numero = 7;
        String tipo = numero % 2 == 0 ? "par" : "ímpar";
        System.out.println(numero + " é " + tipo);
    }
}
```

</details>

---

<div align="center">

[⬅️ 2.4 Tipos de dados, variáveis e constantes](./04-tipos-e-variaveis.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.6 Métodos ➡️](./06-metodos-introducao.md)

</div>
