# Primeiros passos no SQL

## Definição:

SQL, ou Structured Query Language, é uma linguagem de programação usada para gerenciar dados em um banco de dados relacional. Abaixo, você encontrará algumas informações importantes sobre o SQL.

### Criação do SQL

O SQL foi criado pela IBM na década de 1970 como parte de um projeto de pesquisa para desenvolver um sistema de gerenciamento de banco de dados relacional chamado System R. O projeto foi liderado por Donald D. Chamberlin e Raymond F. Boyce, que criaram uma linguagem de consulta baseada em inglês para acessar e manipular dados armazenados em bancos de dados relacionais. Essa linguagem de consulta, que foi inicialmente chamada de SEQUEL (Structured English QUERY Language), evoluiu posteriormente para o SQL que conhecemos hoje. A IBM lançou o System R em 1979, tornando-o o primeiro sistema de gerenciamento de banco de dados relacional comercial disponível no mercado. Desde então, o SQL se tornou uma linguagem padrão para o gerenciamento de dados em bancos de dados relacionais em todo o mundo.

### Como funciona o SQL

o SQL tem por base a teoria dos conjuntos, que é um ramo da matemática que estuda conjuntos e as operações que podem ser realizadas com eles, como união, interseção e diferença. Em termos simples, o SQL funciona manipulando conjuntos de dados armazenados em um banco de dados relacional. Os dados são organizados em tabelas que consistem em linhas e colunas. As linhas representam registros individuais no banco de dados, enquanto as colunas representam atributos ou características dos dados.

- Com o SQL, é possível executar várias operações em conjuntos de dados, como recuperar informações específicas de uma ou mais tabelas usando consultas de seleção (SELECT), modificar dados existentes em uma tabela usando consultas de atualização (UPDATE), excluir dados usando consultas de exclusão (DELETE), ou inserir novos dados em uma tabela usando consultas de inserção (INSERT).
- O SQL também oferece recursos avançados para trabalhar com conjuntos de dados, como a capacidade de mesclar dados de várias tabelas usando operações de junção (JOIN), agrupar dados usando funções de agregação (como COUNT, SUM, AVG, MAX e MIN), e filtrar dados usando operadores lógicos (como AND, OR e NOT) e operadores de comparação (como =, <>, <, >, <= e >=).

Em suma, o SQL é uma linguagem poderosa para gerenciar dados em bancos de dados relacionais, que usa a teoria dos conjuntos como base para manipular conjuntos de dados com eficiência e precisão.

<br>

## Sublinguagens SQL 
Existem várias sublinguagens no SQL que permitem que os usuários executem diferentes tarefas de gerenciamento de banco de dados. Algumas das sublinguagens mais comuns incluem:

#### Data Definition Language (DDL)
É usada para criar, alterar e excluir objetos no banco de dados, como tabelas, índices, procedimentos armazenados e visões. As principais instruções DDL incluem CREATE, ALTER e DROP.
<details>
<summary>Código exemplo:</summary>

```sql
CREATE TABLE clientes (
   id INT PRIMARY KEY,
   nome VARCHAR(50) NOT NULL,
   email VARCHAR(50) UNIQUE,
   idade INT
);
/*
	Este código cria uma nova tabela chamada "clientes" com quatro colunas: "id", "nome", 
	"email" e "idade". A primeira coluna, "id", é definida como a chave primária da tabela 
	usando a cláusula "PRIMARY KEY". A segunda coluna, "nome", é definida como um campo 
	obrigatório (NOT NULL) com um comprimento máximo de 50 caracteres. A terceira coluna, 
	"email", é definida como um campo exclusivo (UNIQUE) que também tem um comprimento máximo 
	de 50 caracteres. A última coluna, "idade", é definida como um campo opcional.
*/
```

</details>

<br>

#### Data Manipulation Language (DML) 
É usada para inserir, atualizar, excluir e recuperar dados de uma tabela. As principais instruções DML incluem SELECT, INSERT, UPDATE e DELETE.

<details>
<summary>Código exemplo:</summary>

```sql
INSERT INTO clientes (id, nome, email, idade) VALUES
   (1, 'Maria Silva', 'maria@email.com', 30),
   (2, 'João Souza', 'joao@email.com', 25),
   (3, 'Ana Santos', 'ana@email.com', 40);

/*
	Neste exemplo, a sublinguagem DML é usada para inserir três novas linhas na tabela 
	"clientes". A cláusula INSERT INTO é usada para especificar o nome da tabela e as 
	colunas nas quais os valores serão inseridos. Em seguida, a cláusula VALUES é usada 
	para especificar os valores a serem inseridos em cada linha. Cada linha é representada 
	por um conjunto de valores separados por vírgulas entre parênteses. Cada valor 
	corresponde a uma coluna na tabela, na ordem em que foram listados na cláusula INSERT INTO.
*/
```

</details>

<br>

#### Data Control Language (DCL)
É usada para definir permissões de usuário e controlar o acesso a objetos do banco de dados. As principais instruções DCL incluem GRANT e REVOKE.

<details>
<summary>Código exemplo:</summary>

```sql
GRANT SELECT, INSERT, UPDATE, DELETE ON tabela_exemplo TO usuario1;
/*
	Neste exemplo, a sublinguagem DCL é usada para conceder permissões de acesso 
	à tabela "tabela_exemplo" para o usuário "usuario1". As permissões concedidas 
	incluem SELECT, INSERT, UPDATE e DELETE, o que significa que o usuário poderá ler, 
	inserir, atualizar e excluir dados nesta tabela.
*/
```

</details>

<br>

#### Transaction Control Language (TCL) 
É usada para controlar transações no banco de dados. As principais instruções TCL incluem COMMIT, ROLLBACK e SAVEPOINT.

<details>
<summary>Código exemplo:</summary>

```sql
COMMIT;
/*
	Neste exemplo, a sublinguagem TCL é usada para confirmar uma transação que foi 
	realizada no banco de dados. A instrução COMMIT é usada para confirmar todas as 
	alterações feitas na transação e torná-las permanentes no banco de dados.
*/
```

</details>

<br>

### importante!
A sublinguagem DDL é particularmente importante, pois é usada para criar e definir a estrutura do banco de dados. Com o DDL, é possível criar tabelas, definir colunas, especificar índices e restrições de integridade referencial, além de modificar e excluir objetos de banco de dados existentes. O DDL permite que os usuários personalizem a estrutura do banco de dados para atender às suas necessidades específicas e gerenciem a evolução do esquema do banco de dados ao longo do tempo.

Em resumo, o SQL oferece várias sublinguagens para executar tarefas específicas no gerenciamento de banco de dados, sendo a DDL uma das mais importantes para criar e definir a estrutura do banco de dados.

<br>

## Modelos conceituais de banco de dados

### Modelo Relacional

O modelo relacional é um modelo de dados que organiza as informações em tabelas (ou relações), compostas por linhas e colunas. Cada tabela representa uma entidade no mundo real e as colunas representam os atributos dessa entidade. Os dados são armazenados em células dentro da tabela.

Algumas características importantes do modelo relacional incluem:

- O relacionamento entre as entidades é representado por meio de chaves primárias e chaves estrangeiras.
- As operações são realizadas por meio de comandos SQL (Structured Query Language).
- A integridade dos dados é mantida por meio de restrições e regras, como chaves primárias, chaves estrangeiras e restrições de integridade referencial.

<details>
<summary>Código exemplo:</summary>

Suponha que queremos criar uma tabela de clientes em um banco de dados relacional. Podemos usar a seguinte sintaxe SQL:

```sql
CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY,
    nome VARCHAR(50),
    endereco VARCHAR(100),
    telefone VARCHAR(15)
);

```

</details>

<br>

### Modelo ER

O modelo ER (Entidade-Relacionamento) é um modelo de dados que representa as entidades, os relacionamentos e os atributos de um sistema em um diagrama. Ele é usado para visualizar a estrutura do banco de dados de uma forma mais fácil de entender do que o modelo relacional.

Algumas características importantes do modelo ER incluem:

- As entidades são representadas por meio de retângulos e os atributos por meio de elipses.
- Os relacionamentos são representados por meio de linhas que conectam as entidades.
- Os tipos de relacionamentos incluem um para um, um para muitos e muitos para muitos.
- O diagrama ER pode ser transformado em um modelo relacional por meio de um processo chamado de normalização.

Em resumo, o modelo relacional e o modelo ER são duas abordagens diferentes para modelar os dados em um banco de dados. O modelo relacional é focado em armazenar os dados em tabelas, enquanto o modelo ER é focado em representar a estrutura dos dados em um diagrama visual. Ambos são importantes para o desenvolvimento de sistemas de banco de dados eficazes e confiáveis.

<details>
<summary>Código exemplo:</summary>

Usando o mesmo exemplo de clientes, podemos criar um diagrama ER para representar o relacionamento entre as entidades "Clientes" e "Endereços":

```sql
[Clientes]--(possui)-->[Endereços]

```

</details>

<br>

## Tipos de dados no SQL

No SQL, os tipos de dados definem o tipo de valor que pode ser armazenado em uma coluna de uma tabela. Cada coluna em um banco de dados possui um tipo de dado associado, que define as características e o intervalo de valores que podem ser armazenados nessa coluna. 

<details>
<summary>Exemplos de tipos de dados em uso:</summary>

```sql
CREATE TABLE exemplo (

	-- Número inteiro sem sinal. Pode armazenar valores de -2147483648 a 2147483647.
	id INT, 
	
	-- String de tamanho variável, com no máximo 50 caracteres.
    nome VARCHAR(50), 

	-- Número decimal com até 8 dígitos, sendo 2 após a vírgula.
    salario FLOAT(8,2),

	-- Armazena uma data, no formato YYYY-MM-DD. 
    data_nascimento DATE, 

	-- Armazena valores verdadeiro/falso (1/0).
    ativo BOOLEAN,

	-- String de tamanho variável, com no máximo 4 GB de tamanho.
    texto LONGTEXT, 

	-- Armazena dados binários, como imagens ou arquivos compactados.
    imagem BLOB, 

	-- Armazena um valor da lista de opções pré-definidas.
    cor ENUM('vermelho', 'azul', 'amarelo'), 

	-- Armazena uma ou várias opções da lista pré-definida.
    tamanho SET('pequeno', 'médio', 'grande'), 

	-- String de tamanho fixo, com tamanho de até 10 caracteres.
    char_1 CHAR(10), 

	-- Armazena uma data, no formato YYYY-MM-DD.
    date_1 DATE, 

	-- Armazena uma hora, no formato HH:MM:SS.
    time_1 TIME, 

	-- Armazena data e hora, no formato YYYY-MM-DD HH:MM:SS.
    datetime_1 DATETIME, 

	-- Armazena um ano no formato YYYY.
    year_1 YEAR(4),

	-- Número inteiro pequeno, com valores de -32768 a 32767.
    smallint_1 SMALLINT,

	-- Define o campo id como chave primária da tabela.
	PRIMARY KEY (id),

	-- Cria uma chave estrangeira na tabela, referenciando a tabela "outra_tabela" e seu campo "id".
	FOREIGN KEY (outra_tabela_id) REFERENCES outra_tabela(id),

	-- Restringe a inserção de valores duplicados no campo "nome".
	UNIQUE (nome),

	-- Restringe a inserção de valores nulos no campo "salario".
	NOT NULL (salario),

	-- Define o valor mínimo permitido para o campo "salario".
	CHECK (salario > 0),

	-- Agrupa os resultados da consulta pelo campo "nome".
	GROUP BY nome,

	-- Filtra os resultados da consulta, exibindo apenas aqueles que atendem à condição especificada.
	HAVING count(*) > 1,

	-- Ordena os resultados da consulta em ordem decrescente pelo campo "salario".
	ORDER BY salario DESC,

	-- Cria um índice na tabela para o campo "data_nascimento".
	INDEX idx_data_nascimento (data_nascimento)

	-- Armazena valores verdadeiro/falso (1/0).
	ativo TINYINT(1),

	-- String de tamanho variável, com no máximo 16777215 caracteres.
	descricao TEXT,

	-- String de tamanho variável, com no máximo 65535 caracteres.
	comentario MEDIUMTEXT,

	-- String de tamanho variável, com no máximo 4294967295 caracteres.
	historico LONGTEXT

	-- Armazena pequenas quantidades de texto, como expressões matemáticas. 
	formula TINYTEXT,

	-- Armazena textos formatados em linguagem LaTeX.
	documento TEX
);
```

</details>

<br>

### Constrains 

Constraints (restrições) são regras aplicadas a uma ou mais colunas de uma tabela que limitam o tipo de dados que pode ser armazenado nessas colunas. Elas são usadas para garantir a integridade e consistência dos dados armazenados em uma tabela, evitando que valores inválidos ou inconsistentes sejam inseridos. Algumas das restrições mais comuns são:

- Primary Key: restringe os valores em uma coluna a serem únicos e não nulos, sendo usada para identificar exclusivamente cada linha de uma tabela.
- Foreign Key: restringe os valores em uma coluna a serem apenas os valores existentes em outra tabela, geralmente usada para criar relacionamentos entre duas tabelas.
- Unique: restringe os valores em uma coluna a serem únicos, mas permite valores nulos.
- Not Null: restringe os valores em uma coluna a não serem nulos.
- Check: permite que uma condição seja definida para limitar os valores que podem ser armazenados em uma coluna.

<details>
<summary>Exemplos de constrains em uso:</summary>

1. Constraint NOT NULL
    
    ```sql
    sqlCopy code
    CREATE TABLE exemplo (
        id INT NOT NULL,
        nome VARCHAR(50) NOT NULL,
        salario FLOAT(8,2) NOT NULL
    );
    ```
    
2. Constraint UNIQUE
    
    ```sql
    sqlCopy code
    CREATE TABLE exemplo (
        id INT,
        nome VARCHAR(50) UNIQUE,
        salario FLOAT(8,2)
    );
    ```
    
3. Constraint PRIMARY KEY
    
    ```sql
    sqlCopy code
    CREATE TABLE exemplo (
        id INT PRIMARY KEY,
        nome VARCHAR(50),
        salario FLOAT(8,2)
    );
    ```
    
4. Constraint FOREIGN KEY
    
    ```sql
    sqlCopy code
    CREATE TABLE venda (
        id INT PRIMARY KEY,
        cliente_id INT,
        valor FLOAT(8,2),
        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
    );
    
    CREATE TABLE cliente (
        id INT PRIMARY KEY,
        nome VARCHAR(50),
        cpf VARCHAR(11)
    );
    ```
    
5. Constraint CHECK
    
    ```sql
    sqlCopy code
    CREATE TABLE exemplo (
        id INT,
        nome VARCHAR(50),
        idade INT CHECK (idade >= 18),
        salario FLOAT(8,2)
    );
    ```

</details>

<br>

<br>

<br>

[Voltar ao inicio](/README.md)