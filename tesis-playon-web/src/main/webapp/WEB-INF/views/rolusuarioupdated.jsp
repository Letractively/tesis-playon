<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rol de Usuario borrado</title>
</head>
<body>

<h1>Roles de Usuario</h1>

<p>Ha editado el rol de usuario con el ID ${id}: <%= new java.util.Date() %></p>

<c:url var="mainUrl" value="/welcome" />
<p>Regresar a la <a href="${mainUrl}">página principal</a></p>

</body>
</html>