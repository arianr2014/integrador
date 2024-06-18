package Controler;

import Entidades.usuarios;
import java.io.IOException;
import conexion.conexionBD;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ValidarLogin", urlPatterns = {"/ValidarLogin"})
public class ValidarLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("txtUsuario");
        String pass = request.getParameter("txtClave");

        conexionBD conexion = new conexionBD();
        try (Connection conn = conexion.Connected()) {
            CallableStatement stmt = conn.prepareCall("{CALL ValidateLogin(?, ?, ?, ?)}");
            stmt.setString(1, user);
            stmt.setString(2, pass);
            stmt.registerOutParameter(3, java.sql.Types.TINYINT);
            stmt.registerOutParameter(4, java.sql.Types.TINYINT);
            stmt.execute();

            int esAdministrador = stmt.getByte(3);
            int validado = stmt.getByte(4);

            if (validado == 1) {
                usuarios nuser = new usuarios(user, pass);
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(20000); // tiempo en segundos
                session.setAttribute("user", nuser);
                
                if (esAdministrador == 1) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("Cliente/usr_index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Datos incorrectos.Vuelva a intentarlo");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos", e);
        } finally {
            conexion.Discconet();
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para validar login";
    }
}
