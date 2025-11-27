package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.repository.EjercicioRepository;

import java.util.List;

@Service
public class EjercicioService {
    private final EjercicioRepository ejercicioRepository;

    public EjercicioService(EjercicioRepository ejercicioRepository){
        this.ejercicioRepository = ejercicioRepository;
    }

    public List<Ejercicio> listarTodos(){
        return ejercicioRepository.findAll();
    }

    public Ejercicio buscarPorId(long id){
        return ejercicioRepository.findById(id).orElse(null);
    }

    public Ejercicio guardar(Ejercicio ejercicio){
        return ejercicioRepository.save(ejercicio);
    }

    public void eliminar(long id){
        ejercicioRepository.deleteById(id);
    }

    public List<Ejercicio> buscarPorGrupoMuscular(String grupo){
        return ejercicioRepository.findByGrupoMuscular_NombreIgnoreCase(grupo);
    }


}
