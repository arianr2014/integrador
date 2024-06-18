<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.detallePedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<detallePedido> Lista = (List<detallePedido>) request.getAttribute("Lista");
    String nombreCliente = (String) request.getAttribute("nombreCliente");
    String idPedido = (String) request.getAttribute("idPedido");
    java.util.Date fechaPedido = (java.util.Date) request.getAttribute("fechaPedido");
    String fechaPedidoStr = fechaPedido != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaPedido) : "Fecha no disponible";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de Pedido</title>

        <link rel="stylesheet" href="css/sitio.css">



    </head>
    <body>
    <jsp:include page="header.jsp" />
        <div class="barra">
            <h1>Consulta de Pedido</h1>
            <h1>Administrador</h1>
        </div>

    <div class="container-fluid">
        <div class="info">
            <div>
                <p><strong>Nombre del Cliente:</strong> ${nombreCliente}</p>
            </div>
            <div>
                <p><strong>ID de Pedido:</strong> ${idPedido}</p>
            </div>
        </div>

        <p><strong>Fecha del Pedido:</strong> ${fechaPedidoStr}</p>

        <table>
            <thead>
                <tr>
                    <th>Código de Producto</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>IGV</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="campo" items="${Lista}">
                    <tr>
                        <td>${campo.getId_Prod()}</td>
                        <td>${campo.getDescripcion()}</td>
                        <td>
                            <fmt:formatNumber value="${campo.getPrecio()}" type="currency" currencySymbol="S/. " maxFractionDigits="2"/>
                        </td>
                        <td>${campo.getCantidad()}</td>
                        <td>
                            <fmt:formatNumber value="${campo.getPrecio() * 0.18}" type="currency" currencySymbol="S/. " maxFractionDigits="2"/>
                        </td>
                        <td>
                            <fmt:formatNumber value="${campo.getCantidad() * campo.getPrecio() + (campo.getPrecio() * 0.18)}" type="currency" currencySymbol="S/. " maxFractionDigits="2"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    </body>
</html>
