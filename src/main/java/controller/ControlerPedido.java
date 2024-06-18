package Controler;

import Entidades.detallePedido;
import Entidades.pedido;
import Entidades.producto;
import conexion.conexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControlerPedido", urlPatterns = {"/ControlerPedido"})
public class ControlerPedido extends HttpServlet {

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
                request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
                break;
            case "Filtrar":
                filtrarPedidos(request, response, conn, Lista);
                break;
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
            System.out.println("Error de SQL..." + ex.getMessage());
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
            System.out.println("Error de SQL..." + ex.getMessage());
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
            System.out.println("Error de SQL..." + ex.getMessage());
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
        String accionbuscarProducto = request.getParameter("accionbuscarProducto");
        if (accion!=null && accion.equals("BuscarCliente")) {
            buscarCliente(request, response);

        }
        if (accionbuscarProducto.equals("BuscarProducto")) {
            buscarProducto(request, response);

        }

/*
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
        }*/
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String codigoCliente = request.getParameter("codigocliente");
    conexionBD conBD = new conexionBD();
    Connection conn = conBD.Connected();
    try {
        String sql = "SELECT Nombres, Apellidos FROM t_cliente WHERE Id_Cliente = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, codigoCliente);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombres = rs.getString("Nombres");
            String apellidos = rs.getString("Apellidos");
            request.setAttribute("Nombres", nombres + " " + apellidos);
        } else {
            request.setAttribute("Nombres", "Cliente no encontrado");
        }
        request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
    } catch (SQLException ex) {
        System.out.println("Error de SQL..." + ex.getMessage());
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
        String sql = "SELECT Descripcion, Precio, Stock FROM t_producto WHERE Id_Prod = ?";
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
        } else {
            request.setAttribute("nombreProducto", "Producto no encontrado");
            request.setAttribute("precio", "");
            request.setAttribute("stock", "");
        }
        request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
    } catch (SQLException ex) {
        System.out.println("Error de SQL..." + ex.getMessage());
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
