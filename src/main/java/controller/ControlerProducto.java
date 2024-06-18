package Controler;

import Entidades.producto;
import conexion.conexionBD;
import Entidades.usuarios;
import Entidades.cliente;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControlerProducto", urlPatterns = {"/ControlerProducto"})
public class ControlerProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        String id = request.getParameter("id");
        ArrayList<producto> listaProductos = new ArrayList<producto>();
        conexionBD conBD = new conexionBD();
        Connection conn = conBD.Connected();
        PreparedStatement ps;
        ResultSet rs;

        try {
            switch (op) {
                case "Listar":
                    String sqlListar = "SELECT * FROM t_producto";
                    ps = conn.prepareStatement(sqlListar);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        producto prod = new producto();
                        prod.setId(rs.getString("Id_Prod"));
                        prod.setDescripcion(rs.getString("Descripcion"));
                        prod.setCosto(rs.getFloat("costo"));
                        prod.setPrecio(rs.getFloat("precio"));
                        prod.setCantidad(rs.getInt("cantidad"));
                        listaProductos.add(prod);
                    }
                    request.setAttribute("listaProductos", listaProductos);
                    request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
                    break;
                case "Consultar":
                    String sqlConsultar = "SELECT * FROM t_producto WHERE Id_Prod=?";
                    ps = conn.prepareStatement(sqlConsultar);
                    ps.setString(1, id);
                    rs = ps.executeQuery();
                    producto prodConsultar = new producto();
                    while (rs.next()) {
                        prodConsultar.setId(rs.getString("Id_Prod"));
                        prodConsultar.setDescripcion(rs.getString("Descripcion"));
                        prodConsultar.setCosto(rs.getFloat("Costo"));
                        prodConsultar.setPrecio(rs.getFloat("Precio"));
                        prodConsultar.setCantidad(rs.getInt("Cantidad"));
                        listaProductos.add(prodConsultar);
                    }
                    request.setAttribute("listaProductos", listaProductos);
                    request.getRequestDispatcher("consultarProducto.jsp").forward(request, response);
                    break;
                case "Modificar":
                    try {
                        String sqlModificar = "SELECT * FROM t_producto WHERE Id_Prod=?";
                        ps = conn.prepareStatement(sqlModificar);
                        ps.setString(1, id);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            producto prod = new producto();
                            prod.setId(rs.getString("Id_Prod"));
                            prod.setDescripcion(rs.getString("Descripcion"));
                            prod.setCosto(rs.getFloat("Costo"));
                            prod.setPrecio(rs.getFloat("Precio"));
                            prod.setCantidad(rs.getInt("Cantidad"));
                            listaProductos.add(prod);
                        }
                        request.setAttribute("listaProductos", listaProductos);
                        request.getRequestDispatcher("modificarProducto.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        System.out.println("Error de SQL..." + ex.getMessage());
                    } finally {
                        conBD.Discconet();
                    }
                    break;
                case "Eliminar":
                    String sqlEliminar = "DELETE FROM t_producto WHERE Id_Prod=?";
                    ps = conn.prepareStatement(sqlEliminar);
                    ps.setString(1, id);
                    ps.executeUpdate();
                    response.sendRedirect("ControlerProducto?op=Listar");
                    break;
                case "Nuevo":
                    request.getRequestDispatcher("nuevoProducto.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        } finally {
            conBD.Discconet();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String descripcion = request.getParameter("descripcion");
        float costo = Float.parseFloat(request.getParameter("costo"));
        float precio = Float.parseFloat(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        producto prod = new producto();
        prod.setId(id);
        prod.setDescripcion(descripcion);
        prod.setCosto(costo);
        prod.setPrecio(precio);
        prod.setCantidad(cantidad);

        conexionBD conBD = new conexionBD();
        Connection conn = conBD.Connected();
        PreparedStatement ps;

        try {
            if (id.isEmpty()) {
                String sqlNewId = "SELECT MAX(Id_Prod) AS Id_Prod FROM t_producto";
                ps = conn.prepareStatement(sqlNewId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String newId = rs.getString("Id_Prod");
                    prod.setId(newCod(newId));
                }

                String sqlInsert = "INSERT INTO t_producto (Id_Prod, Descripcion, Costo, Precio, Cantidad) VALUES (?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(sqlInsert);
                ps.setString(1, prod.getId());
                ps.setString(2, prod.getDescripcion());
                ps.setFloat(3, prod.getCosto());
                ps.setFloat(4, prod.getPrecio());
                ps.setInt(5, prod.getCantidad());
                ps.executeUpdate();
            } else {
                String sqlUpdate = "UPDATE t_producto SET Descripcion=?, Costo=?, Precio=?, Cantidad=? WHERE Id_Prod=?";
                ps = conn.prepareStatement(sqlUpdate);
                ps.setString(1, prod.getDescripcion());
                ps.setFloat(2, prod.getCosto());
                ps.setFloat(3, prod.getPrecio());
                ps.setInt(4, prod.getCantidad());
                ps.setString(5, prod.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Error actualizando tabla: " + ex.getMessage());
        } finally {
            conBD.Discconet();
        }
//lista producto despues de agregar producto
        response.sendRedirect("ControlerProducto?op=Listar");
    }

    private String newCod(String pCodigo) {
        int numero = Integer.parseInt(pCodigo.substring(1));
        numero++;
        pCodigo = String.valueOf(numero);
        while (pCodigo.length() < 5) {
            pCodigo = '0' + pCodigo;
        }
        pCodigo = 'P' + pCodigo;
        return pCodigo;
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gestionar productos";
    }
}