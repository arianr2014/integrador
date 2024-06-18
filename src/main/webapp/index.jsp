<%--
    Document   : index
    Created on : 17/09/2022, 08:38:47 AM
    Author     : javie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="css/sitio.css">
</head>
<body>
<%-- Redirección si el usuario no ha iniciado sesión --%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<jsp:include page="header.jsp" />
<div class="barra">
    <h1>Menú Principal</h1>
    <h1>Administrador</h1>
</div>


</body>
</html>
