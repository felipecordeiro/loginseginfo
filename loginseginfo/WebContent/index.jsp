<%-- 
    Document   : index
    Created on : Nov 14, 2017, 1:36:41 AM
    Author     : Felipe
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro/login</title>
<link
	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">


<link rel="stylesheet" href="css/style.css">


</head>

<body>
	<div class="form">

		<ul class="tab-group">
			<li class="tab active"><a href="#signup">Registrar</a></li>
			<li class="tab"><a href="#login">Entrar</a></li>
		</ul>

		<div class="tab-content">
			<div id="signup">
				<h1>Cadastro de usuário</h1>

				<form action="/loginseginfo/ServletValidaCadastro" method="post">

					<div class="field-wrap">
						<label> Email<span class="req">*</span>
						</label> <input type="email" name="email" maxlength="50" required
							autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> Login<span class="req">*</span>
						</label> <input type="text" name="login" maxlength="20" required
							autocomplete="off" />
					</div>

					<button type="submit" class="button button-block" />
					Enviar email de confirmação
					</button>

				</form>

				<%
					if (request.getAttribute("mensagem1") != null) {
						out.println("<br><br><h3>" + request.getAttribute("mensagem1").toString() + "</h3>");
					}
				%>

			</div>

			<div id="login">
				<h1>Bem-vindo!</h1>

				<form action="/loginseginfo/ServletVerificaCadastro" method="post">

					<div class="field-wrap">
						<label> Login<span class="req">*</span>
						</label> <input type="text" name="login" maxlength="20" required
							autocomplete="off" />
					</div>

					<div class="field-wrap">
						<label> Senha<span class="req">*</span>
						</label> <input type="password" name="senha" maxlength="60" required
							autocomplete="off" />
					</div>

					<p class="forgot">
						<a href="/loginseginfo/recovery.jsp">Esqueceu sua senha?</a>
					</p>

					<button class="button button-block" />
					Entrar
					</button>

				</form>

				<%
					if (request.getAttribute("mensagem2") != null) {
						out.println("<br><br><h3>" + request.getAttribute("mensagem2").toString() + "</h3>");
					}
				%>

			</div>

		</div>
		<!-- tab-content -->

	</div>
	<!-- /form -->
	<div class="divCenter">
		<a href="/loginseginfo/about.html">About</a>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="js/index.js"></script>

</body>

</html>