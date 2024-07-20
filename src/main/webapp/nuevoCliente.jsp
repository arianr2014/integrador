<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="header.jsp" />
<div class="barra">
    <h1>Agregar Cliente</h1>
    <h1>Administrador</h1>
</div>
<br />
<div class="container">
    <form id="clienteForm" action="ControlerCliente" method="post" onsubmit="return validarFormulario()">
        <table border="1">
            <tr>
                <td>Id Cliente</td>
                <td><input type="hidden" name="Id"></td>
            </tr>
            <tr>
                <td>Apellidos</td>
                <td><input type="text" name="apellidos" id="apellidos"></td>
            </tr>
            <tr>
                <td>Nombres</td>
                <td><input type="text" name="nombres" id="nombres"></td>
            </tr>
            <tr>
                <td>DNI</td>
                <td><input type="text" name="DNI" id="DNI"></td>
            </tr>
            <tr>
                <td>Dirección</td>
                <td><input type="text" name="direccion" id="direccion"></td>
            </tr>
            <tr>
                <td>Teléfono</td>
                <td><input type="text" name="telefono" id="telefono"></td>
            </tr>
            <tr>
                <td>Móvil</td>
                <td><input type="text" name="movil" id="movil"></td>
            </tr>
            <tr>
                <td>Mail</td>
                <td><input type="text" name="email" id="email"></td>
            </tr>
        </table>
        <br />
        <input type="submit" name="modificar" value="Grabar">
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
