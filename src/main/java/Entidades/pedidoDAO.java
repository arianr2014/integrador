/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Entidades.pedido;
import conexion.conexionBD;

public class pedidoDAO {

    private conexionBD conexion;

    public pedidoDAO() {
        conexion = new conexionBD();
    }

    public void agregarPedido(pedido pedido) throws SQLException {
        String sql = "INSERT INTO t_pedido (Id_Pedido, Id_Cliente, Apellidos, Nombres, Fecha, SubTotal, TotalVenta) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.Connected();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pedido.getId_Pedido());
            pstmt.setString(2, pedido.getId_Cliente());
            pstmt.setString(3, pedido.getApellidos());
            pstmt.setString(4, pedido.getNombres());
            pstmt.setDate(5, pedido.getFecha());
            pstmt.setDouble(6, pedido.getSubTotal());
            pstmt.setDouble(7, pedido.getTotalVenta());
            pstmt.executeUpdate();
        }
    }

    public pedido obtenerPedido(String idPedido) throws SQLException {
        String sql = "SELECT * FROM t_pedido WHERE Id_Pedido = ?";
        try (Connection conn = conexion.Connected();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idPedido);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pedido pedido = new pedido();
                    pedido.setId_Pedido(rs.getString("Id_Pedido"));
                    pedido.setId_Cliente(rs.getString("Id_Cliente"));
                    pedido.setApellidos(rs.getString("Apellidos"));
                    pedido.setNombres(rs.getString("Nombres"));
                    pedido.setFecha(rs.getDate("Fecha"));
                    pedido.setSubTotal(rs.getDouble("SubTotal"));
                    pedido.setTotalVenta(rs.getDouble("TotalVenta"));
                    return pedido;
                }
            }
        }
        return null;
    }

    public List<pedido> listarPedidos() throws SQLException {
        List<pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM t_pedido";
        try (Connection conn = conexion.Connected();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pedido pedido = new pedido();
                pedido.setId_Pedido(rs.getString("Id_Pedido"));
                pedido.setId_Cliente(rs.getString("Id_Cliente"));
                pedido.setApellidos(rs.getString("Apellidos"));
                pedido.setNombres(rs.getString("Nombres"));
                pedido.setFecha(rs.getDate("Fecha"));
                pedido.setSubTotal(rs.getDouble("SubTotal"));
                pedido.setTotalVenta(rs.getDouble("TotalVenta"));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    public void eliminarPedido(String idPedido) throws SQLException {
        String sql = "DELETE FROM t_pedido WHERE Id_Pedido = ?";
        try (Connection conn = conexion.Connected();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idPedido);
            pstmt.executeUpdate();
        }
    }
}
