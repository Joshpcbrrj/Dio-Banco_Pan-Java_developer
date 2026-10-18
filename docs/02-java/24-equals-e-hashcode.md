# 2.24 · `equals()` e `hashCode()`

> **Em uma frase:** `equals()` diz se dois objetos são "iguais" para o seu programa, e `hashCode()` gera um número que as coleções usam para encontrar objetos rápido; quando você sobrescreve um, **precisa** sobrescrever o outro.

## 🤔 O problema

Por padrão, dois objetos só são "iguais" se forem **o mesmo objeto** na memória:

```java
Carro c1 = new Carro("Ford");
Carro c2 = new Carro("Ford");
System.out.println(c1 == c2);        // false: objetos diferentes
System.out.println(c1.equals(c2));   // false também, se Carro não sobrescrever equals
```

Para o seu sistema, porém, dois carros da mesma marca podem ser "o mesmo carro". Quem define isso é você, sobrescrevendo `equals()`.

## 📏 O contrato

1. Se `a.equals(b)` é `true`, então `a.hashCode() == b.hashCode()` **obrigatoriamente**.
2. Se os hashes são iguais, os objetos **não** são necessariamente iguais (pode haver colisão).
3. `equals` deve ser reflexivo (`a.equals(a)`), simétrico e consistente, e `a.equals(null)` deve ser `false`.

> [!CAUTION]
> Sobrescrever só o `equals` e esquecer o `hashCode` quebra o `HashSet` e o `HashMap`: eles procuram primeiro pelo hash, então dois objetos "iguais" com hashes diferentes são tratados como diferentes, gerando duplicatas e buscas que não acham nada.

## 🛠️ Implementando

```java
import java.util.Objects;

public class Carro {
    private final String marca;

    public Carro(String marca) { this.marca = marca; }

    public String getMarca() { return marca; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                     // mesmo objeto
        if (!(o instanceof Carro outro)) return false;  // tipo diferente ou null
        return Objects.equals(marca, outro.marca);      // compara os campos
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca);                     // mesmos campos do equals
    }

    @Override
    public String toString() { return "Carro{" + marca + "}"; }
}
```

```java
List<Carro> carros = new ArrayList<>(List.of(new Carro("Ford"), new Carro("Fiat")));
System.out.println(carros.contains(new Carro("Ford")));   // true (usa equals)

Carro a = new Carro("Ford");
Carro b = new Carro("Ford");
System.out.println(a.equals(b));                    // true
System.out.println(a.hashCode() == b.hashCode());   // true
```

> [!TIP]
> Não escreva isso na mão: no IntelliJ, `Alt+Insert` → **equals() and hashCode()** gera os dois juntos. E se a classe só carrega dados, um **`record`** já vem com `equals`, `hashCode` e `toString` prontos: `public record Carro(String marca) { }`.

## 📌 Resumindo

- `==` compara referências; `equals()` compara o que você definir como "igual".
- `hashCode()` é usado por `HashSet`/`HashMap` para encontrar objetos rápido.
- Objetos iguais **devem** ter o mesmo hash: sempre sobrescreva os dois juntos, usando os mesmos campos.

## 🏋️ Mão na massa

**1.** Implemente `equals` e `hashCode` em uma classe `Pessoa` considerando que duas pessoas são iguais quando têm o mesmo CPF.

<details>
<summary>Ver resposta</summary>

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pessoa outra)) return false;
    return Objects.equals(cpf, outra.cpf);
}

@Override
public int hashCode() {
    return Objects.hash(cpf);
}
```

</details>

**2.** Você colocou dois objetos "iguais" num `HashSet`, mas o tamanho do set ficou 2. O que provavelmente aconteceu?

<details>
<summary>Ver resposta</summary>

A classe sobrescreveu o `equals`, mas **não o `hashCode`** (ou usou campos diferentes nos dois). Com hashes diferentes, o `HashSet` nem chega a comparar com `equals` e guarda os dois.

</details>

**3.** Dois objetos com o mesmo `hashCode` são sempre iguais?

<details>
<summary>Ver resposta</summary>

**Não.** Objetos diferentes podem ter o mesmo hash (colisão). A regra só vale no outro sentido: objetos iguais precisam ter o mesmo hash.

</details>

---

<div align="center">

[⬅️ 2.23 Árvores e árvores binárias de busca](./23-arvores-binarias.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [2.25 Pilhas prontas do Java ➡️](./25-stack-e-deque.md)

</div>
