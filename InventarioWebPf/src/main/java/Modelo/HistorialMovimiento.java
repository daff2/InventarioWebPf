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
public class HistorialMovimiento {
    
    private int idHistorial;
    private int idMovimiento;
    private String accion;
    private Timestamp fechaccion;
    private int usuarioResponsable;

    public HistorialMovimiento() {
    }

    public HistorialMovimiento(int idHistorial, int idMovimiento, String accion, Timestamp fechaccion, int usuarioResponsable) {
        this.idHistorial = idHistorial;
        this.idMovimiento = idMovimiento;
        this.accion = accion;
        this.fechaccion = fechaccion;
        this.usuarioResponsable = usuarioResponsable;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Timestamp getFechaccion() {
        return fechaccion;
    }

    public void setFechaccion(Timestamp fechaccion) {
        this.fechaccion = fechaccion;
    }

    public int getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(int usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }
    
    
    
}
