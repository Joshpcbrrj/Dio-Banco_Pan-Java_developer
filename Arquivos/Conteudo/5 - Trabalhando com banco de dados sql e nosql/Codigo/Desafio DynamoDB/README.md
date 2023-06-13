# Documentação do projeto AWS DynamoDB
> Baseado no repositório para o live coding do dia 30/09/2021 sobre o Amazon DynamoDB [(Link do original)](https://github.com/cassianobrexbit/dio-live-dynamodb)

### Serviço utilizado
  - Amazon DynamoDB
  - Amazon CLI para execução em linha de comando

### O que fazer ? 
- Crie uma tabela no DynamoDb utilizando o Amazon CLI.
- Insira dados
- Crie índices globais secundários
- Realize algumas pesquisas

#### Arquitetura da prática
<img src="img\1.jpg" alt="Esquema do projeto" height="400">

### (Complementar) Como criar usuário no AWS e habilitar uso do CLI:
[Como instalar e configurar o AWS-CLI](https://www.youtube.com/watch?v=hBRnWjz9YxM)
[Primeiros passos com AWS CLI! Como usar o aws cli ?](https://www.youtube.com/watch?v=nqazfZEj2G0)

### (Complementar) Caso queira fazer o projeto pelo WSL 
##### Instalação WSL
[Como instalar WSL (Rocketseat youtube)](https://www.youtube.com/watch?v=hd6lxt5iVsg)
[WSL mais completo com sysmed (Distrod)](https://github.com/nullpo-head/wsl-distrod)

##### Instalação do CLI no WSL
[Opção de instalação para linux no site da amazon](https://docs.aws.amazon.com/pt_br/cli/latest/userguide/getting-started-install.html)
[Video no youtube](https://www.youtube.com/watch?v=ug6X8kl0M_k)

## Comandos para execução do experimento no CLI:
#### Criando tabela, inserindo elementos e criando indices secundários de busca:
Para criar a tabela "Music"
```bash
aws dynamodb create-table \
    --table-name Music \
    --attribute-definitions \
        AttributeName=Artist,AttributeType=S \
        AttributeName=SongTitle,AttributeType=S \
    --key-schema \
        AttributeName=Artist,KeyType=HASH \
        AttributeName=SongTitle,KeyType=RANGE \
    --provisioned-throughput \
        ReadCapacityUnits=10,WriteCapacityUnits=5  
```
<br>

Inserindo item avulso a tabela
```bash
aws dynamodb put-item \
    --table-name Music \
    --item file://itemmusic.json \
```

<br>


Inserir múltiplos itens
```bash
aws dynamodb batch-write-item \
    --request-items file://batchmusic.json
```

<br>

Criando index global secundário baseado no título do album
```bash
aws dynamodb update-table \
    --table-name Music \
    --attribute-definitions AttributeName=AlbumTitle,AttributeType=S \
    --global-secondary-index-updates \
        "[{\"Create\":{\"IndexName\": \"AlbumTitle-index\",\"KeySchema\":[{\"AttributeName\":\"AlbumTitle\",\"KeyType\":\"HASH\"}], \
        \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"
```

<br>

Criando index global secundário baseado no nome do artista e no título do álbum
```bash
aws dynamodb update-table \
    --table-name Music \
    --attribute-definitions\
        AttributeName=Artist,AttributeType=S \
        AttributeName=AlbumTitle,AttributeType=S \
    --global-secondary-index-updates \
        "[{\"Create\":{\"IndexName\": \"ArtistAlbumTitle-index\",\"KeySchema\":[{\"AttributeName\":\"Artist\",\"KeyType\":\"HASH\"}, {\"AttributeName\":\"AlbumTitle\",\"KeyType\":\"RANGE\"}], \
        \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"
```

<br>

Criando index global secundário baseado no título da música e no ano
```bash
aws dynamodb update-table \
    --table-name Music \
    --attribute-definitions\
        AttributeName=SongTitle,AttributeType=S \
        AttributeName=SongYear,AttributeType=S \
    --global-secondary-index-updates \
        "[{\"Create\":{\"IndexName\": \"SongTitleYear-index\",\"KeySchema\":[{\"AttributeName\":\"SongTitle\",\"KeyType\":\"HASH\"}, {\"AttributeName\":\"SongYear\",\"KeyType\":\"RANGE\"}], \
        \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"
```

<br>

Criando index global secundário baseado no ano da música <span style="color:red">(Criado por mim)</span>
```bash
aws dynamodb update-table \
    --table-name Music \
    --attribute-definitions \
        AttributeName=SongYear,AttributeType=S \
    --global-secondary-index-updates \
        "[{\"Create\":{\"IndexName\": \"SongYear-index\",\"KeySchema\":[{\"AttributeName\":\"SongYear\",\"KeyType\":\"HASH\"}], \
        \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5},\"Projection\":{\"ProjectionType\":\"ALL\"}}}]"
```

<br>

#### Testendo os index criados executando algumas queries 

Pesquisando por artista
```bash
aws dynamodb query \
    --table-name Music \
    --key-condition-expression "Artist = :artist" \
    --expression-attribute-values  '{":artist":{"S":"Silverchair"}}'
```

<br>

Pesquisando por artista e título da música
```bash
aws dynamodb query \
    --table-name Music \
    --key-condition-expression "Artist = :artist and SongTitle = :title" \
    --expression-attribute-values file://keyconditions.json
```

<br>

Pesquisa pelo index secundário baseado no título do album
```bash
aws dynamodb query \
    --table-name Music \
    --index-name AlbumTitle-index \
    --key-condition-expression "AlbumTitle = :name" \
    --expression-attribute-values  '{":name":{"S":"Fear of the Dark"}}'
```
<br>

Pesquisa pelo index secundário baseado no nome do artista e no título do album
```bash
aws dynamodb query \
    --table-name Music \
    --index-name ArtistAlbumTitle-index \
    --key-condition-expression "Artist = :v_artist and AlbumTitle = :v_title" \
    --expression-attribute-values  '{":v_artist":{"S":"Iron Maiden"},":v_title":{"S":"Peace sells"} }'
```

<br>

Pesquisa pelo index secundário baseado no título da música e no ano
```bash
aws dynamodb query \
    --table-name Music \
    --index-name SongTitleYear-index \
    --key-condition-expression "SongTitle = :v_song and SongYear = :v_year" \
    --expression-attribute-values  '{":v_song":{"S":"Wasting Love"},":v_year":{"S":"1992"} }'
```
<br>

Pesquisa pelo index secundário baseado no ano da música <span style="color:red">(Criado por mim)</span>
```bash
aws dynamodb query \
    --table-name Music \
    --index-name SongYear-index \
    --key-condition-expression "SongYear = :y" \
    --expression-attribute-values '{ ":y": {"S": "1995"} }'
```

<br>

### Alguns prins da minha tabela no DynamoDB
##### indices
<img src="img\2.jpg" alt="Esquema do projeto" height="400">

##### Itens
<img src="img\3.jpg" alt="Esquema do projeto" height="400">
