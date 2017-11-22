package com.felipe.loginseginfo.jdbc.dao;

//import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
//import org.apache.commons.mail.SimpleEmail;

/**
 * @brief Classe EnviarEmail
 * @author Felipe Cordeiro Ramos
 * @date 14/11/2017
 */
public class EnviarEmail {

	private String emailLogin = "loginseginfo@gmail.com";
	private String emailSenha = "Login0101@seginfo";

	// Email para da cria��o de conta e primeiro acesso, na qual envia os dados
	// iniciais de cadastro
	// (login, senha tempor�ria gerada pelo sistema e link para altera��o da
	// senha)
	public void enviaEmailPrimeiroAcesso(String to, String login, String senha) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailSenha));
			email.setSSLOnConnect(true);
			email.setFrom(emailLogin);
			email.addTo(to);
			email.setSubject("loginseginfo - confirma��o e altera��o de senha definitiva");
			email.setHtmlMsg("<html>" + "Dados cadastrais:<br><br>" + "Login: <strong>" + login + "</strong><br>"
					+ "Senha v�lida por 10 minutos: <strong>" + senha + "</strong><br>"
					+ "Link para alterar sua senha tempor�ria: <a href="
					+ "http://localhost:8080/loginseginfo/nova-senha.jsp>" + "alterar senha</a><br><br>"
					+ "Se voc� desconhece este cadastro, ignore esta mensagem e seu email ser� desvinculado em 10 minutos"
					+ "</html>");
			email.setTextMsg("Seu cliente de email n�o suporta mensagens no formato html");
			email.send();
		} catch (EmailException ex) {
			Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// Email enviado pela p�gina recovery.jsp com uma senha tempor�ria gerada pelo
	// sistema junto com o link de altera��o
	public void enviaEmailRecovery(String to, String login, String senha) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailSenha));
			email.setSSLOnConnect(true);
			email.setFrom(emailLogin);
			email.addTo(to);
			email.setSubject("loginseginfo - recupera��o de senha");
			email.setHtmlMsg("<html>" + "Sua nova senha gerada pelo sistema:<br><br>" + "Senha: <strong>" + senha
					+ "</strong><br>" + "Link para altera��o de senha: <a href="
					+ "http://localhost:8080/loginseginfo/nova-senha.jsp>" + "alterar senha</a>" + "</html>");
			email.setTextMsg("Seu cliente de email n�o suporta mensagens no formato html");
			email.send();
		} catch (EmailException ex) {
			Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
