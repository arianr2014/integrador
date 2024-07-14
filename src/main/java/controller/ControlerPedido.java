package controller;

import Entidades.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import conexion.conexionBD;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;
import static java.lang.System.out;

@WebServlet(name = "ControlerPedido", urlPatterns = {"/ControlerPedido"})
public class ControlerPedido extends HttpServlet {

    cliente c = new cliente();
    producto p = new producto();

    Venta v = new Venta();
    ArrayList<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;

    String numeroserie = "";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String Op = request.getParameter("Op");
        ArrayList<pedido> Lista = new ArrayList<>();
        ArrayList<detallePedido> ListaDet = new ArrayList<>();
        conexionBD conBD = new conexionBD();
        Connection conn = conBD.Connected();
        PreparedStatement ps;
        ResultSet rs;

        switch (Op) {
            case "Listar":
                listarPedidos(request, response, conn, Lista);
                break;
            case "Consultar":
                consultarPedido(request, response, conn, ListaDet);
                break;
            case "Eliminar":
                // Implementación para eliminar
                break;
            case "Nuevo":
                //request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                break;
            case "Filtrar":
                filtrarPedidos(request, response, conn, Lista);
                break;
            //IMPLEMENTACION DE PDF
            case "exportarPdf":
                exportarPDF(request, response);
                break;
        }
    }

    //IMPLEMENTACION DE PDF
    private void exportarPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        ServletOutputStream out = response.getOutputStream();
        conexionBD conBD = new conexionBD();
        Connection conn = conBD.Connected();
        try {
            InputStream logoEmpresa = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("reportesJASPER/logoAlexanderStore.png"),
                    logoFooter = this.getServletConfig()
                            .getServletContext()
                            .getResourceAsStream("reportesJASPER/check.png"),
                    reporteEmpleado = this.getServletConfig()
                            .getServletContext()
                            .getResourceAsStream("reportesJASPER/xd.jasper");
            if (logoEmpresa == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "El logo de la empresa no se encontró.");
                return;
            }

            if (logoFooter == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "El logo del pie de página no se encontró.");
                return;
            }

            if (reporteEmpleado == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "El reporte de empleado no se encontró.");
                return;
            }
            if (logoEmpresa != null && logoFooter != null && reporteEmpleado != null) {

                List<imprimirPedido> reportesEmpleados = new ArrayList<>();
                reportesEmpleados= consultarPedido2(request,response,conn);



                Gson gson = new Gson();
                for (imprimirPedido pedido : reportesEmpleados) {
                    System.out.println(gson.toJson(pedido));
                }


                JasperReport report = (JasperReport) JRLoader.loadObject(reporteEmpleado);


                JRBeanArrayDataSource ds = new JRBeanArrayDataSource(reportesEmpleados.toArray());

                System.out.println(ds);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("ds", ds);
                parameters.put("logoEmpresa", logoEmpresa);
                parameters.put("imagenAlternativa", logoFooter);
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=ReportesEmpleados.pdf");
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                out.flush();
                out.close();
            } else {
                response.setContentType("text/plain");
                out.println("no se pudo generar el reporte");
                out.println("esto puede debrse a que la lista de datos no fue recibida o el archivo plantilla del reporte no se ha encontrado");
                out.println("contacte a soporte");
            }
        } catch (Exception e) {
            response.setContentType("text/plain");
            out.print("ocurrió un error al intentar generar el reporte:" + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<imprimirPedido> consultarPedido2(HttpServletRequest request, HttpServletResponse response, Connection conn)
            throws ServletException, IOException {
        try {
            ArrayList<imprimirPedido> ListaDet = new ArrayList<>();
            String idPedido = request.getParameter("Id");
            // Consulta para obtener los detalles del pedido
            String sqlDetalles = "SELECT Id_Pedido, Descripcion, A.Cantidad, A.Precio, TotalDeta FROM t_detalle_pedido A " +
                    "INNER JOIN t_producto B ON A.Id_Prod = B.Id_Prod " +
                    "WHERE Id_Pedido = ?";
            PreparedStatement psDetalles = conn.prepareStatement(sqlDetalles);
            psDetalles.setString(1, idPedido);
            ResultSet rsDetalles = psDetalles.executeQuery();
            ListaDet.add(new imprimirPedido());

            while (rsDetalles.next()) {

                imprimirPedido det = new imprimirPedido();
                det.setIdPedido(rsDetalles.getString(1));
                det.setDescripcion(rsDetalles.getString(2));
                det.setCantidad(rsDetalles.getDouble(3));
                det.setPrecio(rsDetalles.getDouble(4));
                det.setTotal(rsDetalles.getDouble(5));
                det.setIgv((rsDetalles.getDouble(4)*18)/100);
                ListaDet.add(det);
            }

            return ListaDet;

        } catch (SQLException ex) {
            out.println("Error de SQL..." + ex.getMessage());
            return null;
        }
    }




    private void listarPedidos(HttpServletRequest request, HttpServletResponse response, Connection conn, ArrayList<pedido> Lista)
            throws ServletException, IOException {
        try {
            String sql = "SELECT Id_Pedido, A.Id_Cliente, B.Apellidos, B.Nombres, A.Fecha, " +
                         "A.SubTotal, A.TotalVenta FROM t_pedido A " +
                         "INNER JOIN t_cliente B ON A.Id_Cliente = B.Id_Cliente";


            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pedido Pedido = new pedido();
                Pedido.setId_Pedido(rs.getString(1));
                Pedido.setId_Cliente(rs.getString(2));
                Pedido.setApellidos(rs.getString(3));
                Pedido.setNombres(rs.getString(4));
                Pedido.setFecha(rs.getDate(5));
                Pedido.setSubTotal(rs.getDouble(6));
                Pedido.setTotalVenta(rs.getDouble(7));
                Lista.add(Pedido);
            }
            request.setAttribute("Lista", Lista);
            request.getRequestDispatcher("listarPedido.jsp").forward(request, response);
        } catch (SQLException ex) {
            out.println("Error de SQL..." + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private void consultarPedido(HttpServletRequest request, HttpServletResponse response, Connection conn, ArrayList<detallePedido> ListaDet)
            throws ServletException, IOException {
        try {
            String idPedido = request.getParameter("Id");
            // Consulta para obtener los detalles del pedido
            String sqlDetalles = "SELECT Id_Pedido, A.Id_Prod, Descripcion, A.Cantidad, A.Precio, TotalDeta FROM t_detalle_pedido A " +
                                "INNER JOIN t_producto B ON A.Id_Prod = B.Id_Prod " +
                                "WHERE Id_Pedido = ?";
            PreparedStatement psDetalles = conn.prepareStatement(sqlDetalles);
            psDetalles.setString(1, idPedido);
            ResultSet rsDetalles = psDetalles.executeQuery();

            while (rsDetalles.next()) {
                detallePedido det = new detallePedido();
                det.setId_Pedido(rsDetalles.getString(1));
                det.setId_Prod(rsDetalles.getString(2));
                det.setDescripcion(rsDetalles.getString(3));
                det.setCantidad(rsDetalles.getDouble(4));
                det.setPrecio(rsDetalles.getDouble(5));
                det.setTotalDeta(rsDetalles.getDouble(6));
                ListaDet.add(det);
            }

            // Obtener el nombre del cliente basado en el ID de pedido
            String sqlCliente = "SELECT B.Apellidos, B.Nombres FROM t_pedido A INNER JOIN t_cliente B ON A.Id_Cliente = B.Id_Cliente WHERE A.Id_Pedido = ?";
            PreparedStatement psCliente = conn.prepareStatement(sqlCliente);
            psCliente.setString(1, idPedido);
            ResultSet rsCliente = psCliente.executeQuery();
            String nombreCliente = "";
            if (rsCliente.next()) {
                nombreCliente = rsCliente.getString("Nombres") + " " + rsCliente.getString("Apellidos");
            }

            request.setAttribute("Lista", ListaDet);
            request.setAttribute("nombreCliente", nombreCliente);
            request.setAttribute("idPedido", idPedido);
            request.getRequestDispatcher("consultarPedido.jsp").forward(request, response);

        } catch (SQLException ex) {
            out.println("Error de SQL..." + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void filtrarPedidos(HttpServletRequest request, HttpServletResponse response, Connection conn, ArrayList<pedido> Lista)
            throws ServletException, IOException {
        try {
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String sql = "SELECT Id_Pedido, A.Id_Cliente, B.Apellidos, B.Nombres, A.Fecha, " +
                         "A.SubTotal, A.TotalVenta FROM t_pedido A " +
                         "INNER JOIN t_cliente B ON A.Id_Cliente = B.Id_Cliente " +
                         "WHERE A.Fecha BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pedido Pedido = new pedido();
                Pedido.setId_Pedido(rs.getString(1));
                Pedido.setId_Cliente(rs.getString(2));
                Pedido.setApellidos(rs.getString(3));
                Pedido.setNombres(rs.getString(4));
                Pedido.setFecha(rs.getDate(5));
                Pedido.setSubTotal(rs.getDouble(6));
                Pedido.setTotalVenta(rs.getDouble(7));
                Lista.add(Pedido);
            }
            request.setAttribute("Lista", Lista);
            request.getRequestDispatcher("listarPedido.jsp").forward(request, response);
        } catch (SQLException ex) {
            out.println("Error de SQL..." + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        /*
        String accionbuscarProducto = request.getParameter("accionbuscarProducto");
        if (accion!=null && accion.equals("BuscarCliente")) {
            buscarCliente(request, response);

        }
        if (accionbuscarProducto.equals("BuscarProducto")) {
            buscarProducto(request, response);

        }*/


        switch (accion) {
            case "BuscarCliente":
                buscarCliente(request, response);
                break;
            case "BuscarProducto":
                buscarProducto(request, response);
                break;
            case "Agregar":
                agregarProducto(request, response);
                break;
            case "Generar venta":
                generarVenta(request, response);
                break;
            case "Cancelar":
                cancelar(request, response);
                break;
        }
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String _dni = request.getParameter("DNI");
    conexionBD conBD = new conexionBD();
    Connection conn = conBD.Connected();
    try {
        String sql = "SELECT Id_Cliente,Nombres, Apellidos FROM t_cliente WHERE DNI = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, _dni);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombres = rs.getString("Nombres");
            String apellidos = rs.getString("Apellidos");
            String Id_Cliente = rs.getString("Id_Cliente");
            c= new cliente();
            c.setId(Id_Cliente);
            c.setNombres(nombres);
            c.setApellidos(apellidos);
            c.setDNI(_dni);
            request.setAttribute("c", c);
            request.setAttribute("Nombres", nombres + " " + apellidos);
        } else {
            request.setAttribute("Nombres", "Cliente no encontrado");
        }
        request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
    } catch (SQLException ex) {
        out.println("Error de SQL..." + ex.getMessage());
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String codigoProducto = request.getParameter("codigoProducto");
    conexionBD conBD = new conexionBD();
    Connection conn = conBD.Connected();
    try {
        String sql = "SELECT Id_Prod, Descripcion, Precio, Stock FROM t_producto WHERE Id_Prod = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, codigoProducto);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String descripcion = rs.getString("Descripcion");
            double precio = rs.getDouble("Precio");
            int stock = rs.getInt("Stock");
            request.setAttribute("nombreProducto", descripcion);
            request.setAttribute("precio", precio);
            request.setAttribute("stock", stock);
            producto pr = new producto();

            pr.setId(rs.getString("Id_Prod"));
            pr.setDescripcion(rs.getString("Descripcion"));
            pr.setPrecio(rs.getFloat("precio"));
            pr.setCosto(rs.getInt("stock"));
            p=pr;
            request.setAttribute("producto", p);
            request.setAttribute("c", c);


        } else {
            request.setAttribute("nombreProducto", "Producto no encontrado");
            request.setAttribute("precio", "");
            request.setAttribute("stock", "");
        }
        request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
    } catch (SQLException ex) {
        out.println("Error de SQL..." + ex.getMessage());
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    private void agregarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica para agregar producto al pedido
        // Ejemplo de cómo puedes manejar los datos aquí

        totalPagar = 0.0;
        item = item + 1;
        cod =  parseInt( p.getId());
        descripcion = request.getParameter("nomproducto");
        precio = Double.parseDouble(request.getParameter("precio"));
        cant = parseInt(request.getParameter("cant"));
        subtotal = precio * cant;
    }

    private void generarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica para generar venta y almacenar en la base de datos
        // Ejemplo de cómo puedes manejar los datos aquí
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica para cancelar el pedido
        // Ejemplo de cómo puedes manejar los datos aquí
    }
}
