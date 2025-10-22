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
        if(p.getClase().equals("guerrero") || p.getClase().equals("mago") || p.getClase().equals("arquero")){
            dao.save(p);
            logger.info("Personaje añadido: {} (Clase: {})", p.getNombre(), p.getClase());
        }else{
            logger.warn("Clase de Personaje no válida.\nClases válidas:\n- guerrero\n- mago\n- arquero");
        }
    }

    public Personaje getPersonaje(int id) {
        return dao.findById(id);
    }

    public List<Personaje> getAllPersonaje() {
        return dao.findAll();
    }

    public void updatePersonaje(Personaje p) {
        if(p.getClase().equals("guerrero") || p.getClase().equals("mago") || p.getClase().equals("arquero")){
            dao.update(p);
        }else{
            logger.warn("Clase de Personaje no válida.\nClases válidas:\n- guerrero\n- mago\n- arquero");
        }
    }

    public void deletePersonaje(Personaje p) {
        dao.delete(p);
    }

}
