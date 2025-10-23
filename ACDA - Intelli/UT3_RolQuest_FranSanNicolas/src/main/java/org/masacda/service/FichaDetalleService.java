package org.masacda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.masacda.dao.FichaDetalleDAO;
import org.masacda.model.FichaDetalle;

import java.util.List;

public class FichaDetalleService {
    private final FichaDetalleDAO dao = new FichaDetalleDAO();
    private static final Logger logger = LogManager.getLogger(FichaDetalleService.class);

    public void addFichaDetalle(FichaDetalle fd) {
        dao.save(fd);
        logger.info("Ficha añadida: (Personaje: {}, ID: {})", fd.getPersonaje().getNombre(), fd.getFichaID());
    }

    public FichaDetalle getFichaDetalle(int id) {
        return dao.findById(id);
    }

    public List<FichaDetalle> getAllFichaDetalle() {
        return dao.findAll();
    }

    public void updateFichaDetalle(FichaDetalle fd) {
        dao.update(fd);
        logger.info("Ficha modificada: (Personaje: {}, ID: {})", fd.getPersonaje().getNombre(), fd.getFichaID());
    }

    public void deleteFichaDetalle(FichaDetalle fd) {
        dao.delete(fd);
    }
}
