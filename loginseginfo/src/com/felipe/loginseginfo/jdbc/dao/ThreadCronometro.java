package com.felipe.loginseginfo.jdbc.dao;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @brief Classe ThreadCronometro
 * @author Felipe Cordeiro Ramos
 * @date 21/11/2017
 */

public class ThreadCronometro {

	public ThreadCronometro(String login, long tempo) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				UsuarioDao dao = new UsuarioDao();
				if (!dao.existeLogin(login)) {
					System.out.println("N�o existe o login: " + login);
				} else {
					if (!dao.temCadastroAtivoComLogin(login)) {
						dao.deleta(login);
						System.out.println("Login " + login + " n�o alterou a senha tempor�ria, ent�o foi deletado");
					} else {
						System.out.println("Login " + login + " alterou a senha tempor�ria, ent�o n�o foi deletado");
					}
				}

			}

		};
		timer.schedule(task, tempo);
	}
}
