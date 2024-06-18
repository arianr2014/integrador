<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : listar
    Created on : 17/09/2022, 10:54:58 AM
    Author     : javie
--%>
<%@page import="java.util.List"%>
<%@page import="Entidades.pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<pedido> Lista = (List<pedido>) request.getAttribute("Lista");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de Pedidos</title>
    <link rel="stylesheet" href="css/sitio.css">

</head>
<body>
<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Listado de Pedidos</h1>
        <h1>Administrador</h1>
    </div>
<br/>
<div class="container-fluid">

    <div class="row mb-12">
        <div class="col-md-6">
        </div>
        <div class="col-md-6 text-right">
            <a href="ControlerPedido?Op=Nuevo" class="btn btn-primary">Nuevo</a>
        </div>
    </div>

    <form class="filter-form" action="ControlerPedido" method="get">
        <input type="hidden" name="Op" value="Filtrar">
        <label for="startDate">Fecha Inicio:</label>
        <input type="date" id="startDate" name="startDate" required>
        <label for="endDate">Fecha Fin:</label>
        <input type="date" id="endDate" name="endDate" required>
        <button type="submit">Filtrar</button>
    </form>
    
    <table>
        <tr>
            <th>Id Pedido</th>
            <th>Fecha</th>
            <th>Apellidos</th>
            <th>Nombres</th>
            <th>Total Pedido</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="campo" items="${Lista}">
            <tr>
               <td>${campo.getId_Pedido()}</td>
               <td>${campo.getFecha()}</td>
               <td>${campo.getApellidos()}</td>
               <td>${campo.getNombres()}</td>
               <td>${campo.getTotalVenta()}</td>
               <td>
                   <a href="ControlerPedido?Op=Consultar&Id=${campo.getId_Pedido()}">Consultar</a>
                   <a href="ControlerPedido?Op=Eliminar&Id=${campo.getId_Pedido()}">Eliminar</a>
               </td>
            </tr>
        </c:forEach>
    </table> 
</body>
</html>
