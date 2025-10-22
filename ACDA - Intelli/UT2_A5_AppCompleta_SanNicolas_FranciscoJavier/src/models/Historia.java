package models;

public class Historia {
    private int id;
    private String descripcion;
    private int id_videojuego;

    public Historia(String descripcion, int id_videojuego){
        this.descripcion = descripcion;
        this.id_videojuego = id_videojuego;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId_videojuego() {
        return id_videojuego;
    }

    public void setId_videojuego(int id_videojuego) {
        this.id_videojuego = id_videojuego;
    }
}
