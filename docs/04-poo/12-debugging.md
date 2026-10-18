# 4.12 · Debugging: encontrando erros no código

> **Em uma frase:** depurar (*debugar*) é investigar um programa enquanto ele roda: você pausa a execução em **breakpoints**, inspeciona as variáveis e avança linha por linha até achar onde o comportamento foge do esperado.

## 🧰 As ferramentas

| Técnica | Para quê |
| --- | --- |
| **Breakpoints** | Pausar a execução numa linha específica. |
| **Inspeção de variáveis** | Ver o valor de cada variável naquele momento. |
| **Execução passo a passo** | Avançar linha por linha e entrar (ou não) nos métodos chamados. |
| **Logs** | Registrar o que acontece, com níveis (`DEBUG`, `INFO`, `WARN`, `ERROR`). |
| **Profilers** | Descobrir gargalos de desempenho (onde o programa gasta tempo e memória). |

## 🐞 Debugando no IntelliJ

1. Clique na **margem esquerda** da linha para criar um breakpoint (aparece uma bolinha vermelha).
2. Execute em modo debug: **🐞 Debug** (ou `Shift+F9`).
3. Quando parar, use os comandos:

| Comando | Atalho | O que faz |
| --- | --- | --- |
| **Step Over** | `F8` | Executa a linha atual e vai para a próxima (sem entrar nos métodos). |
| **Step Into** | `F7` | **Entra** no método chamado na linha atual. |
| **Step Out** | `Shift+F8` | Termina o método atual e volta para quem o chamou. |
| **Resume** | `F9` | Continua até o próximo breakpoint. |
| **Evaluate Expression** | `Alt+F8` | Calcula qualquer expressão com os valores atuais. |

> [!TIP]
> Clique com o botão direito no breakpoint para criar um **breakpoint condicional** (ex.: `i == 500`). Ele só para quando a condição for verdadeira, ótimo para laços longos. No Eclipse e no VS Code os conceitos são os mesmos (`F5`/`F6`/`F7`/`F8` no Eclipse).

## 📚 A pilha de execução e o *stack trace*

Cada vez que um método chama outro, a JVM **empilha** a chamada (lembra da [pilha](../02-java/18-pilhas.md)?). Quando o método termina, ele é **desempilhado** e a execução volta para quem chamou.

```java
public class Pilha {
    public static void main(String[] args) { a(); }
    static void a() { b(); }
    static void b() { c(); }
    static void c() { Thread.dumpStack(); }   // imprime a pilha atual
}
```

```text
java.lang.Exception: Stack trace
    at java.base/java.lang.Thread.dumpStack(Thread.java:...)
    at Pilha.c(Pilha.java:5)       ← topo: onde estamos agora
    at Pilha.b(Pilha.java:4)
    at Pilha.a(Pilha.java:3)
    at Pilha.main(Pilha.java:2)    ← base: onde tudo começou
```

Quando uma exceção acontece, o Java imprime esse mesmo formato: o **stack trace**. Leia de **cima para baixo**: a primeira linha diz o **tipo do erro** e a mensagem; as linhas `at` mostram o caminho, começando pelo lugar exato onde o erro aconteceu.

> [!IMPORTANT]
> Procure a primeira linha `at` que aponta para **o seu código** (e não para classes do Java ou de bibliotecas). Quase sempre o problema está ali.

## 🔍 Um bug para caçar

```java
import java.util.Scanner;

public class MediaTurma {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String[] alunos = {"Josué", "Camila", "Bruna", "Lucas"};
        double media = calcularMedia(alunos, entrada);
        System.out.printf("A média da turma é %d", media);   // 🐞
    }

    static double calcularMedia(String[] alunos, Scanner entrada) {
        double soma = 0;
        for (String aluno : alunos) {
            System.out.printf("Nota de %s: ", aluno);
            soma += entrada.nextInt();                       // 🐞
        }
        return soma / alunos.length;
    }
}
```

Este código tem dois problemas:

1. `%d` é para inteiros; com um `double`, o programa quebra com `IllegalFormatConversionException`. O correto é `%.2f`.
2. `nextInt()` não aceita notas como `7.5` (`InputMismatchException`). O correto é `nextDouble()`.

O stack trace do primeiro erro aponta direto para a linha do `printf` no `main`.

## 📌 Resumindo

- Breakpoints pausam; `F8` avança, `F7` entra, `Shift+F8` sai, `F9` continua.
- O stack trace é a pilha de chamadas no momento do erro: leia de cima para baixo e procure a primeira linha do seu código.
- Logs e profilers complementam o debugger.

## 🏋️ Mão na massa

**1.** Corrija os dois bugs do programa `MediaTurma`.

<details>
<summary>Ver resposta</summary>

Troque `entrada.nextInt()` por `entrada.nextDouble()` e o `printf` por:

```java
System.out.printf("A média da turma é %.2f%n", media);
```

</details>

**2.** Neste stack trace, em qual linha do **seu** código está o erro?

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "nome" is null
    at br.com.treino.Cadastro.validar(Cadastro.java:18)
    at br.com.treino.Cadastro.salvar(Cadastro.java:10)
    at br.com.treino.Main.main(Main.java:6)
```

<details>
<summary>Ver resposta</summary>

Na linha **18** do arquivo `Cadastro.java`, no método `validar`: a variável `nome` é `null` e alguém chamou `nome.length()`. As outras linhas mostram o caminho até lá (`main` → `salvar` → `validar`).

</details>

**3.** Coloque um breakpoint condicional dentro de um `for` de 1 a 1000 para parar só quando `i == 777`.

<details>
<summary>Ver resposta</summary>

Crie o breakpoint na linha dentro do laço, clique nele com o botão direito, preencha **Condition** com `i == 777` e rode em modo Debug. A execução só para naquela volta.

</details>

📚 Para ir além: [Alura: exceções e controle de erros](https://www.alura.com.br/apostila-java-orientacao-objetos/excecoes-e-controle-de-erros) · [Entendendo o stack trace (em inglês)](https://www.sentinelone.com/blog/java-stack-trace-understanding/) · [Debugger do IntelliJ (documentação)](https://www.jetbrains.com/help/idea/debugging-code.html)

---

<div align="center">

[⬅️ 4.11 Lambdas, referências de método e Stream API](./11-stream-api.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.13 Tratamento de exceções ➡️](./13-excecoes.md)

</div>
