# Pixelink
trabalho em java que propoe simular uma rede social, inspirada nas mecanicas do facebook e no twitter.

## Objetivo
visa demosntrar como seria um prototipo de uma rede social em seu estado inicial, tendo apenas os recursos basicos de sistema de login, cadastro de nova conta, pesquisa de usuarios, postagens em formatos de texto/imagens, conexão de usuários, e edição de dados dos usuarios

## Informações gerais do projeto

### UI/UX
A UI do aplicativo bem como as cores e thema foram produzidos no Canvas

### Interface Gráfica
A interface feita para a construção do software foi usando o [Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html), tendo a sua visualização de desenvolvimento através da utilização do [Window Builder](https://eclipse.dev/windowbuilder/) através da IDE [Eclipse](https://eclipseide.org).
### Controle de versão
OVersionamento do projeto foi executado com a utilização do [Git](https://git-scm.com) ,⚠️ **Espeficiamente o  [EGit](https://eclipse.dev/egit/)**

### Banco de dados
COmo o projeto não terá a utilização de Servidores ou de Banco de daods [SQL](https://www.oracle.com/br/database/technologies/appdev/sql.html)/[MongoDB](https://www.mongodb.com/pt-br), será abordado o gerenciamento de informações por arquivos CSV.

## Como contribuir
Primeiramente tenha a IDE [Eclipse](https://eclipseide.org) baixada no seu Pc e siga essas instruções a seguir:

 - na opção **Window**, vai em **Show View** e depois em clique na opção **Other**
 - você verá muitas pastas, a que interessa é a chamada **Git**, nela você clicará em Git Repositories e mostrará a tela de Versionamento de repositórios
 - Clique em "clone a Git repository"
 - Agr preenche o formulario do repositorio com essas chave:
```bash
https://github.com/RaphaelRates/Pixelink
```
 - com isso coloque sua autenticação com nome de usuario e sua senha token, apertando next em seguida
 - depois defina o nome padrão da branch como main (⚠️ pode estar como master como padrão do eclipse, então certifique-se de configurar como "main" a branch principal para não causar conflito).
 - Importa o projeto para a sua IDE, clicando com o botã direito no repositório

**"PRONTO AGORA MÂO NA MASSA"**


## Praticas de Desenvolvimento
Toda vez que editar o codigo, usa a pratica de "Pull" com o versionamento, (que seria pegar as alterações do repositorio remoto para o repositório local do seu computador)
 - Clique no projeto com o botão direito do Mouse e vai até Team
 - Na sequencia, executa a função a função Pull

Ou se preferir clique no botão direito no repositorio e clica na funçã de Pull
 - adicione as modificações ao indicie (uma area temporarea que prepara o envio das alterações pelo commit). Clique no projeto com o botão direito do Mouse e vai até Team
 - Na sequencia, executa a função "Add to index"
 - Clique no projeto com o botão direito do Mouse e vai até Team
 - Execute a função de commit para salvar as alterações pro repositrio local
 - perto do terminal, acesse a janela "Git starting" e coloque sua mensagem de commit
 - dps disso aperte em commit abaixo do TextField da "message commit"

Após a modificação do repositorio local, hora de mandar para o repositorio remoto
- Clique no projeto com o botão direito do Mouse e vai até Team
 - Na sequencia, vai em "Remote" e excute a função Push
 - aperta em Next, dps selecione a opção "All branchs", depois finaliza o envio para o repositorio remoto



