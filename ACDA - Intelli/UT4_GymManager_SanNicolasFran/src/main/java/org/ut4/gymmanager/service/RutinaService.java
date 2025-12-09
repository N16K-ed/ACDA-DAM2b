package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.Rutina;
import org.ut4.gymmanager.model.RutinaEjercicio;
import org.ut4.gymmanager.repository.EjercicioRepository;
import org.ut4.gymmanager.repository.RutinaEjercicioRepository;
import org.ut4.gymmanager.repository.RutinaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaService {

    private final RutinaRepository rutinaRepository;
    private final RutinaEjercicioRepository rutinaEjercicioRepository;
    private final EjercicioRepository ejercicioRepository;

    public RutinaService(RutinaRepository rutinaRepository,
                         RutinaEjercicioRepository rutinaEjercicioRepository,
                         EjercicioRepository ejercicioRepository) {
        this.rutinaRepository = rutinaRepository;
        this.rutinaEjercicioRepository = rutinaEjercicioRepository;
        this.ejercicioRepository = ejercicioRepository;
    }

    // Métodos mínimos:
    // - List<Rutina> listarTodas()

    public List<Rutina>  listarTodos(){
        return rutinaRepository.findAll();
    }

    public Optional<Rutina> buscarPorId(long id){
        return rutinaRepository.findById(id);
    }

    public Rutina guardar(Rutina rutina){
        return rutinaRepository.save(rutina);
    }

    public void borrarPorId(long id){
        rutinaRepository.deleteById(id);
    }


    public List<RutinaEjercicio> listarEjerciciosDeRutina (long rutinaId){
        return rutinaEjercicioRepository.findByRutinaId(rutinaId);
    }

    public RutinaEjercicio aniadirEjercicioARutina(RutinaEjercicio rutinaEjercicio){
        return rutinaEjercicioRepository.save(rutinaEjercicio);
    }

    public void borrarEjercicioDeRutina(long rutinaEjercicioId){
        rutinaEjercicioRepository.deleteById(rutinaEjercicioId);
    }


}
