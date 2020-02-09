package com.txr22.coche.model;

/**
 * Clase entidad para gestionar los coches
 * 
 * @author txema
 * @version 1.0
 */
public class Coche {

	private String matricula;
	private String marca;
	private String modelo;
	private int idVivienda;

	/**
	 * Constructor con todos los atributos
	 * 
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param idVivienda
	 * 
	 */
	public Coche(String matricula, String marca, String modelo, int idVivienda) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.idVivienda = idVivienda;

	}

	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the idVivienda
	 */
	public int getIdVivienda() {
		return idVivienda;
	}

	/**
	 * @param propietario the propietario to set
	 */
	public void setIdVivienda(int idVivienda) {
		this.idVivienda = idVivienda;
	}

}
