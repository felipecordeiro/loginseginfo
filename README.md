# loginseginfo
Sistema de criação de conta e login o seguinte enunciado:
- identificação - colocar regras de identificação de nomes, não permitir controle de id iguais.
- autenticação - o sistema após receber o pré-cadastro deverá enviar através de e-mail o login (identificação) escolhida com a senha (autenticação) do primeiro acesso. Atenção esta senha deverá ser criada pelo próprio sistema de forma randomica.
- O acesso deve ser realizado em X tempo caso contrário o login e senha serão cancelados.
- O usuário deverá inserir nova senha de acesso, sendo que ao salvar a senha de acesso esta deverá ter caracteres numéricos e alfa numéricos (controle para baixa, média e alta complexidade).
- Senha deve ser criptografada ( pesquisar qual algoritmo de hash confiável).
- Esquecimento de senha: apenas enviar por e-mail e novamente inicia o mesmo processo de senha pela primeira vez, porém o login desta vez não será cancelado.
- O sistema deverá expirar a senha depois de X tempo: não poderá ser a mesma senha (hash identifica) o sistema deve apresentar campos "Senha atual" "Nova senha" e "confirme Nova Senha".

Linguagem:
- Java (JSP/Servlet)

Ferramentas: 
- Eclipse Java EE
- Banco de dados MariaDB
- SGBD MySQL Workbench

Servidor:
- Apache Tomcat 7.0.82

Portas:
- Tomcat admin port - 8005
- HTTP/1.1 - 8080
- AJP/1.3 - 8009

Script da criação da tabela de usuário:

  create database seginfo;

  use seginfo;

  create table usuario (

  id int not null auto_increment,

  email varchar(50) unique not null,

  login varchar(20) unique not null,

  senha char(64) unique not null,

  verificado int not null,

  dataCadastro date not null,

  primary key (id))


select * from usuario;
