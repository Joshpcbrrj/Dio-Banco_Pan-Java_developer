# Gerenciamento de dependências 

## Tipos de dependências do Maven

Existem dois tipos de dependências no Maven: dependências diretas e dependências transitivas.

### Dependências Diretas

As dependências diretas são aquelas que são explicitamente declaradas no arquivo POM (Project Object Model) do seu projeto.

<details>
<summary>Código exemplo:</summary>

```xml
xmlCopy code
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.13.RELEASE</version>
    </dependency>
</dependencies>

#Neste exemplo, a dependência do **spring-core** é declarada diretamente no POM do projeto. O Maven baixará esta dependência e suas dependências transitivas (se houverem) e adicionará ao classpath do projeto. 

```

</details>

### Dependências Transitivas

As dependências transitivas são aquelas que são necessárias para o funcionamento das dependências diretas do projeto.

<details>
<summary>Código exemplo:</summary>

```xml
xmlCopy code
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>5.3.13.RELEASE</version>
    </dependency>
</dependencies>

#Neste exemplo, a dependência do spring-web é declarada diretamente no POM do projeto. No entanto, esta dependência tem uma dependência transitiva com o spring-core, que será automaticamente baixada e adicionada ao classpath do projeto pelo Maven.
```

</details>

<br>

## Transitividade e escopos

### Transitividade
A transitividade é a capacidade das dependências transitivas serem baixadas e adicionadas automaticamente pelo Maven ao classpath do projeto. Por exemplo, se o **`spring-web`** depende do **`spring-core`**, o Maven irá baixar e adicionar ao classpath ambos automaticamente.

<br>

### Escopos
Os escopos no Maven indicam quando e como as dependências são usadas durante o ciclo de vida do projeto. Para especificar o escopo de uma dependência no Maven, basta adicionar o elemento **`<scope>`** ao bloco de dependência no arquivo POM do projeto.

#### Existem diferentes escopos disponíveis no Maven, incluindo:
`compile` 
Este é o escopo padrão do Maven e indica que a dependência é necessária para compilar e executar o projeto. As dependências com este escopo são incluídas no classpath do projeto e também são empacotadas no arquivo JAR ou WAR final.

<details>
<summary>Código exemplo:</summary>

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

</details>

<br>

`provided:`
Este escopo indica que a dependência é fornecida pelo ambiente em tempo de execução. Por exemplo, o Servlet API em um servidor de aplicativos Java EE. Essas dependências não são incluídas no arquivo JAR ou WAR final, mas ainda são necessárias para compilar e testar o código do projeto.

<details>
<summary>Código exemplo:</summary>

```xml
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

</details>

<br>

`runtime`

Este escopo indica que a dependência é necessária apenas em tempo de execução. Essas dependências não são necessárias para compilar o código, mas são necessárias para executá-lo. As dependências com este escopo não são empacotadas no arquivo JAR ou WAR final.

<details>
<summary>Código exemplo:</summary>

```xml
<dependencies>
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <version>19.3.0.0</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

</details>


<br>

`test`

Este escopo indica que a dependência é necessária apenas para testes unitários e de integração. Essas dependências não são incluídas no arquivo JAR ou WAR final.

<details>
<summary>Código exemplo:</summary>

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

</details>


<br>

`system`

Este escopo indica que a dependência está presente no sistema de arquivos local. É necessário fornecer a localização exata da dependência usando o elemento **`<systemPath>`** no arquivo POM. Esse escopo não é recomendado porque as dependências do sistema podem variar de uma máquina para outra.

<details>
<summary>Código exemplo:</summary>

```xml
<dependencies>
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>my-library</artifactId>
        <version>1.0</version>
        <scope>system</scope>
        <systemPath>/path/to/my-library.jar</systemPath>
    </dependency>
</dependencies>
```

</details>


<br>

`import` 

Este escopo é usado apenas em arquivos POM e permite que você inclua dependências de outros arquivos POM no projeto. As dependências importadas são incluídas no classpath do projeto e não são empacotadas no arquivo JAR ou WAR final.

<details>
<summary>Código</summary>

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-framework-bom</artifactId>
            <version>5.3.13.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

</details>


<br>

### Escopo opcional

As bibliotecas opcionais no Maven são dependências que não são necessárias para compilar ou executar o projeto, mas são opcionais e podem ser usadas se necessário. Um exemplo de implementação de uma biblioteca opcional no pom.xmlé usando o elemento <optional> dentro da tag <dependency>. 

<details>
<summary>Código exemplo</summary>

```xml
<dependencies>
  <dependency>
    <groupId>com.example</groupId>
    <artifactId>my-library</artifactId>
    <version>1.0</version>
    <optional>true</optional>
  </dependency>
</dependencies>
```

> 💡OBS: Para incluir essa dependência opcional manualmente, é possível usar o comando: 

```bash
**#**para incluir todas as dependências opcionais
**mvn install -DincludeOptional=true

#para resolver apenas as dependências opcionais.**
**mvn dependency:resolve -DincludeOptional=true**
```

</details>

<br>

### Exclusions

O elemento **`exclusions`** é usado no arquivo **`pom.xml`** do Maven para excluir uma ou mais dependências transitivas de um determinado artefato. Isso é útil quando uma dependência transitiva está causando conflitos ou problemas de compatibilidade.

- Por exemplo, imagine que um projeto precisa da dependência **`my-library`**, que por sua vez depende da biblioteca **`old-library`**. No entanto, o projeto precisa usar uma versão mais recente da biblioteca **`new-library`**, que é incompatível com a **`old-library`**. Nesse caso, é possível usar a tag **`exclusions`** para excluir a **`old-library`** da dependência **`my-library`**.

<details>
<summary>Código exemplo de uso do elemento exclusions no pom.xml:</summary>

```xml
<dependencies>
  <dependency>
    <groupId>com.example</groupId>
    <artifactId>my-library</artifactId>
    <version>1.0</version>
    <exclusions>
      <exclusion>
        <groupId>com.example</groupId>
        <artifactId>old-library</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
</dependencies>
```

</details>

<br>

<br>

[Voltar a Gerenciamento de dependências e build em Java com Maven](/Arquivos/Conteudo/4%20-%20Programa%C3%A7%C3%A3o%20orientada%20a%20objetos/4.5%20Gerenciamento%20de%20dependencias%20e%20build%20em%20java%20com%20maven.md)<br>
[Voltar ao inicio](/README.md)