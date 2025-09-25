package UT1_A8_San_Nicolas_Fran;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {
    private List<Cancion> canciones;
    private String grupo;
    private LocalDate fecha;

    public Album (String grupo, LocalDate fecha){
        this.canciones = new ArrayList<>();
        this.grupo = grupo;
        this.fecha = fecha;
    }

    public void agregarCancion(Cancion cancion){
        this.canciones.add(cancion);
    }

    @Override
    public String toString(){
        return "Grupo: " + grupo + " | Fecha: " + fecha + " | Canciones: " + canciones.toString();
    }
}

