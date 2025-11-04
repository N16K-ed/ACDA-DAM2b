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
        if(p.getEstado().equalsIgnoreCase("en curso") || p.getEstado().equalsIgnoreCase("encurso")){
            p.setEstado("EN CURSO");
            dao.save(p);
            logger.info("Partida añadida: {} (Estado: EN CURSO)", p.getNombre());
        }else if(p.getEstado().equalsIgnoreCase("finalizada") || p.getEstado().equalsIgnoreCase("finalizado")){
            p.setEstado("FINALIZADA");
            dao.save(p);
            logger.info("Partida añadida: {} (Estado: FINALIZADA)", p.getNombre());
        }else if(p.getEstado().equalsIgnoreCase("pausada") || p.getEstado().equalsIgnoreCase("pausado")){
            p.setEstado("PAUSADA");
            dao.save(p);
            logger.info("Partida añadida: {} (Estado: PAUSADA)", p.getNombre());
        }else{
            logger.warn("Estado de partida no válido.\nEstados válidos:\n- En curso\n- finalizada\n- pausada");
        }
    }

    public Partida getPartida(int id) {
        return dao.findById(id);
    }

    public List<Partida> getAllPartida() {
        return dao.findAll();
    }

    public void updatePartida(Partida p) {
        if(p.getEstado().equalsIgnoreCase("en curso") || p.getEstado().equalsIgnoreCase("encurso")){
            p.setEstado("EN CURSO");
            dao.update(p);
            logger.info("Partida modificada: {} (Estado: EN CURSO)", p.getNombre());
        }else if(p.getEstado().equalsIgnoreCase("finalizada") || p.getEstado().equalsIgnoreCase("finalizado")){
            p.setEstado("FINALIZADA");
            dao.update(p);
            logger.info("Partida modificada: {} (Estado: FINALIZADA)", p.getNombre());
        }else if(p.getEstado().equalsIgnoreCase("pausada") || p.getEstado().equalsIgnoreCase("pausado")){
            p.setEstado("PAUSADA");
            dao.update(p);
            logger.info("Partida modificada: {} (Estado: PAUSADA)", p.getNombre());
        }else{
            logger.warn("Estado de partida no válido.\nEstados válidos:\n- En curso\n- finalizada\n- pausada");
        }
    }

    public void deletePartida(Partida p) {
        dao.delete(p);
    }

    public List<Partida> obtenerPartidasConMisiones(int numMisiones){
        List<Partida> partidas = dao.obtenerConMisiones(numMisiones);
        logger.info("{} partidas con más de {} misiones.", partidas.size(), numMisiones);
        return partidas;
    }
}
