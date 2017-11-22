package com.felipe.loginseginfo.jdbc.modelo;

import java.util.Objects;

/**
 * @brief Classe Usuario
 * @author Felipe Cordeiro Ramos
 * @date 14/11/2017
 */
public class Usuario {

	private Integer id;
	private String email;
	private String login;
	private String senha;
	private Integer verificado;

	public int getVerificado() {
		return verificado;
	}

	public void setVerificado(int verificado) {
		this.verificado = verificado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 17 * hash + Objects.hashCode(this.login);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuario other = (Usuario) obj;
		if (!Objects.equals(this.login, other.login)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Usuario{" + "email=" + email + ", login=" + login + ", senha=" + senha + ", verificado=" + verificado
				+ '}';
	}

}
