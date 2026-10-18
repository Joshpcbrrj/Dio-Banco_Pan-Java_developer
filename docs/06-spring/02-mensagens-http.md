# 6.2 · Mensagens HTTP: request, response e códigos de status

> **Em uma frase:** toda mensagem HTTP tem uma **linha inicial**, **cabeçalhos** e um **corpo** opcional; na resposta, o **código de status** (200, 404, 500...) diz em uma palavra como a requisição terminou.

🐱 Para memorizar os códigos rindo: [http.cat](https://http.cat/)

## 📤 Request (requisição)

```text
POST /clientes HTTP/1.1                ← linha de requisição: MÉTODO  CAMINHO  VERSÃO
Host: api.exemplo.com                  ← cabeçalhos (headers)
Content-Type: application/json
Accept: application/json
Authorization: Bearer eyJhbGciOi...
                                       ← linha em branco separa cabeçalhos e corpo
{ "nome": "Ana", "email": "ana@email.com" }   ← corpo (body)
```

| Parte | O que contém |
| --- | --- |
| **Método** | A ação desejada (`GET`, `POST`...) |
| **URI / caminho** | O recurso (`/clientes`, `/produtos/1?ativo=true`) |
| **Versão** | `HTTP/1.1`, por exemplo |
| **Cabeçalhos** | Metadados: `Host`, `Content-Type`, `Accept`, `Authorization`, `User-Agent`, `Cookie`... |
| **Corpo** | Os dados enviados (opcional; comum em `POST`, `PUT` e `PATCH`) |

## 📥 Response (resposta)

```text
HTTP/1.1 201 Created                   ← linha de status: VERSÃO  CÓDIGO  TEXTO
Content-Type: application/json
Location: /clientes/42
Date: Sat, 18 Oct 2026 12:00:00 GMT

{ "id": 42, "nome": "Ana", "email": "ana@email.com" }
```

> [!NOTE]
> No HTTP/1.x a mensagem é **texto** (legível por humanos). No HTTP/2 e no HTTP/3 ela é **binária**, mas o conteúdo lógico (método, cabeçalhos, corpo) é o mesmo.

## 🔢 Códigos de status

| Faixa | Significa | Mais comuns |
| --- | --- | --- |
| **1xx** | Informativo | `101 Switching Protocols` (usado pelo WebSocket) |
| **2xx** | Sucesso ✅ | `200 OK`, `201 Created`, `204 No Content` |
| **3xx** | Redirecionamento ↪️ | `301 Moved Permanently`, `302 Found`, `304 Not Modified` |
| **4xx** | Erro **do cliente** 🙋 | `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `409 Conflict`, `422 Unprocessable Content`, `429 Too Many Requests` |
| **5xx** | Erro **do servidor** 🔥 | `500 Internal Server Error`, `502 Bad Gateway`, `503 Service Unavailable` |

> [!TIP]
> `401` = "não sei quem você é" (falta autenticação). `403` = "sei quem você é, mas você não pode" (falta permissão).

## 🛡️ Métodos seguros e idempotentes

| Método | Seguro? (não altera o servidor) | Idempotente? (repetir dá o mesmo resultado) |
| --- | --- | --- |
| `GET`, `HEAD`, `OPTIONS` | ✅ | ✅ |
| `PUT` | ❌ | ✅ |
| `DELETE` | ❌ | ✅ |
| `POST` | ❌ | ❌ |
| `PATCH` | ❌ | ❌ (pode ser, dependendo da implementação) |

Repetir um `PUT` que define o nome como "Ana" deixa o nome "Ana", não importa quantas vezes. Repetir um `POST /pedidos` pode criar **vários** pedidos. Isso importa, por exemplo, quando a rede falha e o cliente tenta de novo.

## 📌 Resumindo

- Request: método + caminho + versão, cabeçalhos, corpo opcional.
- Response: versão + código de status, cabeçalhos, corpo opcional.
- 2xx sucesso, 3xx redirecionamento, 4xx erro do cliente, 5xx erro do servidor.
- GET/HEAD/OPTIONS são seguros; GET, PUT e DELETE são idempotentes.

## 🏋️ Mão na massa

**1.** Qual código devolver quando: (a) um recurso foi criado; (b) o recurso não existe; (c) o JSON enviado está mal formatado; (d) o banco de dados caiu?

<details>
<summary>Ver resposta</summary>

(a) `201 Created`; (b) `404 Not Found`; (c) `400 Bad Request`; (d) `500 Internal Server Error` (ou `503 Service Unavailable`, se for uma indisponibilidade temporária).

</details>

**2.** Um usuário logado tenta acessar uma tela só de administradores. `401` ou `403`?

<details>
<summary>Ver resposta</summary>

`403 Forbidden`: o sistema sabe quem ele é (está autenticado), mas ele não tem permissão.

</details>

**3.** Por que `POST` não é idempotente?

<details>
<summary>Ver resposta</summary>

Porque cada `POST` normalmente cria um **novo** recurso. Enviar o mesmo `POST` três vezes pode criar três registros iguais, enquanto três `PUT` iguais deixam o recurso no mesmo estado.

</details>

---

<div align="center">

[⬅️ 6.1 Como funciona o protocolo HTTP](./01-http.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [6.3 Cookies, cache e proxy ➡️](./03-cookies-cache-proxy.md)

</div>
