<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<jsp:include page="header.jsp" />
<div class="barra">
    <h1>Agregar Producto</h1>
    <h1>Administrador</h1>
</div>
<br />
<div class="container">
    <form id="productoForm" action="ControlerProducto" method="post" onsubmit="return validarFormulario()">
        <table>
            <tr>
                <td>ID Producto</td>
                <td><input type="hidden" name="id"></td>
            </tr>
            <tr>
                <td>Descripción</td>
                <td><input type="text" name="descripcion" id="descripcion"></td>
            </tr>
            <tr>
                <td>Costo</td>
                <td><input type="number" name="costo" id="costo"></td>
            </tr>
            <tr>
                <td>Precio</td>
                <td><input type="number" name="precio" id="precio"></td>
            </tr>
            <tr>
                <td>Cantidad</td>
                <td><input type="number" name="cantidad" id="cantidad"></td>
            </tr>
        </table>
        <br />
        <input type="submit" name="agregar" value="Agregar">
    </form>
</div>
<jsp:include page="footer.jsp" />

<script>
    function validarFormulario() {
        var descripcion = document.getElementById("descripcion").value;
        var costo = document.getElementById("costo").value;
        var precio = document.getElementById("precio").value;
        var cantidad = document.getElementById("cantidad").value;

        if (descripcion === "" || costo === "" || precio === "" || cantidad === "") {
            alert("Por favor, complete todos los campos.");
            return false;
        }
        return true;
    }
</script>
