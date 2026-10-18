# 7.4 · Desafio: Contar espaços e vogais

> **Em uma frase:** dada uma frase, contar quantos espaços em branco e quantas vogais ela tem.

## 📋 Enunciado

O professor Jorginho precisa de um programa que, para cada frase entregue pelos alunos, informe quantos espaços em branco e quantas vogais ela contém.

| Entrada | Saída |
| --- | --- |
| `Amo a DIO` | `Espacos em branco: 2 Vogais: 5` |
| `Esse desafio foi facil` | `Espacos em branco: 3 Vogais: 10` |
| `Navegar nas aguas do teu mar` | `Espacos em branco: 5 Vogais: 11` |

## 🧠 Como pensar

Percorra a frase **caractere por caractere**:

- se for um espaço, conte um espaço;
- se, em minúscula, estiver em `"aeiou"`, conte uma vogal.

Assim não é preciso separar em palavras com `split`, e espaços duplicados também são contados corretamente.

<details>
<summary>Ver resposta</summary>

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String frase = entrada.nextLine();

        int espacos = 0, vogais = 0;
        for (char c : frase.toCharArray()) {
            if (c == ' ') {
                espacos++;
            } else if ("aeiou".indexOf(Character.toLowerCase(c)) >= 0) {
                vogais++;
            }
        }

        System.out.println("Espacos em branco: " + espacos + " Vogais: " + vogais);
    }
}
```

A versão do curso contava os espaços com `split(" ").length - 1`, que funciona com espaços simples, mas erra quando há dois espaços seguidos ou espaços no fim da frase.

</details>

## 🏋️ Mão na massa

**1.** Faça uma versão com Stream API.

<details>
<summary>Ver resposta</summary>

```java
long espacos = frase.chars().filter(c -> c == ' ').count();
long vogais = frase.toLowerCase().chars().filter(c -> "aeiou".indexOf(c) >= 0).count();
```

</details>

**2.** Como contar também as vogais acentuadas (á, ê, õ...)?

<details>
<summary>Ver resposta</summary>

Inclua-as na lista: `"aeiouáéíóúâêôãõàü".indexOf(Character.toLowerCase(c)) >= 0`. Outra opção é remover os acentos antes com `java.text.Normalizer.normalize(frase, Normalizer.Form.NFD).replaceAll("\\p{M}", "")`.

</details>

---

<div align="center">

[⬅️ 7.3 Triângulo](./03-triangulo.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [7.5 Taxa de Imposto de Renda ➡️](./05-imposto-de-renda.md)

</div>
