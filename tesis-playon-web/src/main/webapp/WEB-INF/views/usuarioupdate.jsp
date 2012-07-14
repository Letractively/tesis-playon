<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Usuario</title>
</head>
<body>

	<h1>Editar Usuario</h1>

	<c:url var="saveUrl"
		value="/usuario/update?id=${usuarioAtributo.id}" />
	<form:form modelAttribute="usuarioAtributo" method="POST"
		action="${saveUrl}">
		<table>

			<tr>
				<td><form:label path="nombre">Nombre</form:label></td>
				<td><form:input path="nombre" /></td>
			</tr>

			<tr>
				<td><form:label path="apellido">Apellido</form:label></td>
				<td><form:input path="apellido" /></td>
			</tr>
						<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" /></td>
			</tr>
						<tr>
				<td><form:label path="nombreUser">Nombre de usuario</form:label></td>
				<td><form:input path="nombreUser" /></td>
			</tr>
						<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><form:label path="tipoDoc">Tipo de documento</form:label></td>
				<form:select path="tipoDocumento" items="${tiposDocumento}" itemLabel="nombre" itemValue="id">
				</form:select>
			</tr>
			<tr>
				<td><form:label path="nroDoc">Numero de documento</form:label></td>
				<td><form:input path="nroDOc" /></td>
			</tr>

		</table>

		<input type="submit" value="Actualizar" />
	</form:form>

</body>
</html>