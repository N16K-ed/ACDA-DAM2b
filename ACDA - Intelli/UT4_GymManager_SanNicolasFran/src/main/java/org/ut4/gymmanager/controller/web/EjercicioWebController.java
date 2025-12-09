package org.ut4.gymmanager.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.service.EjercicioService;
import org.ut4.gymmanager.service.GrupoMuscularService;

import java.util.List;

@Controller
public class EjercicioWebController {
    private final EjercicioService ejercicioService;
    private final GrupoMuscularService grupoMuscularService;

    public EjercicioWebController (EjercicioService ejercicioService, GrupoMuscularService grupoMuscularService){
        this.ejercicioService = ejercicioService;
        this.grupoMuscularService = grupoMuscularService;
    }

    @GetMapping("web/ejercicios")
    public String listadoGrupos(Model model){
        List<Ejercicio> listado =  ejercicioService.listarTodos();

        model.addAttribute("ejercicios", listado);

        return "ejercicios-list";
    }

    @GetMapping("web/ejercicios/nuevo")
    public String nuevoEjercicio(Model model){
        Ejercicio nuevoEjercicio = new Ejercicio();

        List<String> dificultades = List.of("PRINCIPIANTE", "INTERMEDIO", "AVANZADO");
        List<GrupoMuscular> grupos = grupoMuscularService.listarTodos();

        model.addAttribute("ejercicio", nuevoEjercicio);
        model.addAttribute("dificultades", dificultades);
        model.addAttribute("grupos", grupos);

        return "ejercicio-form";
    }

    @PostMapping("web/ejercicios/guardar")
    public String guardar(@ModelAttribute("ejercicio") Ejercicio ejercicio, Model model){
        ejercicioService.guardar(ejercicio);

        return "redirect:/web/ejercicios";
    }

    @GetMapping("web/ejercicios/{id}/editar")
    public String editar(@PathVariable long id, Model model){

        Ejercicio ejercicio = ejercicioService.buscarPorId(id);

        if(ejercicio != null){
            List<String> dificultades = List.of("PRINCIPIANTE", "INTERMEDIO", "AVANZADO");
            List<GrupoMuscular> grupos = grupoMuscularService.listarTodos();

            model.addAttribute(ejercicio);
            model.addAttribute("dificultades", dificultades);
            model.addAttribute("grupos", grupos);

            return "ejercicio-form";
        }

        return "redirect:/web/ejercicios";
    }

    @GetMapping("web/ejercicios/{id}/borrar")
    public String eliminar(@PathVariable long id){
        ejercicioService.eliminar(id);
        return "redirect:/web/ejercicios";
    }
}
