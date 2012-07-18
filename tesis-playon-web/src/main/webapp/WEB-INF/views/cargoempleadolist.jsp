<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="template/head.jsp" />

<jsp:include page="template/menu.jsp" />

	<h1>Cargos de empleado</h1>

	<p><c:out value="${mensaje}" /></p>
	<c:url var="addUrl" value="/cargos-empleado/add" />
	<table style="border: 1px solid; width: 500px; text-align: center">
		<thead style="background: #fcf">
			<tr>
				<th>ID</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th colspan="3"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cargosEmpleado}" var="cargoEmpleado">
				<c:url var="editUrl"
					value="/cargos-empleado/update?nombre=${cargoEmpleado.nombre}" />
				<c:url var="deleteUrl"
					value="/cargos-empleado/delete?id=${cargoEmpleado.id}" />
				<tr>
					<td><c:out value="${cargoEmpleado.id}" /></td>
					<td><c:out value="${cargoEmpleado.nombre}" /></td>
					<td><c:out value="${cargoEmpleado.descripcion}" /></td>
					<td><a href="${editUrl}">Editar</a></td>
					<td><a href="${deleteUrl}">Borrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>	<a href="${addUrl}">Agregar</a>	</p>
	
	<c:if test="${empty cargosEmpleado}">
		No hay cargos de empleado actualmente en la lista. <a href="${addUrl}">Agregar</a> un cargo de empleado.
	</c:if>

<jsp:include page="template/foot.jsp" />