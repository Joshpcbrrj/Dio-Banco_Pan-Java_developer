# Set / HashSet

## Set

A `interface Set` em Java é uma das interfaces de coleção de dados fornecidas pela plataforma Java. Ela estende a interface `Collection` e define um conjunto não ordenado de elementos únicos. Isso significa que não há elementos duplicados em um conjunto e não há garantia da ordem em que os elementos são armazenados. A interface `Set` fornece uma série de métodos para trabalhar com conjuntos, incluindo:

- **`add(E element)`**: adiciona o elemento especificado ao conjunto se ele ainda não estiver presente.
- **`remove(Object o)`**: remove o elemento especificado do conjunto, se presente.
- **`contains(Object o)`**: retorna **`true`** se o conjunto contiver o elemento especificado.
- **`isEmpty()`**: retorna **`true`** se o conjunto não contiver nenhum elemento.
- **`size()`**: retorna o número de elementos no conjunto.
- **`clear()`**: remove todos os elementos do conjunto.
- **`iterator()`**: retorna um iterador sobre os elementos no conjunto.

As implementações da interface `Set` em Java incluem `HashSet`, `TreeSet` e `LinkedHashSet`. O `HashSet` é a implementação mais rápida e eficiente, mas não garante a ordem dos elementos. O `TreeSet` mantém os elementos em ordem natural ou de acordo com um Comparator especificado, enquanto o `LinkedHashSet` mantém a ordem de inserção dos elementos. Os conjuntos são frequentemente usados para remover elementos duplicados de uma lista, verificar a presença de um elemento em uma coleção ou fazer operações de conjunto como união, interseção e diferença.

### Falando mais de HashSet
`HashSet` é uma classe em Java que implementa a interface `Set`. É uma coleção que não permite elementos duplicados e não tem ordem definida para os elementos. Em outras palavras, ele armazena um conjunto de elementos únicos em uma ordem aleatória. Os elementos armazenados em um `HashSet` são acessados por meio de seu valor e não por um índice específico. Uma das principais vantagens do `HashSet` é que ele possui um tempo de acesso constante (O(1)) para a maioria das operações, o que o torna uma escolha eficiente para conjuntos de elementos grandes. No entanto, o `HashSet` não garante a ordem em que os elementos são armazenados. Se a ordem dos elementos é importante, é necessário usar uma outra classe, como o `LinkedHashSet`, que mantém a ordem em que os elementos foram adicionados à coleção.

<br>

### Mostrando em código:
<details>
<summary>Código</summary>

```java
//Class main
package com.projeto.Set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        //Criando um hashSet de carros "conjunto_de_carros_hashSet" (sem ordenamento)
        Set<Carro> conjunto_de_carros_hashSet = new HashSet<>();
        //Método add (Adicionando elemento no "conjunto_de_carros_hashSet"
        conjunto_de_carros_hashSet.add(new Carro("Ford"));
        conjunto_de_carros_hashSet.add(new Carro("Toyota"));
        conjunto_de_carros_hashSet.add(new Carro("Nissan"));
        conjunto_de_carros_hashSet.add(new Carro("Hyundai"));
        conjunto_de_carros_hashSet.add(new Carro("Chevrolet"));
        conjunto_de_carros_hashSet.add(new Carro("Audi"));
        //Mostdando "conjunto_de_carros_hashSet" na tela (Veremos sem ordem de adição)
        System.out.println("Conjunto hashSet sem ordem específica: " + conjunto_de_carros_hashSet);

        //Criando um treeSet de carros "conjunto_de_carros_treeSet" (Exige método comparaTo)
        Set <Carro> conjunto_de_carros_treeSet = new TreeSet<>();
        //Método add (Adicionando elemento no "conjunto_de_carros_treeSet"
        conjunto_de_carros_treeSet.add(new Carro("Ford"));
        conjunto_de_carros_treeSet.add(new Carro("Toyota"));
        conjunto_de_carros_treeSet.add(new Carro("honda"));
        conjunto_de_carros_treeSet.add(new Carro("Volkswagen"));
        conjunto_de_carros_treeSet.add(new Carro("Chevrolet"));
        conjunto_de_carros_treeSet.add(new Carro("BMW"));
        //Mostdando "conjunto_de_carros_treeSet" na tela (Organizado por ordem alfabética)
        System.out.println("Conjunto treehSet ordem alfabética': " +conjunto_de_carros_treeSet);

    }
}
```

```java
//Class Carro
package com.projeto.Set;

import java.util.Objects;

public class Carro implements Comparable<Carro>{
    String marca;

    //Construtor
    public Carro(String marca) {
        this.marca = marca;
    }

    //Getters e setters
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    //Método "Equals"
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(marca, carro.marca);
    }
    //Método "hashCode"
    @Override
    public int hashCode() {
        return Objects.hash(marca);
    }
    //Método "toString"
    @Override
    public String toString() {
        return "Carro{" +
                "marca='" + marca + '\'' +
                '}';
    }
    //Método "compareTo" implementado para usar o "treeSet" (Estruturas de arvores exigem)
    @Override
    public int compareTo(Carro o) {
      return this.getMarca().compareTo(o.getMarca());
    }
}
```

</details>

<br>

<br>

<br>

[Voltar a Principais implementações das estruturas de dados em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.5.8%20Principais%20implementacoes.md)<br>
[Voltar a Estrutura de dados em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.5%20Estrutura%20de%20dados%20em%20java.md)<br>
[Voltar para inicio](/README.md)