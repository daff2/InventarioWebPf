package controlador;

import Modelo.HistorialMovimiento;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class HistorialBean implements Serializable{

    private List<HistorialMovimiento> listaHistorial;
    private HistorialMovimiento historialSeleccionado;

    public HistorialBean() {
        listaHistorial = new ArrayList<>();
    }

   
    @PostConstruct
    public void init() {
        cargarHistorial();
    }


    public void cargarHistorial() {
        listaHistorial.clear();

        listaHistorial.add(new HistorialMovimiento(
                1, 101, "ENTRADA", new Timestamp(System.currentTimeMillis()), 5
        ));
        listaHistorial.add(new HistorialMovimiento(
                2, 102, "SALIDA", new Timestamp(System.currentTimeMillis()), 7
        ));
        listaHistorial.add(new HistorialMovimiento(
                3, 103, "AJUSTE", new Timestamp(System.currentTimeMillis()), 3
        ));
    }


    public String verDetalle(int idMovimiento) {

        for (HistorialMovimiento h : listaHistorial) {
            if (h.getIdMovimiento() == idMovimiento) {
                historialSeleccionado = h;
                break;
            }
        }


        return null;
    }



    public List<HistorialMovimiento> getListaHistorial() {
        return listaHistorial;
    }

    public void setListaHistorial(List<HistorialMovimiento> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    public HistorialMovimiento getHistorialSeleccionado() {
        return historialSeleccionado;
    }

    public void setHistorialSeleccionado(HistorialMovimiento historialSeleccionado) {
        this.historialSeleccionado = historialSeleccionado;
    }


    
}
