package models;

public class Cliente {
    private int id;
    private String nombre;
    private String correo;

    public Cliente(String nombre, String correo){
        this.nombre = nombre;
        this.correo = correo;
    }

    public Cliente(int id, String nombre, String correo){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}
