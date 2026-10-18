# Blocos de código

## Definição

- Conjunto de instruções em um programa de computador que são agrupadas e executadas juntas. Um bloco de código é delimitado por chaves "{" e "}", e pode conter várias instruções, incluindo declarações de variáveis, operações aritméticas, chamadas de função, estruturas de controle de fluxo (como if, while, for, etc.) e outros comandos que compõem a lógica do programa.

- Os blocos de código são uma forma de organizar e estruturar o código fonte de um programa de computador. Eles permitem que os programadores criem estruturas de decisão complexas e iterem sobre coleções de dados de forma eficiente, tornando o código mais legível e fácil de manter.

- Quando só temos uma linha para executar não precisamos usar o par de chaves.
  
### Tipos:

- Locais → Dentro dos métodos.
  
- Estáticos → Dentro de classes.
  
- Instância → Dentro de classes.

<br>

### Pratica: Vendo o limte dos blocos de código

```java
public class ExemploBlocosCodigo {
    // Bloco de código estático
    static {
        System.out.println("Este é um bloco de código estático.");
        System.out.println("Ele é executado quando a classe é carregada.");
        System.out.println("Pode ser usado para inicializar variáveis estáticas ou realizar outras ações.");
    }

    // Método principal
    public static void main(String[] args) {
        // Bloco de código local
        {
            int numero = 10;
            System.out.println("Este é um bloco de código local.");
            System.out.println("Ele está dentro do método main e é executado quando o método é chamado.");
            System.out.println("O número é: " + numero);
        }

        // Bloco de código de instância
        ExemploBlocosCodigo exemplo = new ExemploBlocosCodigo();
        exemplo.metodoDeInstancia();
    }

    // Método de instância
    public void metodoDeInstancia() {
        // Bloco de código de instância
        {
            String mensagem = "Este é um bloco de código de instância.";
            System.out.println(mensagem);
            System.out.println("Ele está dentro de um método de instância e é executado quando o método é chamado.");
        }
    }
}
```

<br>

<br>

[Voltar a Lógica condicional e controle de fluxos em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.3%20Logica%20condicional%20e%20controle%20de%20fluxo%20java.md)<br>
[Voltar ao inicio](/README.md)