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
    <%  
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  
        if (session.getAttribute("user")==null){
            response.sendRedirect("login.jsp");
        }
    %>
    <div class="barra">
        <h1>Menú Clientes</h1>
        <h1>Administrador</h1>
    </div>
    <jsp:include page="header.jsp" />

    <div class="contenido">
        <a href="ControlerCliente?Op=Listar" class="enlace">Listar Clientes</a>
        <a href="ControlerCliente?Op=Nuevo" class="enlace">Nuevo Cliente</a>
    </div>
</body>
</html>
