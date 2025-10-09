package models;

import java.util.Date;

public class Cliente {
    private int id;
    private Date fecha;
    private String correo;

    public Cliente(Date fecha, String correo){
        this.fecha = fecha;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCorreo() {
        return correo;
    }
}
