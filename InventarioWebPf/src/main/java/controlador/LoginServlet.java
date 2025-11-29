package controlador;

import DAO.UsuarioDAO;
import Modelo.LogSistema;
import Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controlLogin", urlPatterns = {"/controlLogin"})
public class LoginServlet extends HttpServlet {

    
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String usuario = request.getParameter("usuario");
    String password = request.getParameter("password");

    UsuarioDAO dao = new UsuarioDAO();
    Usuario u = dao.validarLogin(usuario, password);

    // Obtener IP del usuario
    String ipUsuario = request.getRemoteAddr();

    LogSistema log = new LogSistema();
    log.setFecha(new Timestamp(System.currentTimeMillis()));
    log.setIpUsuario(ipUsuario);

    if (u != null) {
        log.setIdUsuario(u.getIdUsuario());
        log.setAccion("LOGIN_EXITOSO");
        log.setDetalle("El usuario '" + u.getUsuario() + "' inició sesión correctamente.");

        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", u);

        response.sendRedirect("index.jsp");
    } else {
        log.setIdUsuario(0);
        log.setAccion("LOGIN_FALLIDO");
        log.setDetalle("Intento de acceso fallido para el usuario '" + usuario + "'.");

        response.sendRedirect("login.jsp?error=true");
    }

    registrarLogEnBD(log);
}

    private void registrarLogEnBD(LogSistema log) {
  
       
        try (Connection con = Conexion.getConnection()) {
            String sql = "INSERT INTO logs_sistema(idUsuario, accion, detalle, fecha, ipUsuario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, log.getIdUsuario());
            ps.setString(2, log.getAccion());
            ps.setString(3, log.getDetalle());
            ps.setTimestamp(4, log.getFecha());
            ps.setString(5, log.getIpUsuario());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("LOG: " + log.getAccion() + " - " + log.getDetalle());
    }
}