/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Usuario;
import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hector
 */
public class UsuarioDAO {
      // Listar todos los usuarios
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario ORDER BY idUsuario DESC";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setContraseña(rs.getString("contraseña"));
                u.setCorreo(rs.getString("correo"));
                u.setIdRol(rs.getInt("idRol"));
                u.setEstado(rs.getString("estado"));
                u.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
                lista.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Obtener usuario por ID
    public Usuario obtenerPorId(int idUsuario) {
        Usuario u = null;
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuario();
                    u.setIdUsuario(rs.getInt("idUsuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setContraseña(rs.getString("contraseña"));
                    u.setCorreo(rs.getString("correo"));
                    u.setIdRol(rs.getInt("idRol"));
                    u.setEstado(rs.getString("estado"));
                    u.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    // Insertar nuevo usuario
    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO Usuario (nombre, usuario, contraseña, correo, idRol, estado, fechaCreacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        if (u.getFechaCreacion() == null) {
            u.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
        }
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getContraseña());
            ps.setString(4, u.getCorreo());
            ps.setInt(5, u.getIdRol());
            ps.setString(6, u.getEstado());
            ps.setTimestamp(7, u.getFechaCreacion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar usuario
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE Usuario SET nombre=?, usuario=?, contraseña=?, correo=?, idRol=?, estado=?, fechaCreacion=? WHERE idUsuario=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getContraseña());
            ps.setString(4, u.getCorreo());
            ps.setInt(5, u.getIdRol());
            ps.setString(6, u.getEstado());
            ps.setTimestamp(7, u.getFechaCreacion());
            ps.setInt(8, u.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar usuario
    public boolean eliminar(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE idUsuario=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Validar usuario (para login opcional)
      public Usuario validarLogin(String usuario, String password) {
        Usuario u = null;
        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE usuario=? AND contrasena=? AND estado='Activo'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setCorreo(rs.getString("correo"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setEstado(rs.getString("estado"));
                u.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

}
