/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/testdb"})
public class TestDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try (Connection conn = Conexion.getConnection()) {
                if (conn != null) {
                    out.println("✅ Conexión exitosa a la base de datos!\n");

                    // Hacemos la consulta
                    String sql = "SELECT id_usuario, usuario, contrasena, estado FROM usuarios";
                    try (PreparedStatement ps = conn.prepareStatement(sql);
                         ResultSet rs = ps.executeQuery()) {

                        int contador = 0;
                        while (rs.next()) {
                            contador++;
                            out.println("ID: " + rs.getInt("id_usuario"));
                            out.println("Usuario: " + rs.getString("usuario"));
                            out.println("Contraseña: " + rs.getString("contrasena"));
                            out.println("Estado: " + rs.getString("estado"));
                            out.println("-------------------------");
                        }

                        if (contador == 0) {
                            out.println("⚠️ La tabla 'usuarios' está vacía.");
                        }

                    } catch (Exception e) {
                        out.println("❌ Error al consultar usuarios: " + e.getMessage());
                    }

                } else {
                    out.println("❌ No se pudo establecer la conexión.");
                }
            } catch (Exception e) {
                out.println("❌ Error general: " + e.getMessage());
            }
        }
    }
}
