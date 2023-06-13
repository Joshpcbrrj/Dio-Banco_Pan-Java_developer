# Fila (Queue/ Linkedlist)

## Interface Queue
A interface `queue` em Java define uma coleção de elementos em que a inserção ocorre no final e a remoção ocorre no início da coleção. Ela estende a interface `Collection` e adiciona métodos para manipular a fila, como adicionar elementos no final da fila, remover elementos no início da fila e verificar se a fila está vazia. Uma `queue` é uma coleção de elementos organizados em ordem linear, onde o elemento mais antigo é sempre o primeiro a ser removido. É como uma fila em um supermercado ou em um banco, onde o primeiro cliente a chegar é o primeiro a ser atendido. 

A classe **Queue** é uma interface que define os principais métodos que uma fila deve suportar, como:

- `add(element)` → adiciona um elemento ao final da fila. Se a fila já estiver cheia (ou seja, atingiu sua capacidade máxima), esse método lançará uma exceção IllegalStateException.
- `offer(element)` → adiciona um elemento ao final da fila. Se a fila já estiver cheia, esse método retornará false, caso contrário, retornará true.
- `remove()` → remove e retorna o elemento na frente da fila. Se a fila estiver vazia, esse método lançará uma exceção NoSuchElementException.
- `poll()` → remove e retorna o elemento na frente da fila. Se a fila estiver vazia, esse método retornará null.
- `element()` → retorna o elemento na frente da fila sem removê-lo. Se a fila estiver vazia, esse método lançará uma exceção NoSuchElementException.
- `peek()` → retorna o elemento na frente da fila sem removê-lo. Se a fila estiver vazia, esse método retornará null.

### Outros usos:

a interface queue é apenas uma especificação que define um conjunto de métodos que uma fila deve implementar. Na linguagem Java, existem várias classes que implementam essa interface, cada uma delas com suas próprias particularidades e características. Aqui está uma breve descrição de algumas das classes mais comuns que implementam a interface queue:

1. **`ArrayDeque`**: é uma classe que implementa a interface **`Deque`**, que por sua vez estende a interface **`Queue`**. Ela representa uma fila de elementos que podem ser adicionados e removidos tanto no final quanto no início da fila. Internamente, essa classe usa um array para armazenar seus elementos, e é altamente eficiente tanto para adicionar quanto para remover elementos.
2. **`LinkedList`**: é uma classe que também implementa a interface **`Deque`**. Ela representa uma fila de elementos que podem ser adicionados e removidos tanto no final quanto no início da fila, bem como em qualquer posição intermediária. Internamente, essa classe usa uma estrutura de lista ligada para armazenar seus elementos, o que a torna menos eficiente do que a **`ArrayDeque`** em algumas operações.
3. **`PriorityQueue`**: é uma classe que implementa a interface **`Queue`**, mas em vez de simplesmente armazenar seus elementos em ordem de chegada, ela os armazena em ordem de prioridade. Isso significa que cada elemento na fila tem uma "prioridade" associada a ele, e quando é adicionado à fila, ele é inserido na posição correta de acordo com sua prioridade.

<br>

## Falando mais sobre LinkedList

LinkedList é uma estrutura de dados que permite armazenar uma lista de elementos, onde cada elemento possui um ponteiro para o próximo elemento na lista. Isso permite que os elementos sejam inseridos e removidos de maneira eficiente, sem precisar realocar todos os elementos na lista. A classe LinkedList implementa a interface List, que define métodos como "add", "remove", "get", "set" e "size", além de outros métodos específicos para trabalhar com listas duplamente encadeadas, como:

Alguns dos principais métodos da classe LinkedList são:
- `add(element)` → Adiciona um elemento ao final da lista.
- `addFirst(element)` →  Adiciona um elemento no início da lista.
- `addLast(element)` →  Adiciona um elemento no final da lista.
- `remove()` →  Remove e retorna o primeiro elemento da lista.
- `removeFirst()` →  Remove e retorna o primeiro elemento da lista.
- `removeLast()` → Remove e retorna o último elemento da lista.
- `get(index)` → Retorna o elemento na posição especificada.
- `set(index, element)` → Substitui o elemento na posição especificada pelo novo elemento.
- `size()` →  Retorna o número de elementos na lista.
- `clear()` → Remove todos os elementos da lista.

<br>

### Mostrando em código:
<details>
<summary>Código</summary>

```java
//Class "main"
package com.projeto.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        //Criando uma fila de carros linkada "fila_de_carros"
        Queue<Carro> fila_de_carros = new LinkedList<>();

        //Método add (Adicionando elementos em "fila_de_carros"/ Retorna erro se nao adicionar)
        fila_de_carros.add(new Carro("Honda"));
        fila_de_carros.add(new Carro("Mercedes Benz"));
        fila_de_carros.add(new Carro("Kia"));
        fila_de_carros.add(new Carro("Chevrolet"));

        //Adicionando mais um elemento Carro a lista e mostrando se adição foi ou não sucedida
        System.out.println("Adição foi bem sucedida ? " + fila_de_carros.add(new Carro("BMW")));

        //Mostrando "fila_de_carros" na tela
        System.out.println("Fila de carros: " + fila_de_carros);

        //Método offer (outra forma de adicionar/ retorna true se conseguir adicionar
        System.out.println("Adicionou ? " + fila_de_carros.offer(new Carro("Renault")));

        //Método peek (Retorna o primeiro elemento da fila sem remover)
        System.out.println("Primeiro carro da fila: " + fila_de_carros.peek());
        System.out.println("Fila de carros: " + fila_de_carros);

        //Método poll (Retorna o primeiro elemento da fila e o remove
        System.out.println("Primeiro carro da fila: " + fila_de_carros.poll());
        System.out.println("Fila de carros: " + fila_de_carros);

        //Método "isEmpty" (Verifica se a fila está vazia)
        System.out.println("A fila esta vázia ? " + fila_de_carros.isEmpty());

        //Método size (Para saber o tamanho da fila (Quandos elementos tem))
        System.out.println("Quantidade de elementos na fila: " + fila_de_carros.size());
    }
}
```

```java
//Class "Carro"
package com.projeto.Queue;

import java.util.Objects;

public class Carro {
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
}
```

</details>

<br>

<br>

<br>

[Voltar a Principais implementações das estruturas de dados em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.5.8%20Principais%20implementacoes.md)<br>
[Voltar a Estrutura de dados em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.5%20Estrutura%20de%20dados%20em%20java.md)<br>
[Voltar para inicio](/README.md)