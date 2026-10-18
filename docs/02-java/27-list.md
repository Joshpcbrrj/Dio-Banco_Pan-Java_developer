# Listas (List)

A interface `List` em Java define uma coleção de elementos ordenados que permite o acesso aos seus elementos por meio de um índice (posição). A interface `List` é uma subinterface da interface `Collection` e estende a mesma, portanto, ela herda todos os métodos da interface `Collection`. Além disso, a interface `List` adiciona métodos específicos para trabalhar com listas:

- **`add(E element)`**: adiciona um elemento ao final da lista.
- **`add(int index, E element)`**: adiciona um elemento em uma posição específica na lista.
- **`get(int index)`**: retorna o elemento na posição especificada na lista.
- **`set(int index, E element)`**: substitui o elemento na posição especificada na lista pelo elemento fornecido.
- **`remove(int index)`**: remove o elemento na posição especificada na lista.
- **`indexOf(Object o)`**: retorna o índice da primeira ocorrência do elemento especificado na lista.
- **`lastIndexOf(Object o)`**: retorna o índice da última ocorrência do elemento especificado na lista.
- **`size()`**: retorna o número de elementos na lista.
- **`isEmpty()`**: retorna **`true`** se a lista estiver vazia, ou seja, não contiver nenhum elemento.
- **`contains(Object o)`**: retorna **`true`** se a lista contiver o elemento especificado.
- **`toArray()`**: retorna um array contendo todos os elementos da lista na ordem em que aparecem.
- **`addAll(Collection<? extends E> c)`**: adiciona todos os elementos da coleção especificada à lista, na ordem em que aparecem na coleção.
- **`clear()`**: remove todos os elementos da lista.
- **`subList(int fromIndex, int toIndex)`**: retorna uma sub lista contendo os elementos da lista original entre os índices **`fromIndex`** e **`toIndex`**. A sub lista é uma visão da lista original, o que significa que as alterações feitas na sub lista serão refletidas na lista original e vice-versa.

### As classes que implementam a interface `List` em Java incluem:

**`ArrayList`** → que é uma implementação de lista baseada em matriz.

**`LinkedList` →** que é uma implementação de lista baseada em lista ligada. 

**`Vector`** → que é uma implementação de lista semelhante à **`ArrayList`**mas é sincronizada (ou seja, pode ser usada em ambientes multithread).

<br>

### Mostrando em código
<details>
<summary>Código</summary>

```java
//Class "main"
package com.projeto.List;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Criando lista de carros "lista_de_carros"
        List<Carro> lista_de_carros = new ArrayList<>();

        //Método add (Adicionando elementos a lista "lista_de_carros"
        lista_de_carros.add(new Carro("Ford"));
        lista_de_carros.add(new Carro("Kia"));
        lista_de_carros.add(new Carro("Mercedes Benz"));
        lista_de_carros.add(new Carro("BMW"));
        lista_de_carros.add(new Carro("Nissan"));

        //Mostrando "lista_de_carros" na tela
        System.out.println("Lista de carros: " + lista_de_carros);

        //Método contains (Verificando se a lista contém determinado elemento
        System.out.println("Contem marca 'Kia': " + lista_de_carros.contains(new Carro("Kia")));

        //Método get (Mostrando apenas uma posição de acordo com o índice
        System.out.println("Carro indice 2: " + lista_de_carros.get(2));

        //Método indeceOf (Verificar qual é o indice de um elemento na fila
        System.out.println("Qual o indice: " + lista_de_carros.indexOf(new Carro("Kia")));

        //Método remove (Removendo elemento da lista de acordo com o indice)
        System.out.println("Elemento removido: " + lista_de_carros.remove(1));

        //Mostrando a "lista_de_carros" na tela novamente
        System.out.println("Lista de carros com um item removido: " + lista_de_carros);

    }
}
```

```java
//Class "Carro"
package com.projeto.List;

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