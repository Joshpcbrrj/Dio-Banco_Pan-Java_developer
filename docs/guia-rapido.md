# ⚡ Guia rápido e glossário

> Uma "cola" com os comandos, as sintaxes e as anotações mais usados no bootcamp, e os termos que aparecem nas aulas. Para a explicação completa, siga os links para as aulas.

[🏠 Voltar ao índice](../README.md)

## Sumário

- [Git](#git)
- [Terminal: compilar e rodar Java](#terminal-compilar-e-rodar-java)
- [Sintaxe básica do Java](#sintaxe-básica-do-java)
- [Collections](#collections)
- [Stream API](#stream-api)
- [Maven](#maven)
- [JUnit 5](#junit-5)
- [HTTP: métodos e códigos de status](#http-métodos-e-códigos-de-status)
- [Anotações do Spring](#anotações-do-spring)
- [SQL](#sql)
- [JDBC](#jdbc)
- [MongoDB (mongosh)](#mongodb-mongosh)
- [Docker](#docker)
- [Erros comuns](./08-extras/06-erros-comuns.md)
- [Glossário](#glossário)

## Git

| Comando | O que faz | Aula |
| --- | --- | --- |
| `git config --global user.name "Nome"` | Define seu nome nos commits | [1.3](./01-principios/03-primeiro-commit.md) |
| `git init` | Transforma a pasta em um repositório | [1.3](./01-principios/03-primeiro-commit.md) |
| `git clone <url>` | Baixa um repositório remoto | [1.1](./01-principios/01-git-e-github.md) |
| `git status` | Mostra o estado dos arquivos | [1.4](./01-principios/04-ciclo-de-vida-e-conflitos.md) |
| `git add .` | Prepara todas as alterações (*staging*) | [1.3](./01-principios/03-primeiro-commit.md) |
| `git commit -m "msg"` | Salva um ponto no histórico | [1.3](./01-principios/03-primeiro-commit.md) |
| `git log --oneline` | Histórico resumido | [1.2](./01-principios/02-como-o-git-funciona.md) |
| `git switch -c <branch>` | Cria uma branch e muda para ela | [1.4](./01-principios/04-ciclo-de-vida-e-conflitos.md) |
| `git merge <branch>` | Junta a branch na atual | [1.4](./01-principios/04-ciclo-de-vida-e-conflitos.md) |
| `git restore <arquivo>` | Descarta alterações não preparadas | [1.4](./01-principios/04-ciclo-de-vida-e-conflitos.md) |
| `git push` / `git pull` | Envia / traz commits do GitHub | [1.3](./01-principios/03-primeiro-commit.md) |

## Terminal: compilar e rodar Java

| Comando | O que faz | Aula |
| --- | --- | --- |
| `java -version` | Mostra a versão do Java instalada | [2.1](./02-java/01-historia-e-plataforma.md) |
| `javac Ola.java` | Compila e gera `Ola.class` | [2.10](./02-java/10-terminal.md) |
| `java Ola` | Executa a classe compilada (sem `.class`) | [2.10](./02-java/10-terminal.md) |
| `java Ola.java` | Compila e executa de uma vez (Java 11+) | [2.10](./02-java/10-terminal.md) |
| `javac -d out src/com/app/*.java` | Compila para a pasta `out` | [2.10](./02-java/10-terminal.md) |
| `java -cp out com.app.Main` | Executa uma classe dentro de um pacote | [2.10](./02-java/10-terminal.md) |
| `jshell` | Abre o console interativo do Java | [2.10](./02-java/10-terminal.md) |
| `javadoc -d doc *.java` | Gera a documentação em HTML | [2.9](./02-java/09-javadoc.md) |

## Sintaxe básica do Java

```java
// Tipos primitivos mais usados               (aula 2.4)
int idade = 30;         long populacao = 8_000_000_000L;
double preco = 9.90;    boolean ativo = true;      char letra = 'A';
String nome = "Ana";    final double PI = 3.14159;   // constante
var cidade = "Recife";  // tipo inferido (Java 10+)

// Condicionais                                 (aula 2.13)
if (idade >= 18) { ... } else if (idade >= 16) { ... } else { ... }
String tipo = switch (dia) {
    case SABADO, DOMINGO -> "fim de semana";
    default -> "dia útil";
};
String status = idade >= 18 ? "adulto" : "menor";   // ternário

// Repetição e arrays                           (aula 2.15)
for (int i = 0; i < 10; i++) { ... }
for (String item : lista) { ... }
while (condicao) { ... }
int[] notas = {7, 8, 9};      int[] vazio = new int[5];

// Classe, construtor e método                  (aulas 4.1 e 2.31)
public class Conta {
    private double saldo;
    public Conta(double saldoInicial) { this.saldo = saldoInicial; }
    public void depositar(double valor) { saldo += valor; }
    public double getSaldo() { return saldo; }
}

// Exceções                                     (aula 4.14)
try { ... } catch (NumberFormatException e) { ... } finally { ... }

// Record (Java 16+)                            (aula 8.1)
public record Cliente(String nome, String cpf) { }

// Enum e membros estáticos                     (aula 4.5)
public enum Status { ABERTO, PAGO, CANCELADO }
public static final double TAXA = 0.02;         // constante da classe

// Strings e Scanner                            (aula 2.11)
String limpo = texto.strip().toLowerCase();     // strings são imutáveis: guarde o resultado
boolean igual = "admin".equals(perfil);         // compare com equals, nunca com ==
String msg = String.format("%s tem %d anos", nome, idade);
Scanner entrada = new Scanner(System.in).useLocale(Locale.US);
```

| Operador | Significado | Aula |
| --- | --- | --- |
| `+ - * / %` | Aritméticos (`%` é o resto da divisão) | [2.5](./02-java/05-operadores.md) |
| `== != > < >= <=` | Relacionais | [2.12](./02-java/12-operadores-relacionais-e-logicos.md) |
| <code>&amp;&amp; &#124;&#124; !</code> | E, ou, não (lógicos) | [2.12](./02-java/12-operadores-relacionais-e-logicos.md) |
| `++ -- += -=` | Incremento e atribuição composta | [2.5](./02-java/05-operadores.md) |
| `a.equals(b)` | Compara o **conteúdo** de objetos (use em `String`) | [2.25](./02-java/25-equals-e-hashcode.md) |

## Collections

| Tipo | Quando usar | Implementação comum | Aula |
| --- | --- | --- | --- |
| `List` | Ordem de inserção, aceita repetidos, acesso por índice | `ArrayList` | [4.9](./04-poo/09-list.md) |
| `Set` | Sem repetidos | `HashSet`, `LinkedHashSet`, `TreeSet` | [4.10](./04-poo/10-set.md) |
| `Map` | Pares chave → valor | `HashMap`, `LinkedHashMap`, `TreeMap` | [4.11](./04-poo/11-map.md) |
| `Queue` / `Deque` | Filas (FIFO) e pilhas (LIFO) | `ArrayDeque`, `PriorityQueue` | [2.27](./02-java/27-queue.md) |

```java
List<String> nomes = new ArrayList<>(List.of("Ana", "Bia"));
nomes.add("Caio");   nomes.get(0);   nomes.remove("Bia");   nomes.size();
nomes.sort(Comparator.naturalOrder());

Map<String, Integer> estoque = new HashMap<>();
estoque.put("maçã", 10);   estoque.getOrDefault("pera", 0);
estoque.merge("maçã", 5, Integer::sum);   // soma 5 ao valor existente
for (var e : estoque.entrySet()) { e.getKey(); e.getValue(); }
```

## Stream API

```java
List<String> resultado = produtos.stream()          // aula 4.12
        .filter(p -> p.getPreco() > 100)            // mantém só os que passam
        .sorted(Comparator.comparing(Produto::getNome))
        .map(Produto::getNome)                      // transforma
        .toList();                                  // coleta (Java 16+)

double total = produtos.stream().mapToDouble(Produto::getPreco).sum();
Map<String, List<Produto>> porCategoria = produtos.stream()
        .collect(Collectors.groupingBy(Produto::getCategoria));
```

## Maven

| Comando | O que faz | Aula |
| --- | --- | --- |
| `mvn archetype:generate -DgroupId=com.app -DartifactId=meu-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false` | Cria um projeto | [4.16](./04-poo/16-maven-comandos.md) |
| `mvn compile` | Compila o código | [4.16](./04-poo/16-maven-comandos.md) |
| `mvn test` | Roda os testes | [4.18](./04-poo/18-maven-ciclo-de-vida.md) |
| `mvn package` | Gera o `.jar` em `target/` | [4.18](./04-poo/18-maven-ciclo-de-vida.md) |
| `mvn clean install` | Build do zero e instala no repositório local (`~/.m2`) | [4.18](./04-poo/18-maven-ciclo-de-vida.md) |
| `mvn dependency:tree` | Mostra a árvore de dependências | [4.17](./04-poo/17-maven-dependencias.md) |
| `./mvnw spring-boot:run` | Sobe uma aplicação Spring Boot | [6.12](./06-spring/12-spring-boot.md) |

| Escopo | Disponível em | Exemplo |
| --- | --- | --- |
| `compile` (padrão) | Compilação, testes e execução | Spring, Jackson |
| `test` | Só nos testes | JUnit, Mockito |
| `runtime` | Execução, não na compilação | Driver do PostgreSQL |
| `provided` | Compilação; quem roda fornece | Lombok, API de Servlet |

## JUnit 5

```java
class CalculadoraTest {                                   // aulas 4.21 e 4.22
    @BeforeEach void preparar() { ... }

    @Test
    @DisplayName("soma dois números positivos")
    void deveSomar() {
        assertEquals(5, calc.somar(2, 3));
    }

    @Test
    void deveRecusarDivisaoPorZero() {
        assertThrows(ArithmeticException.class, () -> calc.dividir(1, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6})
    void devemSerPares(int n) { assertTrue(n % 2 == 0); }
}
```

## HTTP: métodos e códigos de status

| Método | Uso em APIs REST | Exemplo |
| --- | --- | --- |
| `GET` | Ler | `GET /clientes/42` |
| `POST` | Criar | `POST /clientes` |
| `PUT` | Substituir por completo | `PUT /clientes/42` |
| `PATCH` | Alterar parte | `PATCH /clientes/42` |
| `DELETE` | Remover | `DELETE /clientes/42` |

| Código | Significado | Quando aparece |
| --- | --- | --- |
| `200 OK` | Deu certo | `GET`, `PUT` |
| `201 Created` | Recurso criado | `POST` |
| `204 No Content` | Deu certo, sem corpo | `DELETE` |
| `400 Bad Request` | Requisição inválida | JSON malformado, validação |
| `401 Unauthorized` | Não autenticado | Sem login ou token |
| `403 Forbidden` | Autenticado, mas sem permissão | Perfil sem acesso |
| `404 Not Found` | Não encontrado | ID inexistente |
| `500 Internal Server Error` | Erro no servidor | Exceção não tratada |

Mais detalhes nas aulas [6.2](./06-spring/02-mensagens-http.md) e [6.14](./06-spring/14-rest.md).

## Anotações do Spring

| Anotação | Para quê | Aula |
| --- | --- | --- |
| `@SpringBootApplication` | Classe principal (configuração + *component scan*) | [6.12](./06-spring/12-spring-boot.md) |
| `@Component` / `@Service` / `@Repository` | Transforma a classe em bean | [6.11](./06-spring/11-ioc-di-e-beans.md) |
| `@Configuration` + `@Bean` | Cria beans de classes que não são suas | [6.11](./06-spring/11-ioc-di-e-beans.md) |
| `@Autowired` | Injeta dependência (prefira o construtor) | [6.11](./06-spring/11-ioc-di-e-beans.md) |
| `@Value("${chave}")` | Lê uma propriedade | [6.13](./06-spring/13-projeto-spring-boot.md) |
| `@ConfigurationProperties` | Lê um grupo de propriedades | [6.13](./06-spring/13-projeto-spring-boot.md) |
| `@RestController` | Classe de endpoints que devolvem JSON | [6.15](./06-spring/15-rest-controller.md) |
| `@RequestMapping("/caminho")` | Prefixo das rotas | [6.15](./06-spring/15-rest-controller.md) |
| `@GetMapping` `@PostMapping` `@PutMapping` `@DeleteMapping` | Rota de cada método HTTP | [6.15](./06-spring/15-rest-controller.md) |
| `@PathVariable` / `@RequestParam` / `@RequestBody` | Lê da URL / da *query string* / do corpo | [6.15](./06-spring/15-rest-controller.md) |
| `@Valid` | Valida o objeto recebido | [6.17](./06-spring/17-tratamento-de-erros.md) |
| `@RestControllerAdvice` + `@ExceptionHandler` | Tratamento global de erros | [6.17](./06-spring/17-tratamento-de-erros.md) |
| `@Entity` / `@Id` / `@GeneratedValue` | Mapeia a classe para uma tabela | [6.25](./06-spring/25-spring-data-jpa.md) |
| `@OneToMany` / `@ManyToOne` | Relacionamentos JPA | [6.25](./06-spring/25-spring-data-jpa.md) |
| `@Transactional` | Executa o método em uma transação | [6.25](./06-spring/25-spring-data-jpa.md) |
| `@EnableWebSecurity` + `SecurityFilterChain` | Configura o Spring Security | [6.20](./06-spring/20-autenticacao.md) |
| `@EnableFeignClients` / `@FeignClient` | Cliente HTTP declarativo | [6.27](./06-spring/27-padroes-com-spring.md) |

## SQL

| Comando | O que faz | Aula |
| --- | --- | --- |
| `CREATE TABLE t (id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, ...)` | Cria uma tabela com id automático | [5.4](./05-banco-de-dados/04-sql-criando-tabelas.md) |
| `col INTEGER REFERENCES outra (id)` | Chave estrangeira | [5.4](./05-banco-de-dados/04-sql-criando-tabelas.md) |
| `ALTER TABLE t ADD COLUMN c VARCHAR(20)` | Acrescenta uma coluna | [5.4](./05-banco-de-dados/04-sql-criando-tabelas.md) |
| `INSERT INTO t (a, b) VALUES (1, 'x')` | Insere uma linha | [5.5](./05-banco-de-dados/05-sql-inserindo-e-alterando.md) |
| `UPDATE t SET a = 2 WHERE id = 1` | Altera (**sempre** com `WHERE`) | [5.5](./05-banco-de-dados/05-sql-inserindo-e-alterando.md) |
| `DELETE FROM t WHERE id = 1` | Apaga (**sempre** com `WHERE`) | [5.5](./05-banco-de-dados/05-sql-inserindo-e-alterando.md) |
| `SELECT a, b FROM t WHERE ... ORDER BY a DESC LIMIT 10` | Consulta, ordena e limita | [5.6](./05-banco-de-dados/06-sql-consultas.md) |
| `WHERE x IN (...)` / `BETWEEN` / `LIKE 'A%'` / `IS NULL` | Filtros comuns | [5.6](./05-banco-de-dados/06-sql-consultas.md) |
| `SELECT c, COUNT(*) FROM t GROUP BY c HAVING COUNT(*) > 1` | Agrupa e filtra grupos | [5.6](./05-banco-de-dados/06-sql-consultas.md) |
| `FROM a JOIN b ON b.a_id = a.id` | Junta tabelas (só quem tem par) | [5.7](./05-banco-de-dados/07-sql-joins-e-subconsultas.md) |
| `FROM a LEFT JOIN b ON ...` | Todos de `a`, com `b` quando houver | [5.7](./05-banco-de-dados/07-sql-joins-e-subconsultas.md) |
| `BEGIN; ... COMMIT;` / `ROLLBACK;` | Transação: tudo ou nada | [5.8](./05-banco-de-dados/08-transacoes-indices-e-views.md) |
| `CREATE INDEX idx ON t (coluna)` / `EXPLAIN SELECT ...` | Cria índice / mostra o plano da consulta | [5.8](./05-banco-de-dados/08-transacoes-indices-e-views.md) |

Ordem de execução de um `SELECT`: `FROM` → `WHERE` → `GROUP BY` → `HAVING` → `SELECT` → `ORDER BY` → `LIMIT`.

## JDBC

```java
String sql = "SELECT id, nome FROM produtos WHERE preco > ?";      // aula 5.9
try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
     PreparedStatement comando = conexao.prepareStatement(sql)) {
    comando.setBigDecimal(1, new BigDecimal("100"));                 // nunca concatene valores!
    try (ResultSet linhas = comando.executeQuery()) {
        while (linhas.next()) {
            System.out.println(linhas.getInt("id") + " " + linhas.getString("nome"));
        }
    }
}
```

## MongoDB (mongosh)

| Comando | O que faz | Aula |
| --- | --- | --- |
| `show dbs` / `use loja` | Lista bancos / usa (ou cria) um banco | [5.12](./05-banco-de-dados/12-mongodb-comandos.md) |
| `db.produtos.insertOne({...})` | Insere um documento | [5.12](./05-banco-de-dados/12-mongodb-comandos.md) |
| `db.produtos.find({ preco: { $gt: 100 } })` | Busca com filtro | [5.12](./05-banco-de-dados/12-mongodb-comandos.md) |
| `db.produtos.updateOne({ nome: "X" }, { $set: { preco: 9 } })` | Atualiza | [5.12](./05-banco-de-dados/12-mongodb-comandos.md) |
| `db.produtos.deleteMany({ ativo: false })` | Remove | [5.12](./05-banco-de-dados/12-mongodb-comandos.md) |
| `db.produtos.createIndex({ nome: 1 })` | Cria um índice | [5.14](./05-banco-de-dados/14-mongodb-indices-e-agregacoes.md) |
| `db.produtos.aggregate([...])` | Agregações (agrupar, somar...) | [5.14](./05-banco-de-dados/14-mongodb-indices-e-agregacoes.md) |

## Docker

| Comando | O que faz | Aula |
| --- | --- | --- |
| `docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=senha postgres:17` | Sobe um PostgreSQL | [8.3](./08-extras/03-docker-para-java.md) |
| `docker ps` | Lista os contêineres rodando | [8.3](./08-extras/03-docker-para-java.md) |
| `docker logs <nome>` | Mostra os logs | [8.3](./08-extras/03-docker-para-java.md) |
| `docker compose up -d` / `down` | Sobe / derruba os serviços do `compose.yaml` | [8.3](./08-extras/03-docker-para-java.md) |

## Glossário

| Termo | Significado | Aula |
| --- | --- | --- |
| **ACID** | Garantias de uma transação: atomicidade, consistência, isolamento e durabilidade | [5.8](./05-banco-de-dados/08-transacoes-indices-e-views.md) |
| **API** | Conjunto de "portas" (endpoints) pelas quais um sistema conversa com outro | [6.14](./06-spring/14-rest.md) |
| **Array** | Sequência de tamanho fixo de valores do mesmo tipo | [2.15](./02-java/15-repeticao-e-arrays.md) |
| **Bean** | Objeto criado e gerenciado pelo Spring | [6.11](./06-spring/11-ioc-di-e-beans.md) |
| **Branch** | Linha paralela de desenvolvimento no Git | [1.4](./01-principios/04-ciclo-de-vida-e-conflitos.md) |
| **Bytecode** | Código intermediário (`.class`) que a JVM executa | [2.1](./02-java/01-historia-e-plataforma.md) |
| **Chave estrangeira (FK)** | Coluna que aponta para a chave primária de outra tabela | [5.2](./05-banco-de-dados/02-modelagem-relacional.md) |
| **Chave primária (PK)** | Coluna que identifica cada linha de uma tabela | [5.2](./05-banco-de-dados/02-modelagem-relacional.md) |
| **Classe** | Molde que define atributos e comportamentos de objetos | [4.1](./04-poo/01-classes-e-objetos.md) |
| **Classe abstrata** | Classe incompleta, que não pode ser instanciada e serve de base para outras | [4.5](./04-poo/05-abstratas-static-enum.md) |
| **Commit** | Ponto salvo no histórico do Git | [1.3](./01-principios/03-primeiro-commit.md) |
| **Construtor** | Método especial que inicializa um objeto no `new` | [2.31](./02-java/31-metodos-conceitos.md) |
| **CRUD** | *Create, Read, Update, Delete*: as quatro operações básicas com dados | [5.5](./05-banco-de-dados/05-sql-inserindo-e-alterando.md) |
| **Dependência (Maven)** | Biblioteca de que o projeto precisa, declarada no `pom.xml` | [4.17](./04-poo/17-maven-dependencias.md) |
| **DTO** | Objeto só para transportar dados entre camadas (por exemplo, o JSON de uma API) | [8.5](./08-extras/05-boas-praticas-api.md) |
| **Encapsulamento** | Esconder os dados (`private`) e expor só o necessário (métodos) | [4.1](./04-poo/01-classes-e-objetos.md) |
| **Endpoint** | Uma rota da API, como `GET /clientes` | [6.15](./06-spring/15-rest-controller.md) |
| **Enum** | Tipo com um conjunto fechado de valores, como `PAGO` e `CANCELADO` | [4.5](./04-poo/05-abstratas-static-enum.md) |
| **Exceção** | Objeto que representa um erro durante a execução | [4.14](./04-poo/14-excecoes.md) |
| **Flyway / migração** | Arquivos SQL versionados que aplicam as mudanças no banco, em ordem | [8.5](./08-extras/05-boas-praticas-api.md) |
| **Generics** | Parâmetros de tipo, como em `List<String>` | [2.18](./02-java/18-estruturas-de-dados.md) |
| **Hash** | Número calculado a partir de um objeto, usado para achá-lo rápido em `HashSet`/`HashMap` | [2.25](./02-java/25-equals-e-hashcode.md) |
| **Herança** | Uma classe reaproveitar outra com `extends` | [4.2](./04-poo/02-heranca-e-polimorfismo.md) |
| **HTTPS** | HTTP protegido por criptografia (TLS) | [6.7](./06-spring/07-tls.md) |
| **Índice (banco)** | Estrutura que acelera buscas, como o índice remissivo de um livro | [5.8](./05-banco-de-dados/08-transacoes-indices-e-views.md) |
| **Injeção de dependência (DI)** | O Spring entrega os objetos de que a classe precisa, em vez de ela criá-los | [6.11](./06-spring/11-ioc-di-e-beans.md) |
| **Interface** | Contrato com métodos que as classes se comprometem a implementar | [4.4](./04-poo/04-interfaces.md) |
| **IoC** | Inversão de controle: o framework, e não você, cria e liga os objetos | [6.11](./06-spring/11-ioc-di-e-beans.md) |
| **JDBC** | API padrão do Java para conversar com bancos relacionais | [5.9](./05-banco-de-dados/09-jdbc.md) |
| **JDK / JRE / JVM** | Kit de desenvolvimento / ambiente de execução / máquina virtual que roda o bytecode | [2.1](./02-java/01-historia-e-plataforma.md) |
| **JOIN** | Junta linhas de tabelas relacionadas numa consulta | [5.7](./05-banco-de-dados/07-sql-joins-e-subconsultas.md) |
| **JPA / Hibernate** | Especificação / implementação que mapeia objetos Java para tabelas | [6.25](./06-spring/25-spring-data-jpa.md) |
| **JSON** | Formato de texto para trocar dados: `{"nome": "Ana"}` | [6.14](./06-spring/14-rest.md) |
| **JWT** | Token assinado que prova quem é o usuário em cada requisição | [6.22](./06-spring/22-jwt.md) |
| **Lambda** | Função curta sem nome: `x -> x * 2` | [4.12](./04-poo/12-stream-api.md) |
| **LIFO / FIFO** | Último a entrar, primeiro a sair (pilha) / primeiro a entrar, primeiro a sair (fila) | [2.19](./02-java/19-pilhas.md) |
| **Log** | Registro do que a aplicação faz, com data, nível e origem | [8.5](./08-extras/05-boas-praticas-api.md) |
| **LTS** | Versão com suporte de longo prazo (Java 17, 21, 25) | [2.1](./02-java/01-historia-e-plataforma.md) |
| **Mock** | Objeto "dublê" usado nos testes no lugar de uma dependência real | [8.4](./08-extras/04-testes-spring-boot.md) |
| **Normalização** | Regras (1FN, 2FN, 3FN) para organizar tabelas sem repetição | [5.3](./05-banco-de-dados/03-normalizacao.md) |
| **NoSQL** | Bancos que não usam tabelas relacionais (documentos, chave-valor...) | [5.10](./05-banco-de-dados/10-nosql.md) |
| **NPE** | `NullPointerException`: usar um método em algo que vale `null` | [8.2](./08-extras/02-optional-e-null.md) |
| **Objeto** | Uma instância concreta de uma classe | [4.1](./04-poo/01-classes-e-objetos.md) |
| **ORM** | Mapeamento objeto-relacional (tabelas ↔ classes) | [6.25](./06-spring/25-spring-data-jpa.md) |
| **Pacote** | Pasta que organiza as classes (`com.empresa.app`) | [4.6](./04-poo/06-pacotes.md) |
| **Paginação** | Devolver uma lista em partes (páginas), em vez de tudo de uma vez | [8.5](./08-extras/05-boas-praticas-api.md) |
| **Polimorfismo** | O mesmo método se comportar de forma diferente em cada subclasse | [4.2](./04-poo/02-heranca-e-polimorfismo.md) |
| **POM** | O `pom.xml`, arquivo de configuração do Maven | [4.15](./04-poo/15-maven.md) |
| **Repository** | Camada que conversa com o banco de dados | [6.25](./06-spring/25-spring-data-jpa.md) |
| **REST** | Estilo de arquitetura de APIs baseado em recursos e métodos HTTP | [6.14](./06-spring/14-rest.md) |
| **SGBD** | Programa que gerencia o banco de dados (PostgreSQL, MySQL, MongoDB...) | [5.1](./05-banco-de-dados/01-o-que-e-banco-de-dados.md) |
| **Sobrecarga / sobrescrita** | Mesmo nome com parâmetros diferentes / redefinir o método herdado | [2.32](./02-java/32-sobrecarga-e-sobrescrita.md) |
| **Sprint** | Ciclo curto (1 a 4 semanas) de trabalho no Scrum | [1.8](./01-principios/08-scrum.md) |
| **SQL** | Linguagem para criar e consultar bancos relacionais | [5.4](./05-banco-de-dados/04-sql-criando-tabelas.md) |
| **SQL injection** | Ataque que insere comandos SQL pela entrada do usuário; evitado com `PreparedStatement` | [5.9](./05-banco-de-dados/09-jdbc.md) |
| **Stack trace** | A "pilha" de chamadas impressa junto com um erro | [4.13](./04-poo/13-debugging.md) |
| **static** | Membro que pertence à classe, e não a cada objeto | [4.5](./04-poo/05-abstratas-static-enum.md) |
| **String** | Classe que representa textos; é imutável | [2.11](./02-java/11-strings-e-entrada.md) |
| **Swagger / OpenAPI** | Padrão e ferramenta para documentar e testar APIs | [6.16](./06-spring/16-openapi-swagger.md) |
| **TDD** | Escrever o teste antes do código | [4.23](./04-poo/23-testes-boas-praticas.md) |
| **Teste unitário** | Teste automático de uma pequena parte do código, isolada | [4.21](./04-poo/21-testes-unitarios.md) |
| **Transação** | Grupo de comandos que acontece por completo ou não acontece | [5.8](./05-banco-de-dados/08-transacoes-indices-e-views.md) |
| **View** | Consulta SQL salva com um nome, usada como uma tabela | [5.8](./05-banco-de-dados/08-transacoes-indices-e-views.md) |

---

<div align="center">

[🏠 Voltar ao índice](../README.md)

</div>
