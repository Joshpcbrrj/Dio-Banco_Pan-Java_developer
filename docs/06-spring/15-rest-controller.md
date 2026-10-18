# 6.15 · `@RestController`: criando os endpoints

> **Em uma frase:** no Spring, uma classe `@RestController` expõe os endpoints da API; cada método é ligado a um verbo e a uma URL (`@GetMapping`, `@PostMapping`...), e os dados chegam por `@PathVariable`, `@RequestParam` e `@RequestBody`.

## 🧰 As anotações

| Anotação | Para quê |
| --- | --- |
| `@RestController` | Marca a classe como controlador REST (`@Controller` + `@ResponseBody`: o retorno vira JSON). |
| `@RequestMapping("/usuarios")` | Prefixo de URL para a classe toda (também aceita o método HTTP). |
| `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping` | Ligam um método a um verbo + caminho. |
| `@PathVariable` | Lê um pedaço da URL: `/usuarios/{id}`. |
| `@RequestParam` | Lê a *query string*: `/usuarios?nome=Ana`. |
| `@RequestBody` | Converte o corpo JSON em um objeto Java. |
| `@ResponseStatus` | Define o status de sucesso (ex.: `201`). |
| `ResponseEntity<T>` | Controle total da resposta: status, cabeçalhos e corpo. |

## 🛠️ Exemplo completo

```java
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping                                        // GET /usuarios?nome=ana
    public List<Usuario> listar(@RequestParam(required = false) String nome) {
        return service.listar(nome);
    }

    @GetMapping("/{id}")                               // GET /usuarios/42
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)               // 200 com o usuário
                .orElse(ResponseEntity.notFound().build());   // 404
    }

    @PostMapping                                       // POST /usuarios
    public ResponseEntity<Usuario> criar(@RequestBody @Valid Usuario usuario) {
        Usuario salvo = service.salvar(usuario);
        URI local = URI.create("/usuarios/" + salvo.getId());
        return ResponseEntity.created(local).body(salvo);    // 201 + cabeçalho Location
    }

    @PutMapping("/{id}")                               // PUT /usuarios/42
    public Usuario atualizar(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        return service.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")                            // DELETE /usuarios/42
    @ResponseStatus(HttpStatus.NO_CONTENT)             // 204
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
```

> [!TIP]
> O `@Valid` ativa a validação do objeto (com anotações como `@NotBlank` e `@Email` na classe), usando o `spring-boot-starter-validation`. Erros de validação viram `400 Bad Request` automaticamente; na aula [6.17](./17-tratamento-de-erros.md) você aprende a deixar essa resposta amigável.

> [!NOTE]
> Em projetos maiores, o controller **não** recebe nem devolve as entidades do banco diretamente: usam-se **DTOs** (objetos de transferência), muitas vezes `record`s, para controlar exatamente o que entra e sai da API.

## 🧪 Testando a API

- Navegador: só faz `GET`.
- **Postman**, **Insomnia** ou **Bruno**: ferramentas gráficas para todos os verbos.
- **curl** no terminal:

```bash
curl http://localhost:8080/usuarios
curl -X POST http://localhost:8080/usuarios -H "Content-Type: application/json" -d '{"nome":"Ana","login":"ana"}'
```

- **Swagger UI**: documentação interativa gerada pela própria API (próxima aula).
- No IntelliJ: arquivos `.http` (**Tools → HTTP Client**).

## 📌 Resumindo

- `@RestController` + `@RequestMapping` na classe; `@GetMapping`/`@PostMapping`... nos métodos.
- Dados de entrada: `@PathVariable` (URL), `@RequestParam` (query string) e `@RequestBody` (JSON).
- `ResponseEntity` controla status e cabeçalhos; `@Valid` valida a entrada.

## 🏋️ Mão na massa

**1.** Crie um endpoint `GET /calculadora/somar?a=2&b=3` que devolva `5`.

<details>
<summary>Ver resposta</summary>

```java
@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @GetMapping("/somar")
    public int somar(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }
}
```

</details>

**2.** Qual a diferença entre `@PathVariable` e `@RequestParam`?

<details>
<summary>Ver resposta</summary>

`@PathVariable` lê uma parte do **caminho** (`/usuarios/42` → `42`), normalmente usada para identificar um recurso. `@RequestParam` lê a **query string** (`/usuarios?nome=Ana` → `Ana`), normalmente usada para filtros e opções.

</details>

**3.** Faça o `POST /produtos` devolver `201 Created` com o cabeçalho `Location`.

<details>
<summary>Ver resposta</summary>

```java
@PostMapping
public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
    Produto salvo = repository.save(produto);
    return ResponseEntity
            .created(URI.create("/produtos/" + salvo.getId()))
            .body(salvo);
}
```

</details>

---

<div align="center">

[⬅️ 6.14 REST e APIs RESTful](./14-rest.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [6.16 Documentando a API com OpenAPI e Swagger ➡️](./16-openapi-swagger.md)

</div>
