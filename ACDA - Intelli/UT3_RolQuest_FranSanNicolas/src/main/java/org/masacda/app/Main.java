package org.masacda.app;

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

        Personaje p = new Personaje();
        p.setNombre("Ayla la Hechicera");
        p.setClase("mago");
        p.setNivel(5);
        p.setPuntosVida(80);
        p.setArmaPrincipal("Bastón del Alba");
        personajeService.addPersonaje(p);
        personajeService.deletePersonaje(p);

        // 2. Crear una misión
        Mision m = new Mision();
        m.setTitulo("El Bosque de las Sombras");
        m.setDescripcion("Explora las ruinas antiguas y vence a la Sombra del Olvido.");
        m.setDificultad("alta");
        m.setRecompensa(500);
        m.setActiva(true);
        misionService.addMision(m);
        misionService.deleteMision(m);

        // 3. Crear una partida
        Partida partida = new Partida();
        partida.setNombre("La leyenda de Ayla");
        partida.setNumeroJugadores(4);
        partida.setEstado("en curso");
        partidaService.addPartida(partida);
        partidaService.deletePartida(partida);
        System.out.println("Datos iniciales añadidos correctamente.");
        //FALTA ARREGLAR LAS VALIDACIONES DE LOS "ENUMS"
    }
}