package UT1_A6_San_Nicolas_Fran;

public class Coche {
    private String marca;
    private String modelo;
    private int caballos;

    public Coche (String marca, String modelo, int caballos){
        this.caballos = caballos;
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCaballos() {
        return caballos;
    }
}
