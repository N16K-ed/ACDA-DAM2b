package models;

public class DetallePedido {
    private int id_pedido;
    private int id_videojuego;
    private int cantidad;
    private double precio_unitario;

    public DetallePedido(int id_pedido, int id_videojuego, int cantidad, double precio_unitario){
        this.id_pedido = id_pedido;
        this.id_videojuego = id_videojuego;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public int getId_videojuego() {
        return id_videojuego;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }
}
