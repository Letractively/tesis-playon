<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="template/head.jsp" />

<jsp:include page="template/menu.jsp" />

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

<jsp:include page="template/foot.jsp" />