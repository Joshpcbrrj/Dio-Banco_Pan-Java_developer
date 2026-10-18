# 6.20 · Autenticação simples, usuários em memória e configuração de segurança

> **Em uma frase:** a configuração de segurança é feita declarando um bean `SecurityFilterChain` (regras de acesso por URL) e um `UserDetailsService` (de onde vêm os usuários); as senhas são sempre guardadas com um **PasswordEncoder**, como o BCrypt.

## 1️⃣ O jeito mais simples: `application.properties`

Para um único usuário de teste:

```properties
spring.security.user.name=josue
spring.security.user.password=123
spring.security.user.roles=USER
```

## 2️⃣ Configuração completa (Spring Security 6+)

> [!IMPORTANT]
> O curso usou a classe `WebSecurityConfigurerAdapter` sobrescrevendo os métodos `configure(...)` (o "configure adapter"). Ela foi **descontinuada no Spring Security 5.7** e **removida no 6.0** (Spring Boot 3). Hoje a configuração é feita com **beans**, como abaixo.

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity          // habilita @PreAuthorize nos métodos
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/managers").hasRole("MANAGER")
                .requestMatchers("/users").hasAnyRole("USER", "MANAGER")
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())      // usuário/senha no cabeçalho Authorization
            .formLogin(Customizer.withDefaults());     // tela de login no navegador
        return http.build();
    }

    @Bean
    public UserDetailsService usuariosEmMemoria(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("senha123"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("master123"))
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

| Trecho | O que faz |
| --- | --- |
| `authorizeHttpRequests` | Regras por URL, da mais específica para a mais genérica. |
| `permitAll()` | Libera sem login. |
| `hasRole("MANAGER")` | Exige o papel (o Spring acrescenta o prefixo `ROLE_`). |
| `anyRequest().authenticated()` | Todo o resto exige login. |
| `httpBasic()` / `formLogin()` | Formas de enviar as credenciais. |
| `InMemoryUserDetailsManager` | Usuários guardados na memória (bom para testes e estudos). |

### Autorização por método

```java
@GetMapping("/managers")
@PreAuthorize("hasRole('MANAGER')")
public String managers() {
    return "Área dos gerentes";
}
```

> [!NOTE]
> Em APIs REST que usam tokens (sem cookies de sessão), é comum desabilitar a proteção CSRF: `http.csrf(csrf -> csrf.disable())`. Em aplicações com login por formulário e cookies, **mantenha** o CSRF ativo.

## 🔑 Codificação de senhas

**Nunca** guarde senhas em texto puro. Use um `PasswordEncoder`, que aplica um **hash** lento e com *salt* (um valor aleatório por senha):

| Encoder | Situação |
| --- | --- |
| **`BCryptPasswordEncoder`** | ✅ O mais usado; salt automático e "custo" configurável. |
| **`Argon2PasswordEncoder`** | ✅ Recomendado pela OWASP; muito resistente a ataques com placas de vídeo. |
| **`SCryptPasswordEncoder`** | ✅ Forte, usa bastante memória de propósito. |
| **`Pbkdf2PasswordEncoder`** | ✅ Aceito (inclusive em normas como a FIPS). |
| `StandardPasswordEncoder` (SHA-256) | ❌ Descontinuado: rápido demais, fácil de quebrar por força bruta. |
| `NoOpPasswordEncoder` / `{noop}` | ❌ Não codifica nada. Só para demonstrações. |

```java
String hash = encoder.encode("senha123");    // "$2a$10$Vh1R..."  (muda a cada execução, por causa do salt)
encoder.matches("senha123", hash);           // true
```

> [!TIP]
> `PasswordEncoderFactories.createDelegatingPasswordEncoder()` grava o algoritmo junto com o hash (`{bcrypt}$2a$10$...`), permitindo trocar de algoritmo no futuro sem invalidar as senhas antigas.

## 📌 Resumindo

- Usuário único de teste: `spring.security.user.*` no `application.properties`.
- Configuração atual: beans `SecurityFilterChain`, `UserDetailsService` e `PasswordEncoder` (o `WebSecurityConfigurerAdapter` foi removido).
- Regras por URL com `authorizeHttpRequests` e por método com `@PreAuthorize`.
- Senhas sempre com BCrypt, Argon2, SCrypt ou PBKDF2.

## 🏋️ Mão na massa

**1.** Libere `GET /` para todos e exija o papel `ADMIN` para `DELETE /usuarios/**`.

<details>
<summary>Ver resposta</summary>

```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers(HttpMethod.GET, "/").permitAll()
    .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
    .anyRequest().authenticated())
```

</details>

**2.** Por que o hash BCrypt da mesma senha sai diferente a cada `encode`?

<details>
<summary>Ver resposta</summary>

Por causa do **salt** aleatório, que é gerado a cada vez e guardado dentro do próprio hash. Assim, duas pessoas com a mesma senha têm hashes diferentes, e tabelas de hashes pré-calculados (*rainbow tables*) não funcionam. O `matches` extrai o salt do hash para conferir.

</details>

**3.** Teste a API protegida com HTTP Basic pelo `curl`.

<details>
<summary>Ver resposta</summary>

```bash
curl -u user:senha123 http://localhost:8080/users        # 200
curl -u user:senha123 http://localhost:8080/managers     # 403 (falta o papel MANAGER)
curl http://localhost:8080/users                         # 401 (sem credenciais)
```

</details>

---

<div align="center">

[⬅️ 6.19 Introdução ao Spring Security](./19-spring-security.md) &nbsp;|&nbsp; [🏠 Índice](../../README.md) &nbsp;|&nbsp; [6.21 Autenticação com banco de dados ➡️](./21-autenticacao-com-banco.md)

</div>
