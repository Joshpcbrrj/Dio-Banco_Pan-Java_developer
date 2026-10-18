# 6.22 · JWT: autenticação com tokens

> **Em uma frase:** um **JWT** (*JSON Web Token*) é um "crachá digital" assinado pelo servidor: depois do login, o cliente o envia em cada requisição, e o servidor confere a **assinatura** para saber quem é o usuário, sem precisar guardar sessão.

🔗 Para decodificar e inspecionar tokens: [jwt.io](https://jwt.io/)

## 🧩 As três partes

Um JWT tem três partes em Base64URL, separadas por pontos:

```text
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqbyIsInJvbGVzIjpbIlVTRVJTIl19.SfKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
└────────────── header ─────────────┘ └──────────────── payload ────────────────┘ └─────────────── signature ───────────────┘
```

| Parte | Conteúdo | Exemplo |
| --- | --- | --- |
| **Header** | Tipo do token e algoritmo da assinatura | `{"alg": "HS256", "typ": "JWT"}` |
| **Payload** | As *claims*: dados sobre o usuário e o token | `{"sub": "jo", "roles": ["USERS"], "exp": 1792348800}` |
| **Signature** | `HMACSHA256(base64(header) + "." + base64(payload), chaveSecreta)` | Garante que nada foi alterado |

*Claims* registradas mais comuns: `sub` (sujeito/usuário), `exp` (expiração), `iat` (emitido em), `iss` (emissor) e `aud` (público).

> [!CAUTION]
> O payload é só **codificado** em Base64, **não criptografado**: qualquer pessoa consegue lê-lo (cole um token no jwt.io e veja). **Nunca** coloque senhas ou dados sensíveis no JWT. A assinatura garante **integridade** (ninguém altera sem ser descoberto), não sigilo.

## 🔄 O fluxo

```mermaid
sequenceDiagram
    participant C as Cliente
    participant A as API
    C->>A: POST /login { usuario, senha }
    A->>A: confere a senha e gera o JWT assinado
    A-->>C: { "token": "eyJhbGci..." }
    C->>A: GET /users  (Authorization: Bearer eyJhbGci...)
    A->>A: filtro JWT: valida assinatura e expiração, monta o usuário
    A-->>C: 200 OK
```

1. O cliente faz login e recebe o token.
2. Guarda o token (em memória no app ou num cookie `HttpOnly` no navegador).
3. Envia em cada requisição no cabeçalho `Authorization: Bearer <token>`.
4. Um **filtro** na API valida a assinatura e a expiração e coloca o usuário no `SecurityContext`.

### Vantagens e cuidados

| ✅ Vantagens | ⚠️ Cuidados |
| --- | --- |
| **Sem estado**: o servidor não guarda sessão; ótimo para escalar e para microsserviços | Não dá para "deslogar" um token antes de expirar sem um mecanismo extra (lista de bloqueio) |
| Compacto, viaja no cabeçalho HTTP | Use **expiração curta** (minutos) + *refresh token* |
| Padrão aberto (RFC 7519), com bibliotecas em todas as linguagens | A chave secreta precisa ser forte e **ficar fora do código** (variável de ambiente) |

## ☕ Gerando e validando em Java (biblioteca JJWT)

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```

```java
@Component
public class JwtService {

    private final SecretKey chave;
    private static final long VALIDADE_MS = 30 * 60 * 1000;   // 30 minutos

    public JwtService(@Value("${jwt.secret}") String segredo) {   // no mínimo 32 caracteres
        this.chave = Keys.hmacShaKeyFor(segredo.getBytes(StandardCharsets.UTF_8));
    }

    public String gerar(String usuario, List<String> roles) {
        Date agora = new Date();
        return Jwts.builder()
                .subject(usuario)
                .claim("roles", roles)
                .issuedAt(agora)
                .expiration(new Date(agora.getTime() + VALIDADE_MS))
                .signWith(chave)
                .compact();
    }

    public Claims validar(String token) {          // lança exceção se for inválido ou expirado
        return Jwts.parser().verifyWith(chave).build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
```

> [!NOTE]
> O projeto do curso usa a versão **0.7.0** do JJWT, com métodos antigos (`setSubject`, `signWith(SignatureAlgorithm, ...)`), além do `WebSecurityConfigurerAdapter`. A API acima é a da linha 0.12, atual.

> [!TIP]
> Uma alternativa sem filtro manual é o **Spring Security OAuth2 Resource Server** (`spring-boot-starter-oauth2-resource-server`): você só configura a chave ou o servidor de autorização (como o Keycloak), e o Spring valida os JWTs sozinho.

## 📌 Resumindo

- JWT = header + payload + assinatura, em Base64URL separados por pontos.
- O payload é legível por qualquer um: nada de dados sensíveis.
- Fluxo: login → token → `Authorization: Bearer` → filtro valida.
- Expiração curta, chave secreta forte e fora do código.

## 🏋️ Mão na massa

**1.** Cole um JWT qualquer no [jwt.io](https://jwt.io/). Dá para ler o payload sem a chave? E alterar sem ser percebido?

<details>
<summary>Ver resposta</summary>

**Ler**, sim: o payload é só Base64. **Alterar**, não: qualquer mudança invalida a assinatura, e o servidor (que tem a chave) rejeita o token.

</details>

**2.** Onde o cliente deve enviar o token em cada requisição?

<details>
<summary>Ver resposta</summary>

No cabeçalho `Authorization`, com o prefixo `Bearer`: `Authorization: Bearer eyJhbGciOi...`.

</details>

**3.** Por que o token deve ter expiração curta?

<details>
<summary>Ver resposta</summary>

Porque, sem estado no servidor, um token roubado vale até expirar. Com validade curta (minutos), o estrago é limitado; para não obrigar o usuário a fazer login toda hora, usa-se um *refresh token* para pedir um novo token.

</details>

---

<div align="center">

[⬅️ 6.21 Autenticação com banco de dados](./21-autenticacao-com-banco.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [6.23 APIs com Spring Security e JWT ➡️](./23-projetos-seguranca.md)

</div>
