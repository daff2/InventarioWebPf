/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author Daff
 */
public class LogSistema {
    
    private int idLog;
    private int idUsuario;
    private String accion;
    private String detalle;
    private Timestamp fecha;
    private String ipUsuario;

    public LogSistema() {
    }

    public LogSistema(int idLog, int idUsuario, String accion, String detalle, Timestamp fecha, String ipUsuario) {
        this.idLog = idLog;
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.detalle = detalle;
        this.fecha = fecha;
        this.ipUsuario = ipUsuario;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

    
    
}
