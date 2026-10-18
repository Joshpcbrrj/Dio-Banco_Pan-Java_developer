# 5.4 · SQL: criando tabelas, tipos e restrições (DDL)

> **Em uma frase:** com os comandos de **DDL** (`CREATE`, `ALTER`, `DROP`) você cria a estrutura do banco: as tabelas, o **tipo** de cada coluna e as **restrições** que impedem dados errados de entrar.

## ✍️ Antes de começar: como se escreve SQL

```sql
-- Comentário de uma linha
/* Comentário
   de várias linhas */

SELECT nome FROM clientes;   -- todo comando termina com ponto e vírgula
select nome from clientes;   -- palavras-chave funcionam em maiúsculas ou minúsculas
```

- Por convenção, as **palavras-chave** vão em MAIÚSCULAS e os **nomes** de tabelas e colunas em minúsculas, no estilo `snake_case` (`data_nascimento`), sem acentos nem espaços.
- **Textos** vão entre **aspas simples**: `'Ana'`. Aspas duplas servem para nomes de colunas, não para textos.

## 🧩 As partes do SQL

O SQL é dividido em grupos de comandos, conforme o objetivo:

| Sigla | Nome | Para quê | Comandos | Aula |
| --- | --- | --- | --- | --- |
| **DDL** | *Data Definition Language* | Criar e alterar a **estrutura** | `CREATE`, `ALTER`, `DROP` | Esta |
| **DML** | *Data Manipulation Language* | Inserir, alterar e apagar **dados** | `INSERT`, `UPDATE`, `DELETE` | [5.5](./05-sql-inserindo-e-alterando.md) |
| **DQL** | *Data Query Language* | **Consultar** | `SELECT` | [5.6](./06-sql-consultas.md) e [5.7](./07-sql-joins-e-subconsultas.md) |
| **TCL** | *Transaction Control Language* | **Transações** | `BEGIN`, `COMMIT`, `ROLLBACK` | [5.8](./08-transacoes-indices-e-views.md) |
| **DCL** | *Data Control Language* | **Permissões** | `GRANT`, `REVOKE` | [5.8](./08-transacoes-indices-e-views.md) |

> [!NOTE]
> Algumas referências colocam o `SELECT` dentro da DML. As duas classificações são comuns.

## 🔢 Tipos de dados

Cada coluna tem um tipo, assim como as variáveis em Java:

| Tipo SQL | Guarda | Parecido em Java | Exemplo |
| --- | --- | --- | --- |
| `INTEGER` (ou `INT`) | Inteiros | `int` | `estoque INTEGER` |
| `BIGINT` | Inteiros grandes | `long` | `id BIGINT` |
| `DECIMAL(p, s)` / `NUMERIC(p, s)` | Decimais **exatos**: `p` dígitos no total, `s` depois da vírgula | `BigDecimal` | `preco DECIMAL(10, 2)` |
| `VARCHAR(n)` | Texto de até `n` caracteres | `String` | `nome VARCHAR(100)` |
| `CHAR(n)` | Texto de tamanho **fixo** | `String` | `uf CHAR(2)` |
| `TEXT` | Texto longo, sem limite definido | `String` | `descricao TEXT` |
| `BOOLEAN` | Verdadeiro ou falso | `boolean` | `ativo BOOLEAN` |
| `DATE` | Data | `LocalDate` | `nascimento DATE` |
| `TIMESTAMP` | Data e hora | `LocalDateTime` | `criado_em TIMESTAMP` |

> [!WARNING]
> Para **dinheiro**, use sempre `DECIMAL`/`NUMERIC`. Os tipos `REAL`, `FLOAT` e `DOUBLE PRECISION` são aproximados, com os mesmos problemas de arredondamento do `double` em Java.

> [!NOTE]
> Cada banco tem alguns tipos próprios: o MySQL tem `TINYINT` e `DATETIME`; o PostgreSQL tem `UUID`, `JSONB` e arrays. O básico acima funciona em todos.

## 🏗️ Criando tabelas: `CREATE TABLE`

```sql
CREATE TABLE clientes (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    cidade      VARCHAR(60),
    nascimento  DATE,
    criado_em   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

Lendo linha a linha:

- `id ... GENERATED ALWAYS AS IDENTITY PRIMARY KEY`: o banco **gera** o id sozinho (1, 2, 3...), e ele é a chave primária.
- `nome VARCHAR(100) NOT NULL`: texto de até 100 caracteres, **obrigatório**.
- `email ... UNIQUE`: obrigatório e **sem repetição**.
- `cidade` e `nascimento`: opcionais (aceitam `NULL`, ou seja, "sem valor").
- `criado_em ... DEFAULT CURRENT_TIMESTAMP`: se ninguém informar, recebe a data e hora atuais.

> [!NOTE]
> No **MySQL**, o id automático se escreve `id INT AUTO_INCREMENT PRIMARY KEY`. Em PostgreSQL antigo, você verá `id SERIAL PRIMARY KEY`, que também funciona.

## 🔒 Restrições (*constraints*)

As restrições são regras que o banco **garante sozinho**: mesmo que a aplicação tenha um bug, um dado inválido não entra.

| Restrição | Garante que... | Exemplo |
| --- | --- | --- |
| `PRIMARY KEY` | o valor é único e não nulo | `id ... PRIMARY KEY` |
| `NOT NULL` | a coluna sempre tem valor | `nome VARCHAR(100) NOT NULL` |
| `UNIQUE` | não há valores repetidos | `email VARCHAR(150) UNIQUE` |
| `CHECK` | o valor obedece a uma condição | `preco DECIMAL(10,2) CHECK (preco > 0)` |
| `DEFAULT` | há um valor padrão | `estoque INTEGER DEFAULT 0` |
| `FOREIGN KEY` / `REFERENCES` | o valor existe na outra tabela | `cliente_id INTEGER REFERENCES clientes (id)` |

## 🔗 Chaves estrangeiras e o que fazer ao apagar

```sql
CREATE TABLE pedidos (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id  INTEGER NOT NULL REFERENCES clientes (id),
    data        DATE NOT NULL DEFAULT CURRENT_DATE,
    status      VARCHAR(20) NOT NULL DEFAULT 'ABERTO'
                CHECK (status IN ('ABERTO', 'PAGO', 'ENVIADO', 'CANCELADO'))
);
```

E se alguém tentar apagar um cliente que tem pedidos? Por padrão, o banco **recusa**, para não deixar pedidos "órfãos". Você pode escolher outro comportamento:

| Opção | Ao apagar o cliente... | Quando usar |
| --- | --- | --- |
| (padrão) / `ON DELETE RESTRICT` | Dá erro se houver pedidos | Quase sempre: protege o histórico |
| `ON DELETE CASCADE` | Apaga os pedidos junto | Partes que não existem sem o "pai" (itens de um pedido) |
| `ON DELETE SET NULL` | Deixa `cliente_id` vazio | Quando o vínculo é opcional |

## 🛒 O banco completo da loja

Execute este script inteiro: ele será usado em todas as próximas aulas.

```sql
CREATE TABLE categorias (
    id    INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome  VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE produtos (
    id            INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    preco         DECIMAL(10, 2) NOT NULL CHECK (preco > 0),
    estoque       INTEGER NOT NULL DEFAULT 0 CHECK (estoque >= 0),
    categoria_id  INTEGER REFERENCES categorias (id)
);

CREATE TABLE clientes (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    cidade      VARCHAR(60),
    nascimento  DATE,
    criado_em   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pedidos (
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id  INTEGER NOT NULL REFERENCES clientes (id),
    data        DATE NOT NULL DEFAULT CURRENT_DATE,
    status      VARCHAR(20) NOT NULL DEFAULT 'ABERTO'
                CHECK (status IN ('ABERTO', 'PAGO', 'ENVIADO', 'CANCELADO'))
);

CREATE TABLE itens_pedido (
    pedido_id       INTEGER REFERENCES pedidos (id) ON DELETE CASCADE,
    produto_id      INTEGER REFERENCES produtos (id),
    quantidade      INTEGER NOT NULL CHECK (quantidade > 0),
    preco_unitario  DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (pedido_id, produto_id)
);
```

> [!TIP]
> A ordem importa: uma tabela só pode referenciar outra que **já exista**. Por isso `categorias` vem antes de `produtos`, e `pedidos` antes de `itens_pedido`. Repare também na **chave primária composta** de `itens_pedido`: o mesmo produto não aparece duas vezes no mesmo pedido.

## 🔧 Alterando e apagando a estrutura

```sql
ALTER TABLE clientes ADD COLUMN telefone VARCHAR(20);           -- nova coluna
ALTER TABLE clientes RENAME COLUMN telefone TO celular;          -- renomeia
ALTER TABLE clientes ALTER COLUMN cidade SET NOT NULL;           -- passa a ser obrigatória
ALTER TABLE clientes DROP COLUMN celular;                        -- remove a coluna

DROP TABLE itens_pedido;              -- apaga a tabela INTEIRA (estrutura e dados)
DROP TABLE IF EXISTS itens_pedido;    -- não dá erro se ela não existir
```

> [!CAUTION]
> `DROP TABLE` não tem "desfazer": a tabela e **todos** os dados somem. Em produção, mudanças de estrutura são feitas com cuidado, com backup e, de preferência, com ferramentas de migração como o Flyway (aula [8.5](../08-extras/05-boas-praticas-api.md)).

## 📌 Resumindo

- DDL cria a estrutura: `CREATE TABLE`, `ALTER TABLE`, `DROP TABLE`.
- Escolha bem os tipos: `INTEGER`, `VARCHAR(n)`, `DECIMAL(p, s)` para dinheiro, `DATE`, `TIMESTAMP`, `BOOLEAN`.
- `GENERATED ALWAYS AS IDENTITY` (ou `AUTO_INCREMENT` no MySQL) gera o id.
- Restrições (`NOT NULL`, `UNIQUE`, `CHECK`, `DEFAULT`, `REFERENCES`) protegem os dados.
- `ON DELETE` define o que acontece com os "filhos" ao apagar o "pai".

## 🏋️ Mão na massa

**1.** Crie a tabela `fornecedores` com id automático, razão social (obrigatória), CNPJ (14 caracteres, obrigatório e único) e telefone (opcional).

<details>
<summary>Ver resposta</summary>

```sql
CREATE TABLE fornecedores (
    id            INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    razao_social  VARCHAR(150) NOT NULL,
    cnpj          CHAR(14) NOT NULL UNIQUE,
    telefone      VARCHAR(20)
);
```

</details>

**2.** Acrescente à tabela `produtos` uma coluna `fornecedor_id` que referencie `fornecedores`.

<details>
<summary>Ver resposta</summary>

```sql
ALTER TABLE produtos ADD COLUMN fornecedor_id INTEGER REFERENCES fornecedores (id);
```

</details>

**3.** Por que `itens_pedido` usa `ON DELETE CASCADE` com `pedidos`, mas `pedidos` não usa com `clientes`?

<details>
<summary>Ver resposta</summary>

Os itens **não existem sem o pedido**: se o pedido for apagado, seus itens perdem o sentido, então apagá-los junto é o correto. Já apagar um cliente não deveria apagar o histórico de vendas da loja; por isso o padrão (recusar) é mais seguro.

</details>

---

<div align="center">

[⬅️ 5.3 Normalização](./03-normalizacao.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [5.5 SQL ➡️](./05-sql-inserindo-e-alterando.md)

</div>
