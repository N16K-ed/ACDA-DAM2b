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
        if(m.getDificultad().equalsIgnoreCase("baja") || m.getDificultad().equalsIgnoreCase("bajo")){
            m.setDificultad("BAJA");
            dao.save(m);
            logger.info("Misión añadida: {} (Dificultad: BAJA)", m.getTitulo());
        }else if(m.getDificultad().equalsIgnoreCase("media") || m.getDificultad().equalsIgnoreCase("medio")){
            m.setDificultad("MEDIA");
            dao.save(m);
            logger.info("Misión añadida: {} (Dificultad: MEDIA)", m.getTitulo());
        }else if(m.getDificultad().equalsIgnoreCase("alta") || m.getDificultad().equalsIgnoreCase("alto")){
            m.setDificultad("ALTA");
            dao.save(m);
            logger.info("Misión añadida: {} (Dificultad: ALTA)", m.getTitulo());
        }else{
            logger.warn("Dificultad de Misión no válida.\nDificultades válidas:\n- Baja\n- Media\n- Alta");
        }
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
            dao.update(m);
            logger.info("Misión modificada: {} (Dificultad: BAJA)", m.getTitulo());
        }else if(m.getDificultad().equalsIgnoreCase("media") || m.getDificultad().equalsIgnoreCase("medio")){
            m.setDificultad("MEDIA");
            dao.update(m);
            logger.info("Misión modificada: {} (Dificultad: MEDIA)", m.getTitulo());
        }else if(m.getDificultad().equalsIgnoreCase("alta") || m.getDificultad().equalsIgnoreCase("alto")){
            m.setDificultad("ALTA");
            dao.update(m);
            logger.info("Misión modificada: {} (Dificultad: ALTA)", m.getTitulo());
        }else{
            logger.warn("Dificultad de Misión no válida.\nDificultades válidas:\n- Baja\n- Media\n- Alta");
        }
    }

    public void deleteMision(Mision m) {
        dao.delete(m);
    }

}
