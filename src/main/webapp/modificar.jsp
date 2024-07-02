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
        <h1>Modifica Clientes</h1>
        <h1>Administrador</h1>
    </div>

<div class="container">

    <form action="ControlerCliente" method="post">   
        <table>


            <%
                for (cliente obj: Lista) {
            %>


            <tr>
                <td>Id Cliente</td>
                <td><%= obj.getId() %></td>
                <input type="hidden" name="Id" value="<%= obj.getId() %>">
            </tr>
            <tr>
                <td>Apellidos</td>
                <td><input type="text" name="apellidos" value="<%= obj.getApellidos() %>"></td>
            </tr>
            <tr>
                <td>Nombres</td>
                <td><input type="text" name="nombres" value="<%= obj.getNombres() %>"></td>
            </tr>
            <tr>
                <td>DNI</td>
                <td><input type="text" name="DNI" value="<%= obj.getDNI() %>"></td>
            </tr>
            <tr>
                <td>Dirección</td>
                <td><input type="text" name="direccion" value="<%= obj.getDireccion() %>"></td>
            </tr>
            <tr>
                <td>Teléfono</td>
                <td><input type="text" name="telefono" value="<%= obj.getTelefono() %>"></td>
            </tr>
            <tr>
                <td>Móvil</td>
                <td><input type="text" name="movil" value="<%= obj.getMovil() %>}"></td>
            </tr>
            <%
                }
            %>

        </table>
        <input type="submit" name="modificar" value="Modificar"> 
    </form>
</div>
<jsp:include page="footer.jsp" />
