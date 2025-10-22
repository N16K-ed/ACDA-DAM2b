package org.masacda.model;

import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Videojuego")
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videojuegoID;

    @Column(nullable = false, length = 100, unique = true)
    private String titulo;

    @Column(nullable = false, length = 20)
    private String plataforma;

    private int anioLanzamiento;
    private BigDecimal precio;

    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;

    private boolean disponible;

    public Videojuego() {}

    // Getters y setters omitidos para simplificar
}