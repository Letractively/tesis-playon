<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Empleado</h1>

	<c:url var="addUrl" value="/empleado/add" />
	<table style="border: 1px solid; width: 500px; text-align: center">
		<thead style="background: #fcf">
			<tr>
				<th>Legajo</th>
				<th>Apellido</th>
				<th>Nombre</th>
				<th>Nombre usuario</th>
				<th>Cargo</th>
				<th colspan="5"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${empleados}" var="empleado">
				<c:url var="editUrl"
					value="/empleado/update?legajo=${empleado.legajo}" />
				<c:url var="deleteUrl"
					value="/empleado/delete?id=${empleado.id}" />
				<tr>
					<td><c:out value="${empleado.legajo}" /></td>
					<td><c:out value="${empleado.usuario.getApellido()}" /></td>
					<td><c:out value="${empleado.usuario.getNombre()}" /></td>
					<td><c:out value="${empleado.usuario.getNombreUser()}" /></td>
					<td><c:out value="${empleado.cargoEmpleado.getNombre()}" /></td>
					<td><a href="${editUrl}">Editar</a></td>
					<td><a href="${deleteUrl}">Borrar</a></td>
					<td><a href="${addUrl}">Agregar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty empleados}">
	No hay empleados actualmente en el sistema. <a href="${addUrl}">Agregar</a> un empleado.
</c:if>
</body>
</html>