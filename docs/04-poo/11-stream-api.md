# Stream API

A API Stream é uma das características mais importantes adicionadas ao Java 8. É uma API de processamento de coleções que permite filtrar, mapear, agrupar e reduzir elementos em uma coleção de forma simples e eficiente. A API Stream permite que você processe coleções de dados de forma declarativa, o que significa que você especifica o que deseja fazer com os dados em vez de como fazer. Isso torna o código mais legível, conciso e fácil de manter.

Os streams são compostos por três partes principais: a fonte de dados, as operações intermediárias e as operações terminais. A fonte de dados é a coleção de dados em que você deseja operar. As operações intermediárias são as operações que são executadas nos elementos da coleção e retornam um novo stream. As operações terminais são as operações que finalizam o stream e retornam um resultado, como uma lista ou um valor único.

### Representação caminho de Streams

<img src="./img/09.jpg" alt="" width="500">

<br>

### Exemplo de uso de Streams em código:
<details>
<summary>Código</summary>

```java
import java.util.Arrays;
import java.util.List;

public class ExemploStream {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("João", "Maria", "José", "Pedro", "Lucas");

        // Filtrar elementos que começam com "J"
        nomes.stream()
             .filter(nome -> nome.startsWith("J"))
             .forEach(System.out::println);

        // Mapear elementos para seus tamanhos e imprimir
        nomes.stream()
             .map(String::length)
             .forEach(System.out::println);

        // Reduzir elementos para obter o tamanho total da coleção
        int tamanhoTotal = nomes.stream()
                                .mapToInt(String::length)
                                .sum();
        System.out.println("Tamanho total: " + tamanhoTotal);
    }
}
```

</details>

<br>

## Conceitos importantes

### Classe anônima

Uma classe anônima é uma classe que não possui um nome e é criada no local onde é usada. Ela é útil quando você precisa criar uma instância de uma classe que implementa uma interface, mas não deseja criar uma classe separada apenas para essa finalidade (Instanciada apenas uma vez).

<details>
<summary>Código exemplo:</summary>

```java
public class ExemploClasseAnonima {
    public static void main(String[] args) {
        // Criando uma instância de Thread e passando uma classe anônima como argumento
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Executando thread...");
            }
        }).start();
    }
}

/*No exemplo, estamos criando uma instância de uma Thread e passando uma classe anônima 
como argumento. Essa classe implementa a interface Runnable e define o método run() para imprimir
uma mensagem: */
```

</details>

<br>

### Interface funcional
Uma interface funcional é uma interface que possui apenas um método abstrato. Ela é usada como um tipo de função, permitindo que você passe funções como argumentos e retorne funções.

- Single Abstract Method (SAM) → É um termo usado para descrever uma interface funcional que possui apenas um único método abstrato. Interfaces funcionais são interfaces que possuem apenas um método abstrato, que é a base para o uso de expressões lambda e method references em Java.

- Com a introdução de expressões lambda no Java 8, a criação de interfaces funcionais tornou-se uma parte importante do desenvolvimento em Java. Essas interfaces fornecem um mecanismo para definir comportamentos que podem ser passados como argumentos para outros métodos. Interfaces funcionais também permitem a criação de classes anônimas e o uso de referências de método.

<details>
<summary>Código exemplo:</summary>

```java
@FunctionalInterface
interface Operacao {
    int calcular(int x, int y);
}

public class ExemploInterfaceFuncional {
    public static void main(String[] args) {
        // Criando uma instância de Operacao para somar dois números inteiros
        Operacao soma = (x, y) -> x + y;
        // Criando uma instância de Operacao para subtrair dois números inteiros
        Operacao subtracao = (x, y) -> x - y;

        int resultadoSoma = soma.calcular(5, 3);
        int resultadoSubtracao = subtracao.calcular(5, 3);

        System.out.println("Soma: " + resultadoSoma);
        System.out.println("Subtração: " + resultadoSubtracao);
    }
}

/*No exemplo, estamos criando uma interface funcional chamada Operacao com um único 
método abstrato calcular(). Em seguida, estamos criando duas instâncias de Operacao utilizando 
expressões lambda, uma para somar e outra para subtrair dois números inteiros:*/
```

</details>

<br>

### Expressões lambda

Expressões lambda são funções anônimas que podem ser tratadas como valores e passadas como argumentos para outros métodos. Elas são usadas para implementar interfaces funcionais e simplificar o código.

<details>
<summary>Código exemplo:</summary>

```java
import java.util.Arrays;
import java.util.List;

public class ExemploExpressoesLambda {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("João", "Maria", "José", "Pedro", "Lucas");

        // Ordenar a lista em ordem alfabética utilizando expressão lambda
        nomes.sort((s1, s2) -> s1.compareTo(s2));

        // Imprimir os nomes em maiúsculas utilizando expressão lambda
        nomes.forEach(nome -> System.out.println(nome.toUpperCase()));
    }
}

/*No exemplo, estamos utilizando expressões lambda para ordenar uma lista de strings 
em ordem alfabética e imprimir os nomes em maiúsculas:*/
```

</details>

<br>

### Referência de método

Referência de método é uma abreviação para a criação de expressões lambda simples que chamam um único método. Ela pode ser usada para simplificar ainda mais o código.

<details>
<summary>Código exemplo:</summary>

```java
import java.util.Arrays;
import java.util.List;

public class ExemploReferenciaMetodo {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("João", "Maria", "José", "Pedro", "Lucas");

        // Utilizar referência de método para imprimir a quantidade de caracteres de cada string
        nomes.forEach(System.out::println);
    }
}

/*No exemplo, estamos utilizando referência de método para imprimir
a quantidade de caracteres de cada string em uma lista:*/
```

</details>

<br>

---

## Exercícios práticos:

### Exercício 1
Refatorando exercício anteriore com Streams. 

Dada as informações abaixo, monte uma lista com id e contato e:

1. Ordene a lista com Nome e numero (Usando hashMap)
    - id = 1 - Contato = nome: Simba, numero: 2222;
    - id = 4 - Contato = nome: Cami, numero: 5555;
    - id = 3 - Contato = nome: Jon, numero: 1111;
2. Mostre a lista em ordem aleatória (Usando o hashMap).
3. Mostre os contatos na ordem de inserção (Usando o LinkedHashMap).
4. Mostre conforme ordem de id (Usando o TreeMap).
5. Mostre a lista por ordem de telefone (Refatorando com lambda expressions).
6. Mostre a lista de acordo com ordem alfabética de contato (Também usando TreeSet e comparator)

<details>
<summary>Código resposta:</summary>

```java
//Classe main
package Ex01;

import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        //1 - Criando a lista
        Map<Integer, Contato> agenda = new HashMap<>() {{
            put(1, new Contato("Simba", 5555));
            put(4, new Contato("Cami", 1111));
            put(3, new Contato("Jon", 2222));
        }};

        //2 - Mostrando em ordem aleatória
        System.out.println("-- Ordem aleatória --");
        for (Map.Entry<Integer, Contato> entry: agenda.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
        }

        //3 - Mostrando números em ordem de inserção com o "LinkedHashMap"
        Map<Integer, Contato> agenda1 = new LinkedHashMap<>() {{
            put(1, new Contato("Simba", 5555));
            put(4, new Contato("Cami", 1111));
            put(3, new Contato("Jon", 2222));
        }};
        System.out.println("\n-- Ordem Inserção --");
        for (Map.Entry<Integer, Contato> entry: agenda1.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
        }

        //4 - Mostrando a lista por ordem de id com o TreeSet
        Map<Integer, Contato> agenda2 = new TreeMap<>(agenda);
        System.out.println("\n-- Por ordem de ID --");
        for (Map.Entry<Integer, Contato> entry: agenda2.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
        }

        /*5 - Mostrando a lista por ordem de telefone (Usando expressão anonima)
        Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(new Comparator<Map.Entry<Integer, Contato>>() {
            @Override
            public int compare(Map.Entry<Integer, Contato> contato1, Map.Entry<Integer, Contato> contato2) {
                return Integer.compare(contato1.getValue().getNumero(), contato2.getValue().getNumero());
            }
        }); */

        /*5 (Mais simples) - Usando o método comparing e função anonima (Interface funcional)
        Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(Comparator.comparing(
                new Function<Map.Entry<Integer, Contato>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<Integer, Contato> integerContatoEntry) {
                        return integerContatoEntry.getValue().getNumero();
                    }
                }));*/

        //5 (Mais simples ainda usando lambda expressions)
        Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(Comparator.comparing(
                contato -> contato.getValue().getNumero()));
        set.addAll(agenda.entrySet());
        System.out.println("\n-- Mostrando por ordem de número telefone--");
        for (Map.Entry<Integer, Contato> entry: set) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNumero() +
                    ": " +entry.getValue().getNome());
        }

        //6 - Mostrando lista por ordem alfabética de contatos (Refatorando com classe anonima)
        Set<Map.Entry<Integer, Contato>> set1 = new TreeSet<>(new ComparatorOrdemNomeContato());
        set1.addAll(agenda.entrySet());
        System.out.println("\n-- Mostrando lista com ordem alfabética de nome de contato --");
        for (Map.Entry<Integer, Contato> entry: set1) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
        }
    }
}

class ComparatorOrdemNumerica implements Comparator<Map.Entry<Integer, Contato>> {
    @Override
    public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
        return Integer.compare(cont1.getValue().getNumero(), cont2.getValue().getNumero());
    }
}

class ComparatorOrdemNomeContato implements Comparator<Map.Entry<Integer, Contato>> {
    @Override
    public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
        return cont1.getValue().getNome().compareToIgnoreCase(cont2.getValue().getNome());
    }
}
```

</details>

<br>

### Exercício 2

Dada uma lista de números aleatórios faça:

1. Crie uma lista de String que contenham: “1”, “0”, “4”, “1”, “2”, “3”,”9”,”6”, “5” (Use Arrray.asList).
2. Imprima todos os elementos da lista de Strings.
3. Pegue os 5 primeiros números e coloque dentro de um Set.
4. Transforme essa lista de Strings em uma lista de números inteiros (Operação map).
5. Pegue os números pares e maiores que 2 e coloque em uma lista.
6. Mostre a média dos números da lista.
7. Remova os valores impares da lista.
8. Remova os 3 primeiros números da frente e mostre os demais.
9. Retire os números repetidos da lista.
10. Mostre o menor número da lista.
11. Mostre o maior número da lista.
12. Pegue apenas os números impares e some.
13. Mostre a lista em ordem numérica.
14. Agrupe valores impares múltiplos de 3.

<details>
<summary>Código resposta:</summary>

```java
package Ex02;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //1 - Criando a lista de Strings "numeros_aleatórios
        List<String> numeros_aleatorios =
                Arrays.asList("1", "0", "4", "1", "2", "3", "9", "9", "6", "5");

        /*2 - Imprimindo todos os elementos da lista "numeros_aleatorios" (Usando Stram)
        System.out.println("-- Lista de numeros --");
        numeros_aleatorios.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.print(s + " ");
            }
        });*/

        /*2 - Forma facilida com (Stream + lambda)
        System.out.println("-- Lista de numeros --");
        numeros_aleatorios.stream().forEach(s -> System.out.print(s + " "));*/

        //2 - Forma ainda mais facilitada usando reference method (Ajuda do Gpt)
        System.out.println("-- Lista de numeros --");
        numeros_aleatorios.forEach(num -> System.out.print(num + " "));

        //3 - Pegando os 5 primeiros números e colocando em um "Set" (Ajuda do Gpt)
        /*Detalhe, set não aceita números repetidos, dai pegou apenas 4 números*/
        System.out.println("\n-- Pegando os primeiros 5 números da lista --");
        numeros_aleatorios.stream()
                .limit(5)
                .collect(Collectors.toSet())
                .forEach(num -> System.out.print(num + " "));
        //Adicionando os elementos no Set
        Set<String> collect_set = numeros_aleatorios.stream()
                .limit(5)
                .collect(Collectors.toSet());

        /*4 - Transformando a lista de Strings em números inteiros
        numeros_aleatorios.stream()
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                });*/

        /*4 - Facilitando a solução com lambda
        numeros_aleatorios.stream()
                .map(s -> Integer.parseInt(s)); */

        //4 - Facilitando ainda mais usando o reference method
        System.out.println("\n -- Mostrando lista de String convertida pra números--");
        List<Integer> lista_numeros = numeros_aleatorios.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //mostrando na tela
        System.out.println(lista_numeros);

        /*5 - Pegando os números pares maiores que 2 e colocando em lista
        System.out.println(" -- Pares maiores que 2 --");
        List<Integer> list_pares_maiores_que_2 = numeros_aleatorios.stream()
                .map(Integer::parseInt)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        if ((integer % 2 == 0) && (integer > 2)) {
                            return true;
                        }
                        return false;
                    }
                }).collect(Collectors.toList());
        //Mostrando a lista
        System.out.println(list_pares_maiores_que_2);*/

        //5 - Facilitando a resolução usando Lambda
        System.out.println(" -- Pares maiores que 2 --");
        List<Integer> list_pares_maiores_que_2 = numeros_aleatorios.stream()
                .map(Integer::parseInt)
                .filter(num -> (num % 2 ==0 && num > 2)).collect(Collectors.toList());
        //Mostrando a lista
        System.out.println(list_pares_maiores_que_2);

        /*6 - Mostrando a média dos números da lista
        numeros_aleatorios.stream()
                .mapToInt(new ToIntFunction<String>() {
                    @Override
                    public int applyAsInt(String value) {
                        return Integer.parseInt(value);
                    }
                });*/

        /*6 - Simplificando a resolução com Lambda
        numeros_aleatorios.stream()
                .mapToInt(value -> Integer.parseInt(value));*/

        /*6 - Facilitando ainda mais com reference method
        numeros_aleatorios.stream()
                .mapToInt(Integer::parseInt)
                .average()
                .ifPresent(new DoubleConsumer() {
                    @Override
                    public void accept(double value) {
                        System.out.println(value);
                    }
                });*/

        /*6 - Melhorando a "ifPresent com lambda
        numeros_aleatorios.stream()
                .mapToInt(Integer::parseInt)
                .average()
                .ifPresent(value -> System.out.println(value);*/

        //6 - Usando lambda + reference method
        System.out.println("-- Média dos números --");
        numeros_aleatorios.stream()
                .mapToInt(Integer::parseInt)
                .average()
                .ifPresent(System.out::println);

        /*7 - Removendo valores impares da lista
        System.out.println("\n -- Números impares removidos --");
        List<Integer> numeros_pares_integer = numeros_aleatorios.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //Removendo os números impares
        numeros_pares_integer.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer % 2 != 0) return true;
                return false;
            }
        });
        //Mostrando na tela
        System.out.println(numeros_pares_integer);*/

        //7 - Facilitando a resolucao usando Lambda
        System.out.println("-- Números impares removidos --");
        List<Integer> numeros_pares_integer = numeros_aleatorios.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //Removendo os números impares
        numeros_pares_integer.removeIf(integer -> (integer % 2 != 0));

        //mostrando na tela
        System.out.println(numeros_pares_integer);

    }
}
```

</details>

<br>

<br>

<br>

<br>

[Voltar a Collections](/Arquivos/Conteudo/4%20-%20Programa%C3%A7%C3%A3o%20orientada%20a%20objetos/4.2%20Collections.md)<br>
[Voltar ao inicio](/README.md)