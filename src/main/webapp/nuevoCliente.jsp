<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<jsp:include page="header.jsp" />
    <div class="barra">
        <h1>Agregar Cliente</h1>
        <h1>Administrador</h1>
    </div>
<br/>
<div class="container">
     <form action="ControlerCliente" method="post">   
            <table border="1">
                    <tr>
                        <td>Id Cliente</td>
                        <td><input type="hidden" name="Id"></td>
                    </tr>
                    <tr>
                        <td>Apellidos</td>
                        <td><input type="text" name="apellidos"></td>
                    </tr>
                    <tr>
                        <td>Nombres</td>
                        <td><input type="text" name="nombres"></td>
                    </tr>     
                    <tr>
                        <td>DNI</td>
                        <td><input type="text" name="DNI"></td>
                    </tr>        
                    <tr>
                        <td>Dirección</td>
                        <td><input type="text" name="direccion"></td>
                    </tr>  
                    <tr>
                        <td>Teléfono</td>
                        <td><input type="text" name="telefono"></td>
                    </tr>                 
                    <tr>
                        <td>Móvil</td>
                        <td><input type="text" name="movil"></td>
                    </tr>
                <tr>
                    <td>Mail</td>
                    <td><input type="text" name="email"></td>
                </tr>
            </table>
            <br/>
            <input type="submit" name="modificar" value="Grabar">
        </form>
</div>
<jsp:include page="footer.jsp" />


