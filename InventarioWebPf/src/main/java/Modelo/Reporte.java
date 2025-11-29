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
public class Reporte {
    
    private int idReporte;
    private String titulo;
    private String tipo; // "Inventario", "Movimientos", "Usuarios"
    private Timestamp fechaGeneracion;
    private int generadoPor;
    private String contenido;

    public Reporte() {
    }

    public Reporte(int idReporte, String titulo, String tipo, Timestamp fechaGeneracion, int generadoPor, String contenido) {
        this.idReporte = idReporte;
        this.titulo = titulo;
        this.tipo = tipo;
        this.fechaGeneracion = fechaGeneracion;
        this.generadoPor = generadoPor;
        this.contenido = contenido;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Timestamp getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Timestamp fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public int getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(int generadoPor) {
        this.generadoPor = generadoPor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    
    
}
