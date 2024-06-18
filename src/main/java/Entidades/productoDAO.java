/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Entidades.producto;
import conexion.conexionBD;

public class productoDAO {

    private conexionBD conexion;

    public productoDAO() {
        conexion = new conexionBD();
    }

    public void agregarProducto(producto producto) throws SQLException {
        String sql = "INSERT INTO t_producto (Id, Descripcion, Costo, Precio, Cantidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexion.Connected();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getId());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setFloat(3, producto.getCosto());
            pstmt.setFloat(4, producto.getPrecio());
            pstmt.setInt(5, producto.getCantidad());
            pstmt.executeUpdate();
        }
    }

    public producto obtenerProducto(String idProducto) throws SQLException {
        String sql = "SELECT * FROM t_producto WHERE Id = ?";
        try (Connection conn = conexion.Connected();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idProducto);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto producto = new producto();
                    producto.setId(rs.getString("Id"));
                    producto.setDescripcion(rs.getString("Descripcion"));
                    producto.setCosto(rs.getFloat("Costo"));
                    producto.setPrecio(rs.getFloat("Precio"));
                    producto.setCantidad(rs.getInt("Cantidad"));
                    return producto;
                }
            }
        }
        return null;
    }

    public List<producto> listarProductos() throws SQLException {
        List<producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM t_producto";
        try (Connection conn = conexion.Connected();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                producto producto = new producto();
                producto.setId(rs.getString("Id"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setCosto(rs.getFloat("Costo"));
                producto.setPrecio(rs.getFloat("Precio"));
                producto.setCantidad(rs.getInt("Cantidad"));
                productos.add(producto);
            }
        }
        return productos;
    }

    public void eliminarProducto(String idProducto) throws SQLException {
        String sql = "DELETE FROM t_producto WHERE Id = ?";
        try (Connection conn = conexion.Connected();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idProducto);
            pstmt.executeUpdate();
        }
    }
}
