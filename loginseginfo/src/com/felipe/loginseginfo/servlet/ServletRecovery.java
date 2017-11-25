package com.felipe.loginseginfo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.felipe.loginseginfo.jdbc.dao.EnviarEmail;
import com.felipe.loginseginfo.jdbc.dao.UsuarioDao;
import com.felipe.loginseginfo.jdbc.dao.UtilidadesSenha;
import com.felipe.loginseginfo.jdbc.modelo.Usuario;

/**
 * Servlet implementation class ServletRecovery
 */
@WebServlet("/ServletRecovery")
public class ServletRecovery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRecovery() {
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
		// se não existir esse email ou não existir esse login...
		if (!dao.existeEmail(email) || !dao.existeLogin(login)) {
			mensagem1 = "E-mail e/ou login inválidos!";
			rd = request.getRequestDispatcher("recovery.jsp");
			request.setAttribute("mensagem1", mensagem1);
			rd.include(request, response);
		} else {
			// se não, altera a senha no banco e envia html email para o email dado como
			// parâmetro
			Usuario usuario = new Usuario();
			UtilidadesSenha u = new UtilidadesSenha();
			usuario.setEmail(email);
			usuario.setLogin(login);
			usuario.setSenha(u.geraRandom());
			dao.alteraSenhaComLogin(usuario.getLogin(), usuario.getSenha());
			dao.alteraVerificado(usuario.getSenha(), 0);
			EnviarEmail enviaEmail = new EnviarEmail();
			enviaEmail.enviaEmailRecovery(usuario.getEmail(), usuario.getLogin(), usuario.getSenha());
			mensagem1 = "Email enviado com sucesso, verifique sua caixa de entrada";
			rd = request.getRequestDispatcher("recovery.jsp");
			request.setAttribute("mensagem1", mensagem1);
			rd.include(request, response);
		}

	}
}
