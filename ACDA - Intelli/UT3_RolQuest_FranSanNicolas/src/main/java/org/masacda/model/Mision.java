package org.masacda.model;

import jakarta.persistence.*;
import org.masacda.service.MisionService;

@Entity
@Table(name = "Mision")
public class Mision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int misionID;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false, length = 20)
    private String dificultad;

    @Column(nullable = false)
    private int recompensa;

    @Column(nullable = false)
    private boolean activa;

    public Mision(){

    }
    public int getMisionID() {
        return misionID;
    }

    public void setMisionID(int misionID) {
        this.misionID = misionID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(int recompensa) {
        this.recompensa = recompensa;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
