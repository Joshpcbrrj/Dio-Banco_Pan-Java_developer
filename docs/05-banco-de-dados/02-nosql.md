# 5.2 · NoSQL: o que é e quando usar

> **Em uma frase:** bancos **NoSQL** (*Not Only SQL*) guardam dados em formatos diferentes de tabelas (documentos, chave-valor, colunas, grafos), com esquema flexível e foco em **escalar horizontalmente**.

## 📖 Como surgiram

No início dos anos 2000, empresas como Google e Amazon passaram a lidar com volumes de dados e acessos que os bancos relacionais tradicionais tinham dificuldade de atender em um só servidor. Surgiram soluções próprias e distribuídas:

- **2006:** o Google publica o artigo do **Bigtable**.
- **2007:** a Amazon publica o artigo do **Dynamo** (escrito por Werner Vogels, CTO da empresa, e colegas).
- **2008:** o Facebook cria o **Cassandra**, inspirado nos dois.
- **2009:** é lançado o **MongoDB**, e o termo "NoSQL" se populariza.

## 🗂️ Os quatro tipos principais

| Tipo | Como guarda | Exemplos | Bom para |
| --- | --- | --- | --- |
| **Documento** | Documentos parecidos com JSON | MongoDB, Couchbase, Firestore | Catálogos, perfis, conteúdo |
| **Chave-valor** | Um valor por chave, como um `Map` gigante | Redis, DynamoDB | Cache, sessões, carrinhos |
| **Colunar** (*wide-column*) | Linhas com colunas flexíveis, agrupadas em famílias | Cassandra, HBase | Muita escrita, séries temporais, IoT |
| **Grafo** | Nós e relacionamentos | Neo4j | Redes sociais, recomendações, detecção de fraude |

## ⚖️ SQL × NoSQL

| | Relacional (SQL) | NoSQL |
| --- | --- | --- |
| Estrutura | Tabelas com linhas e colunas | Documentos, chave-valor, colunas ou grafos |
| Esquema | Rígido, definido antes | Flexível; pode variar entre registros |
| Consultas | SQL, com `JOIN`s poderosos | API ou linguagem própria; *joins* limitados |
| Escalabilidade típica | **Vertical** (servidor maior) | **Horizontal** (mais servidores) |
| Transações | ACID completas | Variam; muitos priorizam disponibilidade |
| Melhor para | Dados estruturados, relações complexas, consistência forte | Grandes volumes, esquema variável, alta escala |

> [!NOTE]
> Essa divisão é uma tendência, não uma regra: bancos relacionais modernos também escalam horizontalmente (com réplicas e particionamento) e suportam JSON; vários NoSQL (como MongoDB e DynamoDB) já oferecem transações ACID.

> [!TIP]
> Não existe "o melhor banco". Muitos sistemas usam os dois: **PostgreSQL** para pedidos e pagamentos (consistência), **Redis** para cache e **MongoDB** ou **Elasticsearch** para catálogo e busca. Isso se chama **persistência poliglota**.

### O teorema CAP

Em um sistema distribuído, quando há uma falha de rede entre servidores (*Partition*, partição), é preciso escolher entre **consistência** (*Consistency*: todos veem o mesmo dado) e **disponibilidade** (*Availability*: todos recebem resposta). As iniciais formam o nome **CAP**. Bancos NoSQL fazem escolhas diferentes nesse equilíbrio.

## 🔗 Para conhecer

- [Neo4j Sandbox](https://sandbox.neo4j.com/) (grafos)
- [Apache Cassandra](https://cassandra.apache.org/) (colunar)
- [Redis](https://redis.io/) (chave-valor em memória)
- [MongoDB](https://www.mongodb.com/) (documentos, próximas aulas)

## 📌 Resumindo

- NoSQL = modelos além das tabelas: documento, chave-valor, colunar e grafo.
- Esquema flexível e foco em escala horizontal.
- SQL e NoSQL se complementam; escolha pelo problema.

## 🏋️ Mão na massa

**1.** Que tipo de banco NoSQL você usaria para: (a) cache de sessões de usuário; (b) "amigos de amigos" numa rede social; (c) catálogo de produtos com atributos diferentes por categoria?

<details>
<summary>Ver resposta</summary>

(a) **Chave-valor** (Redis); (b) **Grafo** (Neo4j); (c) **Documento** (MongoDB).

</details>

**2.** Um sistema bancário de transferências deve usar um banco que priorize o quê?

<details>
<summary>Ver resposta</summary>

**Consistência** e transações **ACID**: o saldo não pode ficar errado. Por isso o núcleo de sistemas bancários costuma usar bancos relacionais.

</details>

**3.** O que significa escalar horizontalmente?

<details>
<summary>Ver resposta</summary>

Aumentar a capacidade **adicionando mais servidores** e distribuindo os dados entre eles, em vez de trocar por um servidor mais potente (escala vertical).

</details>

---

<div align="center">

[⬅️ 5.1 Primeiros passos com SQL e bancos relacionais](./01-sql.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [5.3 Conceitos e instalação ➡️](./03-mongodb.md)

</div>
