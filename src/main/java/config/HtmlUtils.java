package config;

import modeloDTO.Venta;

import java.util.List;

public class HtmlUtils {

    public static String generateHtmlContent(Venta ventaGeneral, List<Venta> ventas) {
        StringBuilder html = new StringBuilder();

        // Inicia el HTML
        html.append("<html><head>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
        html.append(".container { width: 100%; max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        html.append("h2 { color: #007BFF; }");
        html.append("p { font-size: 16px; color: #333333; }");
        html.append("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        html.append("table, th, td { border: 1px solid #ddd; }");
        html.append("th, td { padding: 12px; text-align: left; }");
        html.append("th { background-color: #007BFF; color: #ffffff; }");
        html.append("tr:nth-child(odd) { background-color: #ffffff; }");
        html.append("tr:nth-child(even) { background-color: #e3f2fd; }"); // Azul pastel
        html.append("tr:hover { background-color: #ddd; }");
        html.append("</style>");
        html.append("</head><body>");

        html.append("<div class='container'>");


        // Mensaje inicial
        html.append("<p>Estimado Cliente, le enviamos su resumen de venta. Gracias.</p>");



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
        html.append("<table>");
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
        html.append("</div>"); // Cierra el contenedor
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
                return "EFECTIVO";
            case "TC":
                return "T. DE CREDITO";
            case "TD":
                return "T. DE DEBITO";
            default:
                return "Desconocido";
        }
    }
}
