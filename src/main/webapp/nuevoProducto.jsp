<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Agregar Producto</h1>
        <h1>Administrador</h1>
    </div>
<br/>
<div class="container">
    <form action="ControlerProducto" method="post">   
        <table>
            <tr>
                <td>ID Producto</td>
                <td><input type="hidden" name="id"></td>
            </tr>
            <tr>
                <td>Descripción</td>
                <td><input type="text" name="descripcion"></td>
            </tr>
            <tr>
                <td>Costo</td>
                <td><input type="text" name="costo"></td>
            </tr>     
            <tr>
                <td>Precio</td>
                <td><input type="text" name="precio"></td>
            </tr>        
            <tr>
                <td>Cantidad</td>
                <td><input type="text" name="cantidad"></td>
            </tr>         
        </table>
        <br/>
        <input type="submit" name="agregar" value="Agregar"> 
    </form>
</div>
<jsp:include page="footer.jsp" />