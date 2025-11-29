/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import Modelo.HistorialMovimiento;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Hector
 */
@WebServlet(name = "HistorialMovimientoServlet", urlPatterns = {"/HistorialMovimientoServlet"})
public class HistorialMovimientoServlet extends HttpServlet {

    /**
     * Carga y envía a la vista (historial.jsp) la lista de movimientos históricos
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<HistorialMovimiento> listaHistorial = new ArrayList<>();
        
        try {
  
            Connection con = Conexion.getConnection();
            
            String sql = "SELECT idHistorial, idMovimiento, accion, fechaccion, usuarioResponsable FROM HistorialMovimiento";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int idHistorial = rs.getInt("idHistorial");
                int idMovimiento = rs.getInt("idMovimiento");
                String accion = rs.getString("accion");
                Timestamp fechaccion = rs.getTimestamp("fechaccion");
                int usuarioResponsable = rs.getInt("usuarioResponsable");
                
                HistorialMovimiento hm = new HistorialMovimiento(idHistorial, idMovimiento, accion, fechaccion, usuarioResponsable);
                listaHistorial.add(hm);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        request.setAttribute("listaHistorial", listaHistorial);
        request.getRequestDispatcher("historial.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int idMovimiento = Integer.parseInt(request.getParameter("idMovimiento"));
            String accion = request.getParameter("accion");
            int usuarioResponsable = Integer.parseInt(request.getParameter("usuarioResponsable"));
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            
            Connection con = Conexion.getConnection();
            String sql = "INSERT INTO HistorialMovimiento (idMovimiento, accion, fechaccion, usuarioResponsable) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMovimiento);
            ps.setString(2, accion);
            ps.setTimestamp(3, fechaActual);
            ps.setInt(4, usuarioResponsable);
            
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
    
            response.sendRedirect("HistorialMovimientoServlet");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para mostrar y registrar movimientos en el historial";
    }
}