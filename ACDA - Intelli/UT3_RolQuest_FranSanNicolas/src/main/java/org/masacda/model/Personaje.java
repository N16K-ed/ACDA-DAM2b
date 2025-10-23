package org.masacda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Personaje")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personajeID;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 10)
    private String clase;

    @Column(nullable = false)
    private int nivel;

    @Column(nullable = false)
    private int puntosVida;

    @Column(nullable = false, length = 100)
    private String armaPrincipal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fichaID")
    private FichaDetalle fichaDetalle;


    public Personaje(){ }

    public int getPersonajeID() {
        return personajeID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClase() {
        return clase;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public String getArmaPrincipal() {
        return armaPrincipal;
    }

    public FichaDetalle getFichaDetalle() {
        return fichaDetalle;
    }

    public void setPersonajeID(int personajeID) {
        this.personajeID = personajeID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public void setArmaPrincipal(String armaPrincipal) {
        this.armaPrincipal = armaPrincipal;
    }

    public void setFichaDetalle(FichaDetalle fichaDetalle) {
        this.fichaDetalle = fichaDetalle;
    }
}
