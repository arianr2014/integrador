

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<producto> listaProductos= (List<producto>) request.getAttribute("listaProductos");
%>

    <style>

        
        table {
            margin: 20px auto; 
            border-collapse: collapse; 
            width: 50%; 
        }
        th, td {
            padding: 8px;
            border: 1px solid #ddd; 
            text-align: left; 
        }
        tr:nth-child(even) {
            background-color: #f2f2f2; 
        }
        tr:hover {
            background-color: #ddd; 
        }
    </style>


<jsp:include page="header.jsp" />


    <div class="barra">
        <h1>Consulta de Productos</h1>
        <h1>Administrador</h1>
    </div>

    <div class="container">
    <table>
        <c:forEach var="producto" items="${listaProductos}">
            <tr>
                <td>ID Producto</td>
                <td>${producto.id}</td>
            </tr>
            <tr>
                <td>Descripción</td>
                <td>${producto.descripcion}</td>
            </tr>
            <tr>
                <td>Costo</td>
                <td>${producto.costo}</td>
            </tr>     
            <tr>
                <td>Precio</td>
                <td>${producto.precio}</td>
            </tr>        
            <tr>
                <td>Cantidad</td>
                <td>${producto.cantidad}</td>
            </tr>         
        </c:forEach>
    </table>
    </div>
    <jsp:include page="footer.jsp" />
