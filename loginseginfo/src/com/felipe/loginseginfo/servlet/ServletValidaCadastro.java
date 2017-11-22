package com.felipe.loginseginfo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.felipe.loginseginfo.jdbc.dao.EnviarEmail;
import com.felipe.loginseginfo.jdbc.dao.ThreadCronometro;
import com.felipe.loginseginfo.jdbc.dao.UsuarioDao;
import com.felipe.loginseginfo.jdbc.dao.UtilidadesSenha;
import com.felipe.loginseginfo.jdbc.modelo.Usuario;

/**
 * Servlet implementation class ServletValidaCadastro
 */
@WebServlet("/ServletValidaCadastro")
public class ServletValidaCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletValidaCadastro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String mensagem1 = null;
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		UsuarioDao dao = new UsuarioDao();
		RequestDispatcher rd = null;
		// se existir esse email...
		if (dao.existeEmail(email)) {
			mensagem1 = "E-mail j� est� sendo usado!";
			rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("mensagem1", mensagem1);
			rd.include(request, response);
		} else if (dao.existeLogin(login)) {
			// se n�o se existir esse login...
			mensagem1 = "Login j� est� sendo usado!";
			rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("mensagem1", mensagem1);
			rd.include(request, response);
		} else {
			// se n�o, grava no banco o email, login, hash SHA-256 da senha e seta o
			// campo 'verificado' como 0 = conta de primeiro acesso tempor�ria de 10
			// minutos de dura��o
			// envia html email com os dados da conta e link para altera��o de senha
			Usuario usuario = new Usuario();
			UtilidadesSenha u = new UtilidadesSenha();
			usuario.setEmail(email);
			usuario.setLogin(login);
			usuario.setVerificado(0);
			usuario.setSenha(u.geraRandom());
			dao.adiciona(usuario);
			// Deleta a conta se a senha da mesma n�o for alterada para uma senha definitiva
			// dentro de 10 minutos
			new ThreadCronometro(usuario.getLogin(), 120000);
			System.out.println("Login " + usuario.getLogin() + " registrado");
			EnviarEmail enviaEmail = new EnviarEmail();
			enviaEmail.enviaEmailPrimeiroAcesso(usuario.getEmail(), usuario.getLogin(), usuario.getSenha());
			mensagem1 = "Email enviado com sucesso, verifique sua caixa de entrada e clique no link para alterar a sua senha tempor�ria";
			rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("mensagem1", mensagem1);
			rd.include(request, response);
		}
	}
}
