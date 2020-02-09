<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/funciones.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion coches</title>
</head>
<body>
	<h1>Lista de coches registrados</h1>
	<a href="ctCoche?action=index" >Ir al menú</a><br><br>
	<a href="ctCoche?action=nuevo">Nuevo</a><br><br>
	<% int cont =0; %>
	<table class="table table-striped"">
	 <thead class="thead-dark">
		<tr>
			<th scope="col">PLAZAS</th>
			<th scope="col">MATRICULA</th>
			<th scope="col">MARCA</th>
			<th scope="col">MODELO</th>
			<th scope="col">ID_VIVIENDA</th>
			<th scope="col">EDITAR</th>
			<th scope="col">ELIMINAR</th>
		</tr>
		</thead>
		<c:forEach var="coche" items="${lista}">
			<tr>
				<td><% out.print(++cont); %></td>
				<td><c:out value="${coche.matricula}" /></td>
				<td><c:out value="${coche.marca}" /></td>
				<td><c:out value="${coche.modelo}" /></td>
				<td><c:out value="${coche.idVivienda}" /></td>

				<td><a
					href="ctCoche?action=showedit&matricula=<c:out value="${coche.matricula}"/>">Editar</a>
				</td>
<!-- 				<td><a -->
<%-- 					href="ctCoche?action=eliminar&matricula=<c:out value="${coche.matricula}"/>">Eliminar</a> --%>
<!-- 				</td> -->
				<td><a
					href="ctCoche?action=eliminar&matricula=<c:out value="${coche.matricula}"/>" onclick="confirmar()">Eliminar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	 <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
