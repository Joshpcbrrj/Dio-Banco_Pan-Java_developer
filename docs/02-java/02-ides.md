# 2.2 · IDEs: IntelliJ, Eclipse e VS Code

> **Em uma frase:** uma IDE é um editor "turbinado" que compila, executa, depura e ainda sugere e corrige o código para você; para Java, as mais usadas são o **IntelliJ IDEA**, o **Eclipse** e o **VS Code** com extensões.

## 🧰 As opções

| IDE | Quem faz | Destaques |
| --- | --- | --- |
| [**IntelliJ IDEA**](https://www.jetbrains.com/idea/download/) | JetBrains (2001) | A mais usada no mercado Java. A versão gratuita (antiga *Community*) atende tudo deste curso. |
| [**Eclipse**](https://www.eclipse.org/downloads/) | Criado pela IBM em 2001, hoje mantido pela Eclipse Foundation | Gratuito, open source e baseado em plugins. Ainda comum em empresas. |
| [**VS Code**](https://code.visualstudio.com/docs/languages/java) | Microsoft | Leve; vira uma boa IDE Java com o *Extension Pack for Java*. |

> [!TIP]
> Se você está começando, use o **IntelliJ IDEA**. A [JetBrains Toolbox](https://www.jetbrains.com/toolbox-app/) facilita instalar e atualizar. Antes, instale um **JDK** (aula [2.1](./01-historia-e-plataforma.md)); o IntelliJ também consegue baixar um JDK para você em **File → Project Structure → SDK → Download JDK**.

## 🚀 Criando o projeto de treino

Os exercícios deste módulo usam um projeto chamado **`treino-java`**.

1. No IntelliJ: **New Project → Java**, nome `treino-java`, *Build system*: **IntelliJ** (ou Maven, mais adiante), JDK 21 ou superior.
2. Clique com o botão direito na pasta `src` → **New → Java Class** → `Main`.
3. Digite `psvm` e aperte **Tab**: o IntelliJ gera o método `main`. Dentro dele, digite `sout` + **Tab** para gerar um `System.out.println()`.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Olá, Java!");
    }
}
```

4. Clique no ▶️ verde ao lado do `main` (ou **Shift+F10**).

## ⌨️ Atalhos essenciais

| Ação | IntelliJ (Windows/Linux) | Eclipse | VS Code |
| --- | --- | --- | --- |
| Executar | `Shift+F10` (`Ctrl+Shift+F10` na 1ª vez) | `Ctrl+F11` | `F5` / ▶️ |
| Gerar construtor, getters, setters... | `Alt+Insert` | `Alt+Shift+S` | `Ctrl+.` (*Source Action*) |
| Sugestão / correção rápida | `Alt+Enter` | `Ctrl+1` | `Ctrl+.` |
| Autocompletar | `Ctrl+Space` | `Ctrl+Space` | `Ctrl+Space` |
| Formatar código | `Ctrl+Alt+L` | `Ctrl+Shift+F` | `Shift+Alt+F` |
| Organizar imports | `Ctrl+Alt+O` | `Ctrl+Shift+O` | `Shift+Alt+O` |
| Comentar linha / bloco | `Ctrl+/` · `Ctrl+Shift+/` | `Ctrl+/` | `Ctrl+/` |
| Duplicar linha | `Ctrl+D` | `Ctrl+Alt+↓` | `Shift+Alt+↓` |
| Apagar linha | `Ctrl+Y` | `Ctrl+D` | `Ctrl+Shift+K` |
| Mover linha | `Alt+Shift+↑/↓` | `Alt+↑/↓` | `Alt+↑/↓` |
| Extrair variável | `Ctrl+Alt+V` | `Alt+Shift+L` | `Ctrl+.` |
| Renomear (refatorar) | `Shift+F6` | `Alt+Shift+R` | `F2` |
| Buscar classe | `Ctrl+N` | `Ctrl+Shift+T` | `Ctrl+P` |
| Ir para a declaração | `Ctrl+B` | `F3` | `F12` |
| Buscar em tudo | `Shift` `Shift` | `Ctrl+3` | `Ctrl+Shift+P` |

> [!NOTE]
> No macOS, troque `Ctrl` por `Cmd` na maioria dos atalhos. No IntelliJ, **Help → Keyboard Shortcuts PDF** abre a lista completa oficial.

> [!TIP]
> Os atalhos que mais economizam tempo no começo são **`Alt+Enter`** (resolve quase qualquer sublinhado vermelho, como imports faltando) e **`Alt+Insert`** (gera construtores, getters e setters).

## 📌 Resumindo

- IntelliJ IDEA é a IDE mais usada para Java; Eclipse e VS Code também são boas opções.
- Antes da IDE, instale um JDK.
- Atalhos-chave no IntelliJ: `Alt+Enter`, `Alt+Insert`, `Shift+F10`, `Ctrl+Alt+L`, `Shift+F6`.

## 🏋️ Mão na massa

**1.** Crie o projeto `treino-java` e faça o programa imprimir seu nome.

<details>
<summary>Ver resposta</summary>

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Meu nome é Fulano");
    }
}
```

Execute com o ▶️ ou `Shift+F10`.

</details>

**2.** Use o atalho de "gerar código" para criar um construtor e os getters de uma classe `Pessoa` com `nome` e `idade`.

<details>
<summary>Ver resposta</summary>

Crie a classe com os atributos, posicione o cursor dentro dela e aperte `Alt+Insert` → **Constructor** (selecione os dois campos) e depois `Alt+Insert` → **Getter**:

```java
public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
}
```

</details>

**3.** Bagunce a indentação do `Main.java` de propósito e conserte com um atalho.

<details>
<summary>Ver resposta</summary>

No IntelliJ, `Ctrl+Alt+L` reformata o arquivo inteiro (no Eclipse, `Ctrl+Shift+F`; no VS Code, `Shift+Alt+F`).

</details>

---

<div align="center">

[⬅️ 2.1 História do Java, JVM, JRE e JDK](./01-historia-e-plataforma.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.3 Anatomia de uma classe e convenções de nomes ➡️](./03-anatomia-das-classes.md)

</div>
