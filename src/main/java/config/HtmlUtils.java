package config;

import modeloDTO.Venta;

import java.util.List;

public class HtmlUtils {

    public static String generateHtmlContent(Venta ventaGeneral, List<Venta> ventas) {
        StringBuilder html = new StringBuilder();

        // Inicia el HTML
        html.append("<html><body>");
        html.append("<h2>Detalles de Venta</h2>");

        // Datos generales de la venta
        html.append("<p><strong>Número de Serie:</strong> ").append(ventaGeneral.getNumserie()).append("</p>");
        html.append("<p><strong>Fecha:</strong> ").append(ventaGeneral.getFecha()).append("</p>");
        html.append("<p><strong>Monto Total:</strong> ").append(ventaGeneral.getMonto()).append("</p>");
        html.append("<p><strong>Estado:</strong> ").append(ventaGeneral.getEstado()).append("</p>");
        html.append("<p><strong>Tipo de Comprobante:</strong> ").append(translateTipoComprobante(ventaGeneral.getTipocomprobante())).append("</p>");
        html.append("<p><strong>Método de Pago:</strong> ").append(translateMetodoPago(ventaGeneral.getMetodopago())).append("</p>");

        // Añade la tabla de ventas
        html.append("<h3>Detalles de Productos</h3>");
        html.append("<table border='1' style='border-collapse:collapse;'>");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>ID Producto</th>");
        html.append("<th>Item</th>");
        html.append("<th>Descripción</th>");
        html.append("<th>Precio</th>");
        html.append("<th>Cantidad</th>");
        html.append("<th>Subtotal</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");

        for (Venta venta : ventas) {
            html.append("<tr>");
            html.append("<td>").append(venta.getIdproducto()).append("</td>");
            html.append("<td>").append(venta.getItem()).append("</td>");
            html.append("<td>").append(venta.getDescripcionP()).append("</td>");
            html.append("<td>").append(venta.getPrecio()).append("</td>");
            html.append("<td>").append(venta.getCantidad()).append("</td>");
            html.append("<td>").append(venta.getSubtotal()).append("</td>");
            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</body></html>");

        return html.toString();
    }

    private static String translateTipoComprobante(String tipo) {
        switch (tipo) {
            case "1":
                return "FACTURA";
            case "2":
                return "BOLETA";
            default:
                return "Desconocido";
        }
    }

    private static String translateMetodoPago(String metodo) {
        switch (metodo) {
            case "Efectivo":
                return "Efectivo";
            case "TC":
                return "Tarjeta Crédito";
            case "TD":
                return "Tarjeta Débito";
            default:
                return "Desconocido";
        }
    }
}
