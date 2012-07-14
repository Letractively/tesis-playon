<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Usuario</title>
</head>
<body>
	<h1>Usuario</h1>

	<c:url var="addUrl" value="/usuario/add" />
	<table style="border: 1px solid; width: 500px; text-align: center">
		<thead style="background: #fcf">
			<tr>
				<th>apellido</th>
				<th>nombre</th>
				<th>email</th>
				<th>password</th>
				<th>nombreUsuario</th>
				<th>tipoDocumento</th>
				<th>nroDoc</th>
				<th colspan="7"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="usuario">
				<c:url var="editUrl"
					value="/usuario/update?nombre=${usuario.nombreUser}" />
				<c:url var="deleteUrl"
					value="/usuario/delete?id=${usuario.id}" />
				<tr>
					<td><c:out value="${usuario.apellido}" /></td>
					<td><c:out value="${usuario.nombre}" /></td>
					<td><c:out value="${usuario.email}" /></td>
					<td><c:out value="${usuario.password}" /></td>
					<td><c:out value="${usuario.nombreUser}" /></td>
					<td><c:out value="${usuario.tipoDoc.getNombre()}" /></td>
					<td><c:out value="${usuario.nroDoc}" /></td>
					<td><a href="${editUrl}">Editar</a></td>
					<td><a href="${deleteUrl}">Borrar</a></td>
					<td><a href="${addUrl}">Agregar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty usuarios}">
	No hay usuarios actualmente en el sistema. <a href="${addUrl}">Agregar</a> un usuario.
</c:if>

</body>
</html>