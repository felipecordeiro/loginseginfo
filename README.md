# loginseginfo
Sistema de criação de conta e login de acordo com o seguinte enunciado:
- identificação - colocar regras de identificação de nomes, não permitir controle de id iguais.
- autenticação - o sistema após receber o pré-cadastro deverá enviar através de e-mail o login (identificação) escolhida com a senha (autenticação) do primeiro acesso. Atenção esta senha deverá ser criada pelo próprio sistema de forma randomica.
- O acesso deve ser realizado em X tempo caso contrário o login e senha serão cancelados.
- O usuário deverá inserir nova senha de acesso, sendo que ao salvar a senha de acesso esta deverá ter caracteres numéricos e alfa numéricos (controle para baixa, média e alta complexidade).
- Senha deve ser criptografada (pesquisar qual algoritmo de hash confiável).
- Esquecimento de senha: apenas enviar por e-mail e novamente inicia o mesmo processo de senha pela primeira vez, porém o login desta vez não será cancelado.
- O sistema deverá expirar a senha depois de X tempo: não poderá ser a mesma senha (hash identificador) o sistema deve apresentar campos "Senha atual" "Nova senha" e "Confirme nova senha".

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

![registro](https://user-images.githubusercontent.com/18239321/33363112-26002db4-d4c6-11e7-990f-7db66bb3cd3a.PNG)

![entrar](https://user-images.githubusercontent.com/18239321/33363136-3cff43a6-d4c6-11e7-9435-e45fa78250f9.PNG)

![emailenviado](https://user-images.githubusercontent.com/18239321/33363143-42c04c22-d4c6-11e7-8fb0-280b794148aa.PNG)

![respostaemail](https://user-images.githubusercontent.com/18239321/33363156-4e0b573e-d4c6-11e7-82e2-734c16365222.PNG)

![usuariobd](https://user-images.githubusercontent.com/18239321/33363151-4a1d0d16-d4c6-11e7-9dd2-6edecaf7d34a.PNG)

![senhafraca](https://user-images.githubusercontent.com/18239321/33363180-586bd2e4-d4c6-11e7-8820-ce6e9200c089.PNG)

![novasenha](https://user-images.githubusercontent.com/18239321/33363175-541f5fe4-d4c6-11e7-82a6-1450f171b788.PNG)

![senhaforte](https://user-images.githubusercontent.com/18239321/33363184-5cb8852c-d4c6-11e7-8ea0-5fe34de54cfe.PNG)

![verificado](https://user-images.githubusercontent.com/18239321/33363549-f4eb0b48-d4c7-11e7-9392-f037801d63f1.PNG)
