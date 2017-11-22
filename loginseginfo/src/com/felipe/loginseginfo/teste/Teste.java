package com.felipe.loginseginfo.teste;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.felipe.loginseginfo.jdbc.dao.ThreadCronometro;
import com.felipe.loginseginfo.jdbc.dao.UsuarioDao;

/**
 * @brief Classe Teste
 * @author Felipe Cordeiro Ramos
 * @date 14/11/2017
 */
public class Teste {

	public static void main(String[] args) throws InterruptedException {
		UsuarioDao dao = new UsuarioDao();

		// for (int x = 0; x < 5000; x++) {
		// String login = "login" + x;
		// UsuarioTemporario.listaLoginTemporario.add(login);
		// }

		// Timer timer = new Timer();
		// TimerTask task = new TimerTask() {
		// @Override
		// public void run() {
		// for (String string : UsuarioTemporario.listaLoginTemporario) {
		// if (!dao.existeLogin(string)) {
		// System.out.println("Não existe o login: " + string);
		// } else {
		// if (!dao.temCadastroAtivoComLogin(string)) {
		// dao.deleta(string);
		// System.out.println("Login " + string + " deletado com sucesso");
		// }
		// }
		//
		// }
		// }
		//
		// };
		// timer.scheduleAtFixedRate(task, 0, 5000);
//		String string = "felipe";
//		UsuarioTemporario.listaLoginTemporario.add(string);
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//
//				if (!dao.existeLogin(string)) {
//					System.out.println("Não existe o login: " + string);
//				} else {
//					if (!dao.temCadastroAtivoComLogin(string)) {
//						dao.deleta(string);
//						System.out.println("Login " + string + " deletado com sucesso");
//						UsuarioTemporario.listaLoginTemporario.remove(string);
//						System.out.println(UsuarioTemporario.listaLoginTemporario.contains(string));
//					}
//				}
//
//			}
//		};
//		timer.schedule(task, 60000);
		
		Timer timer2 = new Timer();
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				System.out.println(Calendar.getInstance().getTime());
			}
		};
		timer2.scheduleAtFixedRate(task2, 0, 1000);

//		ThreadCronometro t1 = new ThreadCronometro("jon", 1000);
		
	};

}