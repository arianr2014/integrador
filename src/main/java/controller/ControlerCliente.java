/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entidades.cliente;
import Entidades.usuarios;
import conexion.conexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javie
 */


@WebServlet(name = "ControlerCliente", urlPatterns = {"/ControlerCliente"})
public class ControlerCliente extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        HttpSession session=request.getSession();
        usuarios user=(usuarios)session.getAttribute("user");
        if(user==null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        String Op =request.getParameter("Op");
        ArrayList<cliente> Lista= new ArrayList<cliente>();
        conexionBD conBD = new conexionBD();
        Connection conn = conBD.Connected();
        PreparedStatement ps;
        ResultSet rs;
        switch(Op){
            case "Listar":
                try{
                    String sql="SELECT * FROM t_cliente";
                    ps= conn.prepareStatement(sql);
                    rs= ps.executeQuery();
                    while(rs.next()){
                        cliente client=new cliente();
                        client.setId(rs.getString("Id_Cliente"));
                        client.setApellidos(rs.getString("Apellidos"));
                        client.setNombres(rs.getString("Nombres"));
                        client.setDNI(rs.getString("DNI"));
                        client.setDireccion(rs.getString("Direccion"));
                        client.setTelefono(rs.getString("Telefono"));
                        client.setMovil(rs.getString("Movil"));
                        client.setEmail(rs.getString("Email"));
                        Lista.add(client);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("listar.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                } finally{
                    conBD.Discconet();
                }
                break;
            case "Consultar":
                try{
                    String Id=request.getParameter("Id");
                    String sql="select * from t_cliente where Id_Cliente=?";
                    ps= conn.prepareStatement(sql);
                    ps.setString(1, Id);
                    rs= ps.executeQuery();
                    cliente client=new cliente();
                    while(rs.next()){
                        client.setId(rs.getString("Id_Cliente"));
                        client.setApellidos(rs.getString("Apellidos"));
                        client.setNombres(rs.getString("Nombres"));
                        client.setDNI(rs.getString("DNI"));
                        client.setDireccion(rs.getString("Direccion"));
                        client.setTelefono(rs.getString("Telefono"));
                        client.setMovil(rs.getString("Movil"));
                        client.setEmail(rs.getString("Email"));

                        Lista.add(client);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("consultar.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                } finally{
                    conBD.Discconet();
                }                
                break;    
            case "Modificar":
                try{
                    String Id=request.getParameter("Id");
                    String sql="select * from t_cliente where Id_Cliente=?";
                    ps= conn.prepareStatement(sql);
                    ps.setString(1, Id);
                    rs= ps.executeQuery();
                    cliente client=new cliente();
                    while(rs.next()){
                        client.setId(rs.getString("Id_Cliente"));
                        client.setApellidos(rs.getString("Apellidos"));
                        client.setNombres(rs.getString("Nombres"));
                        client.setDNI(rs.getString("DNI"));
                        client.setDireccion(rs.getString("Direccion"));
                        client.setTelefono(rs.getString("Telefono"));
                        client.setMovil(rs.getString("Movil"));
                        client.setEmail(rs.getString("Email"));
                        Lista.add(client);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("modificar.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                } finally{
                    conBD.Discconet();
                }                 
                
                break;
            case "Eliminar":
                try{
                    String Id=request.getParameter("Id");
                    String sql="delete from t_cliente where Id_Cliente=?";
                    ps= conn.prepareStatement(sql);
                    ps.setString(1, Id);
                    ps.executeUpdate();
                    request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                } finally{
                    conBD.Discconet();
                }                          
                break;
            case "Nuevo":
                request.getRequestDispatcher("nuevoCliente.jsp").forward(request, response);
                break;
            case "BuscarCliente":
                try {
                String Id_Cliente = request.getParameter("Id_Cliente");
                String sql = "SELECT Nombres FROM t_cliente WHERE Id_Cliente = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, Id_Cliente);
                rs = ps.executeQuery();
        
            if (rs.next()) {
                String nombres = rs.getString("Nombres");
                request.setAttribute("nombres", nombres);
        } else {
            request.setAttribute("Nombres", "Cliente no encontrado");
        }
        
        // Enviar respuesta de vuelta al JSP
        request.getRequestDispatcher("nuevoPedido.jsp").forward(request, response);
    } catch (SQLException ex) {
        System.out.println("Error de SQL..." + ex.getMessage());
    } finally {
        conBD.Discconet();
    }
    break;

            
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String Id =request.getParameter("Id");       
        String Apellidos=request.getParameter("apellidos"); 
        String Nombres=request.getParameter("nombres"); 
        String DNI=request.getParameter("DNI");
        String Direccion=request.getParameter("direccion"); 
        String Telefono=request.getParameter("telefono"); 
        String Movil=request.getParameter("movil");
        String Email=request.getParameter("email");
        cliente client=new cliente();
        
        client.setId(Id);
        client.setApellidos(Apellidos);
        client.setNombres(Nombres);
        client.setDNI(DNI);
        client.setDireccion(Direccion);
        client.setTelefono(Telefono);
        client.setMovil(Movil);
        client.setEmail(Email);
        
        conexionBD conBD = new conexionBD();
        Connection conn = conBD.Connected();
        PreparedStatement ps;
        ResultSet rs;        
        if(Id.isEmpty()){
            String sql_new="select max(Id_Cliente) Id_Cliente from t_cliente";
            String sql="insert into t_cliente(Id_Cliente, apellidos, nombres, DNI, direccion, telefono, movil, Email) values(?, ?, ?, ?, ?, ?, ?,?)";

            try{
                /*Algoritmo para autogeneral el código*/
                //String Id_Cliente="C00020";
                /*Algoritmo para autogenerar el código*/
                String Id_Cliente="";
                ps= conn.prepareStatement(sql_new);
                rs= ps.executeQuery();
                while(rs.next()){
                    Id_Cliente=rs.getString("Id_Cliente");
                }
                Id_Cliente=newCod(Id_Cliente);
                ps= conn.prepareStatement(sql);
                ps.setString(1, Id_Cliente);
                ps.setString(2, client.getApellidos());
                ps.setString(3, client.getNombres());
                ps.setString(4, client.getDNI());
                ps.setString(5, client.getDireccion());
                ps.setString(6, client.getTelefono());
                ps.setString(7, client.getMovil());
                ps.setString(8, client.getEmail());
                
                ps.executeUpdate(); 
            }catch(SQLException ex){
                System.out.println("Error actualizando tabla..."+ex.getMessage());
            } finally{
                conBD.Discconet();
            }               
        }else{
            String sql="update t_cliente set apellidos=?, nombres=?, DNI=?, direccion=?, telefono=?, movil=? , Email=? where Id_Cliente=?";

            try{
                ps= conn.prepareStatement(sql);
                ps.setString(1, client.getApellidos());
                ps.setString(2, client.getNombres());
                ps.setString(3, client.getDNI());
                ps.setString(4, client.getDireccion());
                ps.setString(5, client.getTelefono());
                ps.setString(6, client.getMovil());
                ps.setString(7, client.getEmail());
                ps.setString(8, client.getId());

                ps.executeUpdate(); 
            }catch(SQLException ex){
                System.out.println("Error actualizando tabla..."+ex.getMessage());
            } finally{
                conBD.Discconet();
            }               
        }
        
       // response.sendRedirect("Clientes.jsp");
        response.sendRedirect("ControlerCliente?Op=Listar");


    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private String newCod(java.lang.String pCodigo) {
       
        int Numero;
        Numero=Integer.parseInt(pCodigo.substring(2));
        Numero=Numero+1;
        pCodigo=String.valueOf(Numero);
        while (pCodigo.length()<5){
            pCodigo='0'+ pCodigo;
        }
        pCodigo='C'+pCodigo;        
        return (pCodigo);
    }
}
