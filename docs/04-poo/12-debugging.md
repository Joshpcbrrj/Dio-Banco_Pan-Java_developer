# Debugging

O debugging é o processo de encontrar e corrigir erros em um código. No Java, podemos utilizar diversas ferramentas para debuggar nosso código, como o Eclipse, IntelliJ IDEA, NetBeans, entre outros.

Algumas das principais técnicas de debugging incluem:

- Usar pontos de interrupção (breakpoints) para pausar a execução do programa em um determinado ponto e examinar variáveis, estado do programa e executar o código linha a linha;
- Usar ferramentas de inspeção de variáveis para examinar valores de variáveis em tempo de execução;
- Usar logging para registrar mensagens de depuração em diferentes níveis de gravidade para ajudar a identificar onde ocorre um problema;
- Usar ferramentas de profiling para identificar gargalos de desempenho no código.


## Depuração/Debugging
- Linguagens de alto nível tornam a depuração mais fácil, pois fornecem mais ferramentas para identificar erros, como o tratamento de exceções.

- Os depuradores funcionam assumindo o controle do tempo de execução de um programa e permitindo que você o observe e controle. Para fazer isso, ele mostra a pilha do programa e permite que você a atravesse em qualquer direção. Quando você está em um depurador, obtém uma imagem mais completa de um quadro de pilha do que quando olha os rastreamentos de pilha em uma mensagem de log.

### Pilha de Execução de um Programa Java/Stack Trace

#### Pilha de Execução

Toda invocação de método é empilhada em uma estrutura de dados que isola a área de memória de cada um. Quando um método termina (retorna), ele volta para o método que o invocou.

#### Stack Trace

É a matriz onde encontramos a pilha de execução da exceção. Em outras palavras, podemos dizer que o rastreamento da pilha busca (rastreio) para a próxima linha onde a exceção pode surgir.

##### Exemplo de pilha Thread.dumpStack

<details>
<summary>Código</summary>

#### Classe Debugging

```java
public class Debugging {
    public static void main(String[] args) {
        System.out.println("Entrou no método 'main'");
        a();
        System.out.println("Finalizou o programa no método 'main'");
    }

    public static void a(){
        System.out.println("Entrou no método 'a'");
        b();
        System.out.println("Finalizou o método 'a'");
    }
    public static void b(){
        System.out.println("Entrou no método 'b'");
        for(int i = 0; i <= 5; i++){
            System.out.println(i);
        }
        c();
        System.out.println("Finalizou o método 'b'");
    }
    public static void c(){
        System.out.println("Entrou no método 'a'");
        //Mostrando pilha de processos (Sempre vamos ler de baixo pra cima
        Thread.dumpStack();
        System.out.println("Finalizou o método 'c'");
    }
}
```

#### Classe main

```java
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String [] alunos = {"Josué", "Camila", "Bruna", "Lucas"};

       double media = Calculadora_media_turma(alunos, scan);
        System.out.printf("A média da turma é %d", media);
    }

    public static double Calculadora_media_turma(String[] alunos, Scanner scanner){
        double soma = 0;
        for (String aluno: alunos){
            System.out.printf("Nota do aluno %s: ", aluno);
            double nota = scanner.nextInt();
            soma += nota;
        }

        return soma / alunos.length;
    }
}
```

</details>

<br>

### Rererências úteis:
[Alura - Exceções e controle de erros](https://www.alura.com.br/apostila-java-orientacao-objetos/excecoes-e-controle-de-erros#exercicio-para-comecar-com-os-conceitos)<br>
[SentinelOne - Java stack trace](https://www.sentinelone.com/blog/java-stack-trace-understanding/)<br>
[Alura - Java Exceções](https://www.alura.com.br/conteudo/java-excecoes)<br>
[Hostgator - O que é debug ?](https://www.hostgator.com.br/blog/debug-desenvolvimento-web/)

<br>

<br>

<br>

[Voltar ao inicio](/README.md)