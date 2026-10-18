# 4.7 · Modificadores de acesso

> **Em uma frase:** `public`, `protected`, *padrão* (sem modificador) e `private` controlam **quem pode enxergar** uma classe, atributo ou método, do mais aberto ao mais fechado.

## 🔐 A tabela que você precisa saber

| Modificador | Própria classe | Mesmo pacote | Subclasse em outro pacote | Qualquer lugar |
| --- | --- | --- | --- | --- |
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| *padrão* (nenhum) | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

> [!TIP]
> **Analogia de uma casa:** `public` é a calçada (qualquer um passa); `protected` é a casa da família, incluindo os filhos que moram em outra cidade; *padrão* é o condomínio (só vizinhos do mesmo pacote); `private` é o seu diário.

## 🏷️ Onde cada um pode ser usado

| Em... | Modificadores permitidos |
| --- | --- |
| Classe de nível superior (a principal do arquivo) | `public` ou *padrão* |
| Atributos, métodos, construtores | Os quatro |
| Classes internas (dentro de outra classe) | Os quatro |

> [!NOTE]
> Não existe "classe privada" ou "classe protegida" de nível superior: escrever `private class Teste` no topo de um arquivo **não compila**. Só classes **internas** podem ser `private` ou `protected`.

## 🛠️ Exemplo

```java
package com.banco.modelo;

public class Conta {
    public String numero;              // qualquer um vê (evite em atributos!)
    protected double limite;           // pacote + subclasses
    String agencia;                    // padrão: só o pacote com.banco.modelo
    private double saldo;              // só a própria classe

    public double getSaldo() {         // acesso controlado ao saldo
        return saldo;
    }

    private void registrarLog(String msg) {   // detalhe interno
        System.out.println(msg);
    }
}
```

```java
package com.banco.app;

import com.banco.modelo.Conta;

public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta();
        conta.numero = "123";          // ✅ public
        System.out.println(conta.getSaldo());   // ✅ método public
        // conta.saldo = 1000;         ❌ private
        // conta.agencia = "001";      ❌ padrão, e Main está em outro pacote
        // conta.limite = 500;         ❌ protected, e Main não é subclasse
    }
}
```

## ✅ Boas práticas

- **Atributos sempre `private`**, expostos por métodos quando necessário (encapsulamento).
- Comece com o modificador **mais restrito** e só abra se precisar.
- Use `protected` com moderação: ele expõe detalhes para todas as subclasses, até as de outros projetos.
- Métodos auxiliares internos devem ser `private`.

## 📌 Resumindo

- Do mais aberto ao mais fechado: `public` → `protected` → *padrão* → `private`.
- Classes de nível superior só podem ser `public` ou *padrão*.
- Atributos `private`, métodos auxiliares `private`, e só o necessário `public`.

## 🏋️ Mão na massa

**1.** Uma subclasse em **outro pacote** consegue acessar um atributo *padrão* (sem modificador) da classe pai?

<details>
<summary>Ver resposta</summary>

**Não.** O acesso padrão vale só para o mesmo pacote. Para que subclasses de outros pacotes enxerguem, o atributo precisaria ser `protected` (ou `public`).

</details>

**2.** Qual modificador usar em: (a) o atributo `senha` de `Usuario`; (b) o método `sacar` de `Conta`; (c) um método `calcularDigitoVerificador` usado só dentro de `ValidadorCpf`?

<details>
<summary>Ver resposta</summary>

(a) `private`; (b) `public` (faz parte do que a conta oferece); (c) `private` (detalhe interno da classe).

</details>

**3.** Este código compila? `private class Utilitario { }` (no topo do arquivo `Utilitario.java`).

<details>
<summary>Ver resposta</summary>

**Não.** Classes de nível superior só aceitam `public` ou nenhum modificador.

</details>

---

<div align="center">

[⬅️ 4.6 Pacotes](./06-pacotes.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [4.8 O framework Collections ➡️](./08-collections.md)

</div>
