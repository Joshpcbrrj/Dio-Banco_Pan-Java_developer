# Performance e índices/ agregaões 

## Importancia de uso de indices na performace

Os índices do MongoDB são utilizados para acelerar consultas ao banco de dados, permitindo que os documentos sejam recuperados com mais eficiência. Existem vários tipos de índices que podem ser criados no MongoDB, como índices simples, compostos, geoespaciais e de texto. Cada tipo de índice é otimizado para diferentes tipos de consultas.

O desempenho do MongoDB pode ser medido por vários indicadores, como a taxa de transferência, a latência e a utilização de recursos do sistema. A taxa de transferência mede a quantidade de dados que pode ser transferida entre o banco de dados e a aplicação em um determinado período de tempo. A latência mede o tempo necessário para executar uma determinada operação no banco de dados. A utilização de recursos do sistema mede a quantidade de recursos que o MongoDB está utilizando, como CPU, memória e armazenamento.

Para otimizar o desempenho do MongoDB, é importante utilizar índices apropriados para cada tipo de consulta e monitorar regularmente a taxa de transferência, a latência e a utilização de recursos do sistema. Além disso, é importante realizar ajustes no hardware e na configuração do MongoDB para garantir uma melhor performance.

<br>

## Agregações
#### Links úteis:
[Operações com agregação](https://www.mongodb.com/docs/manual/aggregation/)<br>
[Construindo com patterns](https://www.mongodb.com/blog/post/building-with-patterns-a-summary)

As Agregações no MongoDB são uma das funcionalidades mais poderosas desse banco de dados NoSQL. Elas permitem que os usuários processem, agrupem e analisem grandes quantidades de dados no banco de dados de uma forma eficiente. Em essência, uma agregação é uma operação que processa um conjunto de documentos e retorna um conjunto de resultados calculados com base em uma ou mais etapas de processamento. As etapas de processamento podem incluir a filtragem de documentos, a ordenação, o agrupamento e a realização de cálculos aritméticos e estatísticos.

A linguagem de agregação do MongoDB é bastante expressiva e flexível, permitindo que os usuários construam pipelines complexos de processamento de dados. O pipeline de agregação consiste em uma série de etapas que são executadas em sequência. Cada etapa do pipeline recebe os resultados da etapa anterior e os transforma ou os filtra de alguma forma.





### Tipos de agregação:

#### Count no MongoDB

A agregação de count é utilizada para contar o número de documentos que satisfazem uma determinada condição. Ela pode ser usada para contar o número total de documentos em uma coleção ou para contar o número de documentos que correspondem a um critério específico.

Algumas das etapas de agregação mais comuns para o count incluem:

- $match: filtra os documentos com base em um conjunto de condições.
- $count: conta o número de documentos que passaram pelas etapas anteriores do pipeline.

<details>
<summary>Exemplo de uso:</summary>

```bash
db.collection.aggregate ([
    { $match: { status: "active" } },
    { $count: "total_active" }
])
```

</details>

<br>

#### Distinct no MongoDB

A agregação de distinct é utilizada para retornar um conjunto distinto de valores para um determinado campo em uma coleção. Ela pode ser usada para encontrar todos os valores únicos de um campo ou para encontrar os valores únicos que correspondem a um critério específico.

Algumas das etapas de agregação mais comuns para o distinct incluem:

- $match: filtra os documentos com base em um conjunto de condições.
- $group: agrupa os documentos com base em um ou mais campos.
- $project: projeta um subconjunto dos campos de um documento.
- $sort: ordena os documentos de acordo com um ou mais campos.
- $limit: limita o número de documentos retornados pela agregação.
- $addToSet: adiciona um valor a um conjunto se ele ainda não estiver presente.

<details>
<summary>Exemplo de uso:</summary>

```bash
db.collection.aggregate([
    { $match: { status: "active" } },
    { $group: { _id: "$field_name", uniqueValues: { $addToSet: "$field_name" } } },
    { $project: { _id: 0, field_name: "$_id", uniqueValues: 1 } }
])
```

</details>

<br>

<br>

<br>

<br>

[Voltar a MongoDB](/Arquivos/Conteudo/5%20-%20Trabalhando%20com%20banco%20de%20dados%20sql%20e%20nosql/5.2.2%20Mongodb.md)<br>
[Voltar a Introdução ao MongoDB](/Arquivos/Conteudo/5%20-%20Trabalhando%20com%20banco%20de%20dados%20sql%20e%20nosql/5.2%20Introducao%20ao%20mongodb.md)<br>
[Voltar ao inicio](/README.md)