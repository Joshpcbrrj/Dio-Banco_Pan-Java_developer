# 2.13 · Blocos de código

> **Em uma frase:** um bloco é um trecho entre chaves `{ }` que é executado em conjunto; além dos blocos de métodos, `if` e `for`, o Java tem blocos **estáticos** e **de inicialização** que rodam em momentos especiais.

## 🧱 O que é um bloco

Qualquer conjunto de instruções entre `{` e `}`. Os blocos organizam o código e definem o **escopo** das variáveis (aula [2.7](./07-escopo.md)): o que é declarado dentro de um bloco só existe ali.

```java
if (saldo > 0) {           // bloco do if
    double taxa = 0.01;    // existe só aqui dentro
    saldo -= taxa;
}
```

## 🗂️ Tipos de bloco

| Tipo | Onde fica | Quando executa |
| --- | --- | --- |
| **Local** | Dentro de métodos (inclusive um `{ }` solto) | Quando a execução chega nele |
| **Estático** (`static { }`) | No corpo da classe | **Uma vez**, quando a classe é carregada pela JVM |
| **De inicialização** (`{ }` no corpo da classe) | No corpo da classe | **A cada objeto criado**, antes do construtor |

```java
public class ExemploBlocos {

    static int contador;

    static {                                     // bloco estático
        System.out.println("1. Classe carregada");
        contador = 100;
    }

    {                                            // bloco de inicialização
        System.out.println("3. Novo objeto sendo criado");
    }

    public ExemploBlocos() {
        System.out.println("4. Construtor");
    }

    public static void main(String[] args) {
        System.out.println("2. Início do main");

        {                                        // bloco local
            int numero = 10;
            System.out.println("Bloco local: " + numero);
        }
        // numero não existe mais aqui

        new ExemploBlocos();
        new ExemploBlocos();
    }
}
```

Saída:

```text
1. Classe carregada
2. Início do main
Bloco local: 10
3. Novo objeto sendo criado
4. Construtor
3. Novo objeto sendo criado
4. Construtor
```

> [!TIP]
> Blocos estáticos são úteis para inicializar dados da classe uma única vez (por exemplo, carregar uma tabela fixa). Blocos de inicialização são raros: na prática, coloque essa lógica no **construtor**.

## 📌 Resumindo

- Bloco = instruções entre `{ }`; define o escopo das variáveis.
- Bloco estático roda uma vez, quando a classe é carregada.
- Bloco de inicialização roda a cada `new`, antes do construtor.

## 🏋️ Mão na massa

**1.** Em que ordem são impressas as mensagens do exemplo acima se o `main` criar só **um** objeto?

<details>
<summary>Ver resposta</summary>

"1. Classe carregada" → "2. Início do main" → "Bloco local: 10" → "3. Novo objeto sendo criado" → "4. Construtor".

</details>

**2.** Quantas vezes um bloco `static` executa se você criar 50 objetos da classe?

<details>
<summary>Ver resposta</summary>

**Uma vez só**, quando a classe é carregada. Ele pertence à classe, não aos objetos.

</details>

**3.** Crie uma classe `Configuracao` com um bloco estático que preenche uma constante `VERSAO` e imprima-a no `main`.

<details>
<summary>Ver resposta</summary>

```java
public class Configuracao {
    static final String VERSAO;

    static {
        VERSAO = "1.0." + java.time.Year.now().getValue();
    }

    public static void main(String[] args) {
        System.out.println("Versão: " + VERSAO);
    }
}
```

</details>

---

<div align="center">

[⬅️ 2.12 Controle de fluxo](./12-controle-de-fluxo.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.14 Estruturas de repetição e arrays ➡️](./14-repeticao-e-arrays.md)

</div>
