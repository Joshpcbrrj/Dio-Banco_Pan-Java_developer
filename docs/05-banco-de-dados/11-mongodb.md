# 5.11 · MongoDB: conceitos e instalação

> **Em uma frase:** o MongoDB é um banco NoSQL **de documentos**: em vez de linhas em tabelas, ele guarda documentos parecidos com JSON dentro de **coleções**, e cada documento pode ter campos diferentes.

## 📖 Origem

Criado em **2007** por **Dwight Merriman, Eliot Horowitz e Kevin Ryan** (que vinham da DoubleClick), na empresa 10gen, depois renomeada **MongoDB Inc.** A primeira versão pública saiu em **2009**. O núcleo é escrito em **C++**, e o nome vem de "*humongous*" (gigantesco).

## 🧱 Relacional × MongoDB

| Relacional | MongoDB |
| --- | --- |
| Banco de dados | Banco de dados (*database*) |
| Tabela | **Coleção** (*collection*) |
| Linha | **Documento** |
| Coluna | Campo |
| Chave primária | Campo `_id` (gerado automaticamente como `ObjectId`) |
| `JOIN` | Documentos aninhados ou `$lookup` |

```json
{
  "_id": ObjectId("609aeec3aa1a68ca982b47d7"),
  "nome": "Patrick",
  "idade": 30,
  "enderecos": [
    { "rua": "Av. das Conchas", "numero": 102 },
    { "rua": "Rua das Pérolas", "numero": 100 }
  ],
  "ativo": true
}
```

Repare: um documento pode conter **listas** e **outros documentos** dentro dele, algo que no modelo relacional exigiria várias tabelas.

> [!NOTE]
> Por dentro, o MongoDB guarda os documentos em **BSON** (*Binary JSON*), uma versão binária do JSON que é mais rápida de processar e tem tipos extras, como datas, `ObjectId` e números decimais (detalhes na aula [5.13](./13-mongodb-modelagem.md)).

## 🌍 Onde é usado

Aplicações web e mobile, catálogos de produtos, gestão de conteúdo, IoT, análise em tempo real. Ele escala horizontalmente com **sharding** (dividir os dados entre servidores) e tem alta disponibilidade com **replica sets** (cópias automáticas).

## 💻 Instalando com Docker (recomendado)

O jeito mais simples de ter um MongoDB local é com o [Docker](https://docs.docker.com/get-started/):

```bash
# Baixa a imagem oficial e sobe o banco na porta 27017
docker run -d --name mongodb -p 27017:27017 mongo:8

# Abre o terminal do MongoDB (mongosh) dentro do container
docker exec -it mongodb mongosh
```

Imagens oficiais ficam no [Docker Hub](https://hub.docker.com/_/mongo). Veja mais sobre Docker no [módulo extra](../08-extras/03-docker-para-java.md).

### Interfaces gráficas

- **[MongoDB Compass](https://www.mongodb.com/products/tools/compass)**: a interface oficial e gratuita.
- **[Studio 3T](https://studio3t.com/)**: sucessor do antigo Robo 3T (que foi descontinuado).
- Extensão **MongoDB for VS Code**.

> [!TIP]
> Também dá para usar o MongoDB **na nuvem, de graça**, com o [MongoDB Atlas](https://www.mongodb.com/atlas) (plano gratuito com 512 MB), sem instalar nada.

## 📌 Resumindo

- MongoDB = banco de documentos; coleções guardam documentos JSON/BSON.
- Cada documento tem um `_id` único e pode ter estrutura própria.
- Rode localmente com `docker run ... mongo:8` e acesse com `mongosh` ou Compass.

## 🏋️ Mão na massa

**1.** Suba um MongoDB com Docker e confira se ele está rodando.

<details>
<summary>Ver resposta</summary>

```bash
docker run -d --name mongodb -p 27017:27017 mongo:8
docker ps                        # o container "mongodb" deve aparecer com status Up
docker exec -it mongodb mongosh  # abre o shell; digite "exit" para sair
```

</details>

**2.** Converta este registro relacional para um documento MongoDB: cliente (id 1, nome Ana) com dois telefones na tabela `telefones`.

<details>
<summary>Ver resposta</summary>

```json
{
  "_id": 1,
  "nome": "Ana",
  "telefones": ["21 99999-0000", "21 3333-1111"]
}
```

Os telefones viram um **array** dentro do próprio documento, sem precisar de outra tabela.

</details>

**3.** Qual campo o MongoDB cria automaticamente em todo documento?

<details>
<summary>Ver resposta</summary>

O `_id`, que funciona como chave primária. Se você não informar, ele gera um `ObjectId` único.

</details>

---

<div align="center">

[⬅️ 5.10 NoSQL](./10-nosql.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [5.12 Comandos essenciais (CRUD) ➡️](./12-mongodb-comandos.md)

</div>
