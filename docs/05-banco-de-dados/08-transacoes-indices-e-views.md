# 5.8 · Transações, índices, views e permissões

> **Em uma frase:** uma **transação** agrupa vários comandos num "tudo ou nada"; um **índice** faz as buscas encontrarem linhas sem ler a tabela inteira; uma **view** salva uma consulta com um nome; e `GRANT`/`REVOKE` controlam **quem** pode fazer o quê.

> [!NOTE]
> Os exemplos usam o banco da loja. Se você alterou os dados, rode o [`loja.sql`](./sql/loja.sql) antes.

## 🔁 Transações: tudo ou nada

Pense numa transferência bancária de R$ 100 da conta A para a conta B. São dois comandos:

```sql
UPDATE contas SET saldo = saldo - 100 WHERE id = 'A';
UPDATE contas SET saldo = saldo + 100 WHERE id = 'B';
```

Se o servidor cair **entre** os dois, o dinheiro sai de A e nunca chega em B. A **transação** resolve isso: ou os dois comandos acontecem, ou nenhum acontece.

```sql
BEGIN;                                                    -- inicia a transação
UPDATE contas SET saldo = saldo - 100 WHERE id = 'A';
UPDATE contas SET saldo = saldo + 100 WHERE id = 'B';
COMMIT;                                                   -- confirma tudo de uma vez
```

| Comando | Faz |
| --- | --- |
| `BEGIN` (ou `START TRANSACTION`) | Inicia uma transação |
| `COMMIT` | Confirma: as alterações passam a valer para todos, de forma definitiva |
| `ROLLBACK` | Desfaz **tudo** desde o `BEGIN` |
| `SAVEPOINT nome` / `ROLLBACK TO nome` | Marca um ponto intermediário e volta só até ele |

### Testando na loja

Vamos registrar uma venda: baixar o estoque do Monitor e criar um pedido. Mas, no meio, desistimos:

```sql
BEGIN;
UPDATE produtos SET estoque = estoque - 1 WHERE id = 3;
INSERT INTO pedidos (cliente_id, status) VALUES (5, 'ABERTO');
SELECT estoque FROM produtos WHERE id = 3;    -- 9: dentro da transação, a mudança já aparece

ROLLBACK;

SELECT estoque FROM produtos WHERE id = 3;    -- 10 de novo
SELECT COUNT(*) FROM pedidos;                 -- 6: o pedido novo também sumiu
```

> [!TIP]
> Use transações como **rede de segurança** ao testar `UPDATE` e `DELETE` à mão: rode dentro de um `BEGIN`, confira com `SELECT` e só então decida entre `COMMIT` e `ROLLBACK`.

### E se der erro no meio?

```sql
BEGIN;
UPDATE produtos SET estoque = estoque - 1 WHERE id = 4;    -- ok (5 → 4)
UPDATE produtos SET estoque = estoque - 10 WHERE id = 4;   -- ERRO: violates check constraint "produtos_estoque_check"
COMMIT;

SELECT estoque FROM produtos WHERE id = 4;                 -- 5: NADA foi gravado
```

No PostgreSQL, depois de um erro a transação fica **abortada**: os comandos seguintes são recusados, e o `COMMIT` final vira, na prática, um `ROLLBACK`. Mesmo o primeiro `UPDATE`, que tinha dado certo, foi desfeito. É exatamente a garantia de "tudo ou nada".

> [!NOTE]
> Fora de um `BEGIN`, cada comando é uma transação sozinho e é confirmado automaticamente (*autocommit*). Ferramentas como o DBeaver podem ter o *autocommit* desligado: nesse caso, você precisa dar `COMMIT` para os outros verem suas alterações.

### ACID

Os bancos relacionais garantem quatro propriedades nas transações, conhecidas pela sigla **ACID**:

| Letra | Propriedade | Significa | Na transferência |
| --- | --- | --- | --- |
| **A** | Atomicidade | Tudo ou nada | Não existe "saiu de A, mas não chegou em B" |
| **C** | Consistência | As regras (restrições) sempre valem | O saldo nunca fica negativo se houver um `CHECK` |
| **I** | Isolamento | Transações simultâneas não se atrapalham | Duas transferências ao mesmo tempo não "perdem" dinheiro |
| **D** | Durabilidade | Depois do `COMMIT`, não se perde mais | Mesmo se o servidor cair um segundo depois |

No Spring, a anotação `@Transactional` faz o `BEGIN`/`COMMIT`/`ROLLBACK` por você (aula [6.25](../06-spring/25-spring-data-jpa.md)).

## ⚡ Índices: buscas rápidas

Sem índice, para achar os clientes de Recife o banco lê a tabela **linha por linha** (*sequential scan*). Com 10 milhões de clientes, isso demora.

Um **índice** funciona como o **índice remissivo de um livro**: em vez de folhear todas as páginas, você procura a palavra no fim do livro e vai direto à página certa. Por dentro, ele é uma estrutura ordenada (normalmente uma árvore B, parente da árvore binária da aula [2.24](../02-java/24-arvores-binarias.md)).

```sql
CREATE INDEX idx_clientes_cidade ON clientes (cidade);
CREATE INDEX idx_pedidos_cliente_data ON pedidos (cliente_id, data);   -- índice composto
DROP INDEX idx_clientes_cidade;
```

> [!NOTE]
> As colunas `PRIMARY KEY` e `UNIQUE` ganham um índice **automaticamente**. Por isso buscar por `id` ou por `email` já é rápido.

### Como saber se o índice está sendo usado: `EXPLAIN`

```sql
EXPLAIN SELECT * FROM clientes WHERE email = 'ana@email.com';
```

```text
Index Scan using clientes_email_key on clientes  (cost=0.13..8.15 rows=1 width=690)
  Index Cond: ((email)::text = 'ana@email.com'::text)
```

`Index Scan` significa que o índice foi usado; `Seq Scan` significa leitura da tabela inteira. Em tabelas **pequenas** (como as nossas), o banco às vezes prefere o `Seq Scan` mesmo havendo índice, porque ler poucas linhas é mais rápido que consultar o índice. Isso é normal.

| ✅ Crie índices em | ❌ Evite |
| --- | --- |
| Colunas muito usadas no `WHERE`, `JOIN` e `ORDER BY` | Indexar todas as colunas "por garantia" |
| Chaves estrangeiras (`pedidos.cliente_id`) | Tabelas minúsculas |
| Colunas com muitos valores diferentes (e-mail, CPF) | Colunas com pouquíssimos valores (`ativo` = true/false) |

> [!WARNING]
> Índices aceleram a **leitura**, mas deixam `INSERT`, `UPDATE` e `DELETE` um pouco mais lentos (o índice também precisa ser atualizado) e ocupam espaço. Crie para as consultas que existem de verdade.

## 🪟 Views: consultas com nome

Uma **view** é uma consulta salva que se comporta como uma tabela "virtual":

```sql
CREATE VIEW vw_total_pedidos AS
SELECT p.id AS pedido, c.nome AS cliente, p.status,
       SUM(i.quantidade * i.preco_unitario) AS total
FROM pedidos p
JOIN clientes c      ON c.id = p.cliente_id
JOIN itens_pedido i  ON i.pedido_id = p.id
GROUP BY p.id, c.nome, p.status;

SELECT * FROM vw_total_pedidos WHERE status = 'PAGO' ORDER BY total DESC;
```

| pedido | cliente | status | total |
| --- | --- | --- | --- |
| 3 | Bruno Reis | PAGO | 1358.90 |
| 1 | Ana Lima | PAGO | 269.70 |
| 6 | Bruno Reis | PAGO | 239.80 |

A view **não guarda** os dados: cada vez que você a consulta, o banco executa o `SELECT` por trás. Ela serve para esconder consultas complicadas e para dar acesso a só uma parte dos dados.

## 🔐 Permissões (DCL)

Cada aplicação ou pessoa deve ter um **usuário** próprio no banco, com o **mínimo** de permissões necessário:

```sql
CREATE ROLE relatorios LOGIN PASSWORD 'senha_forte';          -- cria um usuário
GRANT SELECT ON vw_total_pedidos TO relatorios;               -- só pode LER a view
REVOKE SELECT ON vw_total_pedidos FROM relatorios;            -- retira a permissão
```

Assim, a equipe de relatórios consulta os totais, mas não vê e-mails nem consegue apagar pedidos.

> [!CAUTION]
> Nunca conecte a aplicação ao banco com o superusuário (`postgres`, `root`). Se a aplicação for invadida, o atacante herda **todos** os poderes dela.

## 📌 Resumindo

- Transação: `BEGIN` → comandos → `COMMIT` (confirma) ou `ROLLBACK` (desfaz). Erro no meio = nada é gravado.
- **ACID**: atomicidade, consistência, isolamento e durabilidade.
- Índices aceleram buscas (confira com `EXPLAIN`), mas custam na escrita; PK e `UNIQUE` já têm índice.
- Views são consultas com nome; não guardam dados.
- `GRANT`/`REVOKE` dão e tiram permissões; use usuários com o mínimo necessário.

## 🏋️ Mão na massa

**1.** Registre, numa transação, a venda de 2 teclados (produto 1) para a Elisa (cliente 5): crie o pedido, o item e baixe o estoque.

<details>
<summary>Ver resposta</summary>

```sql
BEGIN;
INSERT INTO pedidos (cliente_id, status) VALUES (5, 'PAGO') RETURNING id;   -- suponha que devolveu 7
INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (7, 1, 2, 149.90);
UPDATE produtos SET estoque = estoque - 2 WHERE id = 1;
COMMIT;
```

Se qualquer um dos três falhar (por exemplo, estoque insuficiente), nenhum é gravado. Use o id que o `RETURNING` devolver no seu banco.

</details>

**2.** Que índice você criaria para acelerar `SELECT * FROM pedidos WHERE status = 'PAGO' AND data >= '2026-10-01'` numa tabela com milhões de pedidos?

<details>
<summary>Ver resposta</summary>

Um índice composto: `CREATE INDEX idx_pedidos_status_data ON pedidos (status, data);`. A coluna da igualdade (`status`) vem primeiro e a do intervalo (`data`) depois. Confira com `EXPLAIN` se ele passou a ser usado.

</details>

**3.** Por que a view `vw_total_pedidos` mostra valores atualizados mesmo depois de um novo pedido ser inserido?

<details>
<summary>Ver resposta</summary>

Porque a view não guarda dados: ela é só o `SELECT` com um nome. Toda consulta à view executa a consulta original sobre as tabelas atuais.

</details>

---

<div align="center">

[⬅️ 5.7 SQL](./07-sql-joins-e-subconsultas.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [5.9 Java + banco de dados ➡️](./09-jdbc.md)

</div>
