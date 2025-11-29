/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daff
 */
 

@WebServlet("/editarProducto")
public class EditarProductoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = Conexion.getConnection()) {
            ProductoDAO dao = new ProductoDAO(conn);
            Producto producto = dao.obtenerPorId(id);
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("editarproducto.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al cargar producto", e);
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = Conexion.getConnection()) {
            ProductoDAO dao = new ProductoDAO(conn);
            Producto p = new Producto();
            p.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
            p.setCodigo(request.getParameter("codigo"));
            p.setNombre(request.getParameter("nombre"));
            p.setDescripcion(request.getParameter("descripcion"));
            p.setCategoria(request.getParameter("categoria"));
            p.setMarca(request.getParameter("marca"));
            p.setProveedor(request.getParameter("proveedor"));
            p.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            p.setPrecio_compra(Double.parseDouble(request.getParameter("precio_compra")));
            p.setPrecio_venta(Double.parseDouble(request.getParameter("precio_venta")));
            p.setEstado(request.getParameter("estado"));

            dao.actualizarProducto(p);
            response.sendRedirect("productos");
        } catch (Exception e) {
            throw new ServletException("Error al actualizar producto", e);
        }
    }
}
