# 5.5 · MongoDB: comandos essenciais (CRUD)

> **Em uma frase:** no `mongosh` você cria e escolhe bancos com `use`, e manipula documentos com `insertOne/insertMany`, `find`, `updateOne/updateMany`, `replaceOne` e `deleteOne/deleteMany`.

📚 Documentação oficial: [Consultando documentos](https://www.mongodb.com/docs/manual/tutorial/query-documents/) · [CRUD no MongoDB](https://www.mongodb.com/docs/manual/crud/)

> **Hierarquia:** banco de dados → coleções → documentos.

## 🗄️ Bancos e coleções

```javascript
show dbs                       // lista os bancos
use loja                       // usa o banco "loja" (cria quando você gravar algo)
db                             // mostra o banco atual

show collections               // lista as coleções do banco
db.createCollection("clientes")             // cria explicitamente (opcional)
db.createCollection("produtos", {           // com validação de esquema
  validator: { $jsonSchema: {
    bsonType: "object",
    required: ["nome", "preco"],
    properties: { preco: { bsonType: "number", minimum: 0 } }
  } }
})
db.clientes.drop()             // apaga a coleção
```

> [!NOTE]
> Não é obrigatório criar coleções: elas surgem no primeiro `insert`.

## ➕ Create (inserir)

```javascript
db.clientes.insertOne({ nome: "Ana", idade: 30, cidade: "Rio" })

db.clientes.insertMany([
  { nome: "Bruno", idade: 25, cidade: "São Paulo" },
  { nome: "Carla", idade: 41, cidade: "Rio", vip: true }
])
```

Cada inserção devolve o `_id` gerado.

## 🔍 Read (consultar)

```javascript
db.clientes.find()                                   // todos
db.clientes.find({ cidade: "Rio" })                  // filtro por igualdade
db.clientes.find({ idade: { $gte: 30 } })            // idade >= 30
db.clientes.find({ cidade: "Rio", vip: true })       // E (vírgula)
db.clientes.find({ $or: [{ cidade: "Rio" }, { idade: { $lt: 26 } }] })   // OU
db.clientes.find({}, { nome: 1, _id: 0 })            // projeção: só o nome
db.clientes.find().sort({ idade: -1 }).limit(2)      // ordena (desc) e limita
db.clientes.findOne({ nome: "Ana" })                 // só o primeiro
db.clientes.countDocuments({ cidade: "Rio" })        // conta
```

| Operador | Significa |
| --- | --- |
| `$eq` / `$ne` | igual / diferente |
| `$gt` / `$gte` | maior / maior ou igual |
| `$lt` / `$lte` | menor / menor ou igual |
| `$in` / `$nin` | está / não está na lista |
| `$and` / `$or` / `$not` | lógicos |
| `$exists` | o campo existe |
| `$regex` | busca por padrão de texto |

## ✏️ Update (atualizar)

```javascript
// Altera campos específicos de UM documento
db.clientes.updateOne({ nome: "Ana" }, { $set: { idade: 31 } })

// Altera vários documentos
db.clientes.updateMany({ cidade: "Rio" }, { $set: { estado: "RJ" } })

// Outros operadores de atualização
db.clientes.updateOne({ nome: "Bruno" }, { $inc: { idade: 1 } })          // soma 1
db.clientes.updateOne({ nome: "Bruno" }, { $push: { tags: "novo" } })     // adiciona no array
db.clientes.updateOne({ nome: "Bruno" }, { $unset: { tags: "" } })        // remove o campo

// Substitui o documento INTEIRO (menos o _id)
db.clientes.replaceOne({ nome: "Carla" }, { nome: "Carla", idade: 42 })

// Upsert: atualiza se existir, insere se não existir
db.clientes.updateOne({ nome: "Davi" }, { $set: { idade: 19 } }, { upsert: true })
```

> [!WARNING]
> Os comandos antigos `db.colecao.save()` e `db.colecao.update()`, comuns em tutoriais antigos, foram **removidos** do `mongosh`. Use `insertOne`, `updateOne`/`updateMany` e `replaceOne`.

> [!CAUTION]
> Sem o operador `$set`, um `replaceOne` (ou o antigo `update`) **substitui o documento inteiro** e apaga os campos que você não informou.

## 🗑️ Delete (apagar)

```javascript
db.clientes.deleteOne({ nome: "Davi" })
db.clientes.deleteMany({ cidade: "São Paulo" })
db.clientes.deleteMany({})          // ⚠️ apaga TODOS os documentos da coleção
```

## 📌 Resumindo

| Operação | Comandos |
| --- | --- |
| Create | `insertOne`, `insertMany` |
| Read | `find`, `findOne`, `countDocuments` |
| Update | `updateOne`, `updateMany` (com `$set`, `$inc`, `$push`...), `replaceOne` |
| Delete | `deleteOne`, `deleteMany` |

## 🏋️ Mão na massa

**1.** No banco `loja`, insira três produtos (nome, preço e categoria) e liste os da categoria "informática" ordenados pelo preço.

<details>
<summary>Ver resposta</summary>

```javascript
use loja
db.produtos.insertMany([
  { nome: "Teclado", preco: 149.9, categoria: "informática" },
  { nome: "Mouse", preco: 59.9, categoria: "informática" },
  { nome: "Cadeira", preco: 899.0, categoria: "móveis" }
])
db.produtos.find({ categoria: "informática" }).sort({ preco: 1 })
```

</details>

**2.** Aumente em 10% o preço de todos os produtos de informática.

<details>
<summary>Ver resposta</summary>

```javascript
db.produtos.updateMany({ categoria: "informática" }, { $mul: { preco: 1.1 } })
```

O operador `$mul` multiplica o valor do campo.

</details>

**3.** Qual a diferença entre `updateOne(filtro, { $set: { preco: 10 } })` e `replaceOne(filtro, { preco: 10 })`?

<details>
<summary>Ver resposta</summary>

O `updateOne` com `$set` altera **só** o campo `preco` e mantém os outros. O `replaceOne` troca o documento **inteiro**: depois dele, o documento terá apenas `_id` e `preco`.

</details>

---

<div align="center">

[⬅️ 5.4 Modelagem (embedding × referência) e boas práticas](./04-mongodb-modelagem.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [5.6 Índices, desempenho e agregações ➡️](./06-mongodb-indices-e-agregacoes.md)

</div>
