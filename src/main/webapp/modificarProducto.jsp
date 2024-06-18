<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<producto> listaProductos = (List<producto>) request.getAttribute("listaProductos");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Modificar Producto</title>
    <link rel="stylesheet" href="css/sitio.css">
    <link rel="stylesheet" href="css/sitio.css">
    <style>

        

    </style>
</head>
<body>
<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Modificar Producto</h1>
        <h1>Administrador</h1>
    </div>
    
    <form action="ControlerProducto" method="post">   
        <table>
            <c:forEach var="producto" items="${listaProductos}">
                <tr>
                    <td>Id Producto</td>
                    <td>${producto.id}</td>
                    <input type="hidden" name="id" value="${producto.id}">
                </tr>
                <tr>
                    <td>Descripción</td>
                    <td><input type="text" name="descripcion" value="${producto.descripcion}"></td>
                </tr>
                <tr>
                    <td>Costo</td>
                    <td><input type="text" name="costo" value="${producto.costo}"></td>
                </tr>     
                <tr>
                    <td>Precio</td>
                    <td><input type="text" name="precio" value="${producto.precio}"></td>
                </tr>        
                <tr>
                    <td>Cantidad</td>
                    <td><input type="text" name="cantidad" value="${producto.cantidad}"></td>
                </tr>                 
            </c:forEach>
        </table>
        <input type="submit" name="modificar" value="Modificar"> 
    </form>
</body>
</html>
