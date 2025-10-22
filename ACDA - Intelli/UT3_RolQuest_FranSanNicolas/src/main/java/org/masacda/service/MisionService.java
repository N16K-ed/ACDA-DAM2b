package org.masacda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.masacda.dao.MisionDAO;
import org.masacda.model.Mision;
import java.util.List;

public class MisionService {
    private final MisionDAO dao = new MisionDAO();
    private static final Logger logger = LogManager.getLogger(MisionService.class);

    public void addMision(Mision m) {
        if(m.getDificultad().equals("baja") || m.getDificultad().equals("media") || m.getDificultad().equals("alta")){
            dao.save(m);
            logger.info("Misión añadida: {} (Dificultad: {})", m.getTitulo(), m.getDificultad());
        }else{
            logger.warn("Dificultad de Misioón no válida.\nDificultades válidas:\n- baja\n- media\n- alta");
        }
    }

    public Mision getMision(int id) {
        return dao.findById(id);
    }

    public List<Mision> getAllMision() {
        return dao.findAll();
    }

    public void updateMision(Mision m) {
        if(m.getDificultad().equals("baja") || m.getDificultad().equals("media") || m.getDificultad().equals("alta")){
            dao.update(m);
        }else{
            logger.warn("Dificultad de Misioón no válida.\nDificultades válidas:\n- baja\n- media\n- alta");
        }
    }

    public void deleteMision(Mision m) {
        dao.delete(m);
    }

}
