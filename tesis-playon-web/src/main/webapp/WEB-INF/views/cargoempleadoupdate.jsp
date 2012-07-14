<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actualizacion de un cargo de empleado</title>
</head>
<body>

	<h1>Editar cargo de empleado de playa de estacinamiento</h1>

	<c:url var="saveUrl"
		value="/cargos-empleado/update?id=${cargoEmpleadoAtributo.id}" />
	<form:form modelAttribute="cargoEmpleadoAtributo" method="POST"
		action="${saveUrl}">
		<table>

			<tr>
				<td><form:label path="nombre">Nombre</form:label></td>
				<td><form:input path="nombre" /></td>
			</tr>

			<tr>
				<td><form:label path="descripcion">Descripción</form:label></td>
				<td><form:input path="descripcion" /></td>
			</tr>

		</table>

		<input type="submit" value="Actualizar" />
	</form:form>

</body>
</html>