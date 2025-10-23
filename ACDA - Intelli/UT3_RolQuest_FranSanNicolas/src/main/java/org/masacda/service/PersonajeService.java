package org.masacda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.masacda.dao.PersonajeDAO;
import org.masacda.model.Personaje;
import java.util.List;

public class PersonajeService {
    private final PersonajeDAO dao = new PersonajeDAO();
    private static final Logger logger = LogManager.getLogger(PersonajeService.class);

    public void addPersonaje(Personaje p) {
        if(p.getClase().equalsIgnoreCase("guerrero") || p.getClase().equalsIgnoreCase("guerrera")){
            p.setClase("GUERRERO");
            dao.save(p);
            logger.info("Personaje añadido: {} (Clase: GUERRERO)", p.getNombre());
        }else if(p.getClase().equalsIgnoreCase("mago") || p.getClase().equalsIgnoreCase("maga")){
            p.setClase("MAGO");
            dao.save(p);
            logger.info("Personaje añadido: {} (Clase: MAGO)", p.getNombre());
        }else if(p.getClase().equalsIgnoreCase("arquero") || p.getClase().equalsIgnoreCase("arquera")){
            p.setClase("ARQUERO");
            dao.save(p);
            logger.info("Personaje añadido: {} (Clase: ARQUERO)", p.getNombre());
        }else{
            logger.warn("Clase de Personaje no válida.\nClases válidas:\n- Guerrero\n- Mago\n- Arquero");
        }
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
            dao.update(p);
            logger.info("Personaje modificado: {} (Clase: GUERRERO)", p.getNombre());
        }else if(p.getClase().equalsIgnoreCase("mago") || p.getClase().equalsIgnoreCase("maga")){
            p.setClase("MAGO");
            dao.update(p);
            logger.info("Personaje modificado: {} (Clase: MAGO)", p.getNombre());
        }else if(p.getClase().equalsIgnoreCase("arquero") || p.getClase().equalsIgnoreCase("arquera")){
            p.setClase("ARQUERO");
            dao.update(p);
            logger.info("Personaje modificado: {} (Clase: ARQUERO)", p.getNombre());
        }else{
            logger.warn("Clase de Personaje no válida.\nClases válidas:\n- Guerrero\n- Mago\n- Arquero");
        }
    }

    public void deletePersonaje(Personaje p) {
        dao.delete(p);
    }

}
