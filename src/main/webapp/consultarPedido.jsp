<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.detallePedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<detallePedido> Lista = (List<detallePedido>) request.getAttribute("Lista");
    String nombreCliente = (String) request.getAttribute("nombreCliente");
    String fechaVenta = (String) request.getAttribute("fechaVenta");

    String idPedido = (String) request.getAttribute("idPedido");
    java.util.Date fechaPedido = (java.util.Date) request.getAttribute("fechaPedido");
    String fechaPedidoStr = fechaPedido != null ? new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaPedido) : "Fecha no disponible";
%>

    <jsp:include page="header.jsp" />
        <div class="barra">
            <h1>Consulta de Ventas</h1>
            <h1>Administrador</h1>
        </div>

<br/>
    <div class="container-fluid">
        <div class="info">
            <div>
                <p><strong>ID de Pedido:</strong> ${idPedido}</p>
            </div>
            <div>
                <p><strong>Nombre del Cliente:</strong> ${nombreCliente}</p>
            </div>

            <div>
                <p><strong>Tipo Comprobante:</strong></p>
                <select class="form-control  w-auto" id="tipocomprobate" name="tipocomprobate" aria-readonly="true" >
                    <option value="">Seleccionar tipo comprobate</option>
                    <option value="1">FACTURA</option>
                    <option value="1">BOLETA</option>
                </select>
            </div>
            <div>
                <p><strong>Método de pago:</strong></p>
                <select class="form-control  w-auto" id="metodopago" name="metodopago" aria-readonly="true" >
                    <option value="">Seleccione método de pago</option>
                    <option value="Efectivo">Efectivo</option>
                    <option value="TC">Tarjeta Crédito</option>
                    <option value="TD">Tarjeta Débito</option>
                </select>
            </div>
        </div>

        <p><strong>Fecha del Pedido:</strong> ${fechaVenta}</p>

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


<script type="text/javascript">

    document.addEventListener("DOMContentLoaded", function()
    {

        // Obtener el valor de tipocomprobate desde la solicitud
        var tipocomprobate = "<%= request.getAttribute("tipocomprobate") %>";
        var metodopago = "<%= request.getAttribute("metodopago") %>";
        console.log("tipocomprobate " + tipocomprobate);
        console.log("metodopago " + metodopago);
        // Seleccionar la opción correspondiente en el select

        var selectElement = document.getElementById("tipocomprobate");
        for (var i = 0; i < selectElement.options.length; i++) {
            if (selectElement.options[i].value === tipocomprobate) {
                selectElement.selectedIndex = i;
                break;
            }
        }

        var selectElement2 = document.getElementById("metodopago");
        for (var i = 0; i < selectElement2.options.length; i++) {
            if (selectElement2.options[i].value === metodopago) {
                selectElement2.selectedIndex = i;
                break;
            }
        }

    });


</script>
<jsp:include page="footer.jsp" />