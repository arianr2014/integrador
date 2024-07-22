package modeloDAO;

import conexion.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloDTO.Producto;

public class ProductoDAO {

    conexionBD cn = new conexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Producto buscar(String id) {
        Producto p = new Producto();
        String sql = "select Id_Prod,Descripcion,precio,Stock,'1'  from t_producto where Id_Prod='" + id + "'";
        String mensaje="";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getString(1));
                p.setNom(rs.getString(2));
                p.setPre(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setEstado(rs.getString(5));
            }
        } catch (Exception e) {
            mensaje=e.getMessage();
        }
        return p;
    }

    public int actualizarstock(String id, int stock) {
        String sql = "update t_producto set Stock=? where Id_Prod=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return r;
    }

    //*******Operaciones CRUD***************//
    public List listar() {
        String sql = "select * from producto";
        List<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto em = new Producto();
                em.setId(rs.getString(1));
                em.setNom(rs.getString(2));
                em.setPre(rs.getDouble(3));
                em.setStock(rs.getInt(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return lista;
    }

    public int agregar(Producto p) {
        String sql = "insert into producto(Nombres, Precio,Stock,Estado)values(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPre());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getEstado());
            ps.executeUpdate();
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return r;

    }

    public Producto listarId(String id) {
        Producto pr = new Producto();
        String sql = "select Id_Prod,Descripcion,precio,Stock,'1' from t_producto where Id_Prod='" + id +"'";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pr.setId(rs.getString(1));
                pr.setNom(rs.getString(2));
                pr.setPre(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return pr;
    }

    public int actualizar(Producto em) {
        String sql = "update producto set Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNom());
            ps.setDouble(2, em.getPre());
            ps.setInt(3, em.getStock());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getId());
            ps.executeUpdate();
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return r;
    }

    public void delete(int id) {
        String sql = "delete from producto where IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }

}
