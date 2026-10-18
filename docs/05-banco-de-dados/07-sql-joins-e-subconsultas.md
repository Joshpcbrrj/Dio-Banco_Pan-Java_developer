# 5.7 · SQL: juntando tabelas com `JOIN` e subconsultas

> **Em uma frase:** como os dados ficam separados em várias tabelas (aula [5.3](./03-normalizacao.md)), o `JOIN` os **junta de volta** na consulta usando as chaves; o `INNER JOIN` traz só o que tem par dos dois lados, e o `LEFT JOIN` traz tudo da tabela da esquerda, com ou sem par.

> [!NOTE]
> Os resultados usam o banco da loja. Se você alterou os dados, rode o [`loja.sql`](./sql/loja.sql) antes.

## 🤝 Por que precisamos de `JOIN`?

A tabela `pedidos` guarda só o `cliente_id`. Para mostrar o **nome** do cliente ao lado de cada pedido, precisamos juntar `pedidos` e `clientes`, ligando `pedidos.cliente_id` a `clientes.id`:

```sql
SELECT p.id AS pedido, p.data, c.nome AS cliente
FROM pedidos p
INNER JOIN clientes c ON c.id = p.cliente_id
ORDER BY p.id;
```

| pedido | data | cliente |
| --- | --- | --- |
| 1 | 2026-09-01 | Ana Lima |
| 2 | 2026-09-15 | Ana Lima |
| 3 | 2026-09-20 | Bruno Reis |
| 4 | 2026-10-01 | Carla Dias |
| 5 | 2026-10-05 | Diego Alves |
| 6 | 2026-10-10 | Bruno Reis |

Lendo a consulta:

- `FROM pedidos p`: o `p` é um **apelido** da tabela, para não escrever `pedidos.` toda hora.
- `INNER JOIN clientes c`: junta com a tabela `clientes` (apelido `c`).
- `ON c.id = p.cliente_id`: a **condição de ligação**, quase sempre "PK de um lado = FK do outro".
- `c.nome`, `p.data`: quando duas tabelas têm colunas com o mesmo nome (`id`, `nome`), o prefixo diz de qual tabela é cada uma.

> [!TIP]
> Pense no `JOIN` como "para cada pedido, vá até a tabela de clientes e pegue a linha cujo `id` é igual ao `cliente_id` deste pedido".

## 🔀 Os tipos de `JOIN`

Imagine só dois clientes e dois pedidos:

`clientes`:

| id | nome |
| --- | --- |
| 1 | Ana |
| 2 | Bia |

`pedidos`:

| id | cliente_id |
| --- | --- |
| 10 | 1 |
| 11 | 1 |

`clientes LEFT JOIN pedidos ON pedidos.cliente_id = clientes.id` dá:

| nome | pedido |
| --- | --- |
| Ana | 10 |
| Ana | 11 |
| Bia | NULL |

Com `INNER JOIN`, a linha da Bia **não apareceria**, porque ela não tem pedido. Repare também que a Ana aparece **duas vezes**: uma para cada pedido.

| Tipo | Traz | Exemplo de pergunta |
| --- | --- | --- |
| `INNER JOIN` (ou só `JOIN`) | Só linhas que têm par nos **dois** lados | "Pedidos com o nome do cliente" |
| `LEFT JOIN` | **Todas** as linhas da tabela da esquerda; da direita, o par ou `NULL` | "Todos os clientes, com seus pedidos, **se houver**" |
| `RIGHT JOIN` | O espelho do `LEFT` | Pouco usado: basta inverter a ordem das tabelas e usar `LEFT` |
| `FULL JOIN` | Tudo dos dois lados | "O que está sobrando de um lado ou do outro?" |

### `INNER` × `LEFT` na prática

A Webcam não tem categoria. Veja a diferença:

```sql
SELECT pr.nome AS produto, ca.nome AS categoria
FROM produtos pr
LEFT JOIN categorias ca ON ca.id = pr.categoria_id
ORDER BY pr.id;
```

| produto | categoria |
| --- | --- |
| Teclado | Informática |
| Mouse | Informática |
| Monitor | Informática |
| Cadeira gamer | Móveis |
| Mesa | Móveis |
| Livro de Java | Livros |
| Webcam | NULL |

Com `INNER JOIN` no lugar de `LEFT JOIN`, a mesma consulta devolveria **6** linhas: a Webcam **sumiria**, porque não tem par em `categorias`.

> [!WARNING]
> Esse é o erro mais comum com `JOIN`: usar `INNER` e "perder" linhas sem perceber. Se a pergunta é "**todos** os X, com Y quando houver", use `LEFT JOIN`.

### Encontrando quem **não** tem par

Um padrão muito útil: `LEFT JOIN` + `WHERE ... IS NULL` encontra as linhas **sem** correspondência. Quais clientes nunca fizeram um pedido?

```sql
SELECT c.nome
FROM clientes c
LEFT JOIN pedidos p ON p.cliente_id = c.id
WHERE p.id IS NULL;
```

| nome |
| --- |
| Elisa Moura |

O `LEFT JOIN` trouxe a Elisa com `p.id = NULL` (ela não tem pedidos), e o `WHERE` manteve só esses casos.

## 🔗 Juntando várias tabelas

Dá para encadear quantos `JOIN`s forem necessários. Os itens dos pedidos 1 e 3, com cliente e produto:

```sql
SELECT p.id AS pedido, c.nome AS cliente, pr.nome AS produto, i.quantidade, i.preco_unitario
FROM pedidos p
JOIN clientes c      ON c.id = p.cliente_id
JOIN itens_pedido i  ON i.pedido_id = p.id
JOIN produtos pr     ON pr.id = i.produto_id
WHERE p.id IN (1, 3)
ORDER BY p.id, pr.nome;
```

| pedido | cliente | produto | quantidade | preco_unitario |
| --- | --- | --- | --- | --- |
| 1 | Ana Lima | Mouse | 2 | 59.90 |
| 1 | Ana Lima | Teclado | 1 | 149.90 |
| 3 | Bruno Reis | Cadeira gamer | 1 | 1299.00 |
| 3 | Bruno Reis | Mouse | 1 | 59.90 |

```mermaid
flowchart LR
    C["clientes"] -- "c.id = p.cliente_id" --> P["pedidos"]
    P -- "i.pedido_id = p.id" --> I["itens_pedido"]
    I -- "pr.id = i.produto_id" --> PR["produtos"]
```

Repare que é a tabela associativa `itens_pedido` que liga pedidos a produtos (o N:N da aula [5.2](./02-modelagem-relacional.md)).

### `JOIN` + `GROUP BY`: relatórios

Quanto cada cliente gastou, sem contar pedidos cancelados?

```sql
SELECT c.nome,
       COUNT(DISTINCT p.id)                AS pedidos,
       SUM(i.quantidade * i.preco_unitario) AS total_gasto
FROM clientes c
JOIN pedidos p      ON p.cliente_id = c.id
JOIN itens_pedido i ON i.pedido_id = p.id
WHERE p.status <> 'CANCELADO'
GROUP BY c.nome
ORDER BY total_gasto DESC;
```

| nome | pedidos | total_gasto |
| --- | --- | --- |
| Bruno Reis | 2 | 1598.70 |
| Ana Lima | 2 | 1168.70 |
| Carla Dias | 1 | 179.80 |

> [!NOTE]
> Por que `COUNT(DISTINCT p.id)` e não `COUNT(*)`? Depois do `JOIN` com os itens, um pedido com 2 itens vira **2 linhas**. O `DISTINCT` conta cada pedido uma vez só.

> [!CAUTION]
> Esquecer o `ON` (ou listar tabelas separadas por vírgula sem condição) gera um **produto cartesiano**: cada linha de uma tabela combinada com **todas** as da outra. 5 clientes × 6 pedidos = 30 linhas sem sentido. Em tabelas grandes, isso pode travar o banco.

## 🪆 Subconsultas

Uma **subconsulta** é um `SELECT` dentro de outro, entre parênteses. O banco resolve a de dentro primeiro.

### Comparando com um valor calculado

Produtos mais caros que a média:

```sql
SELECT nome, preco
FROM produtos
WHERE preco > (SELECT AVG(preco) FROM produtos);   -- a média é 456.66
```

| nome | preco |
| --- | --- |
| Monitor | 899.00 |
| Cadeira gamer | 1299.00 |
| Mesa | 499.00 |

### Usando uma lista: `IN` e `NOT IN`

Produtos que **nunca** foram vendidos:

```sql
SELECT nome
FROM produtos
WHERE id NOT IN (SELECT produto_id FROM itens_pedido);
```

| nome |
| --- |
| Webcam |

### Verificando existência: `EXISTS`

Clientes com pelo menos um pedido pago:

```sql
SELECT nome
FROM clientes c
WHERE EXISTS (
    SELECT 1 FROM pedidos p
    WHERE p.cliente_id = c.id AND p.status = 'PAGO'
);
```

| nome |
| --- |
| Ana Lima |
| Bruno Reis |

O `EXISTS` só quer saber se a subconsulta devolve **alguma** linha (por isso o `SELECT 1`).

> [!TIP]
> Muitas perguntas podem ser respondidas tanto com `JOIN` quanto com subconsulta. Escolha a forma que fica **mais fácil de ler**; os bancos modernos costumam otimizar as duas de forma parecida.

## 📌 Resumindo

- `JOIN ... ON pk = fk` junta tabelas relacionadas; apelidos (`p`, `c`) deixam a consulta curta.
- `INNER JOIN`: só quem tem par. `LEFT JOIN`: todos da esquerda, com `NULL` quando falta par.
- `LEFT JOIN` + `WHERE direita.id IS NULL` acha quem **não** tem correspondência.
- Encadeie vários `JOIN`s e combine com `GROUP BY` para relatórios.
- Subconsultas: comparar com um valor (`> (SELECT AVG...)`), listas (`IN`, `NOT IN`) e existência (`EXISTS`).

## 🏋️ Mão na massa

**1.** Liste o nome de cada produto vendido e quantas unidades dele foram vendidas no total, do mais vendido para o menos.

<details>
<summary>Ver resposta</summary>

```sql
SELECT pr.nome, SUM(i.quantidade) AS unidades
FROM produtos pr
JOIN itens_pedido i ON i.produto_id = pr.id
GROUP BY pr.nome
ORDER BY unidades DESC, pr.nome;
```

| nome | unidades |
| --- | --- |
| Livro de Java | 3 |
| Mouse | 3 |
| Teclado | 2 |
| Cadeira gamer | 1 |
| Mesa | 1 |
| Monitor | 1 |

</details>

**2.** Mostre **todas** as categorias e quantos produtos cada uma tem (inclusive as que não têm nenhum). Antes, cadastre a categoria "Games" sem produtos para testar.

<details>
<summary>Ver resposta</summary>

```sql
INSERT INTO categorias (nome) VALUES ('Games');

SELECT ca.nome, COUNT(pr.id) AS produtos
FROM categorias ca
LEFT JOIN produtos pr ON pr.categoria_id = ca.id
GROUP BY ca.nome
ORDER BY produtos DESC, ca.nome;
```

| nome | produtos |
| --- | --- |
| Informática | 3 |
| Móveis | 2 |
| Livros | 1 |
| Games | 0 |

Dois detalhes: `LEFT JOIN` para não perder "Games", e `COUNT(pr.id)` em vez de `COUNT(*)`, para contar **0** (e não 1) quando não há produto.

</details>

**3.** Quais clientes do Rio de Janeiro compraram algum produto da categoria Informática?

<details>
<summary>Ver resposta</summary>

```sql
SELECT DISTINCT c.nome
FROM clientes c
JOIN pedidos p      ON p.cliente_id = c.id
JOIN itens_pedido i ON i.pedido_id = p.id
JOIN produtos pr    ON pr.id = i.produto_id
JOIN categorias ca  ON ca.id = pr.categoria_id
WHERE c.cidade = 'Rio de Janeiro' AND ca.nome = 'Informática';
```

| nome |
| --- |
| Ana Lima |

A Carla também é do Rio, mas só comprou livros.

</details>

---

<div align="center">

[⬅️ 5.6 SQL](./06-sql-consultas.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [5.8 Transações, índices, views e permissões ➡️](./08-transacoes-indices-e-views.md)

</div>
