package org.ut4.gymmanager.model;

import jakarta.persistence.*;

@Entity
public class RutinaEjercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relación con Rutina
    @ManyToOne
    @JoinColumn(name = "rutina_id")
    private Rutina rutina;

    // relación con Ejercicio
    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private Ejercicio ejercicio;

    private Integer series;
    private Integer repeticiones;
    private Integer orden;

    public RutinaEjercicio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "RutinaEjercicio{" +
                "id=" + id +
                ", rutina=" + rutina +
                ", ejercicio=" + ejercicio +
                ", series=" + series +
                ", repeticiones=" + repeticiones +
                ", orden=" + orden +
                '}';
    }
}
