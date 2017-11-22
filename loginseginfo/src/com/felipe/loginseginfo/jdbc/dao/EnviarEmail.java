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

	// Email para da criação de conta e primeiro acesso, na qual envia os dados
	// iniciais de cadastro
	// (login, senha temporária gerada pelo sistema e link para alteração da
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
			email.setSubject("loginseginfo - confirmação e alteração de senha definitiva");
			email.setHtmlMsg("<html>" + "Dados cadastrais:<br><br>" + "Login: <strong>" + login + "</strong><br>"
					+ "Senha válida por 10 minutos: <strong>" + senha + "</strong><br>"
					+ "Link para alterar sua senha temporária: <a href="
					+ "http://localhost:8080/loginseginfo/nova-senha.jsp>" + "alterar senha</a><br><br>"
					+ "Se você desconhece este cadastro, ignore esta mensagem e seu email será desvinculado em 10 minutos"
					+ "</html>");
			email.setTextMsg("Seu cliente de email não suporta mensagens no formato html");
			email.send();
		} catch (EmailException ex) {
			Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// Email enviado pela página recovery.jsp com uma senha temporária gerada pelo
	// sistema junto com o link de alteração
	public void enviaEmailRecovery(String to, String login, String senha) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailSenha));
			email.setSSLOnConnect(true);
			email.setFrom(emailLogin);
			email.addTo(to);
			email.setSubject("loginseginfo - recuperação de senha");
			email.setHtmlMsg("<html>" + "Sua nova senha gerada pelo sistema:<br><br>" + "Senha: <strong>" + senha
					+ "</strong><br>" + "Link para alteração de senha: <a href="
					+ "http://localhost:8080/loginseginfo/nova-senha.jsp>" + "alterar senha</a>" + "</html>");
			email.setTextMsg("Seu cliente de email não suporta mensagens no formato html");
			email.send();
		} catch (EmailException ex) {
			Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
