package org.masacda.app;

import org.masacda.model.FichaDetalle;
import org.masacda.model.Mision;
import org.masacda.model.Partida;
import org.masacda.model.Personaje;
import org.masacda.service.MisionService;
import org.masacda.service.PartidaService;
import org.masacda.service.PersonajeService;

public class Main {
    public static void main(String[] args) {
        PersonajeService personajeService = new PersonajeService();
        MisionService misionService = new MisionService();
        PartidaService partidaService = new PartidaService();

        // 1. Crear un personaje
        Personaje pj = new Personaje();
        pj.setNombre("Ayla la Hechicera");
        pj.setClase("Maga");
        pj.setNivel(5);
        pj.setPuntosVida(80);
        pj.setArmaPrincipal("Bastón del Alba");

        //FichaDetalle
        FichaDetalle fd = new FichaDetalle();
        fd.setAlineamiento("");
        fd.setDescripcion("");
        fd.setDeidad("");
        fd.setRaza("");

        pj.setFichaDetalle(fd);
        personajeService.addPersonaje(pj);

        // 2. Crear una misión
        Mision m = new Mision();
        m.setTitulo("El Bosque de las Sombras");
        m.setDescripcion("Explora las ruinas antiguas y vence a la Sombra del Olvido.");
        m.setDificultad("Alta");
        m.setRecompensa(500);
        m.setActiva(true);
        misionService.addMision(m);

        // 3. Crear una partida
        Partida partida = new Partida();
        partida.setNombre("La leyenda de Ayla");
        partida.setNumeroJugadores(4);
        partida.setEstado("En curso");
        partidaService.addPartida(partida);

        System.out.println("Datos iniciales añadidos correctamente.");


        Partida p = new Partida();
        p.setNombre("Aventuras en el Reino del Norte");
        p.setEstado("en curso");

        Mision m1 = new Mision();
        m1.setTitulo("El Bosque Maldito");
        m1.setDescripcion("Recorre el bosque y derrota a los espectros.");
        m1.setDificultad("MEDIA");
        m1.setRecompensa(100);
        m1.setActiva(true);
        p.addMision(m1);

        Mision m2 = new Mision();
        m2.setTitulo("El Santuario Perdido");
        m2.setDescripcion("Encuentra el santuario perdido y protege el artefacto.");
        m2.setDificultad("ALTA");
        m2.setRecompensa(200);
        m2.setActiva(true);
        p.addMision(m2);

        partidaService.addPartida(p);
    }
}