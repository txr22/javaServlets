package com.txr22.coche.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.swing.JOptionPane;

import com.txr22.coche.dao.CocheDao;
import com.txr22.coche.model.Coche;

/**
 * Servlet implementation class CtCoche
 */
@WebServlet("/ctCoche")
public class CtCoche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CocheDao cocheDao;

	public void init() {

		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			cocheDao = new CocheDao(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Constructor vacio por defecto
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public CtCoche() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
				showEditar(request, response);
				break;
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("metodo doPost redirigiendo........");
		doGet(request, response);
	}

	/**
	 * Index, para llevarnos a la página principal
	 */
	private void index(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Metodo para crear un nuevo registro coche
	 */
	private void nuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/register.jsp");
		//envio lista con las id_viviendas disponibles
		List<Integer> listaIdViviendaLibre = cocheDao.consultarIdViviendaLibre();
		request.setAttribute("listaVivienda", listaIdViviendaLibre);
		dispatcher.forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		Coche cocheNuevo = new Coche(request.getParameter("matricula"), request.getParameter("marca"),
				request.getParameter("modelo"), Integer.parseInt(request.getParameter("idVivienda")));
		
		//****************************
		int resultado = 0;
		//****************************
		resultado = cocheDao.insertar(cocheNuevo);
		String mns = null;
		
		if (resultado != 0) {
			mns = "Correcto!!";
		} 

		request.setAttribute("mensajeOk", mns);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/mostrar.jsp");
		List<Coche> listaCoches = cocheDao.listarCoches();

		// le paso a la pantalla la informacion a mostrar en una lista, con el apodo
		// "lista"
		request.setAttribute("lista", listaCoches);
		dispatcher.forward(request, response);
	}

	private void showEditar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// obtenemos el coche a editar cogiendo la id del coche
		Coche cocheParaEditar = cocheDao.buscarPorId(request.getParameter("matricula"));
		//ENVIAR 2 PARAMETROS
		List<Integer> listaIdViviendaLibre = cocheDao.consultarIdViviendaLibre();
		request.setAttribute("listaVivienda", listaIdViviendaLibre);
		//********************************
		
		request.setAttribute("cocheParaEditar", cocheParaEditar);
		// enviamos a la vista de edicion
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/editar.jsp");
		dispatcher.forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		Coche cocheActualizado = new Coche(request.getParameter("matricula"), request.getParameter("marca"),
				request.getParameter("modelo"), Integer.parseInt(request.getParameter("idVivienda")));
		cocheDao.actualizar(cocheActualizado);
		index(request, response);
	}


	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

		Coche cocheParaEliminar = cocheDao.buscarPorId(request.getParameter("matricula"));
		
		cocheDao.eliminar(cocheParaEliminar);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	
}
