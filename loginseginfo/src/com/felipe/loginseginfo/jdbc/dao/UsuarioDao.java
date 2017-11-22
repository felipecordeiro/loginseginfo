package com.felipe.loginseginfo.jdbc.dao;

/**
 * @brief Classe UsuarioDao
 * @author Felipe Cordeiro Ramos
 * @date 14/11/2017
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.felipe.loginseginfo.jdbc.ConnectionFactory;
import com.felipe.loginseginfo.jdbc.modelo.Usuario;

import java.sql.Date;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	// grava no banco de dados toda a instancia de um objeto Usuario
	public void adiciona(Usuario usuario) {
		UtilidadesSenha u = new UtilidadesSenha();
		try {
			PreparedStatement stmt = connection.prepareStatement("insert into usuario "
					+ "(email, login, senha, verificado, dataCadastro)" + " values (?, ?, ?, ?, ?)");

			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, u.geraHash(usuario.getSenha()));
			stmt.setInt(4, usuario.getVerificado());
			stmt.setDate(5, new Date(System.currentTimeMillis()));
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// public void altera(Usuario usuario) {
	// String sql = "update usuario set email=?, login=?, senha=? where id=?";
	// try {
	// PreparedStatement stmt = connection.prepareStatement(sql);
	// stmt.setString(1, usuario.getEmail());
	// stmt.setString(2, usuario.getLogin());
	// stmt.setString(3, usuario.getSenha());
	// stmt.setLong(4, usuario.getId());
	// stmt.execute();
	// stmt.close();
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	// }
	// altera a senha de acordo com a senha antiga como parâmetro validador

	public void alteraSenhaComSenha(String senhaAtual, String senhaNova) {
		try {
			UtilidadesSenha u = new UtilidadesSenha();
			PreparedStatement stmt = connection.prepareStatement("update usuario set senha=? where senha=?");
			stmt.setString(1, u.geraHash(senhaNova));
			stmt.setString(2, u.geraHash(senhaAtual));
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// altera a senha de acordo com o login da conta como parâmetro validador
	public void alteraSenhaComLogin(String login, String senhaNova) {
		try {
			UtilidadesSenha u = new UtilidadesSenha();
			PreparedStatement stmt = connection.prepareStatement("update usuario set senha=? where login=?");
			stmt.setString(1, u.geraHash(senhaNova));
			stmt.setString(2, login);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// altera o Status da conta para 'ativada' = 1 ou 'desativada' = 0
	public void alteraVerificado(String senha, int verificado) {
		try {
			UtilidadesSenha u = new UtilidadesSenha();
			PreparedStatement stmt = connection.prepareStatement("update usuario set verificado=? where senha=?");
			stmt.setInt(1, verificado);
			stmt.setString(2, u.geraHash(senha));
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Verifica o status da conta se estão ativada ou não de acordo com o login e
	// senha da conta
	public boolean temCadastroAtivo(String login, String senha) {
		try {
			UtilidadesSenha u = new UtilidadesSenha();
			Usuario usuario = new Usuario();
			PreparedStatement stmt = this.connection
					.prepareStatement("select verificado from usuario where login=? and senha=?");
			stmt.setString(1, login);
			stmt.setString(2, u.geraHash(senha));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario.setVerificado(rs.getInt("verificado"));
			}
			stmt.close();
			rs.close();
			if (usuario.getVerificado() == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public boolean temCadastroAtivoComLogin(String login) {
		try {
			Usuario usuario = new Usuario();
			PreparedStatement stmt = this.connection.prepareStatement("select verificado from usuario where login=?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario.setVerificado(rs.getInt("verificado"));
			}
			stmt.close();
			rs.close();
			if (usuario.getVerificado() == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public boolean temCadastroAtivoComSenha(String senha) {
		try {
			UtilidadesSenha u = new UtilidadesSenha();
			Usuario usuario = new Usuario();
			PreparedStatement stmt = this.connection.prepareStatement("select verificado from usuario where senha=?");
			stmt.setString(1, u.geraHash(senha));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario.setVerificado(rs.getInt("verificado"));
			}
			stmt.close();
			rs.close();
			if (usuario.getVerificado() == 1) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	// Deleta o usuário de acordo com o login da conta
	public void deleta(String login) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from usuario where login=?");
			stmt.setString(1, login);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Verifica se existe o email dado como parâmetro no banco de dados
	public boolean existeEmail(String email) {
		try {
			Usuario usuario = new Usuario();
			PreparedStatement stmt = this.connection.prepareStatement("select email from usuario where email=?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario.setEmail(rs.getString("email"));
			}
			stmt.close();
			rs.close();
			if (email.equals(usuario.getEmail())) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	// Verifica se existe o login dado como parâmetro no banco de dados
	public boolean existeLogin(String login) {
		try {
			Usuario usuario = new Usuario();
			PreparedStatement stmt = this.connection.prepareStatement("select login from usuario where login=?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario.setLogin(rs.getString("login"));
			}
			stmt.close();
			rs.close();
			if (login.equals(usuario.getLogin())) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	// Verifica se existe a senha dado como parâmetro no banco de dados
	public boolean existeSenha(String senha) {
		try {
			UtilidadesSenha u = new UtilidadesSenha();
			Usuario usuario = new Usuario();
			PreparedStatement stmt = this.connection.prepareStatement("select senha from usuario where senha=?");
			stmt.setString(1, u.geraHash(senha));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario.setSenha(rs.getString("senha"));
			}
			stmt.close();
			rs.close();
			if (u.geraHash(senha).equals(usuario.getSenha())) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
}
