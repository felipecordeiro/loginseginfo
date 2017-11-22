<%-- 
    Document   : recovery
    Created on : Nov 14, 2017, 1:36:41 AM
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Recuperar senha</title>
<link
	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">


<link rel="stylesheet" href="css/style.css">


</head>

<body>
	<div class="form">
		<div class="tab-content">
			<div id="signup">
				<h1>Digite seu email e login de cadastro</h1>
				<br>
				<h3>Você receberá uma nova senha aleatória</h3>

				<form action="/loginseginfo/ServletRecovery" method="post">

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
					Enviar
					</button>

				</form>

				<%
					if (request.getAttribute("mensagem1") != null) {
						out.println("<br><br><h3>" + request.getAttribute("mensagem1").toString() + "</h3>");
					}
				%>

			</div>

			<div id="login"></div>

		</div>
		<!-- tab-content -->

	</div>

	<div class="divCenter">
		<a href="/loginseginfo/index.jsp">Voltar</a>
	</div>
	<!-- /form -->
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="js/index.js"></script>

</body>

</html>
