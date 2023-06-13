# Map/ HashMap/ TreeMap

## Interface map:
* estruturas de dados em Java que permitem armazenar e manipular pares de chave-valor.

A interface **`Map`** define um mapa de associações entre chaves e valores únicos. Cada chave pode ser associada a apenas um valor, mas diferentes chaves podem estar associadas ao mesmo valor. Se trata de uma estrutura de dados em Java que permite armazenar pares de chave e valor. A interface **`Map`** é genérica e tem a seguinte assinatura: `**public interface Map<K, V>` → Onde `K` é a chave e `V` o valor

<br>

### Falando sobre HashMap:
A classe `HashMap` é uma implementação da interface `Map` que armazena as chaves e valores em uma tabela hash, que é uma estrutura de dados que permite a busca, inserção e remoção de elementos em tempo constante (O(1)), em média. A classe `HashMap` não garante a ordem em que os elementos são armazenados e permite valores nulos e uma chave nula. Algumas operações comuns em um `HashMap` são:

- **`put(K key, V value)`**: associa o valor especificado à chave especificada neste mapa.
- **`get(Object key)`**: retorna o valor associado à chave especificada neste mapa, ou **`null`** se não houver nenhuma correspondência.
- **`containsKey(Object key)`**: retorna **`true`** se este mapa contiver uma chave para a chave especificada.
- **`containsValue(Object value)`**: retorna **`true`** se este mapa mapear uma ou mais chaves para o valor especificado.
- **`remove(Object key)`**: remove a entrada para a chave especificada deste mapa, se existir.
- **`isEmpty()`**: retorna **`true`** se este mapa não contiver nenhuma entrada.
- **`size()`**: retorna o número de entradas neste mapa.
- **`keySet()`**: retorna um conjunto de todas as chaves neste mapa.
- **`values()`**: retorna uma coleção de todos os valores neste mapa.


>💡 **OBS:** HashMap é uma das classes mais usadas em Java, pois oferece uma maneira eficiente e conveniente de armazenar e recuperar pares de chave-valor. No entanto, em certas situações, pode ser necessário usar outras implementações da interface **`Map`**, como **`TreeMap`** ou **`LinkedHashMap`**, dependendo dos requisitos de ordem, sincronização ou desempenho da aplicação.

<br>

### Falando sobre TreeMap:
**`TreeMap`** é uma classe em Java que implementa a interface **`Map`** e fornece um mapa ordenado por meio de uma árvore de busca binária. Ela mantém as chaves em ordem natural crescente ou em uma ordem especificada por meio de um comparador personalizado.**`TreeMap`** é semelhante ao **`HashMap`** em muitos aspectos, mas há algumas diferenças importantes. Enquanto o **`HashMap`** não garante a ordem das chaves e dos valores, o **`TreeMap`** sempre mantém as chaves em ordem. Além disso, enquanto as operações básicas de busca, inserção e remoção em um **`HashMap`** têm tempo de execução médio constante, O(1), em um **`TreeMap`**, essas operações são realizadas em tempo de execução O(log n) no pior caso. Algumas operações comuns em um **`TreeMap`** são semelhantes às de um **`HashMap`**, como **`put(K key, V value)`**, **`get(Object key)`**, **`containsKey(Object key)`**, **`containsValue(Object value)`**, **`remove(Object key)`**, **`isEmpty()`**, **`size()`**, **`keySet()`**, **`values()`**, entre outras. Além disso, **`TreeMap`** fornece alguns métodos extras que são úteis para trabalhar com um mapa ordenado, como **`firstKey()`**, **`lastKey()`**, **`lowerKey(K key)`**, **`higherKey(K key)`**, **`subMap(K fromKey, K toKey)`** e **`descendingMap()`**, entre outros.

* A principal desvantagem do TreeMap em comparação com o HashMap é que ele é mais lento em inserções, remoções e atualizações em massa, porque precisa manter a ordem das chaves o tempo todo. No entanto, se a ordem das chaves for importante para sua aplicação, o TreeMap pode ser uma escolha melhor.

<br>

### Mostrando em código

<details>
<summary>Código</summary>

```java
//Class "main"
package com.projeto.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //Criando map (chave, valor) de "aluno"
        Map<String, String> aluno = new HashMap<>();
        aluno.put("Name", "João");
        aluno.put("idade", "17");
        aluno.put("Media", "8.5");
        aluno.put("Turma", "3A");

        System.out.println(aluno);

        //Método "keyset" para ver todas as chaves de aluno
        System.out.println(aluno.keySet());

        //Método "values" para ver valores de cada chave de aluno
        System.out.println(aluno.values());

        //Criando "aluno2"
        Map<String, String> aluno2 = new HashMap<>();
        aluno2.put("Name", "Maria");
        aluno2.put("idade", "21");
        aluno2.put("Media", "7");
        aluno2.put("Turma", "3B");

        //Criando uma lista de "aluno"
        List<Map<String, String>> lista_alunos = new ArrayList<>();

        //Adicionando "aluno" e "aluno2" na "lista_alunos"
        lista_alunos.add(aluno);
        lista_alunos.add(aluno2);

        //Mostrando a lista de alunos
        System.out.println("Lista de alunos: " + lista_alunos);

        //Método "containsKey" Buscando chave na "lista_alunos"
        System.out.println("Contem nome pesquisado: " + aluno.containsKey("Name"));
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