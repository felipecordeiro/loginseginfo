package com.felipe.loginseginfo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.felipe.loginseginfo.jdbc.dao.UsuarioDao;
import com.felipe.loginseginfo.jdbc.dao.UtilidadesSenha;

/**
 * Servlet implementation class ServletValidaSenhaNova
 */
@WebServlet("/ServletValidaSenhaNova")
public class ServletValidaSenhaNova extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletValidaSenhaNova() {
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
		String senhaAtual = request.getParameter("senhaAtual");
		String senhaNova = request.getParameter("senhaNova");
		String senhaNova2 = request.getParameter("senhaNova2");
		UsuarioDao dao = new UsuarioDao();
		RequestDispatcher rd = null;
		boolean x = false;
		// se senha atual for igual a senha nova ou senha atual for igual senha do campo
		// 'confirme nova senha'...
		if (senhaAtual.equals(senhaNova) || senhaAtual.equals(senhaNova2)) {
			mensagem1 = "'senha atual' e 'nova senha' estão iguais!";
			rd = request.getRequestDispatcher("nova-senha.jsp");
			request.setAttribute("mensagem1", mensagem1);
			request.setAttribute("x", x);
			rd.include(request, response);
			// se a nova senha não for igual ao campo 'confirme nova senha'...
		} else {
			if (!senhaNova.equals(senhaNova2)) {
				mensagem1 = "'nova senha' e 'confirme nova senha' estão diferentes!";
				rd = request.getRequestDispatcher("nova-senha.jsp");
				request.setAttribute("mensagem1", mensagem1);
				request.setAttribute("x", x);
				rd.include(request, response);
			} else {
				// se a nova senha for forte e a nova senha não existir no banco de dados então altera a senha com sucesso
				UtilidadesSenha u = new UtilidadesSenha();
				if (u.senhaForte(senhaNova) && !dao.existeSenha(senhaNova)) {
					dao.alteraSenhaComSenha(senhaAtual, senhaNova);
					mensagem1 = "Senha alterada com sucesso!";
					if (!dao.temCadastroAtivoComSenha(senhaNova)) {
						dao.alteraVerificado(senhaNova, 1);
					}
					x = true;
					rd = request.getRequestDispatcher("nova-senha.jsp");
					request.setAttribute("mensagem1", mensagem1);
					request.setAttribute("x", x);
					rd.include(request, response);
					// então volta para nova-senha.jsp sem fazer nada
				} else {
					mensagem1 = "Senha fraca! Digite uma senha com pelo menos "
							+ "1 letra maiúscula, 2 letras minúsculas, 2 nÃºmeros, "
							+ "1 caracter especial e mínimo de 6 caracteres";
					rd = request.getRequestDispatcher("nova-senha.jsp");
					request.setAttribute("mensagem1", mensagem1);
					request.setAttribute("x", x);
					rd.include(request, response);
				}
			}
		}

	}
}
