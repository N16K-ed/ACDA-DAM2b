package models;

public class Videojuego {
    private int id;
    private String titulo;
    private int anio;
    private String plataforma;

    public Videojuego(String titulo, int anio, String plataforma){
        this.titulo = titulo;
        this.anio = anio;
        this.plataforma = plataforma;
    }

    public Videojuego(int id, String titulo, int anio, String plataforma){
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.plataforma = plataforma;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public String getPlataforma() {
        return plataforma;
    }
}
