package UT1_Refuerzo_Examen_San_Nicolas_Fran;

public class Producto {

    private int id;
    private String nombreProducto;
    private String categoria;
    private double precio;
    private int stock;

    public Producto (int id, String nombreProducto, String categoria, double precio, int stock){
        this.categoria = categoria;
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString(){
        return  "ID Producto: " +  id + "\nNombre: " + nombreProducto + "\nCategoría: " + categoria + "\nPrecio: " + precio + "\nStock: " + stock;
    }
}
