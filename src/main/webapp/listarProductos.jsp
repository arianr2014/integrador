<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : listarProductos
    Created on : 17/09/2022, 10:54:58 AM
    Author     : javie
--%>
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
    <title>Listado de Productos</title>
    <link rel="stylesheet" href="css/sitio.css">
    <style>



    </style>
</head>
<body>
<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Listado de Productos</h1>
        <h1>Administrador</h1>
    </div>

<br/>
<div class="container-fluid">

    <div class="row mb-12">
        <div class="col-md-6">
        </div>
        <div class="col-md-6 text-right">
            <a href="ControlerProducto?op=Nuevo" class="btn btn-primary">Nuevo</a>
        </div>
    </div>
    <br/>

    <div class="contenido">
        <table>
            <tr>
                <th>ID Producto</th>
                <th>Descripción</th>
                <th>Costo</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Consultar</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            <c:forEach var="producto" items="${listaProductos}">
                <tr>
                    <td>${producto.id}</td>
                    <td>${producto.descripcion}</td>
                    <td>${producto.costo}</td>
                    <td>${producto.precio}</td>
                    <td>${producto.cantidad}</td>
                    <td><a href="ControlerProducto?op=Consultar&id=${producto.id}">Consultar</a></td>
                    <td><a href="ControlerProducto?op=Modificar&id=${producto.id}">Modificar</a></td>
                    <td><a href="ControlerProducto?op=Eliminar&id=${producto.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
