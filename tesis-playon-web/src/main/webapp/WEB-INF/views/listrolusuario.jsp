<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Roles de usuario</title>
</head>
<body>
	<h1>Roles de usuario</h1>

	<c:url var="addUrl" value="/roles-usuario/add" />
	<table style="border: 1px solid; width: 500px; text-align: center">
		<thead style="background: #fcf">
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Descripcion</th>
				<th colspan="3"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rolesUsuario}" var="rolUsuario">
				<c:url var="editUrl"
					value="/roles-usuario/edit?nombre=${rolUsuario.nombre}" />
				<c:url var="deleteUrl"
					value="/roles-usuario/delete?nombre=${rolUsuario.nombre}" />
				<tr>
					<td><c:out value="${rolUsuario.id}" /></td>
					<td><c:out value="${rolUsuario.nombre}" /></td>
					<td><c:out value="${rolUsuario.descripcion}" /></td>
					<td><a href="${editUrl}">Editar</a></td>
					<td><a href="${deleteUrl}">Borrar</a></td>
					<td><a href="${addUrl}">Agregar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty rolesUsuario}">
	No hay roles de usuario actualmente en la lista. <a href="${addUrl}">Agregar</a> un rol de usuario.
</c:if>

</body>
</html>