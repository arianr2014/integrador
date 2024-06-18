/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import conexion.conexionBD;
import Entidades.cliente;
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
@WebServlet(name = "Controler", urlPatterns = {"/Controler"})
public class Controler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String vUsuario = request.getParameter("txtUsuario");
        String vClave = request.getParameter("txtClave");
        String vPaswd="";
        String vUser="";
        conexionBD conBD = new conexionBD();
        Connection conn = conBD.Connected();
        PreparedStatement ps;
        ResultSet rs; 
            try{
                    String sql="select * from t_usuario where IdUsuario=?";
                    ps= conn.prepareStatement(sql);
                    ps.setString(1, vUsuario);                    
                    rs= ps.executeQuery();
                    while(rs.next()){
                        vUser = rs.getString(1);
                        vPaswd=rs.getString(2);
                    }
                    
                    if(vUser.equals(vUsuario) && vPaswd.equals(vClave)){
                       
                        response.sendRedirect("index.jsp");
                    }else{
                        Respuesta(response,"Acceso no Válido");
                        //response.sendRedirect("index.jsp");
                    }
                    //request.getRequestDispatcher("listar.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                } finally{
                    conBD.Discconet();
                }        

      
    }

    private void Respuesta(HttpServletResponse objRespuesta, String Mensaje)
    throws ServletException, IOException{
        PrintWriter out= objRespuesta.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<t1>"+Mensaje+"</t1>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
