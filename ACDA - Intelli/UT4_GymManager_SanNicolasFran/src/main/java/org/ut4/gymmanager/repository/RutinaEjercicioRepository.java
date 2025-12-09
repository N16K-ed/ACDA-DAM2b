package org.ut4.gymmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ut4.gymmanager.model.RutinaEjercicio;

import java.util.List;

public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicio, Long> {

    List<RutinaEjercicio> findByRutinaId(long rutinaId);

}
