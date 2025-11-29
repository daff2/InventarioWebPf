/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daff
 */
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = Conexion.getConnection()) {
            ProductoDAO dao = new ProductoDAO(conn);

            // Capturar parámetros de búsqueda
            String nombre = request.getParameter("nombre");
            String categoria = request.getParameter("categoria");
           
            List<Producto> productos;

            // Si hay al menos un filtro, usar búsqueda
            if ((nombre != null && !nombre.isEmpty()) ||
                (categoria != null && !categoria.isEmpty())) {
                productos = dao.buscarProductos(nombre, categoria);
            } else {
                productos = dao.listarProductos();
            }

            request.setAttribute("productos", productos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Productos.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al obtener productos", e);
        }
    }
}





