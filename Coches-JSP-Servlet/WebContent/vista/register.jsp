<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/funciones.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar coche</title>
</head>
<body>

	<h1>Registrar Coche</h1>
	<a href="ctCoche?action=index">Ir al menú</a><br>

		<form action="ctCoche?action=register" method="post">
			<div class="form-group row">
				<div class="col-sm-3">
						<label for="matricula">Matricula:</label><br> <input
							id="matricula" placeholder="Matricula" name="matricula"
							type="text" class="form-control" required>
				</div>
				<div class="col-sm-3">
						<label for="marca">Marca:</label><br> <input id="marca"
							placeholder="Marca" name="marca" type="text" class="form-control" required>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-3">
						<label for="modelo">Modelo:</label><br> <input id="modelo"
							placeholder="Modelo" name="modelo" type="text"
							class="form-control" required>
				</div>
				<div class="col-sm-2">
					<label for="idVivienda">Id vienda:</label><br> 
					    <select class="form-control" id="idVivienda" required type="number" name="idVivienda">
						      <option></option>
							  <c:forEach var="listaVi" items="${listaVivienda}" >
							    <option value="${listaVi}">
							        ${listaVi}
							    </option>
							  </c:forEach>
					</select>
				</div>
			</div>

			<input type="submit" value="Agregar" name="agregar" />
		</form>
	


	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>