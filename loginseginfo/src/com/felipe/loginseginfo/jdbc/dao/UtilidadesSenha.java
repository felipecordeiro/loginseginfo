package com.felipe.loginseginfo.jdbc.dao;

/**
 * @brief Classe UtilidadesSenha
 * @author Felipe Cordeiro Ramos
 * @date 14/11/2017
 */
import java.security.SecureRandom;
import org.apache.commons.codec.digest.DigestUtils;

public class UtilidadesSenha {

	// Retorna o hash SHA-256 da soma do salt3 + espaço + senha + salt1 + salt2
	public String geraHash(String senha) {
		final String salt1 = "frasesecreta";
		final String salt2 = "tecnologia";
		final int salt3 = (4535 * (28 + 2120) - 8981 / 8) * 99;
		return DigestUtils.sha256Hex(salt3 + " " + senha + salt1 + salt2);
	}

	// Retornar a soma de 2 números randômicos de 0 a 999999
	public String geraRandom() {
		SecureRandom r = new SecureRandom();
		return "" + (r.nextInt(1000000) + r.nextInt(1000000));
	}

	// Senha forte = 1 letra maiúscula, 2 letras minusculas, 2 números, 1 caracter
	// especial e mínimo de 6 caracteres
	public boolean senhaForte(String senha) {
		final String EXPRESSAO_REGULAR_SENHA_FORTE = "^(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
		return senha.matches(EXPRESSAO_REGULAR_SENHA_FORTE);
	}
}
