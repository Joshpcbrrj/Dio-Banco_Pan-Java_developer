# Iniciando Git e criando commit

## Links úteis:
[Documentação do Git](https://git-scm.com/docs/git/pt_BR)<br>
[Download do Git](https://git-scm.com/downloads)<br>
[Download do Github desktop](https://desktop.github.com/)<br>
[Documentação do markdown](https://www.markdownguide.org/)<br>
[Atalho para emojis no markdown](https://gist.github.com/rxaviers/7360908)<br>
[Artigo de dicas de markdown](https://support.zendesk.com/hc/pt-br/articles/4408846544922-Formata%C3%A7%C3%A3o-de-texto-com-Markdown#topic_xqx_mvc_43__line_break)

<br> 

### Comandos para realizar a configuração global após instalação do Git:

```bash
# Fazer configurações globais para email
git config --global user.email "seu_email_entre_aspas"

# Para configurar o nome
git config --global user.name "seu_nome_entre_aspas"

# Para ver as configurações
git config --list 

# Para remover alguma configuração para alterar futuramente vamos fazer:
git config --global --unset configuração_que_queremos_apagar
```

### Comandos úteis para acessar a documentação no terminal:

```bash
# Manual com todos os comandos do Git
man git 

# Comandos principais e suas funcionalidades de forma reduzida
git help
```
### Comandos para iniciar versionamento dó código a partir de uma pasta

```bash
#Git criará uma pasta vazia com instruções de versionamento.
git init

#Para ver a pasta oculta do git vamos fazer
ls -a

#Para criar nossa primeira branch vamos fazer 
git branch -M main

#Pra ver as branchs do repositório vamos usar:
git branch

#OBS:
# Devemos nos certificar se temos uma branch no repositório
# A ausencia de branch vai gerar erros no envio do código local p/ GitHub
```
<br>

## Criando um arquivo teste e fazendo um versionamento

### Criando o arquivo "strogonoff.md" por terminal:

```bash
# O arquivo markdown é uma forma mais humanizada de escrever HTML
# A extensão do arquivo é ".md"
touch strogonoff.md
```
### Conteúdo do arquivo:
```markdown
#Strogonoff de frango :chicken:
###ingredientes
* 1 Quilo de frango sem pele
* 1 Tablete de caldo de galinha
* 3 Colheres de sopa de óleo
* 2 Latas de creme de leite sem soro
* 2 Colheres de sopa de molho de tomate
* 2 Colheres de sopa de ketchup
* 2 Colheres de sopa de mostarda
* Champignon
* Batata palha e arroz branco para acompanhar

###Modo de preparo
1. Em uma panela de fogo médio, acrescente o óleo e o caldo de galinha e, 
sissolva o caldo. Logoem seguida coloque ofrango picado emcubos na panela e deixe cozinhar, 
sempre dando uma olhada pra não queimar.
   
2. Assim que o frango estiver bem cozido, acrescente o molho de tomate, 
o molho de tomate, oketchup, amostarda e o champignon a gosto.

3. Abaixe o fogo e coloque o creme de leite e mexa bem 
até se tornar uma mistura homogênea.

4. Esta pronto para servir.

>Receitinha massa, familia ! Testei e deu bom aqui.
```
### 1 - Vamos adicionar o arquivo na área de "staged" com o comando:
```bash
# Adicionando as modificações em staged
git add nome_do_arquivo 

#Ou, se quisermos versionar mais de um arquivo 
git add *
```
### 2 - Agora vamos fazer um commit indicando resumidamente o que foi feito na modificação:
```bash
# Adicionando uma mensagem na modificação feita
git commit -m "mensagem_entre_aspas"
```

### 3 - Agora vamos adicionar um repositorio remoto (do Github) para subir nosso código para internet:
```bash
# Vamos por um "alias" (nome) nesse repositório. Por padrão é usado "origin".
git remote add origin link_http_do_repositório_dado_no_GitHub

# Para verificar se esta incluso vamos usar o comando:
git remote -v
```
### 4 - Agora vamos subir nosso código com o comando:
```bash
# No comando informaremos o repositório que vai receber e depois a branch que vamos enviar
git push origin main
```
<br>

<br>

[Voltar a introdução ao Git](/Arquivos/Conteudo/1%20-%20Principio%20de%20desenvolvimento%20de%20software/1.1%20Introducao%20ao%20git.md)<br>
[Voltar para inicio](/README.md)
