# Interface

Uma interface é uma especificação de um conjunto de métodos e constantes que uma classe deve implementar. É uma forma de definir um contrato que outras classes podem seguir. Em Java, uma interface é definida usando a palavra-chave interface e os métodos são definidos sem implementação. As classes que implementam uma interface devem fornecer uma implementação para todos os métodos da interface.

### Comparação entre interface e herança:
Tanto interface quanto herança são recursos da programação orientada a objetos que permitem a reutilização de código. No entanto, existem algumas diferenças importantes entre eles.

- A herança permite que uma classe herde características (métodos e atributos) de outra classe, permitindo a criação de uma hierarquia de classes. A classe filha pode herdar tudo ou parte do comportamento da classe pai e ainda adicionar novos comportamentos.

- Uma interface é um conjunto de métodos que uma classe deve implementar. Uma classe pode implementar várias interfaces, permitindo a adição de vários comportamentos diferentes. As interfaces não têm implementação de métodos, portanto, a implementação deve ser fornecida pela classe que as implementa.

Resumindo, a herança é usada para estabelecer uma relação "é um" entre duas classes, enquanto a interface é usada para estabelecer uma relação "pode fazer". A herança é uma relação mais forte e mais restrita do que a interface, pois a classe filha precisa herdar todos os atributos e métodos da classe pai, enquanto a interface permite que a classe que a implementa escolha quais métodos implementar.

<br>

## Exemplo prático de uso de interface

<details>
<summary>Código</summary>

```java
public class Main {
    public static void main(String[] args) {
        Jogo xbox = new Xbox();
        xbox.jogar();

        Jogo playStation = new PlayStation();
        playStation.jogar();
    }
}
```

```java
public interface Jogo {
    void jogar();
}
```

```java
public class Xbox implements Jogo {
	
		@Override
    public void jogar() {
        System.out.println("Jogando no Xbox...");
    }
}
```

```java
public class PlayStation implements Jogo {

		@Override
    public void jogar() {
        System.out.println("Jogando no PlayStation...");
    }
}
```


</details>

<br>

<br>

<br>

[Voltar a Programação orientada a objetos com Java](/Arquivos/Conteudo/4%20-%20Programa%C3%A7%C3%A3o%20orientada%20a%20objetos/4.1%20Programacao%20orientada%20a%20objetos%20com%20java.md)<br>
[Voltar ao inicio](/README.md)