<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>

<jsp:include page="template/head_home.jsp" />

<jsp:include page="template/menu_home.jsp" />

	<h1>${message}</h1>
	
	<c:url var="rolesUsuarioUrl" value="/roles-usuario" />
	<c:url var="cargoEmpleadoUrl" value="/cargos-empleado" />
	<c:url var="empleadosUrl" value="/empleados" />
	<p>Ir a la pagina de <a href="${rolesUsuarioUrl}">roles de usuario.</a></p>	
	<p>Ir a la pagina de <a href="${cargoEmpleadoUrl}">cargos de empleado.</a></p>
	<p>Ir a la pagina de <a href="${empleadosUrl}">empleados.</a></p>

<jsp:include page="template/foot.jsp" />