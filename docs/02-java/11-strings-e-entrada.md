# 2.11 · Strings e entrada de dados com `Scanner`

> **Em uma frase:** `String` é a classe que representa textos em Java; ela é **imutável** (todo método devolve uma nova `String`), tem dezenas de métodos prontos (`length`, `toUpperCase`, `contains`, `split`...), deve ser comparada com `equals` e é lida do teclado com a classe `Scanner`.

> [!NOTE]
> Esta aula complementa o curso original: textos aparecem em quase todos os exercícios, e os métodos de `String` são cobrados em qualquer entrevista de Java.

## 🧵 O que é uma `String`

Uma `String` é uma **sequência de caracteres** (`char`). Diferente de `int` e `double`, ela **não é um tipo primitivo**: é uma classe (por isso o `S` maiúsculo), e cada texto é um objeto.

```java
String nome = "Ana Lima";      // texto entre aspas duplas
char inicial = 'A';            // um único caractere, entre aspas simples
String vazia = "";             // texto vazio (existe, mas não tem caracteres)
```

Cada caractere tem uma **posição** (índice), começando em **0**, como nos arrays:

| Índice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Caractere | A | n | a | (espaço) | L | i | m | a |

## 🧰 Os métodos mais usados

```java
String texto = "  Java é Demais  ";

texto.length()                 // 17    → quantidade de caracteres (inclui espaços)
texto.strip()                  // "Java é Demais"   → tira espaços das pontas
texto.toUpperCase()            // "  JAVA É DEMAIS  "
texto.toLowerCase()            // "  java é demais  "
texto.isBlank()                // false → está vazia ou só tem espaços?

String s = "Java é Demais";
s.charAt(0)                    // 'J'   → caractere na posição 0
s.indexOf("é")                 // 5     → posição onde aparece (-1 se não achar)
s.contains("Demais")           // true
s.startsWith("Java")           // true
s.endsWith("!")                // false
s.substring(0, 4)              // "Java"   → do índice 0 até o 4 (o 4 fica de fora)
s.substring(7)                 // "Demais" → do índice 7 até o fim
s.replace("Demais", "legal")   // "Java é legal"
"ha".repeat(3)                 // "hahaha"
```

| Método | Para quê |
| --- | --- |
| `length()` | Tamanho |
| `charAt(i)` | Caractere da posição `i` |
| `indexOf(t)` / `lastIndexOf(t)` | Primeira / última posição de `t` (ou `-1`) |
| `substring(ini, fim)` | Pedaço do texto (o `fim` não entra) |
| `contains`, `startsWith`, `endsWith` | Procurar trechos |
| `toUpperCase()`, `toLowerCase()` | Maiúsculas / minúsculas |
| `strip()` (ou o antigo `trim()`) | Remove espaços das pontas |
| `replace(a, b)` | Troca todas as ocorrências de `a` por `b` |
| `split(sep)` | Quebra em um array de `String` |
| `isEmpty()` / `isBlank()` | Vazia / vazia ou só espaços |
| `String.join(sep, partes)` | Junta várias partes com um separador |

### Quebrando e juntando: `split` e `join`

```java
String csv = "Ana;30;Recife";
String[] partes = csv.split(";");          // ["Ana", "30", "Recife"]
String nome = partes[0];                   // "Ana"
int idade = Integer.parseInt(partes[1]);   // 30 (texto → número)

String frase = String.join(" - ", "Java", "Spring", "SQL");   // "Java - Spring - SQL"
```

## 🔒 Strings são imutáveis

Nenhum método altera a `String` original: todos **devolvem uma nova**.

```java
String nome = "ana";
nome.toUpperCase();              // cria "ANA"... e joga fora
System.out.println(nome);        // ana  😮

nome = nome.toUpperCase();       // guarde o resultado!
System.out.println(nome);        // ANA
```

> [!WARNING]
> Esse é um erro clássico de iniciante: chamar `texto.strip()` ou `texto.replace(...)` e esperar que `texto` mude. Sempre **atribua o resultado** a uma variável.

## ⚖️ Comparando textos: `equals`, nunca `==`

```java
String a = "java";
String b = new Scanner("java").next();   // o mesmo texto, lido de outro lugar

System.out.println(a == b);              // false 😮 (são objetos diferentes)
System.out.println(a.equals(b));         // true  ✅ (o conteúdo é igual)
System.out.println("JAVA".equalsIgnoreCase(a));   // true (ignora maiúsculas)
System.out.println("abacaxi".compareTo("banana")); // negativo: "abacaxi" vem antes
```

O `==` compara se as duas variáveis apontam para o **mesmo objeto**; o `equals` compara o **conteúdo** (mais na aula [2.25](./25-equals-e-hashcode.md)). Às vezes `==` "funciona" por coincidência (o Java reaproveita textos literais iguais), o que torna o bug ainda mais traiçoeiro.

> [!TIP]
> Para evitar `NullPointerException`, coloque o texto fixo à esquerda: `"admin".equals(perfil)` funciona mesmo se `perfil` for `null`.

## 🏗️ Montando textos: concatenação, `StringBuilder` e formatação

```java
String nome = "Ana";
int idade = 30;

String a = "Olá, " + nome + "! Você tem " + idade + " anos.";            // concatenação
String b = String.format("Olá, %s! Você tem %d anos.", nome, idade);     // formatação
String c = "Olá, %s! Você tem %d anos.".formatted(nome, idade);          // o mesmo (Java 15+)
System.out.printf("Preço: R$ %.2f%n", 1234.5);                            // Preço: R$ 1234,50 (no Brasil)
```

| Marcador | Para | Exemplo |
| --- | --- | --- |
| `%s` | Texto (qualquer objeto) | `"Ana"` |
| `%d` | Inteiro | `30` |
| `%.2f` | Decimal com 2 casas | `1234,50` |
| `%5d` | Inteiro ocupando 5 posições (completa com espaços à esquerda) | `30` com 3 espaços antes |
| `%n` | Quebra de linha | |

> [!NOTE]
> O `printf` e o `String.format` usam o idioma do computador: no Brasil, `%.2f` sai com **vírgula** (`1234,50`). Para forçar o ponto, use `String.format(Locale.US, "%.2f", valor)`.

### Muitas concatenações? `StringBuilder`

Como a `String` é imutável, concatenar dentro de um laço cria um objeto novo a cada volta. Para montar textos grandes, use o `StringBuilder`, que é **mutável**:

```java
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= 5; i++) {
    sb.append(i).append(" ");          // altera o mesmo objeto
}
String resultado = sb.toString().strip();   // "1 2 3 4 5"
System.out.println(new StringBuilder("Java").reverse());   // avaJ
```

## ⌨️ Lendo do teclado: `Scanner`

```java
import java.util.Scanner;

public class Cadastro {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Nome completo: ");
        String nome = entrada.nextLine();       // lê a linha inteira

        System.out.print("Idade: ");
        int idade = entrada.nextInt();          // lê um inteiro

        System.out.print("Altura: ");
        double altura = entrada.nextDouble();   // lê um decimal

        System.out.printf("%s, %d anos, %.2f m%n", nome, idade, altura);
    }
}
```

| Método | Lê |
| --- | --- |
| `nextLine()` | A linha inteira, até o Enter |
| `next()` | Uma palavra (até o próximo espaço) |
| `nextInt()` / `nextLong()` | Um inteiro |
| `nextDouble()` | Um decimal |
| `nextBoolean()` | `true` ou `false` |
| `hasNextInt()`, `hasNextLine()`... | Verifica se há um valor daquele tipo para ler |

> [!WARNING]
> **Duas armadilhas do `Scanner`:**
>
> 1. **Decimal com vírgula**: num computador em português, `nextDouble()` espera `1,75`, e `1.75` lança `InputMismatchException`. Para aceitar o ponto, crie com `new Scanner(System.in).useLocale(Locale.US)`.
> 2. **O `nextLine()` que "pula"**: depois de um `nextInt()`, o Enter fica guardado, e o `nextLine()` seguinte lê uma linha **vazia**. Chame um `entrada.nextLine();` extra para consumir o Enter, ou leia tudo com `nextLine()` e converta com `Integer.parseInt(...)`.

## 📌 Resumindo

- `String` é uma classe; índices começam em 0; textos entre aspas duplas, `char` entre simples.
- Métodos-chave: `length`, `charAt`, `substring`, `indexOf`, `contains`, `toUpperCase`, `strip`, `replace`, `split`.
- Strings são **imutáveis**: guarde o resultado dos métodos.
- Compare com `equals` (ou `equalsIgnoreCase`), **nunca** com `==`.
- Monte textos com `+`, `String.format`/`printf` ou, em laços, com `StringBuilder`.
- `Scanner` lê do teclado; cuidado com o `Locale` e com o `nextLine()` depois de `nextInt()`.

## 🏋️ Mão na massa

**1.** Dado `String email = "  Ana.Lima@Email.COM ";`, gere o e-mail "limpo": sem espaços nas pontas e em minúsculas. Depois, mostre só o usuário (antes do `@`).

<details>
<summary>Ver resposta</summary>

```java
String limpo = email.strip().toLowerCase();               // "ana.lima@email.com"
String usuario = limpo.substring(0, limpo.indexOf("@"));  // "ana.lima"
```

</details>

**2.** Conte quantas vezes a letra "a" aparece em "banana", sem diferenciar maiúsculas.

<details>
<summary>Ver resposta</summary>

```java
String palavra = "Banana";
int contador = 0;
for (char c : palavra.toLowerCase().toCharArray()) {
    if (c == 'a') contador++;
}
System.out.println(contador);   // 3
```

Para comparar **caracteres** (`char`, primitivo), o `==` está correto.

</details>

**3.** Escreva um método que diga se uma palavra é um **palíndromo** (lida igual de trás para a frente), como "arara".

<details>
<summary>Ver resposta</summary>

```java
static boolean ehPalindromo(String palavra) {
    String normal = palavra.toLowerCase();
    String invertida = new StringBuilder(normal).reverse().toString();
    return normal.equals(invertida);
}

ehPalindromo("Arara");   // true
ehPalindromo("Java");    // false
```

</details>

**4.** O que este código imprime e por quê? `String s = "java"; s.toUpperCase(); System.out.println(s);`

<details>
<summary>Ver resposta</summary>

Imprime `java`. A `String` é imutável: `toUpperCase()` criou um texto novo, mas ele não foi guardado. O certo é `s = s.toUpperCase();`.

</details>

---

<div align="center">

[⬅️ 2.10 Compilando e executando pelo terminal](./10-terminal.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.12 Operadores relacionais e lógicos a fundo ➡️](./12-operadores-relacionais-e-logicos.md)

</div>
