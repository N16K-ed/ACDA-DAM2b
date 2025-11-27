package org.ut4.gymmanager.controller;

import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.service.GrupoMuscularService;

@RestController
public class GrupoMuscularRestController {

    private final GrupoMuscularService grupoMuscularService;

    public GrupoMuscularRestController(GrupoMuscularService grupoMuscularService){
        this.grupoMuscularService = grupoMuscularService;
    }

    @GetMapping("api/grupos")
    public String printarTodos (){
        StringBuilder lista = new StringBuilder();
        for (GrupoMuscular gm : grupoMuscularService.listarTodos()){
            lista.append(gm.toString());
            //lista.append("\n");
        }
        return lista.toString();
    }

    @GetMapping("api/grupos/{id}")
    public String printarPorId(@PathVariable long id){
        return grupoMuscularService.buscarPorId(id).toString();
    }

    @PostMapping("api/grupos")
    public GrupoMuscular crearGrupoMuscular(@RequestBody GrupoMuscular grupoMuscular){
        return grupoMuscularService.guardar(grupoMuscular);
    }

    @DeleteMapping("api/grupos/{id}")
    public void eliminarGrupoMuscular(@PathVariable long id){
        grupoMuscularService.eliminar(id);
    }
}
