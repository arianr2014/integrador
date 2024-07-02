<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 19/11/2021, 07:15:10 PM
    Author     : javie
--%>

<%@page import="java.util.List"%>
<%@page import="Entidades.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<cliente> Lista= (List<cliente>) request.getAttribute("Lista");
%>

<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Consulta de Clientes</h1>
        <h1>Administrador</h1>
    </div>

<div class="container">

    <table>

        <%
            for (cliente obj: Lista) {
        %>
        <tr>
            <td>Id Cliente</td>
            <td><%= obj.getId() %></td>
        </tr>
        <tr>
            <td>Apellidos</td>
            <td><%= obj.getApellidos() %></td>
        </tr>
        <tr>
            <td>Nombres</td>
            <td><%= obj.getNombres() %></td>
        </tr>
        <tr>
            <td>DNI</td>
            <td><%= obj.getDNI() %></td>
        </tr>
        <tr>
            <td>Dirección</td>
            <td><%= obj.getDireccion() %></td>
        </tr>
        <tr>
            <td>Teléfono</td>
            <td><%= obj.getTelefono() %></td>
        </tr>
        <tr>
            <td>Móvil</td>
            <td><%= obj.getMovil() %></td>
        </tr>
        <%
            }
        %>

    </table>
</div>
<jsp:include page="footer.jsp" />
