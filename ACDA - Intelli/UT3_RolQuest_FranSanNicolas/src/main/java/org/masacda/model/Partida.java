package org.masacda.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partidaID;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaInicio = new Date();

    @Column(nullable = false)
    private int numeroJugadores;

    @Column(nullable = false, length = 10)
    private String estado;

    public Partida(){
        this.misiones = new ArrayList<>();
    }

    @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
    private List<Mision> misiones;

    public int getPartidaID() {
        return partidaID;
    }

    public void setPartidaID(int partidaID) {
        this.partidaID = partidaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(int numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Mision> getMisiones() {
        return misiones;
    }
    public void setMisiones(List<Mision> misiones) {
        this.misiones.clear(); //
        if(misiones != null){
            for(Mision m : misiones){
                addMision(m);
            }
        }
    }
    public void addMision(Mision mision) {
        mision.setPartida(this);
        this.misiones.add(mision);
    }
}
