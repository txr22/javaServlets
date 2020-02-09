package com.txr22.coche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.txr22.coche.model.Coche;
import com.txr22.coche.model.Conexion;
import com.txr22.coche.model.Constantes;

/**
 * Clase dao para gestion de los coches
 * @author txema
 * @version 1.0
 * */
public class CocheDao {

	private Conexion con;
	private Connection connection;

	/**
	 * Constructor para la conexion
	 * */
	public CocheDao(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	//INSERTAR COCHE
	public int insertar(final Coche cocheParametros) throws SQLException {
		
		int resInsertar = Constantes.RESULTADO_NOOK;
		int cont = 0;
		String sql = "INSERT INTO coches (MATRICULA, MARCA, MODELO, ID_VIVIENDA) VALUES (?,?,?,?)";
		
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(++cont, cocheParametros.getMatricula());
		pstm.setString(++cont, cocheParametros.getMarca());
		pstm.setString(++cont, cocheParametros.getModelo());
		pstm.setInt(++cont, cocheParametros.getIdVivienda());

		
		resInsertar = pstm.executeUpdate();
		pstm.close();
		con.desconectar();
		
		return resInsertar;
		
	}
	
	//LISTAR COCHES
	public List<Coche> listarCoches() throws SQLException {
		
		List<Coche> listaCoches = new ArrayList<Coche>();
		String sql = "SELECT * FROM coches ORDER BY ID_VIVIENDA";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement stm = connection.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()) {
			String matricula = rs.getString("MATRICULA");
			String marca = rs.getString("MARCA");
			String modelo = rs.getString("MODELO");
			int idVivienda = rs.getInt("ID_VIVIENDA");

			
			Coche oCoche = new Coche(matricula, marca, modelo, idVivienda);
			listaCoches.add(oCoche);
		}
		
		con.desconectar();
		return listaCoches;
	}
	
	
	//OBTENER POR MATRICULA
	public Coche buscarPorMatricula(final String matricula)throws SQLException {
		
		int cont = 0;
		Coche oCoche = null;
		String sql = "SELECT * FROM coches WHERE MATRICULA = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement stm = connection.prepareStatement(sql);
		
		stm.setString(++cont, matricula);
		
		ResultSet rs = stm.executeQuery();
		if(rs.next()) {
			oCoche = new Coche(rs.getString("MATRICULA"), rs.getString("MARCA"), rs.getString("MODELO"), rs.getInt("ID_VIVIENDA"));
		}
		
		rs.close();
		con.desconectar();
		return oCoche;
	}
	
	//OBTENER POR ID
	public Coche buscarPorId(final String matricula)throws SQLException {
		
		int cont = 0;
		Coche oCoche = null;
		String sql = "SELECT * FROM coches WHERE MATRICULA = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement stm = connection.prepareStatement(sql);
		
		stm.setString(++cont, matricula);

		
		ResultSet rs = stm.executeQuery();
		if(rs.next()) {
			oCoche = new Coche(rs.getString("MATRICULA"), rs.getString("MARCA"), rs.getString("MODELO"), rs.getInt("ID_VIVIENDA"));
		}
		
		rs.close();
		con.desconectar();
		return oCoche;
	}
	
	//ACTUALIZAR SOLO LOS DATOS QUE PUEDEN VARIAR, YA QUE EL PISO NUNCA VA A CAMBIAR
	public int actualizar(Coche cocheNuevo) throws SQLException {
		
		int resActualizar = Constantes.RESULTADO_NOOK;
		int cont = 0;
		String sql = "UPDATE coches SET MARCA = ?, MODELO = ?, ID_VIVIENDA = ? WHERE MATRICULA = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.setString(++cont, cocheNuevo.getMarca());
		stm.setString(++cont, cocheNuevo.getModelo());
		stm.setInt(++cont, cocheNuevo.getIdVivienda());

		stm.setString(++cont, cocheNuevo.getMatricula());
		resActualizar = stm.executeUpdate();
		stm.close();
		con.desconectar();
		
		return resActualizar;
	}

	public int eliminar(Coche cocheParaEliminar) throws SQLException {
		int resultadoEliminar = 0;
		
		String sql = "DELETE FROM coches WHERE MATRICULA = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, cocheParaEliminar.getMatricula());
		resultadoEliminar = stmt.executeUpdate();
		stmt.close();
		con.desconectar();
		
		return resultadoEliminar;
	}
	
	//***************************************
	
	public List<Integer> consultarIdViviendaLibre() throws SQLException {
		
		List<Integer> viviendasLibres = new ArrayList<Integer>();
		String sql = "SELECT ID_VIVIENDA FROM viviendas WHERE ID_VIVIENDA NOT IN (SELECT ID_VIVIENDA FROM coches)"; 
				
		con.conectar();
		connection = con.getJdbcConnection();
		Statement stm = connection.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()) {

			int idVivienda = rs.getInt("ID_VIVIENDA");

			viviendasLibres.add(idVivienda);
		}
		
		con.desconectar();
		return viviendasLibres;
		
	}
	
	
}
