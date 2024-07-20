<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : index
    Created on : 19/11/2021, 07:15:10 PM
    Author     : javie
--%>

<%@ page import="java.util.List" %>
<%@ page import="Entidades.cliente" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<cliente> Lista = (List<cliente>) request.getAttribute("Lista");
%>

<jsp:include page="header.jsp" />
<div class="barra">
    <h1>Modificar Clientes</h1>
    <h1>Administrador</h1>
</div>
<br/>

<div class="container">
    <form id="modificarClienteForm" action="ControlerCliente" method="post" onsubmit="return validarFormulario()">
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
                <td><input type="text" name="apellidos" id="apellidos" value="<%= obj.getApellidos() %>"></td>
            </tr>
            <tr>
                <td>Nombres</td>
                <td><input type="text" name="nombres" id="nombres" value="<%= obj.getNombres() %>"></td>
            </tr>
            <tr>
                <td>DNI</td>
                <td><input type="text" name="DNI" id="DNI" value="<%= obj.getDNI() %>"></td>
            </tr>
            <tr>
                <td>Dirección</td>
                <td><input type="text" name="direccion" id="direccion" value="<%= obj.getDireccion() %>"></td>
            </tr>
            <tr>
                <td>Teléfono</td>
                <td><input type="number" name="telefono" id="telefono" value="<%= obj.getTelefono() %>"></td>
            </tr>
            <tr>
                <td>Móvil</td>
                <td><input type="number" name="movil" id="movil" value="<%= obj.getMovil() %>"></td>
            </tr>
            <tr>
                <td>Mail</td>
                <td><input type="text" name="email" id="email" value="<%= obj.getEmail() %>"></td>
            </tr>
            <%
                }
            %>
        </table>
        <input type="submit" name="modificar" value="Modificar">
    </form>
</div>
<jsp:include page="footer.jsp" />

<script>
    function validarFormulario() {
        var apellidos = document.getElementById("apellidos").value;
        var nombres = document.getElementById("nombres").value;
        var DNI = document.getElementById("DNI").value;
        var direccion = document.getElementById("direccion").value;
        var telefono = document.getElementById("telefono").value;
        var movil = document.getElementById("movil").value;
        var email = document.getElementById("email").value;

        if (apellidos === "" || nombres === "" || DNI === "" || direccion === "" || telefono === "" || movil === "" || email === "") {
            alert("Por favor, complete todos los campos.");
            return false;
        }
        return true;
    }
</script>
