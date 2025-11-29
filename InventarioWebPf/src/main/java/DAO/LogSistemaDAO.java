package DAO;

import Modelo.LogSistema;
import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LogSistemaDAO {

 
    public boolean insertar(LogSistema log) {
        String sql = "INSERT INTO LogSistema (idUsuario, accion, detalle, fecha, ipUsuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, log.getIdUsuario());
            ps.setString(2, log.getAccion());
            ps.setString(3, log.getDetalle());
            ps.setTimestamp(4, log.getFecha());
            ps.setString(5, log.getIpUsuario());

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<LogSistema> listarTodos() {
        List<LogSistema> lista = new ArrayList<>();
        String sql = "SELECT idLog, idUsuario, accion, detalle, fecha, ipUsuario FROM LogSistema ORDER BY fecha DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LogSistema log = new LogSistema();
                log.setIdLog(rs.getInt("idLog"));
                log.setIdUsuario(rs.getInt("idUsuario"));
                log.setAccion(rs.getString("accion"));
                log.setDetalle(rs.getString("detalle"));
                log.setFecha(rs.getTimestamp("fecha"));
                log.setIpUsuario(rs.getString("ipUsuario"));

                lista.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminarPorId(int idLog) {
        String sql = "DELETE FROM LogSistema WHERE idLog = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLog);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
