# Conceitos importantes de métodos

## Definição de método

- Em Java, o termo "método" é usado para se referir a uma função ou procedimento que é definido dentro de uma classe. Um método em Java é uma coleção de instruções que realizam uma determinada tarefa.
- Os métodos são usados para encapsular a lógica de um programa, de modo que ela possa ser facilmente reutilizada em vários pontos do programa. Um método pode receber argumentos de entrada, processar esses argumentos e retornar um valor ou simplesmente realizar uma ação sem retornar nada.
- Para chamar um método em Java, é necessário primeiro criar uma instância da classe que contém o método e, em seguida, chamar o método usando o nome do método e os argumentos necessários.
- Em resumo, um método em Java é um bloco de código que pode ser chamado várias vezes a partir de diferentes partes do programa, tornando o código mais modular, organizado e reutilizável.

### Como criar métodos
```java

public <visibilidade op> <tipo op> <modificador op> <retorno> <nome> 
<parametros op> throws <excecoesop> {
    // Corpo do método
}
```

#### Explicando as partes:
- **`<visibilidade>`**: especifica a visibilidade do método, ou seja, quem pode acessá-lo. Pode ser **`public`**, **`private`** ou **`protected`**, ou pode ser omitido para tornar o método acessível apenas dentro do pacote em que ele está definido.
- **`<tipo>`**: especifica o tipo de dado que o método retorna, ou **`void`** se o método não retorna valor.
- **`<modificador>`**: especifica um modificador adicional para o método, como **`final`**, **`static`**, **`synchronized`**, entre outros.
- **`<retorno>`**: palavra-chave que indica o valor que o método deve retornar. Pode ser omitido se o método não retornar valor.
- **`<nome>`**: nome do método, utilizado para chamá-lo posteriormente.
- **`<parametros>`**: lista de parâmetros do método, separados por vírgulas, que especifica os valores que o método irá receber como entrada.
- **`<excecoes>`**: lista de exceções que o método pode lançar, separadas por vírgulas.
- **`corpo`**: o bloco de código que define as instruções que o método irá executar.

<br>

## Conceitos chave 

### Assinatura do método

A assinatura de método em Java é a combinação única do nome do método e dos tipos e ordem dos parâmetros que ele recebe. A assinatura do método é usada pelo compilador Java para determinar qual método deve ser chamado quando há vários métodos com o mesmo nome em uma classe ou em suas superclasses. A assinatura de um método inclui o nome do método e os tipos de seus parâmetros, mas não inclui o tipo de retorno do método. Por exemplo, o seguinte método tem a assinatura "exibir(String, int)":

```java
public void exibir(String texto, int numero) {
  // código do método
}
	
/* A assinatura do método acima é determinada pelo nome "exibir" e pelos tipos de seus 
	 parâmetros "String" e "int". Se houvesse outro método com o nome "exibir" na mesma 
	 classe, mas com parâmetros diferentes, o compilador Java poderia escolher o método 
   correto com base em suas assinaturas. */

```

### Construtor e destrutor

#### Construtor
Em Java, construtor e destrutor são conceitos relacionados à criação e destruição de objetos. O construtor é um método especial que é chamado automaticamente quando um objeto é criado. Ele é responsável por inicializar os valores dos campos do objeto e executar outras ações necessárias para deixar o objeto em um estado válido. O construtor tem o mesmo nome da classe e não tem tipo de retorno, nem mesmo void.

```java
//Class "Pessoa" (Geralmente classes tem primeira letra em maiúsculo)
public class Pessoa {
    private String nome;
    private int idade;

		//Construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
/* O construtor acima recebe dois parâmetros, um para o nome e outro para a idade, 
	 e atribui esses valores aos campos correspondentes. */
```

#### Destrutor 
- Já o destrutor em Java é chamado de "garbage collector" e é executado automaticamente pelo sistema para destruir objetos que não estão mais sendo usados pelo programa. O garbage collector é responsável por liberar a memória ocupada por esses objetos e torná-la disponível para outros usos.
- Não é necessário implementar um destrutor em Java, pois o garbage collector cuida dessa tarefa automaticamente. No entanto, é possível fornecer um método "finalize()" em uma classe, que será chamado pelo garbage collector antes de destruir o objeto correspondente. Esse método pode ser usado para executar ações de limpeza ou liberação de recursos associados ao objeto, por exemplo.

### Passagem de parâmetro

- Em programação, "passagem de parâmetros" se refere ao processo de transmitir argumentos ou valores para um método ou função. Os parâmetros são informações que o método precisa para executar uma determinada ação. A passagem de parâmetros é uma das formas mais comuns de comunicação entre diferentes partes de um programa.
- Em geral, a passagem de parâmetros pode ser feita de duas formas: por valor(Cópia) ou por referência(Endereço na memória) . Na passagem por valor, uma cópia do valor do argumento é passada para o método. Já na passagem por referência, é passada uma referência ou endereço de memória do argumento para o método, o que permite que o método altere o valor do argumento original.
- Em Java, a passagem de parâmetros é feita sempre por valor, mesmo quando se trata de tipos de referência. Isso significa que, quando um objeto é passado como argumento para um método, na verdade está sendo passada uma cópia da referência do objeto e não do objeto em si. Se o método altera o estado do objeto, essa alteração será refletida também no objeto original, já que ambas as referências apontam para o mesmo objeto na memória. Porém, se o método atribui um novo objeto à referência, essa alteração não será refletida fora do método.

```java
public class ExemploParametros {
  
  // Método que recebe um valor por parâmetro e o altera
  public static void alterarValor(int valor) {
    valor = 10;
    System.out.println("Dentro do método alterarValor: " + valor);
  }
  
  // Método que recebe um objeto por parâmetro e altera um de seus atributos
  public static void alterarObjeto(Objeto obj) {
    obj.setValor(10);
    System.out.println("Dentro do método alterarObjeto: " + obj.getValor());
  }
  
  public static void main(String[] args) {
    int valor = 5;
    System.out.println("Antes do método alterarValor: " + valor);
    alterarValor(valor);
    System.out.println("Depois do método alterarValor: " + valor);
    
    Objeto obj = new Objeto(5);
    System.out.println("Antes do método alterarObjeto: " + obj.getValor());
    alterarObjeto(obj);
    System.out.println("Depois do método alterarObjeto: " + obj.getValor());
  }
}

class Objeto {
  private int valor;
 
	//Construtor 
  public Objeto(int valor) {
    this.valor = valor;
  }
  
 //Getters e setters
  public int getValor() {
    return valor;
  }
  public void setValor(int valor) {
    this.valor = valor;
  }
}
```

<br>

### Algumas dicas de boas práticas: 🛩️

- Métodos devem ter nomes curtos e descritivos.
- As notações mais comuns são: camelCase e snake_case.
- Deve ter de 80 a 120 linhas (geralmente).
- Evite lista de parâmetros muito longas.
- Visibilidade adequada a funcionalidade (Se o método vai ser publico ou só para uso privado).

<br>

<br>

[Voltar a métodos em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.6%20Metodos%20em%20java.md)<br>
[Voltar ao inicio](/README.md)