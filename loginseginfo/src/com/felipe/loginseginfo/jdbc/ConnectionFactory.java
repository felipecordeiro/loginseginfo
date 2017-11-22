package com.felipe.loginseginfo.jdbc;

/**
 * @brief Classe ConnectionFactory
 * @author Felipe Cordeiro Ramos
 * @date   14/11/2017
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mariadb://localhost:3306/seginfo", "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
