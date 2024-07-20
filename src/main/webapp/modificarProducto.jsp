<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="Entidades.producto" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%
    List<producto> listaProductos = (List<producto>) request.getAttribute("listaProductos");
%>

<jsp:include page="header.jsp" />
<div class="barra">
    <h1>Modificar Producto</h1>
    <h1>Administrador</h1>
</div>
<br/>
<form id="modificarProductoForm" action="ControlerProducto" method="post" onsubmit="return validarFormulario()">
    <table>
        <c:forEach var="producto" items="${listaProductos}">
            <tr>
                <td>Id Producto</td>
                <td>${producto.id}</td>
                <input type="hidden" name="id" value="${producto.id}">
            </tr>
            <tr>
                <td>Descripción</td>
                <td><input type="text" name="descripcion" id="descripcion" value="${producto.descripcion}"></td>
            </tr>
            <tr>
                <td>Costo</td>
                <td><input type="number" name="costo" id="costo" value="${producto.costo}"></td>
            </tr>
            <tr>
                <td>Precio</td>
                <td><input type="number" name="precio" id="precio" value="${producto.precio}"></td>
            </tr>
            <tr>
                <td>Cantidad</td>
                <td><input type="number" name="cantidad" id="cantidad" value="${producto.cantidad}"></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="modificar" value="Modificar">
</form>
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
