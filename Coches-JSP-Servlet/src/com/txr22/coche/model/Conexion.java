package com.txr22.coche.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para la conexion a base de datos tabla coches
 * 
 * @author txema
 * @version 1.0
 * 
 */
public class Conexion {

	private Connection jdbcConnection;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;

	/**
	 * Constructor de la clase para coger la conexion, usuario y password
	 * 
	 * @param jdbcURL      url para la conexion a la bbdd
	 * @param jdbcUserName usuario para la conexion
	 * @param jdbcPassword password para la conexion
	 * 
	 */
	public Conexion(String jdbcURL, String jdbcUserName, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUserName;
		this.jdbcPassword = jdbcPassword;
	}

	/**
	 * Metodo para conectar con la bbdd
	 *
	 * @exception SQLException
	 */
	public void conectar() throws SQLException {

		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	/**
	 * Metodo para desconectar la bbdd
	 * 
	 * @exception SQLException
	 */
	public void desconectar() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	/**
	 * Devuelve la conexion a la base de datos
	 * 
	 * @return jdbcConnection
	 */
	public Connection getJdbcConnection() {
		return this.jdbcConnection;
	}

}
