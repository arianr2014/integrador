<%-- 
    Document   : productos
    Created on : 26/05/2024, 09:30:00 AM
    Author     : javie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="css/sitio.css">
    <style>


        .contenido {
            padding: 20px;
            text-align: center;
        }

        .enlace {
            display: block;
            margin: 20px auto;
            text-decoration: none;
            color: #fff;
            background-color: #74512D;
            padding: 12px 0;
            border-radius: 20px;
            width: 500px;
            text-align: center;
            transition: background-color 0.3s;
            font-size: 16px;
        }

        .enlace:hover {
            background-color: #AF8F6F;
        }
    </style>
</head>
<body>
    <%  
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Menú Productos</h1>
        <h1>Administrador</h1>
    </div>
    <div class="contenido">
        <a href="ControlerProducto?op=Listar" class="enlace">Listar Productos</a>
        <a href="ControlerProducto?op=Nuevo" class="enlace">Nuevo Producto</a>
    </div>
</body>
</html>
