<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

	<h1>Editar Empleado</h1>

	<c:url var="saveUrl"
		value="/empleado/update?id=${empleadoAtributo.id}" />
	<form:form modelAttribute="empleadoAtributo" method="POST"
		action="${saveUrl}">
		<table>		
			<tr>
				<td><form:label path="legajo">Legajo</form:label></td>
				<td><form:input path="legajo" /></td>
			</tr>
			
			<tr>
				<td><form:label path="cargoEmpleado">Cargo de empleado</form:label></td>
				<td><form:select path="cargoEmpleado" items="${listaCargosEmpleado}" itemLabel="nombre" itemValue="id">
				</form:select></td>
			</tr>
		</table>

		<input type="submit" value="Actualizar" />
	</form:form>

</body>
</html>