package org.masacda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.masacda.dao.MisionDAO;
import org.masacda.model.Mision;
import org.masacda.model.Personaje;

import java.util.List;

public class MisionService {
    private final MisionDAO dao = new MisionDAO();
    private static final Logger logger = LogManager.getLogger(MisionService.class);

    public void addMision(Mision m) {
        if(m.getDificultad().equalsIgnoreCase("baja") || m.getDificultad().equalsIgnoreCase("bajo")){
            m.setDificultad("BAJA");
        } else if(m.getDificultad().equalsIgnoreCase("media") || m.getDificultad().equalsIgnoreCase("medio")){
            m.setDificultad("MEDIA");
        } else if(m.getDificultad().equalsIgnoreCase("alta") || m.getDificultad().equalsIgnoreCase("alto")){
            m.setDificultad("ALTA");
        } else {
            logger.warn("Dificultad de Misión no válida.\nDificultades válidas:\n- Baja\n- Media\n- Alta");
            return;
        }

        if (m.getPersonajes() != null) {
            PersonajeService personajeService = new PersonajeService();
            for (Personaje p : m.getPersonajes()) {
                if (p.getPersonajeID() == 0) {
                    personajeService.addPersonaje(p);
                }
            }
        }

        dao.save(m);
        logger.info("Misión añadida: {} (Dificultad: {})", m.getTitulo(), m.getDificultad());

    }

    public Mision getMision(int id) {
        return dao.findById(id);
    }

    public List<Mision> getAllMision() {
        return dao.findAll();
    }

    public void updateMision(Mision m) {
        if(m.getDificultad().equalsIgnoreCase("baja") || m.getDificultad().equalsIgnoreCase("bajo")){
            m.setDificultad("BAJA");
        } else if(m.getDificultad().equalsIgnoreCase("media") || m.getDificultad().equalsIgnoreCase("medio")){
            m.setDificultad("MEDIA");
        } else if(m.getDificultad().equalsIgnoreCase("alta") || m.getDificultad().equalsIgnoreCase("alto")){
            m.setDificultad("ALTA");
        } else {
            logger.warn("Dificultad de Misión no válida.\nDificultades válidas:\n- Baja\n- Media\n- Alta");
            return;
        }

        if (m.getPersonajes() != null) {
            PersonajeService personajeService = new PersonajeService();
            for (Personaje p : m.getPersonajes()) {
                if (p.getPersonajeID() == 0) { // Personaje nuevo
                    personajeService.addPersonaje(p);
                }
            }
        }

        dao.update(m);
        logger.info("Misión modificada: {} (Dificultad: {})", m.getTitulo(), m.getDificultad());
    }

    public void deleteMision(Mision m) {
        dao.delete(m);
    }

}
