# 5.5 · SQL: inserindo, alterando e apagando dados (DML)

> **Em uma frase:** com `INSERT` você coloca linhas numa tabela, com `UPDATE` altera valores e com `DELETE` apaga linhas; nos dois últimos, o `WHERE` decide **quais** linhas serão afetadas, e esquecê-lo afeta a tabela **inteira**.

> [!NOTE]
> Esta aula usa as tabelas criadas na aula [5.4](./04-sql-criando-tabelas.md). Se ainda não as criou, execute o script "O banco completo da loja" antes.

## ➕ `INSERT`: inserindo linhas

```sql
-- Forma completa: lista as colunas e depois os valores, na mesma ordem
INSERT INTO categorias (nome) VALUES ('Informática');

-- Várias linhas num comando só
INSERT INTO categorias (nome) VALUES ('Móveis'), ('Livros');
```

Repare que não informamos o `id`: o banco gera sozinho. As colunas que você não citar recebem o `DEFAULT` (ou `NULL`, se não houver padrão).

> [!TIP]
> Sempre **liste as colunas** no `INSERT`. A forma curta `INSERT INTO categorias VALUES (...)` depende da ordem das colunas na tabela e quebra quando alguém acrescenta uma coluna nova.

### Os dados da loja

Execute este script para popular o banco. Ele será usado nas próximas aulas:

```sql
INSERT INTO produtos (nome, preco, estoque, categoria_id) VALUES
    ('Teclado',        149.90, 30, 1),
    ('Mouse',           59.90, 50, 1),
    ('Monitor',        899.00, 10, 1),
    ('Cadeira gamer', 1299.00,  5, 2),
    ('Mesa',           499.00,  8, 2),
    ('Livro de Java',   89.90, 20, 3),
    ('Webcam',         199.90,  0, NULL);

INSERT INTO clientes (nome, email, cidade, nascimento) VALUES
    ('Ana Lima',    'ana@email.com',   'Rio de Janeiro', '1994-03-15'),
    ('Bruno Reis',  'bruno@email.com', 'São Paulo',      '1988-07-02'),
    ('Carla Dias',  'carla@email.com', 'Rio de Janeiro', '2001-11-20'),
    ('Diego Alves', 'diego@email.com', 'Recife',         NULL),
    ('Elisa Moura', 'elisa@email.com', NULL,             '1999-01-30');

INSERT INTO pedidos (cliente_id, data, status) VALUES
    (1, '2026-09-01', 'PAGO'),
    (1, '2026-09-15', 'ENVIADO'),
    (2, '2026-09-20', 'PAGO'),
    (3, '2026-10-01', 'ABERTO'),
    (4, '2026-10-05', 'CANCELADO'),
    (2, '2026-10-10', 'PAGO');

INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES
    (1, 1, 1,  149.90), (1, 2, 2,   59.90),
    (2, 3, 1,  899.00),
    (3, 4, 1, 1299.00), (3, 2, 1,   59.90),
    (4, 6, 2,   89.90),
    (5, 5, 1,  499.00),
    (6, 1, 1,  149.90), (6, 6, 1,   89.90);
```

Datas são escritas como texto no formato `'AAAA-MM-DD'`, e decimais usam **ponto**.

> [!TIP]
> Os comandos de criação e estes dados também estão prontos no arquivo [`loja.sql`](./sql/loja.sql). Ele apaga e recria tudo do zero: rode-o sempre que quiser voltar o banco ao estado original (por exemplo, depois de testar os `UPDATE`s e `DELETE`s abaixo).

Para conferir, rode `SELECT * FROM produtos;` (o `*` significa "todas as colunas"):

| id | nome | preco | estoque | categoria_id |
| --- | --- | --- | --- | --- |
| 1 | Teclado | 149.90 | 30 | 1 |
| 2 | Mouse | 59.90 | 50 | 1 |
| 3 | Monitor | 899.00 | 10 | 1 |
| 4 | Cadeira gamer | 1299.00 | 5 | 2 |
| 5 | Mesa | 499.00 | 8 | 2 |
| 6 | Livro de Java | 89.90 | 20 | 3 |
| 7 | Webcam | 199.90 | 0 | NULL |

### Quando o banco diz "não"

As restrições criadas na aula anterior entram em ação. Tente cada comando abaixo e leia a mensagem:

| Comando | Erro | Por quê |
| --- | --- | --- |
| `INSERT INTO clientes (nome, email) VALUES ('Fábio', 'ana@email.com');` | `duplicate key value violates unique constraint "clientes_email_key"` | O e-mail já existe (`UNIQUE`) |
| `INSERT INTO clientes (nome) VALUES ('Sem email');` | `null value in column "email" ... violates not-null constraint` | E-mail é obrigatório (`NOT NULL`) |
| `INSERT INTO produtos (nome, preco) VALUES ('Brinde', 0);` | `violates check constraint "produtos_preco_check"` | O preço precisa ser maior que zero (`CHECK`) |
| `INSERT INTO pedidos (cliente_id) VALUES (99);` | `violates foreign key constraint "pedidos_cliente_id_fkey"` | Não existe cliente 99 (`REFERENCES`) |

> [!TIP]
> Essas mensagens são suas amigas: elas dizem **qual** regra foi violada. Quando a sua aplicação Java receber um erro do banco, a causa estará escrita assim, no meio do *stack trace*.

### Descobrindo o id gerado

No PostgreSQL, `RETURNING` devolve as colunas da linha recém-criada:

```sql
INSERT INTO clientes (nome, email, cidade) VALUES ('Fábio Costa', 'fabio@email.com', 'Recife')
RETURNING id, criado_em;
```

## ✏️ `UPDATE`: alterando valores

```sql
-- Altera UMA linha (pela chave primária)
UPDATE clientes SET cidade = 'Niterói' WHERE id = 3;

-- Várias colunas de uma vez
UPDATE produtos SET preco = 139.90, estoque = 25 WHERE id = 1;

-- Cálculo com o valor atual: aumento de 10% em todos os produtos de informática
UPDATE produtos SET preco = preco * 1.10 WHERE categoria_id = 1;

-- Baixa de estoque
UPDATE produtos SET estoque = estoque - 2 WHERE id = 2;
```

## 🗑️ `DELETE`: apagando linhas

```sql
DELETE FROM clientes WHERE email = 'fabio@email.com';   -- apaga o Fábio
DELETE FROM pedidos WHERE status = 'CANCELADO'; -- apaga vários (e os itens, por causa do CASCADE)
```

> [!CAUTION]
> `UPDATE` e `DELETE` **sem `WHERE`** atingem **todas as linhas**: `DELETE FROM clientes;` apaga todos os clientes, e `UPDATE produtos SET preco = 0;` zera todos os preços. Não há "Ctrl+Z" no banco (a não ser dentro de uma transação, aula [5.8](./08-transacoes-indices-e-views.md)).

**Hábito que salva empregos:** antes de um `UPDATE` ou `DELETE`, rode um `SELECT` com o **mesmo** `WHERE` e confira quais linhas aparecem:

```sql
SELECT * FROM pedidos WHERE status = 'CANCELADO';   -- 1. confere
DELETE FROM pedidos WHERE status = 'CANCELADO';     -- 2. só então apaga
```

### `DELETE` × `TRUNCATE` × `DROP`

| Comando | O que faz | Pode filtrar? |
| --- | --- | --- |
| `DELETE FROM t WHERE ...` | Apaga as linhas escolhidas | Sim |
| `TRUNCATE TABLE t` | Esvazia a tabela inteira, muito mais rápido | Não |
| `DROP TABLE t` | Apaga a tabela (estrutura e dados) | Não |

## 📌 Resumindo

- `INSERT INTO tabela (colunas) VALUES (...)`: liste sempre as colunas; dá para inserir várias linhas de uma vez.
- `UPDATE tabela SET coluna = valor WHERE ...` altera; pode usar o valor atual (`preco * 1.10`).
- `DELETE FROM tabela WHERE ...` apaga.
- **Nunca** rode `UPDATE`/`DELETE` sem conferir o `WHERE` com um `SELECT` antes.
- As restrições recusam dados inválidos com mensagens que dizem qual regra foi violada.

## 🏋️ Mão na massa

**1.** Cadastre a categoria "Games" e o produto "Controle" (R$ 249,90, estoque 12) nela.

<details>
<summary>Ver resposta</summary>

```sql
INSERT INTO categorias (nome) VALUES ('Games') RETURNING id;   -- devolve 4

INSERT INTO produtos (nome, preco, estoque, categoria_id)
VALUES ('Controle', 249.90, 12, 4);
```

Sem o `RETURNING`, descubra o id com `SELECT id FROM categorias WHERE nome = 'Games';`.

</details>

**2.** A Webcam (id 7) chegou: 15 unidades. Atualize o estoque e coloque-a na categoria Informática.

<details>
<summary>Ver resposta</summary>

```sql
UPDATE produtos SET estoque = estoque + 15, categoria_id = 1 WHERE id = 7;
```

</details>

**3.** O que acontece com `UPDATE clientes SET cidade = 'Recife';`?

<details>
<summary>Ver resposta</summary>

Sem `WHERE`, **todos** os clientes passam a morar em Recife. O comando é válido, por isso o banco não reclama: a responsabilidade é de quem escreve.

</details>

---

<div align="center">

[⬅️ 5.4 SQL](./04-sql-criando-tabelas.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [5.6 SQL ➡️](./06-sql-consultas.md)

</div>
