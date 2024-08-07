package modeloDAO;

import conexion.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modeloDTO.Cliente;

public class ClienteDAO {

    conexionBD cn = new conexionBD();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Cliente buscar(String dni) {
        Cliente c = new Cliente();
        String sql = "Select Id_Cliente, DNI,Nombres,Direccion, '1', Apellidos, Email from t_cliente where Dni=" + dni;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setId(rs.getString(1));
                c.setDni(rs.getString(2));
                c.setNom(rs.getString(3) + ' ' + rs.getString(6));
                c.setDir(rs.getString(4));
                c.setEs(rs.getString(5));
                c.setEmail(rs.getString(7));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return c;
    }

    //*******Operaciones CRUD***************//
    public List listar() {
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getString(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEs(rs.getString(5));
                lista.add(cl);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return lista;
    }

    public int agregar(Cliente cl) {
        String sql = "insert into cliente(Dni, Nombres, Direccion,Estado)values(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEs());
            ps.executeUpdate();

            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return r;

    }

    public Cliente listarId(int id) {
        Cliente cli = new Cliente();
        String sql = "select * from cliente where IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEs(rs.getString(5));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return cli;
    }

    public int actualizar(Cliente em) {
        String sql = "update cliente set Dni=?, Nombres=?, Direccion=?,Estado=? where IdCliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getDir());
            ps.setString(4, em.getEs());
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
        String sql = "delete from cliente where IdCliente=" + id;
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
