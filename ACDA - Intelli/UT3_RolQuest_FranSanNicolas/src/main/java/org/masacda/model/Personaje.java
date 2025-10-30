package org.masacda.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "Personaje_Mision",
            joinColumns = @JoinColumn(name = "personajeID"),
            inverseJoinColumns = @JoinColumn(name = "misionID")
    )
    private List<Mision> misiones;

    public Personaje(){
        this.misiones = new ArrayList<>();
    }

    public Personaje(String nombre, String clase){
        this.nombre = nombre;
        this.clase = clase;
    }

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

    public List<Mision> getMisiones() {
        return misiones;
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

    public void setMisiones(List<Mision> misiones) {
        this.misiones = new ArrayList<>();
        if(misiones != null) {
            this.misiones.addAll(misiones);
        }
    }
    public void addMision(Mision mision) {
        if (!this.misiones.contains(mision)) {
            this.misiones.add(mision);
        }
        if (mision.getPersonajes() == null) {
            mision.setPersonajes(new ArrayList<>());
        }
        if (!mision.getPersonajes().contains(this)) {
            mision.getPersonajes().add(this);
        }
    }

    public void removeMision(Mision mision) {
        if (this.misiones.contains(mision)) {
            this.misiones.remove(mision);
        }
        if (mision.getPersonajes() != null && mision.getPersonajes().contains(this)) {
            mision.getPersonajes().remove(this);
        }
    }
}
