package org.masacda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FichaDetalle")
public class FichaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fichaID;

    private String descripcion;
    private String raza;
    private String alineamiento;
    private String deidad;

    @OneToOne(mappedBy = "fichaDetalle")
    private Personaje personaje;

    public int getFichaID() {
        return fichaID;
    }

    public void setFichaID(int fichaID) {
        this.fichaID = fichaID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getAlineamiento() {
        return alineamiento;
    }

    public void setAlineamiento(String alineamiento) {
        this.alineamiento = alineamiento;
    }

    public String getDeidad() {
        return deidad;
    }

    public void setDeidad(String deidad) {
        this.deidad = deidad;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
