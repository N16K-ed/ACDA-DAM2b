package UT1_A8_San_Nicolas_Fran;

import java.io.Serializable;

public class Cancion implements Serializable {
    private String titulo;
    private double duracion;
    private double tamano;

    public Cancion (String titulo, double duracion, double tamano){
        this.titulo = titulo;
        this.duracion = duracion;
        this.tamano = tamano;
    }
    @Override
    public String toString(){
        return "Canción: " + titulo + " | Duración: " + duracion + "min | Tamaño: " + tamano + " MB";
    }
}
