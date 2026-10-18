# 2.3 · Anatomia de uma classe e convenções de nomes

> **Em uma frase:** todo código Java vive dentro de uma **classe**; o programa começa pelo método `main`, e seguir as convenções de nomes (`PascalCase`, `camelCase`, `MAIUSCULAS`) deixa o código legível para qualquer pessoa.

## 🧱 A estrutura mínima

```java
// arquivo: MinhaClasse.java
public class MinhaClasse {          // declaração da classe

    // corpo da classe: atributos e métodos ficam aqui

    public static void main(String[] args) {   // ponto de partida do programa
        // corpo do método main
        System.out.println("Olá!");
    }
}
```

Regras importantes:

- A classe **pública** precisa ter **exatamente o mesmo nome do arquivo** (`MinhaClasse` → `MinhaClasse.java`).
- Cada par de chaves `{ }` delimita um **bloco**. Saber em qual bloco você está é essencial.
- O método `main` é o ponto de entrada. Ele é:
  - `public`: visível para a JVM;
  - `static`: pode ser chamado sem criar um objeto;
  - `void`: não retorna nada;
  - recebe `String[] args`: um array com os argumentos passados na linha de comando.

> [!TIP]
> Recomenda-se ter **um só** `main` por aplicação, responsável por iniciar tudo.

> [!NOTE]
> A partir do **Java 25**, programas pequenos podem ser escritos de forma simplificada, com `void main() { IO.println("Olá!"); }` e sem declarar a classe. É ótimo para scripts e estudos, mas em projetos reais você continuará vendo a forma completa acima.

## 🏷️ Convenções de nomes

| Elemento | Estilo | Exemplos |
| --- | --- | --- |
| Classes e arquivos | `PascalCase` (cada palavra com inicial maiúscula) | `Calculadora`, `ContaBancaria` |
| Variáveis e métodos | `camelCase` (primeira minúscula, demais palavras com inicial maiúscula) | `idade`, `anoFabricacao`, `calcularTotal()` |
| Constantes | `MAIUSCULAS_COM_UNDERLINE` | `PI`, `TOTAL_ESTADOS` |
| Pacotes | tudo minúsculo, domínio invertido | `com.minhaempresa.banco` |

```java
final double PI = 3.14159;
final int TOTAL_ESTADOS_BRASILEIROS = 27;
```

### Regras obrigatórias para nomes (identificadores)

- Só podem conter **letras, números, `_` e `$`**.
- **Não** podem começar com número.
- **Não** podem ter espaços.
- **Não** podem ser [palavras reservadas](./08-palavras-reservadas.md) (`int`, `class`, `long`...).
- O Java diferencia maiúsculas de minúsculas: `idade` e `Idade` são nomes diferentes.

```java
// ❌ inválidos
int numero&um = 1;   // símbolo não permitido
int 1numero = 1;     // começa com número
int numero um = 1;   // espaço
int long = 1;        // palavra reservada

// ✅ válidos
int numeroUm = 1;
int numero1 = 1;
int longo = 1;
```

## ✍️ Boas práticas de nomenclatura

- Nomes **claros**, sem abreviações misteriosas: `salarioMedio` em vez de `salMed`.
- Variáveis no **singular**, exceto arrays e coleções: `email` × `emails`.
- Escolha **um idioma** e mantenha: tudo em português ou tudo em inglês.
- Métodos são **ações**, então use verbos: `somar()`, `abrirConexao()`, `buscarPorId()`.
- Um método deve ter **uma única responsabilidade**: `calcularEImprimir()` é sinal de que ele faz coisas demais.

## 📐 Indentação

Indentar é recuar o código para mostrar a hierarquia dos blocos. O compilador não liga, mas pessoas sim:

```java
// ❌ sem indentação
public class Boletim {
public static void main(String[] args) {
int media = 6;
if (media < 6)
System.out.println("REPROVADO");
else
System.out.println("APROVADO");
}
}
```

```java
// ✅ indentado
public class Boletim {
    public static void main(String[] args) {
        int media = 6;
        if (media < 6) {
            System.out.println("REPROVADO");
        } else {
            System.out.println("APROVADO");
        }
    }
}
```

> [!TIP]
> Não indente na mão: use o atalho de formatação da IDE (`Ctrl+Alt+L` no IntelliJ).

## 📁 Organizando em pacotes

Conforme o projeto cresce, as classes são agrupadas em **pacotes** (pastas). Uma classe passa a ter dois nomes:

- **nome simples:** `ContaBanco`;
- **nome qualificado:** `com.suaempresa.banco.ContaBanco`.

```text
src/
└── com/suaempresa/banco/
    ├── modelo/
    │   ├── Cliente.java
    │   └── ContaBanco.java
    ├── util/
    │   └── ValidadorCpf.java
    └── Main.java
```

Você verá pacotes com mais detalhes na aula [4.6](../04-poo/06-pacotes.md).

## 📌 Resumindo

- Todo código Java fica dentro de classes; a classe pública tem o mesmo nome do arquivo.
- `public static void main(String[] args)` é o ponto de partida.
- Classes em `PascalCase`, variáveis e métodos em `camelCase`, constantes em `MAIUSCULAS`.
- Nomes claros, verbos para métodos, um idioma só e código sempre indentado.

## 🏋️ Mão na massa

**1.** Quais destes nomes de variável são inválidos? `valorTotal`, `2lugar`, `_contador`, `class`, `preço$`, `nome completo`

<details>
<summary>Ver resposta</summary>

Inválidos: `2lugar` (começa com número), `class` (palavra reservada) e `nome completo` (tem espaço). Os outros são válidos (inclusive `preço$`, já que o Java aceita letras acentuadas e `$`, embora não seja uma boa prática usar acentos).

</details>

**2.** Renomeie seguindo as convenções: a classe `conta_corrente`, a variável `Nome_Do_Cliente` e a constante `taxaFixa`.

<details>
<summary>Ver resposta</summary>

`ContaCorrente` (classe em PascalCase), `nomeDoCliente` (variável em camelCase) e `TAXA_FIXA` (constante em maiúsculas).

</details>

**3.** No `treino-java`, crie a classe `Apresentacao` com um `main` que imprima seu nome e sua cidade em linhas separadas.

<details>
<summary>Ver resposta</summary>

```java
public class Apresentacao {
    public static void main(String[] args) {
        System.out.println("Nome: Fulano");
        System.out.println("Cidade: Rio de Janeiro");
    }
}
```

</details>

---

<div align="center">

[⬅️ 2.2 IDEs](./02-ides.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.4 Tipos de dados, variáveis e constantes ➡️](./04-tipos-e-variaveis.md)

</div>
