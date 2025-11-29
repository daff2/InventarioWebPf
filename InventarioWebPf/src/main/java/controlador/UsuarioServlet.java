package controlador;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import DAO.UsuarioDAO;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Controlador para la gestión de usuarios
 * @author Hector
 */
@WebServlet(name = "controlUsuario", urlPatterns = {"/controlUsuario"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";
        
        switch (accion) {
            case "listar":
                List<Usuario> lista = dao.listar();
                request.setAttribute("listaUsuarios", lista);
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                break;
                
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Usuario usuarioEditar = dao.obtenerPorId(idEditar);
                request.setAttribute("usuarioEditar", usuarioEditar);
                request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                break;
                
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("controlUsuario?accion=listar");
                break;
                
            default:
                response.sendRedirect("controlUsuario?accion=listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion == null) accion = "";
        
        switch (accion) {
            case "guardar":
                Usuario nuevo = new Usuario();
                nuevo.setNombre(request.getParameter("nombre"));
                nuevo.setUsuario(request.getParameter("usuario"));
                nuevo.setContraseña(request.getParameter("contraseña"));
                nuevo.setCorreo(request.getParameter("correo"));
                nuevo.setIdRol(Integer.parseInt(request.getParameter("idRol")));
                nuevo.setEstado(request.getParameter("estado"));
                nuevo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
                
                dao.insertar(nuevo);
                response.sendRedirect("controlUsuario?accion=listar");
                break;
                
            case "actualizar":
                Usuario editado = new Usuario();
                editado.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                editado.setNombre(request.getParameter("nombre"));
                editado.setUsuario(request.getParameter("usuario"));
                editado.setContraseña(request.getParameter("contraseña"));
                editado.setCorreo(request.getParameter("correo"));
                editado.setIdRol(Integer.parseInt(request.getParameter("idRol")));
                editado.setEstado(request.getParameter("estado"));
                editado.setFechaCreacion(Timestamp.valueOf(request.getParameter("fechaCreacion")));
                
                dao.actualizar(editado);
                response.sendRedirect("controlUsuario?accion=listar");
                break;
                
            default:
                response.sendRedirect("controlUsuario?accion=listar");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para la gestión de usuarios (listar, registrar, editar, eliminar)";
    }
}

