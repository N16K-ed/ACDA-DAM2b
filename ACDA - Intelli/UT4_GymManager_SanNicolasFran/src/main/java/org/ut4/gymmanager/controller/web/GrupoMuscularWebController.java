package org.ut4.gymmanager.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.service.GrupoMuscularService;

import java.util.List;

@Controller
public class GrupoMuscularWebController {

    private final GrupoMuscularService grupoMuscularService;

    public GrupoMuscularWebController (GrupoMuscularService grupoMuscularService){
        this.grupoMuscularService = grupoMuscularService;
    }

    @GetMapping("web/grupos")
    public String listadoGrupos(Model model){
        List<GrupoMuscular> listado =  grupoMuscularService.listarTodos();

        model.addAttribute("grupos", listado);

        return "grupos-list";
    }

    @GetMapping("web/grupos/nuevo")
    public String nuevoGrupo(Model model){
        GrupoMuscular nuevo = new GrupoMuscular();

        model.addAttribute("grupoMuscular", nuevo);

        return "grupo-form";
    }

    @PostMapping("web/grupos/guardar")
    public String guardarGrupo(@ModelAttribute("grupoMuscular") GrupoMuscular grupoMuscular, Model model){

        grupoMuscularService.guardar(grupoMuscular);

        return "redirect:/web/grupos";
    }

    @GetMapping("web/grupos/{id}/editar")
    public String editar(@PathVariable long id, Model model){

        GrupoMuscular grupoMuscular = grupoMuscularService.buscarPorId(id);

        if(grupoMuscular != null){
            model.addAttribute(grupoMuscular);


            return "grupo-form";
        }

        return "redirect:/web/grupos";
    }

    @GetMapping("web/grupos/{id}/borrar")
    public String eliminar(@PathVariable long id){
        grupoMuscularService.eliminar(id);
        return "redirect:/web/grupos";
    }
}
