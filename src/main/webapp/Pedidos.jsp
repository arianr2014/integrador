<%-- 
    Document   : Pedidos
    Created on : 12/10/2022, 05:11:15 PM
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

        p {
            margin-bottom: 10px; 
            font-size: 16px;
        }
        
        a {
            margin-bottom: 50px;
            text-decoration: none; 
            color: #F8F4E1;
            display: inline-block; 
            width: 400px;
            padding: 15px 20px; 
            background-color: #543310; 
            border: 1px solid #543310;
            border-radius: 25px;
            transition: background-color 0.3s, color 0.3s;
        }
        
        a:hover {
            background-color: #74512D;
            border-color: #74512D; 
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Menú Pedido</h1>
        <h1>Administrador</h1>
    </div>
    
    <p><a href="ControlerPedido?Op=Listar">Listar Pedidos</a></p>
    <p><a href="ControlerPedido?Op=Nuevo">Nuevo Pedido</a></p>
</body>
</html>
