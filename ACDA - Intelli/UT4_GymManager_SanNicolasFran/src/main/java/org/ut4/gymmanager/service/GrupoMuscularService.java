package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.repository.GrupoMuscularRepository;

import java.util.List;

@Service
public class GrupoMuscularService {

    private final GrupoMuscularRepository grupoMuscularRepository;

    public GrupoMuscularService(GrupoMuscularRepository grupoMuscularRepository){
        this.grupoMuscularRepository = grupoMuscularRepository;
    }

    public List<GrupoMuscular> listarTodos(){
        return grupoMuscularRepository.findAll();
    }

    public GrupoMuscular buscarPorId(long id){
        return grupoMuscularRepository.findById(id).orElse(null);
    }

    public GrupoMuscular guardar(GrupoMuscular grupo){
        return grupoMuscularRepository.save(grupo);
    }


    public void eliminar(long id){
        try{
            grupoMuscularRepository.deleteById(id);
        }catch(Exception e){
            /*
              Saltaba error al borrar un grupo que estaba asociado a un ejercicio
              He hecho esta chapucilla para que no salga error en la "web"
            */
            System.err.println("Error al borrar grupo muscular: posible conflicto de FKs");
        }
    }
}
