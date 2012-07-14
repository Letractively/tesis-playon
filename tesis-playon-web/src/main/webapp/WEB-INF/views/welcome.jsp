<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h1>${message}</h1>
	
	<c:url var="rolesUsuarioUrl" value="/roles-usuario" />
	<p>Ir a la pagina de <a href="${rolesUsuarioUrl}">roles de usuario.</a></p>	
	<p>Ir a la pagina de <a href="${cargoEmpleadoUrl}">cargos de empleado.</a></p>	
</body>
</html>