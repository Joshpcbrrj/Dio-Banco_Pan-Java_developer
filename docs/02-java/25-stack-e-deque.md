# Pilhas (Stacks) 

Em Java, uma stack (pilha) é uma estrutura de dados que segue o modelo LIFO (Last In, First Out), ou seja, o último elemento adicionado à pilha é o primeiro a ser removido.

**Na** classe java.util.Stack, que implementa a interface java.util.Deque, temos os seguintes métodos principais:

- **`push(E e)`**: adiciona um elemento à pilha no topo.
- **`pop()`**: remove e retorna o elemento do topo da pilha. Se a pilha estiver vazia, gera uma exceção **`EmptyStackException`**.
- **`peek()`**: retorna o elemento do topo da pilha, sem removê-lo. Se a pilha estiver vazia, gera uma exceção **`EmptyStackException`**.
- **`empty()`**: retorna **`true`** se a pilha estiver vazia, **`false`** caso contrário.
- **`search(Object o)`**: retorna a posição do objeto na pilha, onde o topo da pilha é a posição 1. Se o objeto não estiver na pilha, retorna -1.
  
<br>

### Mostrando em código:
<details>
<summary>Código</summary>

```java
//Class "main"
package com.projeto.Stack;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        //Criando pilhas do objeto "Carro" (pilha_de_carros)
        Stack<Carro> pilha_de_carros = new Stack<>();

        //Método push (Adicionando carros na "pilha_de_carros")
        pilha_de_carros.push(new Carro("Ford"));
        pilha_de_carros.push(new Carro("Toyota"));
        pilha_de_carros.push(new Carro("Fiat")); //último adicionado vai pro topo da pilha

        //Mostrando "pilha_de_carros" na tela
        System.out.println("Lista de carros: " + pilha_de_carros);

        //Método pop (Mostrando objeto removido do topo da pilha)
        System.out.println(pilha_de_carros.pop());

        //Método peek (Pega e mostra o elemento do topo sem remover)
        System.out.println("Novo top da lista: " + pilha_de_carros.peek());

        //Método isEmpty (Testa se a pilha esta vazia)
        System.out.println("A pilha esta vazia ? " + pilha_de_carros.empty());

    }
}
```

```java
//Class "Carros"
package com.projeto.Stack;

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