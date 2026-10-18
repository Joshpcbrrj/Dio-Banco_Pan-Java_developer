# 2.10 · Compilando e executando pelo terminal

> **Em uma frase:** a IDE é só uma ajuda; por baixo dela, um programa Java é compilado com `javac` e executado com `java`, e saber fazer isso no terminal ajuda a entender o que acontece.

## 🖥️ Por que aprender isso?

Seus clientes não vão instalar o IntelliJ para rodar o sistema. Em servidores, o programa roda pelo terminal, normalmente empacotado em um arquivo `.jar`. E quando algo dá errado no build, entender os comandos por baixo faz toda a diferença.

## ▶️ Passo a passo

1. Crie o arquivo `MinhaClasse.java` em uma pasta qualquer:

```java
public class MinhaClasse {
    public static void main(String[] args) {
        System.out.println("Oi, fui executado pelo terminal!");
    }
}
```

2. Abra o terminal (PowerShell, Prompt de Comando, Git Bash ou o terminal da IDE) **na pasta do arquivo** e compile:

```bash
javac MinhaClasse.java
```

Isso gera o arquivo `MinhaClasse.class` (o bytecode).

3. Execute (sem a extensão `.class`):

```bash
java MinhaClasse
```

```text
Oi, fui executado pelo terminal!
```

> [!TIP]
> Para um arquivo único, o Java 11+ deixa pular a compilação: `java MinhaClasse.java` compila em memória e executa direto. Ótimo para testes rápidos.

## 📂 Onde a IDE coloca os `.class`?

Cada ferramenta usa uma pasta de saída diferente:

| Ferramenta | Pasta dos `.class` |
| --- | --- |
| IntelliJ (projeto simples) | `out/production/<projeto>` |
| Eclipse | `bin` |
| Maven | `target/classes` |

Para executar uma classe já compilada pela IDE, entre nessa pasta e rode `java NomeDaClasse`. Se a classe estiver em um **pacote**, use o nome completo a partir da pasta raiz da saída: `java com.exemplo.Main`.

## 📦 Usando o *classpath* e argumentos

```bash
# Compila e coloca os .class na pasta "out"
javac -d out src/MinhaClasse.java

# Executa dizendo onde estão as classes (-cp = classpath)
java -cp out MinhaClasse

# Passa argumentos para o main (chegam em String[] args)
java -cp out MinhaClasse Ana 30
```

```java
public static void main(String[] args) {
    System.out.println("Olá, " + args[0] + "! Você tem " + args[1] + " anos.");
}
```

> [!NOTE]
> Arquivos `.class`, pastas `out/`, `bin/` e `target/` são **gerados** pela compilação e não devem ir para o Git. Coloque-os no `.gitignore`.

## 📌 Resumindo

- `javac Arquivo.java` compila e gera `Arquivo.class`.
- `java Arquivo` executa (sem `.class`); `java Arquivo.java` faz os dois de uma vez.
- `-d` define onde salvar os `.class`; `-cp` diz onde procurá-los.
- Argumentos depois do nome da classe chegam no `String[] args`.

## 🏋️ Mão na massa

**1.** Crie, compile e rode pelo terminal um programa que imprime "Olá, terminal!".

<details>
<summary>Ver resposta</summary>

```bash
# depois de criar Ola.java com o main
javac Ola.java
java Ola
```

</details>

**2.** Faça um programa que receba o nome pela linha de comando e imprima uma saudação. Rode com `java Saudacao Maria`.

<details>
<summary>Ver resposta</summary>

```java
public class Saudacao {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Informe um nome!");
            return;
        }
        System.out.println("Olá, " + args[0] + "!");
    }
}
```

```bash
java Saudacao.java Maria
# Olá, Maria!
```

</details>

**3.** O que acontece se você rodar `java MinhaClasse.class`?

<details>
<summary>Ver resposta</summary>

Dá erro (`ClassNotFoundException` ou "Could not find or load main class"), porque o comando `java` espera o **nome da classe**, sem a extensão `.class`.

</details>

---

<div align="center">

[⬅️ 2.9 Comentários, Javadoc e a documentação oficial](./09-javadoc.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.11 Strings e entrada de dados com Scanner ➡️](./11-strings-e-entrada.md)

</div>
