# 8.3 · Docker para quem programa em Java

> **Em uma frase:** o Docker roda programas dentro de **contêineres**, ambientes isolados e portáteis; com ele você sobe um PostgreSQL, um MongoDB ou a sua própria API Spring Boot com um único comando, igual em qualquer computador.

> [!NOTE]
> Conteúdo extra: esta aula não faz parte do curso original, mas é usada em várias aulas (MongoDB, DynamoDB, RDS) e é muito pedida em vagas.

## 📦 Conceitos

| Conceito | Analogia | O que é |
| --- | --- | --- |
| **Imagem** | A receita / o molde | Um pacote com o sistema, as bibliotecas e o programa (ex.: `postgres:17`) |
| **Contêiner** | O bolo pronto | Uma imagem **em execução**, isolada das outras |
| **Docker Hub** | O livro de receitas | Repositório público de imagens ([hub.docker.com](https://hub.docker.com/)) |
| **Dockerfile** | A receita escrita por você | Arquivo que descreve como montar a imagem da sua aplicação |
| **Docker Compose** | O cardápio completo | Um arquivo YAML que sobe vários contêineres juntos |

> [!TIP]
> "Na minha máquina funciona" deixa de ser desculpa: o contêiner leva junto tudo o que a aplicação precisa.

## 🚀 Instalando e primeiros comandos

Instale o [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Windows e macOS) ou o Docker Engine (Linux). No Windows, ele usa o WSL 2.

```bash
docker run -d --name postgres -e POSTGRES_PASSWORD=senha -e POSTGRES_DB=banco -p 5432:5432 postgres:17

docker ps                    # contêineres rodando
docker logs postgres         # logs do contêiner
docker exec -it postgres psql -U postgres -d banco    # abre o terminal do banco
docker stop postgres         # para
docker start postgres        # inicia de novo
docker rm -f postgres        # remove
```

| Opção | Significa |
| --- | --- |
| `-d` | Roda em segundo plano |
| `--name` | Dá um nome ao contêiner |
| `-e` | Define uma variável de ambiente |
| `-p 5432:5432` | Liga a porta do seu computador à do contêiner |
| `-v dados:/var/lib/postgresql/data` | Volume: guarda os dados mesmo se o contêiner for removido |

## 🧩 Docker Compose: banco + API

```yaml
# compose.yaml
services:
  banco:
    image: postgres:17
    environment:
      POSTGRES_DB: banco
      POSTGRES_PASSWORD: senha
    ports:
      - "5432:5432"
    volumes:
      - dados:/var/lib/postgresql/data

  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://banco:5432/banco
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: senha
    depends_on:
      - banco

volumes:
  dados:
```

```bash
docker compose up -d      # sobe tudo
docker compose down       # derruba tudo
```

> [!NOTE]
> Dentro da rede do Compose, a API acessa o banco pelo **nome do serviço** (`banco`), e não por `localhost`.

> [!TIP]
> Desde o Spring Boot 3.1, com a dependência `spring-boot-docker-compose`, o próprio Spring **sobe** o `compose.yaml` ao iniciar a aplicação e configura a conexão sozinho.

## 🐳 Dockerfile para uma API Spring Boot

```dockerfile
# Etapa 1: compila o projeto
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw -q package -DskipTests

# Etapa 2: imagem final, só com o necessário para rodar
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```bash
docker build -t minha-api .
docker run -p 8080:8080 minha-api
```

> [!TIP]
> Sem escrever Dockerfile nenhum, o Spring Boot também gera a imagem: `./mvnw spring-boot:build-image`.

## 📌 Resumindo

- Imagem = molde; contêiner = imagem rodando; Docker Hub = repositório de imagens.
- `docker run -d -p ... -e ... imagem` sobe um serviço; `docker ps`, `logs`, `exec`, `stop`, `rm` gerenciam.
- Docker Compose sobe vários serviços juntos; dentro dele, use o nome do serviço como host.
- Dockerfile em duas etapas (build + execução) gera imagens pequenas da sua API.

## 🏋️ Mão na massa

**1.** Suba um PostgreSQL com Docker e conecte a ele uma aplicação Spring Boot com Spring Data JPA.

<details>
<summary>Ver resposta</summary>

```bash
docker run -d --name postgres -e POSTGRES_PASSWORD=senha -e POSTGRES_DB=banco -p 5432:5432 postgres:17
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banco
spring.datasource.username=postgres
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

E a dependência `org.postgresql:postgresql` (escopo `runtime`).

</details>

**2.** Os dados do banco somem quando você remove o contêiner. Como evitar?

<details>
<summary>Ver resposta</summary>

Use um **volume**: `docker run ... -v dados-postgres:/var/lib/postgresql/data postgres:17`. Os dados ficam no volume, que sobrevive à remoção do contêiner.

</details>

**3.** Por que, no Compose, a URL do banco usa `banco:5432` e não `localhost:5432`?

<details>
<summary>Ver resposta</summary>

Porque cada contêiner tem seu próprio `localhost`. Dentro da rede do Compose, os serviços se encontram pelo **nome** (`banco`), que funciona como um nome de DNS interno.

</details>

---

<div align="center">

[⬅️ 8.2 Lidando com null](./02-optional-e-null.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [8.4 Testando uma API Spring Boot ➡️](./04-testes-spring-boot.md)

</div>
