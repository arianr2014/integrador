
<%@ page import="java.util.List" %>
<%@ page import="Entidades.cliente" %>
<%-- 
    Document   : listar
    Created on : 17/09/2022, 10:54:58 AM
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
        <h1>Listado de Clientes</h1>
        <h1>Administrador</h1>
    </div>

<br/>
    <div class="container-fluid">

        <div class="row mb-12">
            <div class="col-md-6">
             </div>
            <div class="col-md-6 text-right">
                <a href="ControlerCliente?Op=Nuevo" class="btn btn-primary">Nuevo</a>
            </div>
        </div>
        <br/>
        <table>
            <tr>
                <th>Id Cliente</th>
                <th>Apellidos</th>
                <th>Nombres</th>
                <th>DNI</th>
                <th>Consultar</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>

            <%
                for (cliente obj: Lista) {
            %>
            <tr>
                <td><%= obj.getId() %></td>
                <td><%= obj.getApellidos() %></td>
                <td><%= obj.getNombres() %></td>
                <td><%= obj.getDNI() %></td>
                <td><a href="ControlerCliente?Op=Consultar&Id=<%= obj.getId() %>">Consultar</a></td>
                <td><a href="ControlerCliente?Op=Modificar&Id=<%= obj.getId() %>">Modificar</a></td>
                <td><a href="ControlerCliente?Op=Eliminar&Id=<%= obj.getId() %>">Eliminar</a></td>
            </tr>
            <%
                }
            %>


        </table>
    </div>
<jsp:include page="footer.jsp" />