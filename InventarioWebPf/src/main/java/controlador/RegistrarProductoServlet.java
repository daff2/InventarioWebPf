/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daff
 */
@WebServlet("/registrarProducto")
public class RegistrarProductoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = Conexion.getConnection()) {
            ProductoDAO dao = new ProductoDAO(conn);
            Producto p = new Producto();
            p.setCodigo(request.getParameter("codigo"));
            p.setNombre(request.getParameter("nombre"));
            p.setDescripcion(request.getParameter("descripcion"));
            p.setCategoria(request.getParameter("categoria"));
            p.setMarca(request.getParameter("marca"));
            p.setProveedor(request.getParameter("proveedor"));
            p.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            
            String precioCompraStr = request.getParameter("precioCompra");
String precioVentaStr = request.getParameter("precioVenta");

double precio_compra = (precioCompraStr != null && !precioCompraStr.isEmpty())
    ? Double.parseDouble(precioCompraStr)
    : 0.0;

double precio_venta = (precioVentaStr != null && !precioVentaStr.isEmpty())
    ? Double.parseDouble(precioVentaStr)
    : 0.0;
        p.setPrecio_compra(precio_compra);
        p.setPrecio_venta(precio_venta);

            p.setEstado(request.getParameter("estado"));
            p.setFecha_registro(new Timestamp(System.currentTimeMillis()));

            dao.insertarProducto(p);
            response.sendRedirect("productos");
        } catch (Exception e) {
            throw new ServletException("Error al registrar producto", e);
        }
    }
    
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    request.getRequestDispatcher("nuevoproducto.jsp").forward(request, response);
}
}
