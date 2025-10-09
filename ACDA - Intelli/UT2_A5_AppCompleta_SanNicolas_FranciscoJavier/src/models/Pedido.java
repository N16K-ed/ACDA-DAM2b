package models;

import java.util.Date;

public class Pedido {
    private int id;
    private Date fecha;
    private int id_cliente;

    public Pedido(Date fecha, int id_cliente){
        this.fecha = fecha;
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }
}
