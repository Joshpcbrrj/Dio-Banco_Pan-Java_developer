# Operadores lógicos

São símbolos especiais que são capazes de realizar comparações lógicas entre operandos lógicos ou expressões e, em seguida, retornar um resultado.

## Tipos 

### Conjunção:

Operação lógica que só é verdadeira quando ambos os operandos ou expressões envolvidas são verdadeiras. (Operador && ou `and` lógico).

| Operando 1 | Operando 2 | Resultado |
| --- | --- | --- |
| Verdadeiro | Verdadeiro | Verdadeiro |
| Verdadeiro | Falso | Falso |
| Falso | Verdadeiro | Falso |
| Falso | Falso | Falso |

<br>

### Disjunção:
Operação que só é falsa quando ambos os operandos ou expressões envolvidas são falsas. (Operador || ou or lógico).

| Operando 1 | Operando 2 | Resultado |
| --- | --- | --- |
| Verdadeiro | Verdadeiro | Verdadeiro |
| Verdadeiro | Falso | Verdadeiro |
| Falso | Verdadeiro | Verdadeiro |
| Falso | Falso | Falso |

<br>

### Disjunção exclusiva

Operação só é verdadeira quando ambos os operandos ou expressões são opostas entre si. (Operador ^ ou `xor` lógico)

| Operando 1 | Operando 2 | Resultado |
| --- | --- | --- |
| Verdadeiro | Verdadeiro | Falso |
| Verdadeiro | Falso | Verdadeiro |
| Falso | Verdadeiro | Verdadeiro |
| Falso | Falso | Falso |

<br>

### Negação

Operação que inverte o valor lógico de um operando ou expressão (operador unário). (Operador ! ou `not` lógico).

| Operando | Resultado |
| --- | --- |
| Verdadeiro | Falso |
| Falso | Verdadeiro |

💡 **Curiosidades**:
    Temos operações que por vezes podem confundir os alunos iniciantes. Essas operações são usadas para casos específicos e não podemos considerar como operadores lógicos.
- Operador bitwise: & ou | → O operador bitwise em Java realiza operações bit a bit em valores inteiros e booleanos. Os operadores bitwise são usados para manipular valores binários (bits) em um nível mais baixo do que operações comuns de adição, subtração, multiplicação e divisão.
    
- Operador Shift ~ ou >> ou >>> ou << → O operador shift em Java é usado para deslocar os bits de um valor inteiro ou longo para a esquerda ou para a direita.

<br>

### Prática: Criando um código que demonstre o uso de operadores lógico
```java
public class ExercicioOperadoresLogicos {
    public static void main(String[] args) {
        //Criando algumas variáveis
        boolean b1 = true;
        boolean b2 = false;
        boolean b3 = true;
        boolean b4 = false;

        int i1 = 10;
        int i2 = 5;
        float f1 = 20F;
        float f2 = 50f;

        //realizando comparações com operadores
        System.out.println("Operador and: "+ (b1 && b2) );
        System.out.println("Operador and: "+ (b1 && b3) );
        System.out.println("Operador or: " + (b1 || b2));
        System.out.println("Operador or: " + (b2 || b4));
        System.out.println("Operador xor: " + (b1 ^ b2));
        System.out.println("Operador xor: " + (b2 ^ b4));
        System.out.println("Operador not: " + !b1);
        System.out.println("Operador not: " + !b2);

        //Realizando comparações com expressões
        System.out.println((i1 + i2) < (f2 - f1) && true);
        System.out.println((i1 > i2) || (f2 < f1));

    }
}
```

<br>

<br>

[Voltar a Lógica condicional e controle de fluxos em Java](/Arquivos/Conteudo/2%20-%20Conhecendo%20a%20linguagem%20Java/2.3%20Logica%20condicional%20e%20controle%20de%20fluxo%20java.md)<br>
[Voltar ao inicio](/README.md)