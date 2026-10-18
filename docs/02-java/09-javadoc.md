# 2.9 · Comentários, Javadoc e a documentação oficial

> **Em uma frase:** comentários explicam o código para pessoas; o **Javadoc** é um tipo especial de comentário que vira documentação em HTML, e saber ler a documentação oficial do Java é uma das habilidades mais valiosas de quem programa.

## 💬 Tipos de comentário

```java
// Comentário de uma linha

/* Comentário de
   várias linhas */

/**
 * Comentário de documentação (Javadoc).
 * Começa com /** e é usado acima de classes, métodos e atributos.
 */
```

> [!TIP]
> Bons comentários explicam o **porquê**, não o óbvio. `i++; // soma 1 em i` não ajuda ninguém. `// Usa 365 dias porque a regra do contrato ignora anos bissextos` ajuda muito.

## 📚 Javadoc

O Javadoc lê os comentários `/** ... */` e gera páginas HTML de documentação, iguais às da própria API do Java.

### Principais tags

| Tag | Para quê |
| --- | --- |
| `@author` | Autor |
| `@version` | Versão |
| `@since` | Desde qual versão o recurso existe |
| `@param nome descrição` | Explica um parâmetro |
| `@return descrição` | Explica o retorno |
| `@throws Tipo descrição` | Exceção que o método pode lançar |
| `@see` | Referência para outra classe ou método |
| `{@code texto}` | Formata um trecho como código |

### Exemplo

```java
/**
 * Calculadora com operações básicas.
 *
 * @author Josué B. Almeida
 * @version 1.0
 * @since 1.0
 */
public class Calculadora {

    /**
     * Divide dois números inteiros.
     *
     * @param dividendo número a ser dividido
     * @param divisor   número que divide; não pode ser zero
     * @return o resultado da divisão com casas decimais
     * @throws IllegalArgumentException se o {@code divisor} for zero
     */
    public double dividir(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor não pode ser zero");
        }
        return (double) dividendo / divisor;
    }
}
```

> [!TIP]
> No IntelliJ, digite `/**` acima de um método e aperte **Enter**: ele já cria o esqueleto com `@param` e `@return`. Passe o mouse sobre um método (ou `Ctrl+Q`) para ver o Javadoc dele.

### Gerando o HTML

```bash
# Gera a documentação de todas as classes da pasta src na pasta docs
javadoc -encoding UTF-8 -d docs src/*.java
```

Em projetos Maven, o comando é `mvn javadoc:javadoc`. No IntelliJ: **Tools → Generate JavaDoc**.

## 🔎 Lendo a documentação oficial

A documentação da API do Java fica em **[docs.oracle.com/en/java/javase](https://docs.oracle.com/en/java/javase/)** (escolha a sua versão → *API Documentation*). Lá estão todas as classes, organizadas por módulo e pacote, com a descrição de cada método.

> [!IMPORTANT]
> Saber **ler documentação** (da linguagem e dos frameworks, como o Spring) é o que separa quem copia código de quem entende o que está fazendo. Sempre que conhecer uma classe nova, como `String` ou `ArrayList`, abra a página dela e passeie pelos métodos.

## 📌 Resumindo

- `//` uma linha, `/* */` várias linhas, `/** */` Javadoc.
- Tags principais: `@param`, `@return`, `@throws`, `@author`, `@since`.
- O `javadoc` gera HTML a partir dos comentários.
- Consulte a documentação oficial com frequência.

## 🏋️ Mão na massa

**1.** Escreva o Javadoc de um método `calcularImc(double peso, double altura)` que retorna o IMC.

<details>
<summary>Ver resposta</summary>

```java
/**
 * Calcula o Índice de Massa Corporal.
 *
 * @param peso   peso em quilos
 * @param altura altura em metros
 * @return o IMC (peso dividido pela altura ao quadrado)
 */
public static double calcularImc(double peso, double altura) {
    return peso / (altura * altura);
}
```

</details>

**2.** Na documentação oficial, encontre a classe `String` e descubra o que fazem os métodos `strip()` e `isBlank()`.

<details>
<summary>Ver resposta</summary>

`strip()` remove espaços em branco do início e do fim (considerando espaços Unicode). `isBlank()` retorna `true` se a String estiver vazia ou tiver apenas espaços em branco. Ambos existem desde o Java 11.

</details>

**3.** Qual comentário é mais útil: `// incrementa contador` ou `// Conta tentativas para bloquear após 3 erros de senha`?

<details>
<summary>Ver resposta</summary>

O segundo, porque explica o **motivo** (a regra de negócio). O primeiro só repete o que o código já diz.

</details>

---

<div align="center">

[⬅️ 2.8 Palavras reservadas](./08-palavras-reservadas.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.10 Compilando e executando pelo terminal ➡️](./10-terminal.md)

</div>
