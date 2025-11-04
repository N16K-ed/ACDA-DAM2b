package org.masacda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.masacda.dao.PersonajeDAO;
import org.masacda.model.Mision;
import org.masacda.model.Personaje;
import java.util.List;

public class PersonajeService {
    private final PersonajeDAO dao = new PersonajeDAO();
    private static final Logger logger = LogManager.getLogger(PersonajeService.class);

    public void addPersonaje(Personaje p) {

        if(p.getClase().equalsIgnoreCase("guerrero") || p.getClase().equalsIgnoreCase("guerrera")){
            p.setClase("GUERRERO");
        } else if(p.getClase().equalsIgnoreCase("mago") || p.getClase().equalsIgnoreCase("maga")){
            p.setClase("MAGO");
        } else if(p.getClase().equalsIgnoreCase("arquero") || p.getClase().equalsIgnoreCase("arquera")){
            p.setClase("ARQUERO");
        } else{
            logger.warn("Clase de Personaje no válida.\nClases válidas:\n- Guerrero\n- Mago\n- Arquero");
            return;
        }
        if (p.getMisiones() != null) {
            MisionService misionService = new MisionService();
            for (Mision m : p.getMisiones()) {
                if (m.getMisionID() == 0) {
                    misionService.addMision(m);
                }
            }
        }
        dao.save(p);
        logger.info("Personaje añadido: {} (Clase: {})", p.getNombre(), p.getClase());

    }

    public Personaje getPersonaje(int id) {
        return dao.findById(id);
    }

    public List<Personaje> getAllPersonaje() {
        return dao.findAll();
    }

    public void updatePersonaje(Personaje p) {
        if(p.getClase().equalsIgnoreCase("guerrero") || p.getClase().equalsIgnoreCase("guerrera")){
            p.setClase("GUERRERO");
        } else if(p.getClase().equalsIgnoreCase("mago") || p.getClase().equalsIgnoreCase("maga")){
            p.setClase("MAGO");
        } else if(p.getClase().equalsIgnoreCase("arquero") || p.getClase().equalsIgnoreCase("arquera")){
            p.setClase("ARQUERO");
        } else{
            logger.warn("Clase de Personaje no válida.\nClases válidas:\n- Guerrero\n- Mago\n- Arquero");
            return;
        }

        if (p.getMisiones() != null) {
            MisionService misionService = new MisionService();
            for (Mision m : p.getMisiones()) {
                if (m.getMisionID() == 0) {
                    misionService.addMision(m);
                }
            }
        }

        dao.update(p);
        logger.info("Personaje modificado: {} (Clase: {})", p.getNombre(), p.getClase());

    }

    public void deletePersonaje(Personaje p) {
        dao.delete(p);
    }

    public List<Personaje> getPersonajesPorNivelMinimo(int nivel){
        List<Personaje> personajes = null;
        if(nivel < 1){
            logger.warn("El nivel de los personajes no puede ser inferior a 1.");
        }else{
            personajes = dao.obtenerPorNivel(nivel);
            logger.info("Obtenidos {} personaje(s).", personajes.size());
        }
        return personajes;
    }

    public List<Personaje> getPersonajesPorMision(Mision m){
        List<Personaje> personajes = dao.obtenerPorMision(m);
        logger.info("{} personaje(s) asociados a la misión: {}", personajes.size(), m.getTitulo());
        return personajes;
    }

}
