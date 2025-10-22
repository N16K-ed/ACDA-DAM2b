package org.masacda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.masacda.dao.PartidaDAO;
import org.masacda.model.Partida;

import java.util.List;

public class PartidaService {
    private final PartidaDAO dao = new PartidaDAO();
    private static final Logger logger = LogManager.getLogger(PartidaService.class);

    public void addPartida(Partida p) {
        if(p.getEstado().equals("en curso") || p.getEstado().equals("finalizada") || p.getEstado().equals("pausada")){
            dao.save(p);
            logger.info("Partida añadida: {} (Estado: {})", p.getNombre(), p.getEstado());

        }else{
            logger.warn("");
        }
    }

    public Partida getPartida(int id) {
        return dao.findById(id);
    }

    public List<Partida> getAllPartida() {
        return dao.findAll();
    }

    public void updatePartida(Partida p) {
        if(p.getEstado().equals("en curso") || p.getEstado().equals("finalizada") || p.getEstado().equals("pausada")){
            dao.update(p);
        }else{
            logger.warn("");
        }
    }

    public void deletePartida(Partida p) {
        dao.delete(p);
    }
}
