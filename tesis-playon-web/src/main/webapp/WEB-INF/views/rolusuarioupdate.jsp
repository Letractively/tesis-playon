<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Rol de Usuario</title>
</head>
<body>

	<h1>Editar Rol de Usuario</h1>

	<c:url var="saveUrl"
		value="/roles-usuario/update?id=${rolUsuarioAtributo.id}" />
	<form:form modelAttribute="rolUsuarioAtributo" method="POST"
		action="${saveUrl}">
		<table>

			<tr>
				<td><form:label path="nombre">Nombre</form:label></td>
				<td><form:input path="nombre" /></td>
			</tr>

			<tr>
				<td><form:label path="descripcion">Descripcion</form:label></td>
				<td><form:input path="descripcion" /></td>
			</tr>

		</table>

		<input type="submit" value="Actualizar" />
	</form:form>

</body>
</html>