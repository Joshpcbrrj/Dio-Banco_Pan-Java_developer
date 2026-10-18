# REST controller

Rest Controllers são classes que fornecem endpoints para sua aplicação. Esses endpoints geralmente fornecem dados em formato JSON ou XML, e podem ser acessados por outras aplicações através de requisições HTTP.

### As anotações mais comuns utilizadas em Rest Controllers são:

### `@RestController`
Indica que a classe é um Rest Controller, e que seus métodos serão mapeados para endpoints da aplicação.

### `@RequestMapping(prefix)`
Mapeia um método ou classe para um endpoint da aplicação, indicando qual verbo HTTP (GET, POST, PUT, DELETE, etc.) deve ser utilizado para acessá-lo.

### `@GetMapping` 
Mapeia um método para um endpoint da aplicação, especificando que ele deve ser acessado através do verbo HTTP GET.

### `@PostMapping` 
Mapeia um método para um endpoint da aplicação, especificando que ele deve ser acessado através do verbo HTTP POST.

### `@PutMapping` 
Mapeia um método para um endpoint da aplicação, especificando que ele deve ser acessado através do verbo HTTP PUT.

### `@DeleteMapping` 
Mapeia um método para um endpoint da aplicação, especificando que ele deve ser acessado através do verbo HTTP DELETE.

### `@PathVariable` 
Utilizada para mapear variáveis presentes na URL da requisição a parâmetros do método do controlador. É necessário especificar o nome da variável entre chaves ({}) na anotação @GetMapping ou @RequestMapping, e indicar a variável correspondente no método com a anotação @PathVariable.

### `@RequestParam` 
Utilizada para acessar parâmetros presentes na requisição. É possível especificar o nome do parâmetro na anotação, ou deixar que o nome seja inferido automaticamente a partir do nome do parâmetro no método.

### `@RequestBody` 
Itilizada para acessar o corpo da requisição como um objeto Java. É necessário especificar o tipo do objeto na assinatura do método.

<br>

<br>

<br>

[Voltar a Criando API REST documentada com Spring web e Swagger](/Arquivos/Conteudo/6%20-%20Ganhando%20produtividade%20com%20spring%20framwork/6.3%20Criando%20api%20rest%20documentada%20com%20spring%20web%20e%20swagger.md)<br>
[Voltar ao inicio](/README.md)