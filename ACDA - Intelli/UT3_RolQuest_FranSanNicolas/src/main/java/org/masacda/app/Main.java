package org.masacda.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.masacda.model.FichaDetalle;
import org.masacda.model.Mision;
import org.masacda.model.Partida;
import org.masacda.model.Personaje;
import org.masacda.service.MisionService;
import org.masacda.service.PartidaService;
import org.masacda.service.PersonajeService;

import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

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

        Personaje p1 = new Personaje("Ayla la Hechicera", "Maga");
        p1.setNivel(5);
        p1.setPuntosVida(80);
        p1.setArmaPrincipal("Bastón del Alba");

        Personaje p2 = new Personaje("Rurik el Fuerte", "Guerrero");
        p2.setNivel(5);
        p2.setPuntosVida(100);
        p2.setArmaPrincipal("Mandoble del Ocaso");

        Mision mis1 = new Mision("El Guardián del Lago");
        mis1.setDescripcion("Recorre el lago y derrota a los espectros.");
        mis1.setDificultad("MEDIA");
        mis1.setRecompensa(100);
        mis1.setActiva(true);

        Mision mis2 = new Mision("La Torre de Cristal");
        mis2.setDescripcion("Encuentra el santuario perdido y protege el artefacto.");
        mis2.setDificultad("ALTA");
        mis2.setRecompensa(200);
        mis2.setActiva(true);

        p1.setMisiones(List.of(mis1, mis2));
        p2.setMisiones(List.of(mis1));

        personajeService.addPersonaje(p1);
        personajeService.addPersonaje(p2);

        List<Personaje> personajesPorMision = personajeService.getPersonajesPorMision(m1);
        logger.info("Personajes con la misión {} asignada.", m1.getTitulo());
        for(Personaje persMision : personajesPorMision){
            logger.info(" {} ({})", persMision.getNombre(),persMision.getClase());
        }

        List<Personaje> personajesPorNivel = personajeService.getPersonajesPorNivelMinimo(4);
        logger.info("Personajes con nivel mayor a {}.", 4);
        for(Personaje persNivel : personajesPorNivel){
            logger.info(" {} ({})", persNivel.getNombre(),persNivel.getClase());
        }
        List<Mision> misionesActivas = misionService.getMisionesActivas();
        logger.info("Lista de misiones activas:");
        for(Mision misionActiva : misionesActivas){
            logger.info("{}",misionActiva.getTitulo());
        }

        List<Partida> partidasConMisiones = partidaService.obtenerPartidasConMisiones(1);
        logger.info("Partidas con más de {} misione(s).", 1);
        for(Partida partidaConMision : partidasConMisiones){
            logger.info("{} | Estado: {}", partidaConMision.getNombre(), partidaConMision.getEstado());
        }
    }
}