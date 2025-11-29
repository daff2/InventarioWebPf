package DAO;

import Modelo.HistorialMovimiento;
import controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad HistorialMovimiento
 */
public class HistorialMovimientoDAO {

    // Lista todos los registros
    public List<HistorialMovimiento> listar() {
        List<HistorialMovimiento> lista = new ArrayList<>();
        String sql = "SELECT idHistorial, idMovimiento, accion, fechaccion, usuarioResponsable FROM HistorialMovimiento ORDER BY fechaccion DESC";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HistorialMovimiento hm = new HistorialMovimiento();
                hm.setIdHistorial(rs.getInt("idHistorial"));
                hm.setIdMovimiento(rs.getInt("idMovimiento"));
                hm.setAccion(rs.getString("accion"));
                hm.setFechaccion(rs.getTimestamp("fechaccion"));
                hm.setUsuarioResponsable(rs.getInt("usuarioResponsable"));
                lista.add(hm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Obtiene un registro por su idHistorial
    public HistorialMovimiento obtenerPorId(int idHistorial) {
        String sql = "SELECT idHistorial, idMovimiento, accion, fechaccion, usuarioResponsable FROM HistorialMovimiento WHERE idHistorial = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHistorial);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HistorialMovimiento hm = new HistorialMovimiento();
                    hm.setIdHistorial(rs.getInt("idHistorial"));
                    hm.setIdMovimiento(rs.getInt("idMovimiento"));
                    hm.setAccion(rs.getString("accion"));
                    hm.setFechaccion(rs.getTimestamp("fechaccion"));
                    hm.setUsuarioResponsable(rs.getInt("usuarioResponsable"));
                    return hm;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Inserta un nuevo registro (devuelve true si se insertó)
    public boolean insertar(HistorialMovimiento hm) {
        String sql = "INSERT INTO HistorialMovimiento (idMovimiento, accion, fechaccion, usuarioResponsable) VALUES (?, ?, ?, ?)";
        // Si no hay fecha, usar la actual
        if (hm.getFechaccion() == null) {
            hm.setFechaccion(new Timestamp(System.currentTimeMillis()));
        }
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, hm.getIdMovimiento());
            ps.setString(2, hm.getAccion());
            ps.setTimestamp(3, hm.getFechaccion());
            ps.setInt(4, hm.getUsuarioResponsable());

            int afect = ps.executeUpdate();
            if (afect > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        hm.setIdHistorial(keys.getInt(1)); // setea el id generado al objeto
                    }
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualiza un registro existente (devuelve true si se actualizó)
    public boolean actualizar(HistorialMovimiento hm) {
        String sql = "UPDATE HistorialMovimiento SET idMovimiento = ?, accion = ?, fechaccion = ?, usuarioResponsable = ? WHERE idHistorial = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hm.getIdMovimiento());
            ps.setString(2, hm.getAccion());
            ps.setTimestamp(3, hm.getFechaccion());
            ps.setInt(4, hm.getUsuarioResponsable());
            ps.setInt(5, hm.getIdHistorial());

            int afect = ps.executeUpdate();
            return afect > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Elimina un registro por idHistorial (devuelve true si se eliminó)
    public boolean eliminar(int idHistorial) {
        String sql = "DELETE FROM HistorialMovimiento WHERE idHistorial = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHistorial);
            int afect = ps.executeUpdate();
            return afect > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Búsqueda simple por acción o por usuario responsable (si parametro es nulo o vacío devuelve listar())
    public List<HistorialMovimiento> buscar(String accionLike, Integer usuarioResponsable) {
        List<HistorialMovimiento> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT idHistorial, idMovimiento, accion, fechaccion, usuarioResponsable FROM HistorialMovimiento WHERE 1=1 ");
        if (accionLike != null && !accionLike.trim().isEmpty()) {
            sql.append("AND accion LIKE ? ");
        }
        if (usuarioResponsable != null) {
            sql.append("AND usuarioResponsable = ? ");
        }
        sql.append("ORDER BY fechaccion DESC");

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int idx = 1;
            if (accionLike != null && !accionLike.trim().isEmpty()) {
                ps.setString(idx++, "%" + accionLike.trim() + "%");
            }
            if (usuarioResponsable != null) {
                ps.setInt(idx++, usuarioResponsable);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HistorialMovimiento hm = new HistorialMovimiento();
                    hm.setIdHistorial(rs.getInt("idHistorial"));
                    hm.setIdMovimiento(rs.getInt("idMovimiento"));
                    hm.setAccion(rs.getString("accion"));
                    hm.setFechaccion(rs.getTimestamp("fechaccion"));
                    hm.setUsuarioResponsable(rs.getInt("usuarioResponsable"));
                    lista.add(hm);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}