package com.felipe.loginseginfo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.felipe.loginseginfo.jdbc.dao.UsuarioDao;

/**
 * Servlet implementation class ServletVerificaCadastro
 */
@WebServlet("/ServletVerificaCadastro")
public class ServletVerificaCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletVerificaCadastro() {
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

		String mensagem2 = null;
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		UsuarioDao dao = new UsuarioDao();
		RequestDispatcher rd = null;
		// se não existir esse login ou não existir essa senha...
		if (!dao.existeLogin(login) || !dao.existeSenha(senha)) {
			mensagem2 = "Login e/ou senha inválidos!";
			rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("mensagem2", mensagem2);
			rd.include(request, response);
			// se não, se não tiver um cadastro ativo (campo Verificado = 1 na tabela de
			// usuario), vai para a nova-senha.jsp
		} else {
			if (!dao.temCadastroAtivo(login, senha)) {
				rd = request.getRequestDispatcher("nova-senha.jsp");
				request.setAttribute("mensagem2", mensagem2);
				rd.forward(request, response);
				// se não, vai para bem-vindo.html
			} else {
				rd = request.getRequestDispatcher("bem-vindo.html");
				request.setAttribute("mensagem2", mensagem2);
				rd.forward(request, response);
			}
		}
	}
}
