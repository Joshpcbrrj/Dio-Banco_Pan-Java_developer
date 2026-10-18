# 2.11 · Operadores relacionais e lógicos a fundo

> **Em uma frase:** operadores relacionais comparam valores e geram `true` ou `false`; operadores lógicos combinam essas respostas, e as **tabelas-verdade** mostram o resultado de cada combinação.

## ⚖️ Relacionais

| Grupo | Operadores |
| --- | --- |
| Igualdade | `==` (igual), `!=` (diferente) |
| Tamanho | `>`, `>=`, `<`, `<=` |

```java
int i1 = 10, i2 = 20;
char c1 = 'x', c2 = 'y';

System.out.println(i1 == i2);   // false
System.out.println(i1 != i2);   // true
System.out.println(i1 < i2);    // true
System.out.println(c1 < c2);    // true: chars são comparados pelo código Unicode
```

> [!CAUTION]
> Para **objetos** (incluindo `String`, `Integer`, `BigDecimal`), `==` compara se são o **mesmo objeto**, não o conteúdo. Use `.equals()`. Comparar decimais com `==` também é arriscado por causa da imprecisão do `double`.

## 🔗 Lógicos e tabelas-verdade

### E (`&&`): verdadeiro só se **as duas** forem verdadeiras

| A | B | A `&&` B |
| --- | --- | --- |
| V | V | **V** |
| V | F | F |
| F | V | F |
| F | F | F |

### OU (`||`): falso só se **as duas** forem falsas

| A | B | A OU B |
| --- | --- | --- |
| V | V | V |
| V | F | V |
| F | V | V |
| F | F | **F** |

### OU exclusivo (`^`): verdadeiro se forem **diferentes**

| A | B | A `^` B |
| --- | --- | --- |
| V | V | F |
| V | F | **V** |
| F | V | **V** |
| F | F | F |

### NÃO (`!`): inverte

| A | `!`A |
| --- | --- |
| V | F |
| F | V |

## ⚡ Curto-circuito: `&&` × `&` e `||` × `|`

Os operadores **duplos** são de **curto-circuito**: se o primeiro lado já decide o resultado, o segundo **nem é avaliado**.

- Em `A && B`, se `A` for falso, o resultado já é falso → `B` é ignorado.
- Em `A || B`, se `A` for verdadeiro, o resultado já é verdadeiro → `B` é ignorado.

Os **simples** (`&` e `|`) avaliam sempre os dois lados:

```java
int numero1 = 1, numero2 = 1;

if (numero1 == 2 & numero2++ == 2) { }
System.out.println(numero2);   // 2: o numero2++ rodou

numero2 = 1;
if (numero1 == 2 && numero2++ == 2) { }
System.out.println(numero2);   // 1: o lado direito nem foi avaliado
```

O curto-circuito é muito útil para evitar erros:

```java
if (texto != null && texto.length() > 5) { ... }
// se texto for null, o length() nem é chamado (evita NullPointerException)
```

> [!NOTE]
> Com números inteiros, `&`, `|` e `^` viram operadores **bit a bit** (*bitwise*), que operam sobre cada bit. Também existem os de **deslocamento** (`<<`, `>>`, `>>>`) e o complemento (`~`). São usados em casos específicos, como criptografia e otimizações.

## 🧪 Prática

```java
public class OperadoresLogicos {
    public static void main(String[] args) {
        boolean b1 = true, b2 = false;
        int i1 = 10, i2 = 5;
        float f1 = 20F, f2 = 50F;

        System.out.println("and: " + (b1 && b2));   // false
        System.out.println("or:  " + (b1 || b2));   // true
        System.out.println("xor: " + (b1 ^ b2));    // true
        System.out.println("not: " + !b1);          // false

        System.out.println((i1 + i2) < (f2 - f1) && true);   // 15 < 30 && true → true
        System.out.println((i1 > i2) || (f2 < f1));          // true || false → true
    }
}
```

## 📌 Resumindo

- Relacionais (`== != > >= < <=`) produzem booleanos; objetos se comparam com `.equals()`.
- `&&` é verdadeiro só com os dois verdadeiros; `||` é falso só com os dois falsos; `^` é verdadeiro quando são diferentes.
- `&&` e `||` fazem curto-circuito; prefira-os a `&` e `|` em condições.

## 🏋️ Mão na massa

**1.** Qual o resultado de `!(5 > 3) || (2 == 2 && 4 != 4)`?

<details>
<summary>Ver resposta</summary>

`!(true)` = `false`; `(true && false)` = `false`; `false || false` = **`false`**.

</details>

**2.** Por que `if (lista.size() > 0 && lista != null)` pode dar erro, e como corrigir?

<details>
<summary>Ver resposta</summary>

Se `lista` for `null`, o `lista.size()` é avaliado primeiro e lança `NullPointerException`. Basta inverter a ordem para aproveitar o curto-circuito: `if (lista != null && lista.size() > 0)`.

</details>

**3.** Escreva uma condição que seja verdadeira quando um ano for bissexto (divisível por 4 e não por 100, ou divisível por 400).

<details>
<summary>Ver resposta</summary>

```java
boolean bissexto = (ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0;
```

</details>

---

<div align="center">

[⬅️ 2.10 Compilando e executando pelo terminal](./10-terminal.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.12 Controle de fluxo ➡️](./12-controle-de-fluxo.md)

</div>
